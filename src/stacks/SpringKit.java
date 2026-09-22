package stacks;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

/**
 * Encapsulates the configuration layout rules for generating Java Spring Boot ecosystems.
 *
 * @version 1.0
 */
public class SpringKit implements StackKit {

    @Override
    public void generateBlueprint(Path targetRoot) throws IOException {
        System.out.println("🍃 Injecting modular Spring template blueprint...");

        Path javaSourceDir = targetRoot.resolve("src/main/java/com/example");
        Files.createDirectories(javaSourceDir);

        Path appJavaPath = javaSourceDir.resolve("Application.java");
        String javaContent = """
                package com.example;
                
                public class Application {
                    public static void main(String[] args) {
                        System.out.println("Hello from your modularly scaffolded Spring Application!");
                    }
                }
                """;
        Files.writeString(appJavaPath, javaContent, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);

        Path pomPath = targetRoot.resolve("pom.xml");
        String pomContent = """
                <?xml version="1.0" encoding="UTF-8"?>
                <project xmlns="http://apache.org"
                         xmlns:xsi="http://w3.org"
                         xsi:schemaLocation="http://apache.org http://apache.org">
                    <modelVersion>4.0.0</modelVersion>
                    <groupId>com.example</groupId>
                    <artifactId>scaffolded-app</artifactId>
                    <version>1.0-SNAPSHOT</version>
                </project>
                """;
        Files.writeString(pomPath, pomContent, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
    }
}