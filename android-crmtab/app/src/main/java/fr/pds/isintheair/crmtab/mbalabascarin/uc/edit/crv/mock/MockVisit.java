package fr.pds.isintheair.crmtab.mbalabascarin.uc.edit.crv.mock;

import java.util.ArrayList;
import java.util.List;

import fr.pds.isintheair.crmtab.mbalabascarin.uc.edit.crv.model.Visit;

/**
 * Created by Muthu on 06/02/2016.
 */
public class MockVisit {


    public MockVisit(){}


    public List<Visit> getMockVisit(){
        List<Visit> visits = new ArrayList<Visit>();

        Visit v1 = new Visit();
        Visit v2 = new Visit();
        Visit v3 = new Visit();
        Visit v4 = new Visit();
        Visit v5 = new Visit();
        Visit v6 = new Visit();
        Visit v7 = new Visit();

        String[] subjects = new String[5];

        subjects[0] = "Présentation promotion";
        subjects[1] = "Action marketing";
        subjects[2] = "Présentation produit";

        v1.setId(1);
        v1.setDate("06/02/2016");
        v1.setIdClient(48400813100019L);
        v1.setSubject(subjects);


        v2.setId(2);
        v2.setDate("02/01/2016");
        v2.setIdClient(34417996500019L);
        v2.setSubject(subjects);

        v3.setId(3);
        v3.setDate("02/01/2016");
        v3.setIdClient(40264891900015L);
        v3.setSubject(subjects);

        v4.setId(4);
        v4.setDate("06/02/2016");
        v4.setIdClient(48400813100019L);
        v4.setSubject(subjects);

        v5.setId(5);
        v5.setDate("06/02/2016");
        v5.setIdClient(44156635300021L);
        v5.setSubject(subjects);

        v6.setId(6);
        v6.setDate("06/02/2016");
        v6.setIdClient(44224107100012L);
        v6.setSubject(subjects);

        v7.setId(7);
        v7.setDate("01/02/2016");
        v7.setIdClient(51529767900018L);
        v7.setSubject(subjects);

        visits.add(v1);
        visits.add(v2);
        visits.add(v3);
        visits.add(v4);
        visits.add(v5);
        visits.add(v6);
        visits.add(v7);



        return visits;
    }


}
