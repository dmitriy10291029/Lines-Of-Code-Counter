package org.example.linecounter;

import java.nio.file.InvalidPathException;
import java.util.ArrayList;
import java.util.Arrays;

public class ArgParser {
    private final ArrayList<String> extensions;
    private final String directory;

    public ArgParser(String[] args) throws InvalidPathException {
        if (args.length > 0) {
            directory = args[0];
        } else {
            throw new InvalidPathException("", "Empty path");
        }
        if (args.length > 1) {
            extensions = new ArrayList<>();
            Arrays.stream(args, 1, args.length).forEach(extensions::add);
        } else {
            extensions = null;
        }
    }

    public boolean hasExtensions() {
        return extensions != null;
    }

    public ArrayList<String> getExtensions() {
        return extensions;
    }

    public String getDirectory() {
        return directory;
    }
}
