import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.TreeSet;
import org.junit.Before;
import org.junit.Test;
import ru.otus.Atm;
import ru.otus.exception.DepositException;
import ru.otus.money.Dollars;
import ru.otus.money.Euros;
import ru.otus.money.Money;
import ru.otus.money.Rubles;
import ru.otus.money.Type;
import ru.otus.exception.WithdrawException;
import ru.otus.withdraw.Aud;
import ru.otus.withdraw.Rub;
import static org.junit.Assert.assertEquals;

public class TestAtm {

    private Atm atm;

    @Before
    public void setup() {
        final HashSet<Money> monies = new HashSet<>();
        monies.add(
            new Rubles(
                new TreeSet<Integer>() {{
                    add(5000);
                    add(2000);
                    add(1000);
                    add(500);
                    add(200);
                    add(100);
                }},
                12000
            )
        );
        monies.add(
            new Euros(
                new TreeSet<Integer>() {{
                    add(500);
                    add(200);
                    add(100);
                    add(50);
                }},
                4300
            )
        );
        monies.add(
            new Dollars(
                new TreeSet<Integer>() {{
                    add(500);
                    add(200);
                    add(100);
                    add(50);
                    add(10);
                }},
                25000
            )
        );

        atm = new Atm(monies);
    }

    @Test
    public void testWithdraw() {
        final Map<Integer, Integer> result = new HashMap<>();
        result.put(5000, 1);
        result.put(200, 2);

        assertEquals(result, this.atm.withdraw(new Rub(5400)));
    }

    @Test
    public void testDeposit() {
        this.atm.deposit(new Rub(2300));

        assertEquals(14300, this.atm.balance().get(Type.RUR).intValue());
    }

    @Test(expected = WithdrawException.class)
    public void shouldNotWithdrawMore() {
        this.atm.withdraw(new Rub(20000));
    }

    @Test(expected = WithdrawException.class)
    public void shouldNotWithdrawForNonDividable() {
        this.atm.withdraw(new Rub(512));
    }

    @Test(expected = WithdrawException.class)
    public void shouldNotWithdrawAud() {
        this.atm.withdraw(new Aud(200));
    }

    @Test(expected = DepositException.class)
    public void shouldNotDepositAud() {
        this.atm.deposit(new Aud(200));
    }
}
