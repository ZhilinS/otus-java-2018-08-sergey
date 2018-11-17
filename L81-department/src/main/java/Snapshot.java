import java.util.Set;
import ru.otus.Atm;
import ru.otus.money.Money;

public final class Snapshot {

    private final Set<Money> monies;

    public Snapshot(
        final Atm atm
    ) {
       this(atm.moneys());
    }

    public Snapshot(
        final Set<Money> monies
    ) {
        this.monies = monies;
    }

    public Set<Money> balance() {
        return this.monies;
    }
}
