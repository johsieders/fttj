package de.qaware.fasttrack.searching;

import java.util.Iterator;


public interface SearchProblemIter<T, R> {
    void doStep(R step);

    void undoStep(R step);

    Iterator<R> getSteps();

    boolean done();

    T getState();
}
