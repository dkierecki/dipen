package net.testwork.dipen.expense;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

@Entity
class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false)
    private BigDecimal value;

    @Column(nullable = false)
    private LocalDate date;

    private String note;

    Expense(double value) {
        this(value, LocalDate.now());
    }

    Expense(double value, LocalDate date) {
        this.value = BigDecimal.valueOf(value).setScale(2, BigDecimal.ROUND_HALF_EVEN);
        this.date = date;
    }

    Long getId() {
        return id;
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
