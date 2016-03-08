package fr.pds.isintheair.crmtab.tlacouque.uc.admin.ref.customer.model.enumeration;

/**
 * Created by tlacouque on 27/12/2015.
 * Enum used to stock independant type
 */
public enum IndependantType {

    MEDECIN_GENERALISTE_LIBERAL("Médecin généraliste libérale"),
    INFIRMIERE_LIBERAL("Infirmière libéral"),
    SPECIALISTE("Spécialiste"),
    PHARMACIE("Pharmacie"),
    CHIRURGIEN_DENTISTE("Dentiste"),
    SAGE_FEMME("Sage femme");


    private String stringValue;

    private IndependantType(String toString) {
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
