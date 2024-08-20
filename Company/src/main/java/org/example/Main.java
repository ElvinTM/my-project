package org.example;

public class Main {
    public static void main(String[] args) {
        Company company = new Company();

        for (int o = 0; o < 180; o++){
            company.hire(new Operator(company));
        }

        for (int m = 0; m < 80; m++){
            company.hire(new Manager(company));
        }

        for (int t = 0; t < 10; t++){
            company.hire(new TopManager(company));
        }
        company.getIncomeCompany();
        System.out.println("");
        company.getTopSalaryStaff(15);
        System.out.println("");
        company.getLowestSalaryStaff(30);
        System.out.println("");
        System.out.println("До увольнения " + company.employes.size());
        for (int o = 0; o < company.employes.size()*1; o++){
            company.fire(company.employes.get(o));
        }
        System.out.println("После увольнения " + company.employes.size());
        System.out.println("");
        company.getTopSalaryStaff(15);
        System.out.println("");
        company.getLowestSalaryStaff(30);

        company.getIncomeCompany();
    }
}