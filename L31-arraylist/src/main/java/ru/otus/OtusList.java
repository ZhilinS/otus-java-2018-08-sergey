package ru.otus;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import static java.util.Objects.nonNull;

public class OtusList<E> implements List<E> {

    private Object[] elements;
    private int size;

    public OtusList () {
        this.elements = new Object[10];
        this.size = 0;
    }

    public OtusList(final int initialSize) {
        this.elements = new Object[4];
        this.size = 0;
    }

    @Override
    public boolean add(E element) {
        if (elements.length == size) {
            extend();
        }

        elements[size++] = element;

        return true;
    }

    @Override
    public E remove(int position) {
        checkPosition(position);

        E elem = (E) elements[position];

        elements[position] = null;

        if (position != size--) {
            System.arraycopy(elements, position + 1, elements, position, size);
        }

        return elem;
    }

    @Override
    public boolean remove(Object o) {
        int position = elementPosition((E) o);
        checkPosition(position);

        return Objects.nonNull(remove(position));
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(Collection<? extends E> foreign) {
        for (E item:foreign) {
            add(item);
        }

        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException();
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

    public E get(int position) {
        checkPosition(position);

        return (E) elements[position];
    }

    @Override
    public E set(int index, E element) {
        final E old = get(index);
        elements[index] = element;

        return old;
    }

    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean contains(Object o) {
        return elementPosition((E) o) != -1;
    }

    @Override
    public Iterator<E> iterator() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(elements, size);
    }

    @Override
    public <T> T[] toArray(T[] a) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int indexOf(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int lastIndexOf(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public ListIterator<E> listIterator() {
        return new OtusListItr();
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
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

    public void trim() {
        if (size < elements.length) {
            elements = Arrays.copyOf(elements, size);
        }
    }

    @Override
    public String toString() {
        this.trim();
        return Arrays.asList(elements).toString();
    }

    private class OtusListItr implements ListIterator<E> {

        int cursor;

        public OtusListItr() {
            this.cursor = -1;
        }

        @Override
        public boolean hasNext() {
            return this.cursor != size;
        }

        @Override
        public E next() {
            int i = cursor + 1;

            if (i > size) {
                throw new ArrayIndexOutOfBoundsException(
                    String.format(
                        "Trying to get element with index %d," +
                            "while list contains only %s elements",
                        i,
                        size
                    )
                );
            }

            cursor = i;

            return (E) elements[cursor];
        }

        @Override
        public boolean hasPrevious() {
            return cursor > 0;
        }

        @Override
        public E previous() {
            int i = cursor - 1;

            if (i < 0) {
                throw new NoSuchElementException();
            }

            cursor = i;

            return (E) elements[cursor];
        }

        @Override
        public int nextIndex() {
            return 0;
        }

        @Override
        public int previousIndex() {
            return 0;
        }

        @Override
        public void remove() {
            OtusList.this.remove(cursor);
        }

        @Override
        public void set(E e) {
            OtusList.this.set(cursor, e);
        }

        @Override
        public void add(E e) {
            OtusList.this.add(e);
        }
    }
}
