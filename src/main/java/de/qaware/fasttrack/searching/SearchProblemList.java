package de.qaware.fasttrack.searching;

import java.util.List;

public interface SearchProblemList<R, S> {
    void doStep(S step);

    void undoStep(S step);

    List<S> getSteps();

    boolean done();

    R getState();
}
