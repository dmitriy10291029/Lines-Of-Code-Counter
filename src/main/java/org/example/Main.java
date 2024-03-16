package org.example;

import org.example.linecounter.ArgParser;
import org.example.linecounter.ConsolePrinter;
import org.example.linecounter.LineCounter;
import org.example.linecounter.Printer;

import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.nio.file.NoSuchFileException;

public class Main {
    public static void main(String[] args) {
        Printer printer = new ConsolePrinter();
        try {
            ArgParser argParser = new ArgParser(args);
            LineCounter lineCounter = new LineCounter(argParser.getDirectory(), printer, argParser.getExtensions());
            lineCounter.count();
        } catch (InvalidPathException | NoSuchFileException e) {
            printer.print("Invalid input\n".concat(LineCounter.USAGE));
        } catch (IOException e) {
            printer.print("A runtime error occurred. The program was stopped...");
        }
    }
}