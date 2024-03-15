# Lines of code counter

This util prints an info about number of lines of every language and/or file.

**Usage**:
```
loc-util --dir|-d "<path>"      sets path of dir or file
         --file-list|-f         count for every file in dir 
         --add|-a <lang name>   adds the code file types of 
                                this language to the list 
                                of considered types. May be 
                                used more one time. If 
                                uncpecified all types will 
                                be considered.
                                
Supported langs: java, cpp, python, markdown, xml, json.
```

**Example 1**:
```
@user:~/dev/project$ loc-util --dir "./src" -a java -a xml -a markdown 
         |    Code|     All|    Part 
---------|--------|--------|--------
java     |     900|    1000|   50.0%
xml      |     850|     900|   45.0%     
markdown |      90|     100|    5.0%

SUMMARY 
Code: 1840
All:  2000
```

**Example 2**:
```
@user: loc-util -d "~/dev/hello-world" -f

DETAILS:JAVA                        |    Code|     All|    Part 
------------------------------------|--------|--------|--------
src/main/java/org.example/Main.java |       6|       7|   50.0%

DETAILS:XML |    Code|     All|    Part
------------|--------|--------|--------
pom.xml     |      15|      17|

DEATAILS:MARKDOWN |    Code|     All|    Part
------------------|--------|--------|--------
README.md         |      50|      58|

DETAILS  |    Code|     All|    Part 
---------|--------|--------|--------
java     |     900|    1000|   50.0%
xml      |     850|     900|   45.0%    
markdown |      90|     100|    5.0%

SUMMARY 
Code: 1840
All:  2000
```