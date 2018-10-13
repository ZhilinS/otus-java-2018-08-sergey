import java.util.Arrays;
import java.util.Collection;
import static java.util.Objects.nonNull;

public class OtusList<E> {

    private Object[] elements;
    private int size;

    public OtusList () {
        this.elements = new Object[10];
        this.size = 0;
    }

    public void add(E element) {
        if (elements.length == size) {
            extend();
        }

        elements[size++] = element;
    }

    public void add(int position, E element) {
        checkPosition(position);

        if (elements.length + 1 == size) {
            extend();
        }

        if (get(position) != null) {
            System.arraycopy(elements, position, elements, position + 1, elements.length - position - 1);
        }

        size++;
        elements[position] = element;
    }

    public void addAll(Collection<E> foreign) {
        for (E item:foreign) {
            add(item);
        }
    }

    public E get(int position) {
        checkPosition(position);

        return (E) elements[position];
    }

    public int size() {
        return size;
    }

    public void remove(int position) {
        checkPosition(position);

        elements[position] = null;

        if (position != size--) {
            System.arraycopy(elements, position + 1, elements, position, size);
        }
    }

    public void remove(E element) {
        int position = elementPosition(element);
        checkPosition(position);

        remove(position);
    }

    public boolean contains(E element) {
        return elementPosition(element) != -1;
    }

    private void extend() {
        Object[] supportArray = new Object[size << 1];
        System.arraycopy(elements, 0, supportArray, 0, elements.length);

        elements = new Object[size << 1];
        System.arraycopy(supportArray, 0, elements, 0, supportArray.length);
    }

    private void checkPosition(int position) {
        if (position == -1 || position >= size) {
            throw new ArrayIndexOutOfBoundsException("Element index is out of the array bounds");
        }
    }

    private int elementPosition(E element) {
        int found = 0;

        for (Object elem:elements) {
            if (nonNull(elem) && ((E) elem).equals(element)) {
                return found;
            }

            found++;
        }

        return -1;
    }

    @Override
    public String toString() {
        return Arrays.asList(elements).toString();
    }
}
