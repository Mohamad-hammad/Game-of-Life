package com.company;

public class ProjectManager extends Employee{
    public int basic;
    public int bonus;

    ProjectManager(){
        System.out.print("Project Manager Created\n\n");
    }
    @Override
    public void TakeInterview(
    ){
        System.out.print("PM taking Interview\n");
    }

    @Override
    public void WorkOnProjects() {
        System.out.print("PM working on Project\n");
    }

    @Override
    public void HaveBenefits() {
        basic = 1000;
        bonus = 1500;
        int benefit = bonus + basic;
        System.out.print("Benefits of PM is "+ benefit+"\n");
    }
}
