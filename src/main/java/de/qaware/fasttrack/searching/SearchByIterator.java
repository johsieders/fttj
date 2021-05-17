package de.qaware.fasttrack.searching;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SearchByIterator<T, R> {
    public final List<T> solutions;
    public final List<R> history;
    public final List<R> historyUndo;
    private final SearchProblemIter<T, R> problem;

    public SearchByIterator(SearchProblemIter<T, R> problem) {
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
        Iterator<R> steps = problem.getSteps();
        while (steps.hasNext()) {
            R step = steps.next();
            history.add(step);
            problem.doStep(step);
            search();
            problem.undoStep(step);
            historyUndo.add(step);
        }
        return solutions;
    }
}
