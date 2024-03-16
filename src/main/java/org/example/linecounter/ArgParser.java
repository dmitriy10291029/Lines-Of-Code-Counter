package org.example.linecounter;

import java.nio.file.InvalidPathException;
import java.util.HashSet;
import java.util.Arrays;
import java.util.HashSet;

public class ArgParser {
    private final HashSet<String> extensions;
    private final String directory;

    public ArgParser(String[] args) throws InvalidPathException {
        if (args.length > 0 && args[0].length() > 2 && args[0].startsWith("\"") && args[0].endsWith("\"")) {
            directory = args[0].substring(1, args[0].length() - 1);
        } else {
            throw new InvalidPathException("", "Invalid Path");
        }

        if (args.length > 1) {
            extensions = new HashSet<>();

            for (int i = 1; i < args.length; ++i){
                if (args[i].length() >= 2 && args[i].startsWith(".")) {
                    extensions.add(args[i].substring(1));
                } else {
                    extensions.add(args[i]);
                }
            }
        } else {
            extensions = null;
        }
    }

    public HashSet<String> getExtensions() {
        return extensions;
    }

    public String getDirectory() {
        return directory;
    }
}
