package com.example.groceryprices.models;



import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Account {

    @Id
    @GeneratedValue
    private int accountId;

    @NotNull
    @Size(min=3, message = "Invalid Username")
    private String username;

    @NotNull
    @Size(min = 3, message = "Invalid Password")
    private String password;

    @NotNull
    @Size(min = 3, message = "Passwords do not match")
    private String confirmPassword;

    public Account(String username, String password, String confirmPassword) {
        this.username = username;
        this.password = password;
        this.confirmPassword = confirmPassword;
    }

    public Account(){ }

    public String getUsername() {     return username;    }

    public void setUsername(String username) {        this.username = username;    }

    public String getPassword() {        return password;    }

    public void setPassword(String password) {        this.password = password;    }

    public int getAccountId() {
        return accountId;
    }

    public String getConfirmPassword() {        return confirmPassword;    }

    public void setConfirmPassword(String confirmPassword) {        this.confirmPassword = confirmPassword;    }

}
