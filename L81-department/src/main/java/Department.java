import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import ru.otus.money.Type;

public final class Department {

    private final Collection<CacheMachine> atms;

    public Department(
        final Collection<CacheMachine> atms
    ) {
        this.atms = atms;
    }

    public Map<Type, Integer> balance() {
        return this.atms
            .stream()
            .map(CacheMachine::balance)
            .map(Map::entrySet)
            .flatMap(Collection::stream)
            .collect(
                Collectors.groupingBy(
                    Map.Entry::getKey,
                    Collectors.summingInt(Map.Entry::getValue)
                )
            );
    }

    public void reset() {
        final Set<CacheMachine> machines = this.atms
            .stream()
            .map(machine -> new CacheMachine(machine.snapshot()))
            .collect(Collectors.toSet());
        this.atms.clear();
        this.atms.addAll(machines);
    }
}
