package fr.pds.isintheair.crmtab.mmefire.uc.sms.send.receive.model;

import java.util.Date;

/**
 * Created by Maimouna MEFIRE on 24/01/2016.
 */
public class Message {

    public String sender;
    public String recipient;
    public String content;
    public Date date;

    /**
     * Constructeur par d�faut
     */
    public Message(){
    }

    /**
     * Constructeur avec param�tres
     * @param sender
     * @param recipient
     * @param content
     * @param date
     */
    public Message(String sender, String recipient,String content,Date date){

        this.sender = sender;
        this.recipient = recipient;
        this.content = content;
        this.date =date;
    }

}


