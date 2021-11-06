package com.company;

public class AssociateSoftwareEngineer extends Employee{
    public int basic;
    public int bonus;

    AssociateSoftwareEngineer(){
        System.out.print("AssociateSoftwareEngineer Created\n\n");
    }

    @Override
    public void WorkOnProjects() {
        System.out.print("ASE working on Project\n");
    }
    @Override
    public void HaveBenefits() {
        basic = 1000;
        bonus = 1500;
        int benefit = bonus + basic;
        System.out.print("Benefits of ASE is "+ benefit+"\n");
    }
}
