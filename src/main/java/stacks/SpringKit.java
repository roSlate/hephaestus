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
    public void generateBlueprint(Path targetRoot, String projectName) throws IOException {
        System.out.println("🍃 Injecting modular Spring template blueprint...");

        Path javaSourceDir = targetRoot.resolve("src/main/java/com/example");
        Files.createDirectories(javaSourceDir);

        Path appJavaPath = javaSourceDir.resolve("Application.java");
        String javaContent = """
        package com.example;
        
        import org.springframework.boot.SpringApplication;
        import org.springframework.boot.autoconfigure.SpringBootApplication;
        
        @SpringBootApplication
        public class Application {
            public static void main(String[] args) {
                SpringApplication.run(Application.class, args);
            }
        }
        """;
        Files.writeString(appJavaPath, javaContent, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);

        Path pomPath = targetRoot.resolve("pom.xml");
        String pomContent = """
        <?xml version="1.0" encoding="UTF-8"?>
        <project xmlns="http://maven.apache.org/POM/4.0.0"
                 xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                 xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
            <modelVersion>4.0.0</modelVersion>
        
            <parent>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-starter-parent</artifactId>
                <version>3.2.5</version>
                <relativePath/>
            </parent>
        
            <groupId>com.example</groupId>
            <artifactId>scaffolded-app</artifactId>
            <version>1.0-SNAPSHOT</version>
        
            <properties>
                <java.version>17</java.version>
            </properties>
        
            <dependencies>
                <dependency>
                    <groupId>org.springframework.boot</groupId>
                    <artifactId>spring-boot-starter</artifactId>
                </dependency>
            </dependencies>
        
            <build>
                <plugins>
                    <plugin>
                        <groupId>org.springframework.boot</groupId>
                        <artifactId>spring-boot-maven-plugin</artifactId>
                    </plugin>
                </plugins>
            </build>
        </project>
        """;
        Files.writeString(pomPath, pomContent, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
    }
}