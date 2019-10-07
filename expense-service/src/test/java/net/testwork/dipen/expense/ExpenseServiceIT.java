package net.testwork.dipen.expense;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static java.util.Collections.singletonList;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ExpenseServiceIT {

    private static final long SOME_EXPENSE_ID = 1L;
    @MockBean
    ExpenseRepository expenseRepository;

    @Autowired
    ExpenseService expenseService;

    @Test
    public void shouldGetAllExpenses() {
        // given
        Expense expense = new Expense(12.34);
        Mockito.when(expenseRepository.findAll())
                .thenReturn(singletonList(expense));

        // when
        List<Expense> found = (List<Expense>) expenseService.getAll();

        // then
        assertThat(found).asList().hasSize(1);
        assertThat(found.get(0)).isEqualTo(expense);
    }

    @Test
    public void shouldDeleteExpense() {
        // when
        expenseService.delete(SOME_EXPENSE_ID);

        // then
        Mockito.verify(expenseRepository).deleteById(SOME_EXPENSE_ID);
    }

    @Test
    public void shouldSaveExpense() {
        // given
        Expense expense = new Expense(23.45);

        // when
        expenseService.add(expense);

        // then
        Mockito.verify(expenseRepository).save(expense);
    }
}
