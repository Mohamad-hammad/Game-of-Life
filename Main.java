package com.company;

public class Main {

    public static void main(String[] args) {
	    Employee e1= new ProjectManager();
        Employee e2= new SoftwareEng();
        Employee e3= new AssociateSoftwareEngineer();
        Employee e4= new Internee();

        e1.WorkOnProjects();
        e2.WorkOnProjects();
        e3.WorkOnProjects();
        e4.WorkOnProjects();

        e1.HaveBenefits();
        e2.HaveBenefits();
        e3.HaveBenefits();
        e4.HaveBenefits();

        e1.TakeInterview();
        e2.TakeInterview();
        e3.TakeInterview();
        e4.TakeInterview();
    }
}
