# Sentryc Interview

This is about basic explanation regarding the project.

1. The project is generated from Spring Initializer.
2. Maven is used as a build tool, so please import accordingly.
3. Business model relationship is updated from M-1 to 1-M.
4. Filtering logic query is implemented with criteria API.


Possible improvements
1. We can restructure & rename the entities.
2. We can think based on the data access pattern of the data that requires frequent
 access and can model our entities accordingly.
3. We can separate out the read and write concerns for data.
4. Test cases can improve further. (Done!)
5. Can use MapStruct library for writing converters.
```
<dependency>
    <groupId>org.mapstruct</groupId>
    <artifactId>mapstruct</artifactId>
    <version>1.5.5.Final</version>
</dependency>

<dependency>
    <groupId>org.mapstruct</groupId>
    <artifactId>mapstruct-processor</artifactId>
    <version>1.5.5.Final</version>
</dependency>
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-compiler-plugin</artifactId>
    <version>3.11.0</version>
    <configuration>
        <annotationProcessorPaths>
            <path>
                <groupId>org.mapstruct</groupId>
                <artifactId>mapstruct-processor</artifactId>
                <version>1.5.5.Final</version>
            </path>
        </annotationProcessorPaths>
    </configuration>
</plugin>
```

**Update**
1. Unit test cases improvement added.
2. Build successfully executed with all the test cases!