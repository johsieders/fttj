package de.qaware.fasttrack.iterators;

import org.testng.annotations.Test;

import java.util.stream.Stream;

import de.qaware.fasttrack.iterators.Iter.*;

import static de.qaware.fasttrack.iterators.Iter.*;

public class IteratorsTest {

    @Test
    public void testFaculty() {
        Faculty f = new Faculty();
        for (int i = 0; i < 8; i++) {
            Long k = f.next();
            System.out.println(k);
        }
    }

    @Test
    public void testFaculty1() {
        Stream<Long> f = faculty().limit(10);
        f.forEach(System.out::println);
    }

    @Test
    public void testFibo() {
        Stream<Long> f = fibo().limit(10);
        f.forEach(System.out::println);
    }

    @Test
    public void testHamming() {
        Stream<Integer> f = hamming(2, 3, 5).limit(20);
        f.forEach(System.out::println);
    }
}
