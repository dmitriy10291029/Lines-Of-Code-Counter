package org.example.linecounter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;


public class LineCounter {
    static public String USAGE = "loc-util \"<path>\" [ext. 1] [ext. 2]...\nIf extensions are not specified all extensions considered.";

    final private Path directory;
    final private HashSet<String> extensions;
    final private Printer printer;
    private boolean isCounted = false;

    public LineCounter(String path, Printer printer) throws InvalidPathException {
        this(path, printer, null);
    }

    public LineCounter(String path, Printer printer, final HashSet<String> extensions)
        throws InvalidPathException
    {
        this.printer = printer;
        directory = Path.of(path);
        this.extensions = extensions;
    }

    public boolean isCounted() {
        return isCounted;
    }

    public void count() throws IOException {
        if (isCounted) return;

        try (var stream = Files.list(directory)) {
            TableGenerator tableGenerator = new TableGenerator(printer);

            tableGenerator.printHeader();

            var lineCounterVisitor = new LineCounterFileVisitor(directory, tableGenerator, extensions);
            Files.walkFileTree(directory, lineCounterVisitor);

            tableGenerator.printSummary(100, 20);
            isCounted = true;
        }
    }
}
