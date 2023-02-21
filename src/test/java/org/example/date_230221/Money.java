package org.example.date_230221;

import java.util.Objects;

public class Money {
    private final int value;

    public Money(int value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Money money = (Money) o;


        return value == money.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
