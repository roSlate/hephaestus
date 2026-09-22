import config.Settings;
import scaffold.ScaffoldManager;

/**
 * The core orchestration engine and entry execution thread for the Hephaestus automation application.
 * Manages the sequential workflow from configuration ingestion down to the final folder architecture generation.
 *
 * @version 2.0
 */
public class Main {

    /**
     * The runtime execution entry point. Bootstraps the scaffolding process by triggering the configuration loader.
     *
     * @param args Command-line execution arguments passed dynamically at runtime (currently unutilized).
     */
    public static void main(String[] args) {
        System.out.println("⚡ Booting Hephaestus...");

        // 1. Instantiate and invoke our configuration parsing component
        Settings settings = new Settings();
        settings.loadSettings();

        // 2. Ensure configurations are perfectly loaded before continuing
        if (settings.getProjectName() != null && settings.getProjectStack() != null) {
            System.out.println("✅ Success! Loaded settings from file.");
            System.out.println("Project Name: " + settings.getProjectName());
            System.out.println("Project Stack: " + settings.getProjectStack());
            System.out.println("------------------------------------------------");

            // 3. Instantiate the scaffold engine and build
            ScaffoldManager scaffold = new ScaffoldManager();
            scaffold.executeScaffold(settings.getProjectName(), settings.getProjectStack());
        }
    }
}