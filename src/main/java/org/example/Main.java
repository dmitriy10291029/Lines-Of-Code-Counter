package org.example;

import org.example.linecounter.ArgParser;
import org.example.linecounter.ConsolePrinter;
import org.example.linecounter.LineCounter;
import org.example.linecounter.Printer;

import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.nio.file.NoSuchFileException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Printer printer = new ConsolePrinter();
        try {
            ArgParser argParser;
            if (args.length != 0) {
                argParser = new ArgParser(args);

            } else {
                System.out.println("Enter args:");
                Scanner scanner = new Scanner(System.in);
                argParser = new ArgParser(scanner.nextLine());
            }
            LineCounter lineCounter = new LineCounter(argParser.getDirectory(), printer, argParser.getExtensions());
            lineCounter.count();

        } catch (InvalidPathException | NoSuchFileException e) {
            printer.print("Invalid input\n".concat(LineCounter.USAGE));

        } catch (IOException e) {
            printer.print("A runtime error occurred. The program was stopped...");
        }
    }
}