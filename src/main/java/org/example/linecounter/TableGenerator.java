package org.example.linecounter;

public class TableGenerator {
    private static final int FILENAME_COL_SIZE = 40;
    private static final int COL_SIZE = 12;
    private final IPrinter printer;

    public TableGenerator(IPrinter printer) {
        this.printer = printer;
    }

    public void printRow(String filename, int i1, int i2) {
        printRow(filename, String.valueOf(i1), String.valueOf(i2));
    }

    public void printRow(String filename, String col1, String col2) {
        int i = 0;
        int j = 0;
        while (i < filename.length()) {
            if (j == FILENAME_COL_SIZE) {
                printCell("");
                printCell("");
                printer.println("");
                j = 0;
            }
            printer.print(filename.charAt(i));
            i++;
            j++;
        }
        while (j < FILENAME_COL_SIZE) {
            printer.print(" ");
            j++;
        }
        printCell(col1);
        printCell(col2);
        printer.println("");
    }

    public void printHeader() {
        printRow("", "Code", "All");
        printDivider();
    }

    public void printSummary(int code, int all) {
        printDivider();
        printRow("SUMMARY", code, all);
    }

    private void printDivider() {
        for (int i = 0; i < FILENAME_COL_SIZE; i++) {
            printer.print("-");
        }
        for (int i = 0; i < 2; ++i) {
            printer.print("|");
            for (int j = 0; j < COL_SIZE; j++) {
                printer.print("-");
            }
        }
        printer.println("");
    }

    private void printCell(String str) {
        printer.print("|");
        for (int i = 0; i < COL_SIZE - str.length(); i++) {
            printer.print(" ");
        }
        printer.print(str);
    }
}
