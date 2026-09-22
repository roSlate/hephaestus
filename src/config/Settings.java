package config;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;
import java.util.Properties;

/**
 * Handles the ingestion, reading, and structural validation of the application settings file.
 * Safely parses property keys and maps them into native data constructs for use across the ecosystem.
 *
 * @version 1.1
 */
public class Settings {
    private String projectName;
    private ProjectStack projectStack;

    /**
     * Accesses the local 'hephaestus.properties' configuration file, reads the required values into memory,
     * and performs strict data integrity checks to guarantee subsequent operations are safe.
     */
    public void loadSettings() {
        Properties prop = new Properties();
        String configFileName = "hephaestus.properties";

        try (InputStream input = new FileInputStream(configFileName)) {
            prop.load(input);

            String nameInput = prop.getProperty("project.name");
            String stackInput = prop.getProperty("project.stack");

            // Integrity Check: Validate Project Name existence
            if (nameInput == null || nameInput.trim().isEmpty()) {
                System.out.println("❌ Error: 'project.name' is missing in hephaestus.properties!");
                return;
            }
            this.projectName = nameInput.trim();

            // Safe parsing using the new Optional-based enum mechanism
            Optional<ProjectStack> parsedStack = ProjectStack.fromString(stackInput);

            if (parsedStack.isPresent()) {
                this.projectStack = parsedStack.get();
            } else {
                System.out.println("❌ Error: 'project.stack' must be either 'spring' or 'react'!");
                this.projectStack = null;
            }

        } catch (IOException ex) {
            System.out.println("❌ Error: Could not locate or process 'hephaestus.properties' in the application root directory!");
        }
    }

    /**
     * Retrieves the successfully loaded and validated project name.
     *
     * @return A {@link String} matching the chosen project directory name, or {@code null} if parsing failed.
     */
    public String getProjectName() {
        return projectName;
    }

    /**
     * Retrieves the successfully evaluated framework stack choice.
     *
     * @return A valid {@link ProjectStack} variant representing the target stack, or {@code null} if parsing failed.
     */
    public ProjectStack getProjectStack() {
        return projectStack;
    }
}
