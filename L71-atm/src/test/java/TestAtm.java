import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;
import org.junit.Before;
import org.junit.Test;
import ru.otus.Atm;
import ru.otus.Rubles;
import ru.otus.Type;
import ru.otus.exception.WithdrawException;
import ru.otus.withdraw.Rub;
import static com.google.common.collect.Sets.newHashSet;
import static org.junit.Assert.assertEquals;

public class TestAtm {

    private Atm atm;

    @Before
    public void setup() {
        atm = new Atm(
            newHashSet(
                new Rubles(
                    Type.RUR,
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
            )
        );
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
        final Map<Type, Integer> result = new HashMap<>();
        result.put(Type.RUR, 14300);
        this.atm.deposit(new Rub(2300));

        assertEquals(result, this.atm.balance());
    }

    @Test(expected = WithdrawException.class)
    public void shouldNotWithdrawMore() {
        this.atm.withdraw(new Rub(20000));
    }

    @Test(expected = WithdrawException.class)
    public void shouldNotWithdrawForNonDividable() {
        this.atm.withdraw(new Rub(512));
    }
}
