package de.qaware.fasttrack.searching;

public class Step {
    int i;
    int j;
    int value;


    public Step(int i, int j, int value) {
        this.i = i;
        this.j = j;
        this.value = value;
    }

    public String toString() {
        return "(" + this.i + ", " + this.j + ", " + this.value + ")";
    }
}
