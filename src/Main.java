import config.Settings;

/**
 * The core orchestration engine and entry execution thread for the Hephaestus automation application.
 * Manages the sequential workflow from configuration ingestion down to the final folder architecture generation.
 * @version 1.0
 */
public class Main {

    /**
     * The runtime execution entry point. Bootstraps the scaffolding process by triggering the configuration loader.
     *
     * @param args Command-line execution arguments passed dynamically at runtime (currently unutilized).
     */
    public static void main(String[] args) {
        System.out.println("⚡ Booting Hephaestus...");

        // Instantiate and invoke our newly structured configuration component
        Settings settings = new Settings();
        settings.loadSettings();

        // Safe runtime block ensuring downstream components only invoke under pristine configurations
        if (settings.getProjectName() != null && settings.getProjectStack() != null) {
            System.out.println("✅ Success! Loaded settings from file.");
            System.out.println("Project Name: " + settings.getProjectName());
            System.out.println("Project Stack: " + settings.getProjectStack());
        }
    }
}
