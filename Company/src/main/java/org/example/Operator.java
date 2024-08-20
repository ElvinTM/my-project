package org.example;

import java.util.Random;

public class Operator implements Employee{
    Random random = new Random();
    int incomeOperator = random.nextInt(60000-salary)+salary;

    public Operator(Company company) {
    }

    @Override
    public double getMonthSalary() {
        return incomeOperator;
    }

    @Override
    public String toString() {
        return "Operator - " + getMonthSalary();
    }
}
