package com.example.budgetapp.controller;

import com.example.budgetapp.services.BudgetServices;
import com.example.budgetapp.services.impl.BudgetServicesImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/budget")
public class BudgetController {

    private BudgetServices budgetService;

    public BudgetController(BudgetServices budgetService) {
        this.budgetService = budgetService;
    }

    @GetMapping("/daily")
    public int dailyBudget() {
        return budgetService.getDailyBudget();
    }

    @GetMapping("/balance")
    public int balance() {
        return budgetService.getBalance();
    }

    @GetMapping("/vac")
    public int vacBonus(@RequestParam int vacDays) {
        return budgetService.getVacBonus(vacDays);
    }

    @GetMapping("/vac/salary")
    public int salaryWithVac(@RequestParam int vacDays, @RequestParam int workDays, @RequestParam int vacWorkDays) {
        return budgetService.getsalWithVa(vacDays, vacWorkDays, workDays);
    }

}

