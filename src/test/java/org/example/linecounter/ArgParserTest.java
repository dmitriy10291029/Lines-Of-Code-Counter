package org.example.linecounter;

import org.junit.jupiter.api.Test;

import java.nio.file.InvalidPathException;

import static org.junit.jupiter.api.Assertions.*;

class ArgParserTest {
    @Test
    void TestEmpty() {
        final String[] args = {};
        try {
            final ArgParser argParser = new ArgParser(args);
            fail();
        } catch (InvalidPathException e) {
            assertTrue(true);
        }
    }

    @Test
    void TestFilename() {
        final String[] args = {"\"test\""};
        final ArgParser argParser = new ArgParser(args);
        assertEquals("test", argParser.getDirectory());
    }

    @Test
    void TestFilenameAndOneExt() {
        final String[] args = {"\"test\"", "ext1"};
        final ArgParser argParser = new ArgParser(args);

        assertEquals("test", argParser.getDirectory());

        assertEquals(1, argParser.getExtensions().size());
        assertTrue(argParser.getExtensions().contains(args[1]));
    }

    @Test
    void TestExtWithDot() {
        final String[] args = {"\"test\"", ".e"};
        final ArgParser argParser = new ArgParser(args);

        assertEquals("test", argParser.getDirectory());

        assertEquals(1, argParser.getExtensions().size());
        assertTrue(argParser.getExtensions().contains("e"));
    }

    @Test
    void TestExtOneLetter() {
        final String[] args = {"\"test\"", "e"};
        final ArgParser argParser = new ArgParser(args);

        assertEquals("test", argParser.getDirectory());

        assertEquals(1, argParser.getExtensions().size());
        assertTrue(argParser.getExtensions().contains("e"));
    }

    @Test
    void TestAnyExtensions() {
        final String[] args = {"\"test\"", "exten1", "ex2", "e3", "4", "ext"};
        final ArgParser argParser = new ArgParser(args);

        assertEquals(args.length - 1, argParser.getExtensions().size());

        for (int i = 1; i < args.length; ++i) {
            assertTrue(argParser.getExtensions().contains(args[i]));
        }
    }
}