package scaffold;

import config.ProjectStack;
import stacks.DjangoKit;
import stacks.ReactKit;
import stacks.SpringKit;
import stacks.StackKit;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

/**
 * The core file-generation engine of the Hephaestus ecosystem.
 * Formulates the universal base directories and invokes modular stack architectures dynamically.
 *
 * @version 2.0
 */
public class ScaffoldManager {

    /**
     * Accepts the target project configurations and delegates specialized structural design
     * out to modular architecture kits.
     *
     * @param projectName The target directory name for the new project.
     * @param stack       The designated technology framework {@link ProjectStack}.
     */
    public void executeScaffold(String projectName, ProjectStack stack) {
        if (projectName == null || stack == null) {
            System.out.println("❌ Error: Cannot execute scaffold with invalid parameters.");
            return;
        }

        Path targetRoot = Path.of(projectName);
        System.out.println("📁 Initializing modular scaffolding execution at: " + targetRoot.toAbsolutePath());

        try {
            // Layer 2: Construct base universal infrastructure matching project parameters
            createBaseStructure(targetRoot, projectName);

            // Layer 3: Choose modular target execution based on polymorphism matching our enum
            StackKit selectedKit = switch (stack) {
                case SPRING -> new SpringKit();
                case REACT -> new ReactKit();
                case DJANGO -> new DjangoKit();
            };

            // Invoke the separate toolkit dynamically
            selectedKit.generateBlueprint(targetRoot, projectName);

            initializeGitRepository(targetRoot);

            System.out.println("🚀 Scaffolding for project '" + projectName + "' completed successfully!");

        } catch (IOException e) {
            System.out.println("❌ Critical Error during scaffolding execution: " + e.getMessage());

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("❌ Critical Error: Git initialization was interrupted.");
        }
    }

    private void createBaseStructure(Path targetRoot, String projectName) throws IOException {
        Files.createDirectories(targetRoot);

        Path readmePath = targetRoot.resolve("README.md");
        String readmeContent = """
                # %s
                
                This project was automatically scaffolded by **Hephaestus**.
                
                ## Getting Started
                Review the core architecture files generated in your modular repository.
                """.formatted(projectName);
        Files.writeString(readmePath, readmeContent, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);

        Path gitignorePath = targetRoot.resolve(".gitignore");
        String gitignoreContent = """
                # Compiled class files
                *.class
                out/
                target/
                .idea/
                
                # Dependencies
                node_modules/
                """;
        Files.writeString(gitignorePath, gitignoreContent, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
    }

    private void initializeGitRepository(Path targetRoot) throws IOException, InterruptedException {
        ProcessBuilder processBuilder = new ProcessBuilder("git", "init");
        processBuilder.directory(targetRoot.toFile());

        Process process = processBuilder.start();
        int exitCode = process.waitFor();

        if (exitCode != 0) {
            throw new IOException("Failed to initialize Git repository.");
        }
    }
}