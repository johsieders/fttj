package de.qaware.fasttrack.searching;


public interface SearchProblem<C, S, T> {
    void doStep(S step);

    void undoStep(S step);

    C getSteps();

    boolean done();

    T getState();
}
