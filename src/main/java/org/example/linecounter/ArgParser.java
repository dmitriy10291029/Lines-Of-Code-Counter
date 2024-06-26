package org.example.linecounter;

import java.nio.file.InvalidPathException;
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

        extensions = getExtensionsSet(Arrays.copyOfRange(args, 1, args.length));
    }

    public ArgParser(String line) throws InvalidPathException {
        int startOfDir = -1;
        int endOfDir = -1;
        char[] charArray = line.toCharArray();
        for (int i = 0; i < line.length(); i++) {
            if (charArray[i] == '\"') {
                if (startOfDir == -1) {
                    startOfDir = i + 1;
                } else {
                    endOfDir = i;
                    break;
                }
            }
        }

        if (endOfDir == -1) {
        throw new InvalidPathException(line, "Invalid Path");
        }

        directory = line.substring(startOfDir, endOfDir);
        String[] lostArgs = line.substring(endOfDir + 1).split("\\s+");

        extensions = getExtensionsSet(lostArgs);
    }

    private static HashSet<String> getExtensionsSet(String[] args) {
        if (args.length > 1) {
            HashSet<String> extensions = new HashSet<>();
            extensions = new HashSet<>();

            for (int i = 1; i < args.length; ++i){
                if (args[i].length() >= 2 && args[i].startsWith(".")) {
                    extensions.add(args[i].substring(1));
                } else {
                    extensions.add(args[i]);
                }
            }
            return extensions;
        } else {
            return null;
        }
    }

    public HashSet<String> getExtensions() {
        return extensions;
    }

    public String getDirectory() {
        return directory;
    }
}
