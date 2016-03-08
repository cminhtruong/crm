package fr.pds.isintheair.crmtab.jdatour.uc.phone.call.receive.model.entity;

public class Register {
    private Integer userId;

    public Register(Integer userId) {
        this.userId = userId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
