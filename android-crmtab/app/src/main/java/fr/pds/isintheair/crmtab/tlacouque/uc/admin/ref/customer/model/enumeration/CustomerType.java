package fr.pds.isintheair.crmtab.tlacouque.uc.admin.ref.customer.model.enumeration;

/**
 * Created by tlacouque on 12/12/2015.
 * Enum used to stock customer type
 */
public enum CustomerType {

    HEALTH_ETABLISHMENT ("Etablissement de santé"),
    INDEPENDENT ("Indépendant");


    private String stringValue;

    private CustomerType(String toString) {
        stringValue = toString;
    }

    /**
     * Method implemented to call Enum.attribute.toString(). Return string of this attribute.
     * @return String
     */
    @Override
    public String toString() {
        return stringValue;
    }
}
