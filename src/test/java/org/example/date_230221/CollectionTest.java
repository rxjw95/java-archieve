package org.example.date_230221;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class CollectionTest {

    @Test
    void equalsTest() {
        Money money1 = new Money(100);
        Money money2 = new Money(100);

        assertThat(money1.equals(money2)).isTrue();
    }

    @Test
    void hashCodeTest() {
        Money money1 = new Money(100);
        Money money2 = new Money(100);

        Set<Money> moneySet = new HashSet<>();
        moneySet.add(money1);
        moneySet.add(money2);

        assertThat(moneySet.size()).isEqualTo(1);
    }

}
