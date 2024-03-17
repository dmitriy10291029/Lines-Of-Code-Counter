package org.example.linecounter;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
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
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
        if (extensions == null || isSupportedExt(file)) {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(
                    new FileInputStream(file.toString()), StandardCharsets.UTF_8))) {
                int codeLines = 0;
                int allLines = 0;
                int read = 0;
                boolean isEmptyLine = true;

                while ((read = reader.read()) >= 0) {
                    char ch = (char) read;
                    if (ch == '\n') {
                        if (!isEmptyLine) {
                            codeLines++;
                            isEmptyLine = true;
                        }
                        allLines++;
                    } else if (!Character.isWhitespace(ch)) {
                        isEmptyLine = false;
                    }
                }
                if (!isEmptyLine) {
                    codeLines++;
                }
                allLines++;
                tableGenerator.printRow(startDirectory.relativize(file).toString(), codeLines, allLines);
                sumCodeLines += codeLines;
                sumAllLines += allLines;
            } catch (IOException e) {
                tableGenerator.printRow(startDirectory.relativize(file).toString(), "error", "error");
            }
        }
        return FileVisitResult.CONTINUE;
    }

    public int getSumCodeLines() {
        return sumCodeLines;
    }

    public int getSumAllLines() {
        return sumAllLines;
    }

    private boolean isSupportedExt(Path file) {
        String fileName = file.getFileName().toString();

        if (fileName.contains(".") && !fileName.endsWith(".")) {
            return extensions.contains(fileName.substring(fileName.lastIndexOf('.') + 1));
        }

        return false;
    }
}
