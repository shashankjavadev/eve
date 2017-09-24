package com.foodkonnekt.clover.vo;

import java.util.List;

public class CustomerJsonVo {

    int iTotalRecords;

    int iTotalDisplayRecords;

    String sEcho;

    String sColumns;

    List<CustomerVo> aaData;

    public int getiTotalRecords() {
        return iTotalRecords;
    }

    public void setiTotalRecords(int iTotalRecords) {
        this.iTotalRecords = iTotalRecords;
    }

    public int getiTotalDisplayRecords() {
        return iTotalDisplayRecords;
    }

    public void setiTotalDisplayRecords(int iTotalDisplayRecords) {
        this.iTotalDisplayRecords = iTotalDisplayRecords;
    }

    public String getsEcho() {
        return sEcho;
    }

    public void setsEcho(String sEcho) {
        this.sEcho = sEcho;
    }

    public String getsColumns() {
        return sColumns;
    }

    public void setsColumns(String sColumns) {
        this.sColumns = sColumns;
    }

    public List<CustomerVo> getAaData() {
        return aaData;
    }

    public void setAaData(List<CustomerVo> aaData) {
        this.aaData = aaData;
    }
}
