package ru.otus;

import java.util.ArrayList;
import java.util.Collections;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class OtusListTest {

    @Test
    public void testCopy() {
        final OtusList<String> dest = new OtusList<>(4);
        dest.add("To be replaced");
        dest.add("To be replaced");
        dest.add("To be replaced");
        dest.add("To be replaced");

        final ArrayList<String> src = new ArrayList<>();
        src.add("Simple");
        src.add("String");
        src.add("To");
        src.add("add");

        Collections.copy(dest, src);

        assertEquals(src.toArray(), dest.toArray());
    }

    @Test
    public void testSort() {
        final OtusList<String> dest = new OtusList<>();
        dest.add("What a wonderful code");
        dest.add("Not exactly");
        dest.add("A string should come first");
        dest.add("Adventure time should come first!");

        Collections.sort(dest);

        assertEquals("A string should come first", dest.get(0));
        assertEquals("Adventure time should come first!", dest.get(1));
        assertEquals("Not exactly", dest.get(2));
        assertEquals("What a wonderful code", dest.get(3));
    }

    @Test
    public void testAddAll() {
        final OtusList<String> dest = new OtusList<>();
        dest.add("What a wonderful code");
        dest.add("Not exactly");
        dest.add("A string should come first");
        dest.add("Adventure time should come first!");

        Collections.addAll(dest, "First added", "Second added");

        final ArrayList<String> src = new ArrayList<>();
        src.add("What a wonderful code");
        src.add("Not exactly");
        src.add("A string should come first");
        src.add("Adventure time should come first!");
        src.add("First added");
        src.add("Second added");

        assertEquals(src.toArray(), dest.toArray());
    }
}
