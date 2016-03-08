package fr.pds.isintheair.crmtab.mbalabascarin.uc.edit.crv.model;

/**
 * Created by Muthu on 30/12/2015.
 */
public class Reporting {


    private Report report;
    public Report getReport ()
    {
        return report;
    }

    public void setReport (Report report)
    {
        this.report = report;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [report = "+report+"]";
    }
}
