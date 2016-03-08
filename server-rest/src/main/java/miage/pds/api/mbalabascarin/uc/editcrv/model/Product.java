package miage.pds.api.mbalabascarin.uc.editcrv.model;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

@Entity("product")
public class Product
{
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
