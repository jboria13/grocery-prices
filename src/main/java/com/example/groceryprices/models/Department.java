package com.example.groceryprices.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Department {

    @Id
    @GeneratedValue
    private int departmentId;

    @NotNull
    @Size(min=3, message = "Invalid Department Name")
    private String departmentName;

    @OneToMany
    @JoinColumn(name = "department_id")
    private List<Item> items = new ArrayList<>();

    public Department(String departmentName) {
        this.departmentName = departmentName;
    }



    public Department(){

    }

    public int getDepartmentId() {
        return departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }
}
