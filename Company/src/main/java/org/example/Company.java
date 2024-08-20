package org.example;

import java.util.*;

public class Company {
    List<Employee> employes = new ArrayList<>();

    public void hire(Employee employee){
        employes.add(employee);
    }
    public void hireAll(Collection<Employee> employes){
        employes.addAll(employes);
    }

    public void fire(Employee employee){
        employes.remove(employee);
    }

    public List<Employee> getTopSalaryStaff(int count){
        List<Employee> TopSalary = new ArrayList<>();
        employes.sort(Comparator.comparingDouble(Employee::getMonthSalary));
        System.out.println("Топ максимальных 5 зарплат в окмпании:");
        TopSalary.addAll(employes);
        Collections.reverse(TopSalary);
        for (int i = 0; i < count; i++){
            System.out.println(TopSalary.get(i));
        }
        return TopSalary;
    }

    public List<Employee> getLowestSalaryStaff(int count){
        List<Employee> LowestSalary = new ArrayList<>();
        employes.sort(Comparator.comparingDouble(Employee::getMonthSalary));
        Collections.reverse(LowestSalary);
        System.out.println("Топ минимальных 5 зарплат в окмпании:");
        for (int i = 0; i < count; i++){
            LowestSalary.add(employes.get(i));
        }
        for (Employee list : LowestSalary){
            System.out.println(list);
        }
        return LowestSalary;
    }

    public double getIncomeCompany(){
        long incomeCompany = employes.size() * 100000;
        System.out.println("Итоговая прибыль компании - " + incomeCompany);
        return incomeCompany;


    }
}
