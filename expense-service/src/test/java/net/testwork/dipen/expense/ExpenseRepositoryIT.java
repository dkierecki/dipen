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
    public void saveExpense() {
        // given
        entityManager.persist(new Expense(12.20));
        // when
        assumeThat(expenseRepository.count()).isEqualTo(1);
        Expense savedExpense = expenseRepository.findAll().iterator().next();
        // then
        assertThat(savedExpense.getValue()).isEqualTo("12.20");
        assertThat(savedExpense.getDate()).isToday();
        assertThat(savedExpense.getNote()).isEmpty();
    }
}
