package miage.pds.api.ctruong.uc.prospect.suggest.rest;

import miage.pds.api.ctruong.uc.prospect.suggest.controller.ProspectController;
import miage.pds.api.ctruong.uc.prospect.suggest.dao.*;
import miage.pds.api.ctruong.uc.prospect.suggest.model.*;
import miage.pds.api.ctruong.uc.prospect.suggest.service.MongoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Truong on 1/23/2016.
 *
 * @version 1.1.19
 * @since 2016-01-24
 */
@Controller
public class ProspectRestController {
    private static final Logger log = LoggerFactory.getLogger(ProspectRestController.class);
    ProspectController controller;
    ProspectDAO prospectDAO;
    SalesDAO salesDAO;
    RelationClientDAO relationClientDAO;
    RelationUserClientDAO relationUserClientDAO;
    CommandDAO commandDAO;

    private MongoService mongoService;


    /**
     * Constructor for rest controller
     */
    public ProspectRestController() {
        this.controller = new ProspectController();
        this.mongoService = new MongoService();
        this.prospectDAO = new ProspectDAOImp(Prospect.class, mongoService.getDatastore());
        this.salesDAO = new SalesDAOImpl(Sales.class, mongoService.getDatastore());
        this.relationUserClientDAO = new RelationUserClientDAOImpl(RelationUserClient.class, mongoService.getDatastore());
        this.relationClientDAO = new RelationClientDAOImpl(RelationClient.class, mongoService.getDatastore());
        this.commandDAO = new CommandDAOImpl(Command.class, mongoService.getDatastore());
    }

    /**
     * Test Spring
     *
     * @return Hello World
     */
    @RequestMapping(value = "/suggestion", method = RequestMethod.GET)
    public
    @ResponseBody
    String helloWorld() {
        return "Hello Davide's World 2016";
    }

    @RequestMapping(value = "/suggestion/prospect", method = RequestMethod.GET)
    public @ResponseBody Prospect startAnalyzeProscess(){
        List<Prospect> prospects = controller.analyseProspect();
        Iterator<Prospect> iterator = prospects.iterator();
        Prospect prospect = new Prospect();
        while (iterator.hasNext()){
            prospect = iterator.next();

        }
        return prospect;
    }

    @RequestMapping(value = "/suggestion/prospect/{siret}", method = RequestMethod.POST)
    public @ResponseBody Prospect createNewClient(@PathVariable long siret){
        Prospect prospect = new Prospect();
        RelationUserClient relation = new RelationUserClient();
        relationUserClientDAO.save(relation);
        return prospect;
    }

    @RequestMapping(value = "/suggestion/prospects", method = RequestMethod.GET)
    public @ResponseBody List<Prospect> analyseProcess(){
        return controller.analyseProspect();
    }

    @RequestMapping(value = "/suggestion/prospect/demo", method = RequestMethod.GET)
    public @ResponseBody String demo(){
        String message = "";
        List<Prospect> prospects = prospectDAO.getListProspect();
        Iterator<Prospect> iterator = prospects.iterator();
        while (iterator.hasNext()) {
            Prospect prospect = iterator.next();
            if (relationUserClientDAO.checkRelationBetweenUserAndClient(prospect.getId()) == true) {
                iterator.remove();
            }
        }
        message += "The system found " + prospects.size() + " prospects which haven't relationship with commercials <br>";
        message += "----------------------------------------------------------------------------------------------------<br><br>";
        long average = controller.getSalesAverage(prospects);
        message += "Turnover's average is " + average + "<br>";
        Iterator<Prospect> iterator1 = prospects.iterator();
        while (iterator1.hasNext()) {
            Prospect prospect = iterator1.next();
            if (prospect.getTurnover() == 0 || prospect.getTurnover() < average) {
                iterator1.remove();
            }
        }
        message += "The system found " + prospects.size() + " prospects which have their name in the market during 6 month and their's turnover's average is higher than " + average + "<br>";
        Collections.sort(prospects, new Comparator<Prospect>() {
            @Override
            public int compare(Prospect o1, Prospect o2) {
                return (int) (o2.getTurnover() - o1.getTurnover());
            }
        });

        if (prospects.size() > 5) {
            for (int i = prospects.size(); i > 5; i--) {
                prospects.remove(i - 1);

            }
        }
        message += "-----------------------------------------------------------------------------------------<br>";
        message += "The top 5 turnover in this period";
        message += "<table>";
        for (Prospect prospect: prospects){
            message += "<tr><td>" + prospect.getName() + "</td><td>" + prospect.getTurnover() + "</td></tr>";
        }
        message += "</table>";

        controller.getProspectByRelationship(prospects);
        message += "The system found " + prospects.size() + " prospects which have relationship with clients in the list <br>";

        message += "-----------------------------------------------------------------------------------------<br><br>";

        for (Prospect prospect: prospects){
            if (commandDAO.checkExistCommandOfProspect(prospect.getId()) == true){
                message += "The prospect " + prospect.getName() + " has command by someone<br>";
            } else {
                message += "The prospect " + prospect.getName() + " hasn't command by someone<br>";
            }

        }
        message += "-----------------------------------------------------------------------------------------<br><br>";
        controller.getProspectByCommand(prospects);
        for (Prospect prospect: prospects){
            message += "=============> Conclusion: The system will suggest the prospect " + prospect.getName();
        }
        return message;
    }


}
