import org.junit.Test;
import ru.otus.HeapMeasure;
import ru.otus.StaticMemory;

import static org.junit.Assert.assertEquals;

public class StaticMemoryTest {

    @Test
    public void testObjectSizes() throws Exception {
        assertEquals(16, StaticMemory.sizeOf(new Object()));
        assertEquals(32, StaticMemory.sizeOf(""));
        assertEquals(24, StaticMemory.sizeOf(new int[]{}));
    }

    @Test
    public void testComplexObjectSizes() throws Exception {
        assertEquals(24, StaticMemory.sizeOf(new HeapMeasure.A((byte) 1, (byte) 2, (byte) 3)));
    }

    @Test
    public void testPrimitive() throws Exception {
        assertEquals(24, StaticMemory.sizeOf(1));
    }
}
