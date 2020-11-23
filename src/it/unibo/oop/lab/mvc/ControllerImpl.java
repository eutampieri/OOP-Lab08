package it.unibo.oop.lab.mvc;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public final class ControllerImpl implements Controller {
    private final Queue<String> printingQueue;
    private final List<String> history;

    ControllerImpl(){
        this.printingQueue = new LinkedList<>();
        this.history = new ArrayList<>();
    }

    public void enqueueString(final String s) {
        if (s == null) {
            throw new IllegalArgumentException();
        }
        this.printingQueue.add(s);
    }

    public String getNext() {
        return this.printingQueue.peek();
    }

    public List<String> getHistory() {
        return List.copyOf(this.history);
    }

    public void printCurrent() {
        final String toBePrinted = this.printingQueue.poll();
        if (toBePrinted == null) {
            throw new IllegalStateException();
        }
        this.history.add(toBePrinted);
        System.out.println(toBePrinted);
    }

}
