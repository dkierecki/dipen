package net.testwork.dipen.expense;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExpenseService {

    private final ExpenseRepository expenseRepository;

    @Autowired
    public ExpenseService(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    public Iterable<Expense> getAll() {
        return expenseRepository.findAll();
    }

    public Expense add(Expense expense) {
        return expenseRepository.save(expense);
    }

    public void delete(Long expenseId) {
        expenseRepository.deleteById(expenseId);
    }

}
