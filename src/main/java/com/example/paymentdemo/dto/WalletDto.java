package com.example.paymentdemo.dto;

public class WalletDto {
    private Integer idone;
    private Integer idtwo;
    private Double amount;
    public  WalletDto(){
        super();
    }

    public Integer getIdone() {
        return idone;
    }

    public void setIdone(Integer idone) {
        this.idone = idone;
    }

    public Integer getIdtwo() {
        return idtwo;
    }

    public void setIdtwo(Integer idtwo) {
        this.idtwo = idtwo;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
