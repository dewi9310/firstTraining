package com.example.dewioktaviani.firsttraining;

/**
 * Created by dewi.oktaviani on 16/03/2019.
 */

public class People {
    private int intId;
    private String txtName;
    private String txtGender;
    private String txtAddress;

    public String Property_ID = "intId";
    public String Property_Name = "txtName";
    public String Property_Gender = "txtGender";
    public String Property_Address = "txtAddress";
    public  String Property_All = Property_ID + "," + Property_Name + "," + Property_Gender + "," + Property_Address;

    public int getIntId() {
        return intId;
    }

    public void setIntId(int intId) {
        this.intId = intId;
    }

    public String getTxtName() {
        return txtName;
    }

    public void setTxtName(String txtName) {
        this.txtName = txtName;
    }

    public String getTxtGender() {
        return txtGender;
    }

    public void setTxtGender(String txtGender) {
        this.txtGender = txtGender;
    }

    public String getTxtAddress() {
        return txtAddress;
    }

    public void setTxtAddress(String txtAddress) {
        this.txtAddress = txtAddress;
    }
}
