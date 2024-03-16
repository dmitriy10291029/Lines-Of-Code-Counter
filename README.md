# Lines of code counter

This util prints an info about number of lines of every language and/or file.

**Usage**:
```
loc-util "<path>" [ext. 1] [ext. 2]...

If extensions are not specified all extensions considered. 
```

**Example 2**:
```
@user: loc-util -d "~/dev/hello-world"
                                        |    Code|     All 
----------------------------------------|--------|--------
src/main/java/org.example/Main.java     |       6|       7
pom.xml                                 |      15|      17
README.md                               |      50|      58
----------------------------------------|--------|--------
SUMMARY                                 |    1840|    2000
```