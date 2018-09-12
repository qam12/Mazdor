package com.example.maju.mazdor;

/**
 * Created by qamber.haider on 5/23/2018.
 */

public class ProfileDet {
    public String employee_FirstName;
    public String employee_Telephone;
    public String employee_Email;
    public String employee_Designation;
    public String employee_Nic;
    public String employee_Profilepicture;
    public String id;

    public ProfileDet(String employee_FirstName, String employee_Telephone, String employee_Email, String employee_Designation, String employee_Nic, String employee_Profilepicture, String id) {
        this.employee_FirstName = employee_FirstName;
        this.employee_Telephone = employee_Telephone;
        this.employee_Email = employee_Email;
        this.employee_Designation = employee_Designation;
        this.employee_Nic = employee_Nic;
        this.employee_Profilepicture = employee_Profilepicture;
        this.id = id;
    }

    public String getEmployee_FirstName() {
        return employee_FirstName;
    }

    public String getEmployee_Telephone() {
        return employee_Telephone;
    }

    public String getEmployee_Email() {
        return employee_Email;
    }

    public String getEmployee_Designation() {
        return employee_Designation;
    }

    public String getEmployee_Nic() {
        return employee_Nic;
    }

    public String getEmployee_Profilepicture() {
        return employee_Profilepicture;
    }

    public String getId() {
        return id;
    }
}

