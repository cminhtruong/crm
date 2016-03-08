package fr.pds.isintheair.crmtab.mbalabascarin.uc.edit.crv.mock;

import java.util.ArrayList;
import java.util.List;

import fr.pds.isintheair.crmtab.mbalabascarin.uc.edit.crv.model.Client;


/**
 * Created by Muthu on 18/12/2015.
 */
public class MockClient {

    public MockClient(){}

    public List<Client> getClients(){
        List<Client> clients = new ArrayList<Client>();
        //mock client list
        Client client1 = new Client();
        Client client2 = new Client();
        Client client3 = new Client();
        Client client4 = new Client();
        Client client5 = new Client();

        client1.setClientId(1);
        client1.setClientName("1");
        client1.setClientSurname("CLIENT");
        client1.setClientAddress("1 Rue de Paris 75016");

        client2.setClientId(2);
        client2.setClientName("2");
        client2.setClientSurname("CLIENT");
        client2.setClientAddress("2 Rue de Paris 75016");

        client3.setClientId(3);
        client3.setClientName("3");
        client3.setClientSurname("CLIENT");
        client3.setClientAddress("3 Rue de Paris 75016");

        client4.setClientId(4);
        client4.setClientName("4");
        client4.setClientSurname("CLIENT");
        client4.setClientAddress("4 Rue de Paris 75016");

        client5.setClientId(5);
        client5.setClientName("5");
        client5.setClientSurname("CLIENT");
        client5.setClientAddress("5 Rue de Paris 75016");

        clients.add(client1);
        clients.add(client2);
        clients.add(client3);
        clients.add(client4);
        clients.add(client5);

        return clients;
    }
}
