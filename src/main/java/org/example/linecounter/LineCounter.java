package org.example.linecounter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.util.ArrayList;


public class LineCounter {
    final private Path directory;
    final private ArrayList<String> extensions;
    private boolean isCounted = false;

    public LineCounter(String path) throws InvalidPathException {
        directory = Paths.get(path);
        extensions = null;
    }

    public LineCounter(String path, final ArrayList<String> extensions) throws InvalidPathException {
        directory = Paths.get(path);
        this.extensions = extensions;
    }

    public boolean isCounted() {
        return isCounted;
    }

    public void count() throws IOException {
        if (isCounted) return;

        try (var stream = Files.list(directory)) {
            TableGenerator tableGenerator = new TableGenerator(new ConsolePrinter());
            tableGenerator.printHeader();
            stream.forEach(fileName -> {
                tableGenerator.printRow(fileName.toString(), "", "");
            });
            tableGenerator.printSummary(100, 20);
            isCounted = true;
        }
    }
}
