package org.example.linecounter;

public class ConsolePrinter implements IPrinter {
    public void print(Object o) {
        System.out.print(o);
    }

    public void println(Object o) {
        System.out.println(o);
    }
}
