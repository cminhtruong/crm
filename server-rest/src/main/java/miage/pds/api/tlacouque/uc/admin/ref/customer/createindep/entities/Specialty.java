package miage.pds.api.tlacouque.uc.admin.ref.customer.createindep.entities;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Property;

/**
 * Created by tlacouque on 28/12/2015.
 */

@Entity("specialty")
public class Specialty {

    @Id
    int id;

    @Property
    String name;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

}
