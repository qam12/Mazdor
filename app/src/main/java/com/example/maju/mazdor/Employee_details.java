package com.example.maju.mazdor;

import android.widget.EditText;

/**
 * Created by qamber.haider on 4/7/2018.
 */

public class Employee_details {

    public String employee_FirstName;
    public String employee_Telephone;
    public String employee_Email;
    public String employee_Password;
    public String employee_Designation;
    public String employee_Nic;
    public String employee_Profilepicture;
    public String id;

    public Employee_details() {
    }

    public Employee_details(String employee_FirstName, String employee_Telephone, String employee_Email, String employee_Password, String employee_Designation, String employee_Nic, String employee_Profilepicture, String id) {
        this.employee_FirstName = employee_FirstName;
        this.employee_Telephone = employee_Telephone;
        this.employee_Email = employee_Email;
        this.employee_Password = employee_Password;
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

    public String getEmployee_Password() {
        return employee_Password;
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
