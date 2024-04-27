package org.example.linecounter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.util.HashSet;


public class LineCounter {
    static public String USAGE = "ARGUMENTS: \"<path>\" [ext. 1] [ext. 2]...\nIf extensions are not specified all extensions considered.";

    final private Path directory;
    final private HashSet<String> extensions;
    final private TableGenerator tableGenerator;
    private boolean isCounted = false;

    public LineCounter(String path, Printer printer) throws InvalidPathException {
        this(path, printer, null);
    }

    public LineCounter(String path, Printer printer, final HashSet<String> extensions)
        throws InvalidPathException
    {
        tableGenerator = new TableGenerator(printer);
        directory = Path.of(path);
        this.extensions = extensions;
    }

    public boolean isCounted() {
        return isCounted;
    }

    public void count() throws IOException {
        if (isCounted) return;

        try (var stream = Files.list(directory)) {
            tableGenerator.printHeader();
            var fileVisitor = newFileVisitor();
            Files.walkFileTree(directory, fileVisitor);
            tableGenerator.printDivider();
            tableGenerator.printHeader();
            tableGenerator.printSummary(fileVisitor.getSumCodeLines(), fileVisitor.getSumAllLines());
            isCounted = true;
        }
    }

    public LineCounterFileVisitor newFileVisitor() {
        return new LineCounterFileVisitor(directory, tableGenerator, extensions);
    }
}
