import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import ru.otus.money.Type;

public final class Department {

    private final Collection<CacheMachine> machines;

    public Department(
        final Collection<CacheMachine> machines
    ) {
        this.machines = machines;
    }

    public Map<Type, Integer> balance() {
        return this.machines
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
        final Set<CacheMachine> machines = this.machines
            .stream()
            .map(machine -> new CacheMachine(machine.snapshot()))
            .collect(Collectors.toSet());
        this.machines.clear();
        this.machines.addAll(machines);
    }

    public Collection<CacheMachine> machines() {
        return this.machines;
    }
}
