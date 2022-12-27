package com.example.budgetapp.services.impl;

import com.example.budgetapp.model.Transaction;
import com.example.budgetapp.services.BudgetServices;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

@Service
public class BudgetServicesImpl implements BudgetServices {

    public static final int SALARY = 20_000;
    public static final int SAVING = 3_000;//то  что оставляем от мес ЗП
   public static final int DAILY_BUD = (SALARY - SAVING) / LocalDate.now().lengthOfMonth();//Бюджет на день(КОЛИЧЕСТВО ДНЕЙ В ТЕК МЕСЯЦЕ)

    public static final int BALANCE = 0;
   public static final int AVG_SAL = (10000 + 10000 + 10000 + 10000 + 10000 + 15000 + 15000 + 15000 + 15000 + 15000 + 15000 + 20000) / 12;
    public static final double AVG_DAY = 29.3;
    private static long lastId=0;//для отслеживания транзакций и обращения к конкретной


    private static Map<Month, Map<Long, Transaction>> transactions = new TreeMap<>();


    @Override
    public int getDailyBudget() {
        return DAILY_BUD;
    }

    @Override
    public int getBalance() {              //сколько всего осталось
        return SALARY - getAllSpend()-SAVING;
    }

    public void addTransaction(Transaction transaction) {
        Map<Long, Transaction> monthTransactions = transactions.getOrDefault(LocalDate.now().getMonth(), new LinkedHashMap<>());
        monthTransactions.put(lastId++, transaction);
    }

    public int getDailyBalance() {                     // сколько можем потратить сегодня(на тек дату)
        return (DAILY_BUD * LocalDate.now().getDayOfMonth()-getAllSpend());
    }

    private int getAllSpend() {                  // сколько мы уже потратили в тек месяце
        Map<Long, Transaction> monthTransactions = transactions.getOrDefault(LocalDate.now().getMonth(), new LinkedHashMap<>());
        int sum = 0;
        for (Transaction transaction : monthTransactions.values()) {
            sum += transaction.getSum();
        }
        return sum;
    }
    @Override
    public int getVacBonus(int daysCount) {
        double avgDaySal = AVG_SAL / AVG_DAY;
        return (int) (daysCount * avgDaySal);
    }

    @Override
    public int getsalWithVa(int vacDaysCount, int vacWorkDaysCount, int workDaysMonth) {
        int salary = SALARY / workDaysMonth * (workDaysMonth - vacWorkDaysCount);
        return salary + getVacBonus(vacDaysCount);
    }

}
