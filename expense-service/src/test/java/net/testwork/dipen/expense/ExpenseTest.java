package net.testwork.dipen.expense;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

class ExpenseTest {

    private static final double SOME_VALUE = 12.34d;
    private static final String SOME_TEXT = "some text";
    private static final LocalDate PAST_DATE = LocalDate.now().minusDays(1);

    @ParameterizedTest
    @ValueSource(doubles = {112.31d, 0.314d, 99.995d, 1.1d, 0d})
    void createExpenseWithValue(double initValue) {
        Expense expense = new Expense(initValue);
        BigDecimal expected = BigDecimal.valueOf(initValue).setScale(2, RoundingMode.HALF_EVEN);
        assertThat(expense.getValue()).isEqualTo(expected);
    }

    @Test
    void createExpenseWithDefaultDate() {
        Expense expense = new Expense(SOME_VALUE);
        assertThat(expense.getDate()).isEqualTo(LocalDate.now());
    }

    @Test
    void createExpenseWithProvidedDate() {
        Expense expense = new Expense(SOME_VALUE, PAST_DATE);
        assertThat(expense.getDate()).isEqualTo(PAST_DATE);
    }

    @Test
    void createExpenseWithEmptyNote() {
        Expense expense = new Expense(SOME_VALUE);
        assertThat(expense.getNote()).isEmpty();
    }

    @Test
    void createExpenseWithNote() {
        Expense expense = new Expense(SOME_VALUE);
        expense.setNote(SOME_TEXT);
        assertThat(expense.getNote()).contains(SOME_TEXT);
    }
}