package fr.pds.isintheair.crmtab.mbalabascarin.uc.edit.crv.model;

import java.io.Serializable;

/**
 * Created by Muthu on 30/12/2015.
 */
public class Product implements Serializable{


    private String id;
    private String name;

    public String getId ()
    {
        return id;
    }
    public String getName ()
    {
        return name;
    }

    public void setId (String id)
    {
        this.id = id;
    }
    public void setName (String name)
    {
        this.name = name;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [id = "+id+"]";
    }
}

