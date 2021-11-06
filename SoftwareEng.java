package com.company;

public class SoftwareEng extends Employee{
    public int basic;
    public int bonus;
    SoftwareEng(){
        System.out.print("SoftwareEng Created\n\n");
    }

    @Override
    public void TakeInterview(){
        System.out.print("SE taking Interview\n");
    }

    @Override
    public void WorkOnProjects() {
        System.out.print("SE working on Project\n");
    }

    @Override
    public void HaveBenefits() {
        basic = 1000;
        bonus = 1500;
        int benefit = bonus + basic;
        System.out.print("Benefits of SE is "+ benefit + "\n");
    }
}
