# Lines of code counter

This utility prints the total number of lines and the number of lines of code of all files 
with the **specified extensions** in the **specified directory.**

**Usage**:
```
ARGUMENTS: "<path>" [ext. 1] [ext. 2]...
If extensions are not specified all extensions considered. 
```

**Example**:
```
ARGUMENTS: "/home/user/Documents/coding/java/LinesOfCodeCounter" .java .md
```
```
OUTPUT:
                                                            |      Code|       All
README.md                                                   |        26|        30
src/test/java/org/example/linecounter/ArgParserTest.java    |        55|        72
src/main/java/org/example/Main.java                         |        22|        25
src/main/java/org/example/linecounter/ArgParser.java        |        34|        42
src/main/java/org/example/linecounter/ConsolePrinter.java   |         9|        12
src/main/java/org/example/linecounter/LineCounterFileVisitor|          |          
.java                                                       |        70|        81
src/main/java/org/example/linecounter/LineCounter.java      |        42|        53
src/main/java/org/example/linecounter/Printer.java          |         5|         8
src/main/java/org/example/linecounter/TableGenerator.java   |        59|        68
------------------------------------------------------------|----------|----------
                                                            |      Code|       All
SUMMARY                                                     |       322|       391
```