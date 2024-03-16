package org.example;

import org.example.linecounter.ArgParser;
import org.example.linecounter.LineCounter;

import java.nio.file.InvalidPathException;

public class Main {
    public static void main(String[] args) {
        ArgParser argParser = new ArgParser(args);
        try {
            LineCounter lineCounter = new LineCounter(argParser.getDirectory());
        } catch (InvalidPathException) {

        }
    }
}