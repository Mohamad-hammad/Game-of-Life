package com.company;

abstract class Employee {
    private int EmpId;
    Employee(){
        System.out.print("Employee Created\n");
    }
    public void WorkOnProjects(){}
    public void  HaveBenefits (){}
    public void TakeInterview(){
        System.out.print("This employee cannot take Interview\n");
    }
}
