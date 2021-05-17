package de.qaware.fasttrack.iterators;

import org.testng.annotations.Test;

import java.util.Iterator;

import static de.qaware.fasttrack.iterators.Iter1.*;

public class Iter1Test {
    @Test
    public void test_myConst() {
        Iterator<Integer> cIter = new myConst(2);
        System.out.println(cIter.next());
    }

    @Test
    public void test_abcIter() {
        Iterator myABC = new abcIter();
        System.out.println(myABC.next());
        System.out.println(myABC.hasNext());
    }

    @Test
    public void test_myABC1() {
        Iterator myABC = myABC1();
        System.out.println(myABC.next());
        System.out.println(myABC.hasNext());
    }

    @Test
    public void test_faculty() {
        Iterator myFac = new faculty();
        System.out.println(myFac.next());
        System.out.println(myFac.next());
        System.out.println(myFac.next());
        System.out.println(myFac.hasNext());
    }
}
