package org.example.date_230221;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CollectionTest {

    @Test
    void equalsTest() {
        Money money1 = new Money(100);
        Money money2 = new Money(100);

        assertThat(money1.equals(money2)).isTrue();
    }

}
