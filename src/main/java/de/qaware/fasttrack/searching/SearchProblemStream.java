package de.qaware.fasttrack.searching;


import java.util.stream.Stream;

public interface SearchProblemStream<T, R> {
    void doStep(R step);

    void undoStep(R step);

    Stream<R> getSteps();

    boolean done();

    T getState();
}
