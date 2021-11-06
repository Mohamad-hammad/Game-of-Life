package com.company;

public class Internee extends Employee{
    public int basic;
    Internee(){
        System.out.print("Internee Created\n");
    }
    @Override
    public void WorkOnProjects() {
        System.out.print("Internee working on Project\n\n");
    }
    @Override
    public void HaveBenefits() {
        basic = 1000;
        System.out.print("Benefits of Internee is "+ basic +"\n");
    }
}
