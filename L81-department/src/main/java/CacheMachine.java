import java.util.Map;
import ru.otus.Atm;
import ru.otus.money.Type;

public final class CacheMachine {

    private final Atm atm;
    private final Snapshot snapshot;

    public CacheMachine(
        final Atm atm
    ) {
        this(atm, new Snapshot(atm));
    }

    public CacheMachine(
        final Snapshot snapshot
    ) {
        this(new Atm(snapshot.balance()), snapshot);
    }

    public CacheMachine(
        final Atm atm,
        final Snapshot snapshot
    ) {
        this.atm = atm;
        this.snapshot = snapshot;
    }

    public Map<Type, Integer> balance() {
        return this.atm.balance();
    }

    public Snapshot snapshot() {
        return this.snapshot;
    }

    public Atm atm() {
        return this.atm;
    }
}
