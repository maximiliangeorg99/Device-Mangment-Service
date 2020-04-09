package c24.thriftshop.stripe.demo.domain.user;

import c24.thriftshop.stripe.demo.util.TestTimer;
import org.junit.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;


public class PasswordTest {
    static TestTimer testTimer = new TestTimer();

    @Test
    public void testHashing() {
        final Password password = new Password("12345678", "salt");
        assertThat(password.getHash()).isNotNull().isEqualTo("1fbef050929deca4a4072f22b25e44d5c4c2056056f1c1b9b9813cecd62eed3a");
    }

    @Test
    public void testPasswordNotInHash() {
        final Password password = new Password("hallo", "salt");
        assertThat(password.getHash()).doesNotContain("hallo");
    }

    @Test
    public void testSamePasswordDifferentHash() {
        final Password password = new Password("password");
        final Password password1 = new Password("password");
        assertThat(password).isNotEqualTo(password1);
    }

    @Test
    public void testPerformance() throws InterruptedException {
        testTimer.start();
        for (int i = 0; i < 10000; i++) {
            final UUID id = UUID.randomUUID();
            final Password password = new Password(id.toString());
        }
        testTimer.stop();
        assertThat(testTimer.getTimeInSeconds()).isLessThan(10);
    }
}