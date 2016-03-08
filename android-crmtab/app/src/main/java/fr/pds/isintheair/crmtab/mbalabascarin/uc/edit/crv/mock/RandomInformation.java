package fr.pds.isintheair.crmtab.mbalabascarin.uc.edit.crv.mock;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Muthu on 13/12/2015.
 */
public class RandomInformation {


    public String getRandomInfo(){
        int random = randInt(0,4);
        List<String> contacts = new ArrayList<String>();
        List<String> clients = new ArrayList<String>();
        List<String> subjects = new ArrayList<String>();
        List<String> tels = new ArrayList<String>();
        List<String> addresses = new ArrayList<String>();

        //mock contacts
        contacts.add("Brener");
        contacts.add("Giraud");
        contacts.add("Bob");
        contacts.add("Jhon");
        contacts.add("Nicolas");

        //mock clients
        clients.add("Client 1");
        clients.add("Client 2");
        clients.add("Client 3");
        clients.add("Client 4");
        clients.add("Client 5");


        //mock addresses
        addresses.add("1 Rue de Paris 75016");
        addresses.add("2 Rue de Paris 75016");
        addresses.add("3 Rue de Paris 75016");
        addresses.add("4 Rue de Paris 75016");
        addresses.add("5 Rue de Paris 75016");

        //mock subjects
        subjects.add("Présentation promotion;Règlement litige");
        subjects.add("Règlement litige");
        subjects.add("Présentation produit;Action marketing");
        subjects.add("Présentation promotion;Action marketing");
        subjects.add("Présentation promotion");

        //mock tels
        tels.add("0778801708");
        tels.add("0623158910");
        tels.add("0685521413");
        tels.add("0712597830");
        tels.add("0614851379");

        JSONObject mock = new JSONObject();
        JSONObject datas = new JSONObject();
        try {
            datas.put("user", "Muthu");
            datas.put("contact", contacts.get(random));
            datas.put("phoneNumber", tels.get(random));
            datas.put("subject", subjects.get(random));
            datas.put("client", clients.get(random));
            datas.put("address", addresses.get(random));
            datas.put("userId", random);
            datas.put("contactId", random);
            datas.put("clientId", random);
            datas.put("visitId", random);

            mock.put("mock", datas);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return mock.toString();
    }

    public static int randInt(int min, int max) {

        Random rand = new Random();
        int randomNum = rand.nextInt((max - min) + 1) + min;

        return randomNum;
    }
}


