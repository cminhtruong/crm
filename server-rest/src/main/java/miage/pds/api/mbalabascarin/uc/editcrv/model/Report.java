package miage.pds.api.mbalabascarin.uc.editcrv.model;

import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;




@Entity("crv")
public class Report
{
private Product[] product;

@Id private String id;

private String client;

private String satisfaction;

private String visit;

private String comment;

private String date;

private String contact;

private String commercial;

public Product[] getProduct ()
{
return product;
}

public void setProduct (Product[] product)
{
this.product = product;
}

public String getId ()
{
return id;
}

public void setId (String id)
{
this.id = id;
}

public String getClient ()
{
return client;
}

public void setClient (String client)
{
this.client = client;
}

public String getSatisfaction ()
{
return satisfaction;
}

public void setSatisfaction (String satisfaction)
{
this.satisfaction = satisfaction;
}

public String getVisit ()
{
return visit;
}

public void setVisit (String visit)
{
this.visit = visit;
}

public String getComment ()
{
return comment;
}

public void setComment (String comment)
{
this.comment = comment;
}

public String getDate ()
{
return date;
}

public void setDate (String date)
{
this.date = date;
}

public String getContact ()
{
return contact;
}

public void setContact (String contact)
{
this.contact = contact;
}

public String getCommercial ()
{
return commercial;
}

public void setCommercial (String commercial)
{
this.commercial = commercial;
}

@Override
public String toString()
{
return "ClassPojo [product = "+product+", id = "+id+", client = "+client+", satisfaction = "+satisfaction+", visit = "+visit+", comment = "+comment+", date = "+date+", contact = "+contact+", commercial = "+commercial+"]";
}
}