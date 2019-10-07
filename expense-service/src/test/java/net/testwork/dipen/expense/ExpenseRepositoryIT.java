package net.testwork.dipen.expense;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.Assumptions.assumeThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class ExpenseRepositoryIT {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ExpenseRepository expenseRepository;

    @Test
    public void shouldAddExpense() {
        // given
        Expense expense = new Expense(23.45);
        expense.setNote("some note");
        // when
        assumeThat(expenseRepository.count()).isEqualTo(0);
        Expense savedExpense = expenseRepository.save(expense);
        // then
        assertThat(savedExpense.getValue()).isEqualTo("23.45");
        assertThat(savedExpense.getDate()).isToday();
        assertThat(savedExpense.getNote()).contains("some note");
    }

    @Test
    public void shouldGetExpnses() {
        // given
        assumeThat(expenseRepository.count()).isEqualTo(0);
        entityManager.persist(new Expense(12.20));
        // when
        assumeThat(expenseRepository.count()).isEqualTo(1);
        Expense savedExpense = expenseRepository.findAll().iterator().next();
        // then
        assertThat(savedExpense.getValue()).isEqualTo("12.20");
        assertThat(savedExpense.getDate()).isToday();
        assertThat(savedExpense.getNote()).isEmpty();
    }

    @Test
    public void shouldDeleteExpense() {
        // given
       entityManager.persist(new Expense(12.34));
       Expense savedExpense = entityManager.persist(new Expense(23.45));
        // when
        assumeThat(expenseRepository.count()).isEqualTo(2);
        expenseRepository.deleteById(savedExpense.getId());
        // then
        assertThat(expenseRepository.count()).isEqualTo(1);
    }
}
