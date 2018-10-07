package ru.otus;

public class HeapMeasure {

    public static void main(String[] args) throws Exception {
//        System.out.println(StaticMemory.sizeOf(new A((byte) 1,(byte)  2,(byte)  3)));
//        System.out.println(StaticMemory.sizeOf(1));
//        System.out.println(StaticMemory.sizeOf(new Long[]{1L, 2L, 3L}));
//        int[] intArray = {};
//        System.out.println(StaticMemory.sizeOf(intArray));
//        System.out.println(StaticMemory.sizeOf(new Object()));
        System.out.println(StaticMemory.sizeOf(""));
    }

    public static class A {
        public byte a1;
        public byte a2;
        public byte a3;

        public A(
                byte a1,
                byte a2,
                byte a3
        ) {
            this.a1 = a1;
            this.a2 = a2;
            this.a3 = a3;
        }
    }
}
