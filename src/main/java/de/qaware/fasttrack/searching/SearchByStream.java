package de.qaware.fasttrack.searching;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class SearchByStream<T, R> {
    public final List<T> solutions;
    public final List<R> history;
    public final List<R> historyUndo;
    private final SearchProblemStream<T, R> problem;

    public SearchByStream(SearchProblemStream<T, R> problem) {
        this.problem = problem;
        this.solutions = new ArrayList<>();
        this.history = new ArrayList<>();
        this.historyUndo = new ArrayList<>();
    }

    public List<T> search() {
        if (problem.done()) {
            T state = problem.getState();
            if (!solutions.contains(state)) solutions.add(problem.getState());
            return solutions;
        }
        Stream<R> steps = problem.getSteps();
        steps.forEach(this::process);
        return solutions;
    }

    public void process(R step) {
        problem.doStep(step);
        this.search();
        problem.undoStep(step);
    }
}
