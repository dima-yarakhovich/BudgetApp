package com.example.budgetapp.services;

public interface BudgetServices {

     int getDailyBudget();
    
    
    
     int getBalance();


     int getVacBonus(int daysCount);

     int getsalWithVa(int vacDaysCount, int vacWorkDaysCount, int workDaysMonth);
}
