package miage.pds.api.ctruong.uc.prospect.suggest.controller;

import miage.pds.api.ctruong.uc.prospect.suggest.dao.*;
import miage.pds.api.ctruong.uc.prospect.suggest.model.*;
import miage.pds.api.ctruong.uc.prospect.suggest.service.MongoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * This is a class which control the algorithm to find the prospect for each user.
 * <p/>
 * Created by Truong on 12/20/2015.
 *
 * @version 1.1.19
 * @serial 111912202015
 */
public class ProspectController {

    private MongoService mongoService;
    private static Logger logger = LoggerFactory.getLogger(ProspectController.class);
    private ProspectDAO prospectDAO;
    private RelationUserClientDAO relationUserClientDAO;
    private SalesDAO salesDAO;
    private RelationClientDAO relationClientDAO;
    private CommandDAO commandDAO;


    /**
     * Constructor allows to create new instance of Controller
     */
    public ProspectController() {
        this.mongoService = new MongoService();
        this.prospectDAO = new ProspectDAOImp(Prospect.class, mongoService.getDatastore());
        this.salesDAO = new SalesDAOImpl(Sales.class, mongoService.getDatastore());
        this.relationUserClientDAO = new RelationUserClientDAOImpl(RelationUserClient.class, mongoService.getDatastore());
        this.relationClientDAO = new RelationClientDAOImpl(RelationClient.class, mongoService.getDatastore());
        this.commandDAO = new CommandDAOImpl(Command.class, mongoService.getDatastore());
    }

    /**
     * @return
     */
    public List<Prospect> analyseProspect() {
        List<Prospect> prospects = getProspectByTurnover();
        List<Prospect> prospectsList = prospectDAO.getListProspect();
        List<Prospect> prospectsAnalyse;
        if (prospects.size() > 0) {
            prospectsAnalyse = getProspectByRelationship(prospects);
            getProspectByCommand(prospectsAnalyse);

        } else {
            Iterator<Prospect> iterator = prospectsList.iterator();
            while (iterator.hasNext()) {
                Prospect prospect = iterator.next();
                if (relationUserClientDAO.checkRelationBetweenUserAndClient(prospect.getId()) == true) {
                    iterator.remove();
                }
            }
            prospectsAnalyse = getProspectByRelationship(prospectsList);
            getProspectByCommand(prospectsAnalyse);
        }
        logger.info(String.valueOf(prospectsAnalyse.size()));
        logger.info(prospectsAnalyse.toString());
        return prospectsAnalyse;
    }


    /**
     * @return
     */
    private List<Prospect> getProspectByTurnover() {
        List<Prospect> prospects = prospectDAO.getListProspect();
        Iterator<Prospect> iterator = prospects.iterator();
        while (iterator.hasNext()) {
            Prospect prospect = iterator.next();
            if (relationUserClientDAO.checkRelationBetweenUserAndClient(prospect.getId()) == true) {
                iterator.remove();
            }
        }
        logger.info("The system found " + prospects.size() + " prospects which haven't relationship with commercials");
        long average = getSalesAverage(prospects);
        Iterator<Prospect> iterator1 = prospects.iterator();
        while (iterator1.hasNext()) {
            Prospect prospect = iterator1.next();
            if (prospect.getTurnover() == 0 || prospect.getTurnover() < average) {
                iterator1.remove();
            }
        }
        logger.info("The system found " + prospects.size() + " prospects which have their name in the market during 6 month");
        Collections.sort(prospects, new Comparator<Prospect>() {
            @Override
            public int compare(Prospect o1, Prospect o2) {
                return (int) (o2.getTurnover() - o1.getTurnover());
            }
        });

        return prospects;
    }

    /**
     * @param prospectsList
     * @return
     */
    public List<Prospect> getProspectByRelationship(List<Prospect> prospectsList) {
        Iterator<Prospect> prospectIterator = prospectsList.iterator();
        while (prospectIterator.hasNext()) {
            Prospect prospect = prospectIterator.next();
            if (relationClientDAO.checkRelationWithClient(prospect.getId()) == false) {
                prospectIterator.remove();

            }
        }
        return prospectsList;
    }

    public List<Prospect> getProspectByCommand(List<Prospect> prospectList) {
        List<Prospect> prospects = null;
        Iterator<Prospect> iterator = prospectList.iterator();
        while (iterator.hasNext()) {
            Prospect prospect = iterator.next();
            if (commandDAO.checkExistCommandOfProspect(prospect.getId()) == true) {
                prospects.add(prospect);
            }
        }

        if (prospects == null) {
            if (prospectList.size() > 1) {
                for (int i = prospectList.size(); i > 1; i--) {
                    prospectList.remove(i - 1);
                }

            }
            return prospectList;
        } else if (prospects.size() == 1) {
            return prospects;
        } else {
            Collections.sort(prospects, new Comparator<Prospect>() {
                @Override
                public int compare(Prospect o1, Prospect o2) {
                    return (int) (o2.getTurnover() - o1.getTurnover());

                }
            });
            for (int i = prospects.size(); i > 1; i--) {
                prospects.remove(i - 1);
            }
            return prospects;
        }


    }


    /**
     * @param prospectsList
     * @return
     */
    public long getSalesAverage(List<Prospect> prospectsList) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -6);
        Date date = calendar.getTime();
        logger.info("6 month ago was: " + String.valueOf(date));
        HashMap<Prospect, Long> hashMap = new HashMap<Prospect, Long>();
        List<Prospect> prospects = prospectsList;
        Iterator<Prospect> iterator = prospects.iterator();
        while (iterator.hasNext()) {
            Prospect prospect = iterator.next();
            List<Sales> sales = salesDAO.getSalesSixMonthEachProspect(prospect.getId(), date);
            long turnover = 0;
            for (Sales sale : sales) {
                turnover += sale.getValue();
            }
            if (sales.size() == 0) {
                long marketShare = 0;
                prospect.setTurnover(marketShare);
                hashMap.put(prospect, marketShare);
            } else {
                long marketShare = turnover / sales.size();
                prospect.setTurnover(marketShare);
                hashMap.put(prospect, marketShare);
            }
        }

        long sum = 0;
        for (long s : hashMap.values()) {
            sum += s;
        }
        logger.info("TurnOver's sum: " + sum);
        long average = sum / hashMap.size();
        logger.info("Average is " + average);


        return average;
    }

}
