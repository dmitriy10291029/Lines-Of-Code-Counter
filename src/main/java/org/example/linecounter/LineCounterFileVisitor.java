package org.example.linecounter;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashSet;

public class LineCounterFileVisitor extends SimpleFileVisitor<Path> {
    final private Path startDirectory;
    final private TableGenerator tableGenerator;
    final private HashSet<String> extensions;
    private int sumCodeLines = 0;
    private int sumAllLines = 0;

    public LineCounterFileVisitor(Path startDirectory, TableGenerator tableGenerator, HashSet<String> extensions) {
        this.startDirectory = startDirectory;
        this.tableGenerator = tableGenerator;
        this.extensions = extensions;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        if (extensions == null || isSupportedExt(file)) {
            int codeLines = 0;
            int allLines = 0;
            try {
                throw new IOException();
                //tableGenerator.printRow(startDirectory.relativize(file).toString(), codeLines, allLines);
            } catch (IOException e) {
                tableGenerator.printRow(startDirectory.relativize(file).toString(), "error", "error");
            }
        }
        return FileVisitResult.CONTINUE;
    }

    private boolean isSupportedExt(Path file) {
        String fileName = file.getFileName().toString();

        if (fileName.contains(".") && !fileName.endsWith(".")) {
            return extensions.contains(fileName.substring(fileName.lastIndexOf('.') + 1));
        }

        return false;
    }
}
