package de.qaware.fasttrack.searching;

import java.util.ArrayList;
import java.util.List;

public class SearchByList<T, R> {
    public final List<T> solutions;
    public final List<R> history;
    public final List<R> historyUndo;
    private final SearchProblemList<T, R> problem;

    private int counter = 0;

    public SearchByList(SearchProblemList<T, R> problem) {
        this.problem = problem;
        this.solutions = new ArrayList<>();
        this.history = new ArrayList<>();
        this.historyUndo = new ArrayList<>();
    }

    public List<T> search() {
        if (problem.done()) {
            solutions.add(problem.getState());
            counter++;
        } else {
            List<R> steps = problem.getSteps();
            for (R step : steps) {
                history.add(step);
                problem.doStep(step);
                search();
                problem.undoStep(step);
                historyUndo.add(step);
            }
        }
        return solutions;
    }
}
