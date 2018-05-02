package com.example.groceryprices.models;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Login {

    @NotNull
    @Size(min=3, max=20, message = "Invalid Username")
    private String loginName;

    @NotNull
    @Size(min = 3, message = "Invalid Password")
    private String loginPassword;



    public Login(String loginName, String loginPassword){
        this.loginName = loginName;
        this.loginPassword = loginPassword;
    }

    public Login(){

    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getLoginPassword() {
        return loginPassword;
    }

    public void setLoginPassword(String loginPassword) {
        this.loginPassword = loginPassword;
    }
}
