package org.example;

import java.util.Random;

public class TopManager implements Employee{
    Random random = new Random();
    int salaryTopManager = random.nextInt(60000-salary)+salary;
    double incomeTopManager = salaryTopManager + (salaryTopManager * 1.5);

    public TopManager(Company company) {
    }

    @Override
    public double getMonthSalary() {
        return incomeTopManager;
    }

    @Override
    public String toString() {
        return "TopManager - " + getMonthSalary();
    }
}
