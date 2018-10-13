package ru.otus;

import java.util.ArrayList;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import ru.otus.OtusList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@Ignore("List is recreated on every test method.")
public class OtusListTest {

    OtusList<String> list;

    @Before
    public void setUp() {
        this.list = new OtusList<String>();
    }

    @Test
    public void shouldAdd() {
        list.add("First");
        list.add("Second");
        list.add("Third");

        System.out.println("shouldAdd");
        System.out.println(list);
        System.out.println("==========");

        assertEquals(3, list.size());
    }

    @Test
    public void shouldRemove() {
        list.remove(1);
        list.remove("Third");

        System.out.println("shouldRemove");
        System.out.println(list);
        System.out.println("==========");

        assertEquals(1, list.size());
    }

    @Test
    public void shouldAddAll() {
        list.addAll(
            new ArrayList<String>() {{
                add("FIRST FROM ARRAYLIST");
                add("SECOND FROM ARRAYLIST");
            }}
        );

        System.out.println("shouldAddAll");
        System.out.println(list);
        System.out.println("==========");

        assertEquals(3, list.size());
    }

    @Test
    public void shouldAddInMiddle() {
        list.add(2, "Middle element");

        System.out.println("shouldAddInMiddle");
        System.out.println(list);
        System.out.println("==========");

        assertEquals(4, list.size());
    }

    @Test
    public void checkArrayExtension() {
        list.add("Fifth");
        list.add("Sixth");
        list.add("Seventh");
        list.add("Eighth");
        list.add("Nine");
        list.add("Tenth");
        list.add("Eleventh");

        System.out.println("checkArrayExtension");
        System.out.println(list);
        System.out.println("==========");

        assertEquals(11, list.size());
    }

    @Test
    public void checkContains() {
        assertTrue(list.contains("Fifth"));
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void shouldThrowException() {
        list.add(200, "NOPE");
    }
}
