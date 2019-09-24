package net.testwork.dipen.expense;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

class Expense {
    private BigDecimal value;
    private LocalDate date;
    private String note;

    Expense(double value) {
        this(value, LocalDate.now());
    }

    Expense(double value, LocalDate date) {
        this.value = BigDecimal.valueOf(value).setScale(2, BigDecimal.ROUND_HALF_EVEN);
        this.date = date;
    }

    BigDecimal getValue() {
        return value;
    }


    LocalDate getDate() {
        return date;
    }

    Optional<String> getNote() {
        return Optional.ofNullable(note);
    }

    void setNote(String note) {
        this.note = note;
    }
}
