package org.example;

import java.util.Random;

public class Manager implements Employee{
    Random random = new Random();
    double incomeManager = (random.nextInt(60000-salary)+salary) + (profitManager() * 0.05);

    public Manager(Company company) {
    }

    public double profitManager(){
        int earnedMoney = random.nextInt(140000-115000)+115000;
        return earnedMoney;
    }

    @Override
    public double getMonthSalary() {
        return incomeManager;
    }

    @Override
    public String toString() {
        return "Manager - " + getMonthSalary();
    }
}
