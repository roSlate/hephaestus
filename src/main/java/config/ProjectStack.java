package config;

import java.util.Optional;

/**
 * Enumerate the strictly supported technology stacks within the Hephaestus ecosystem.
 * This handles parsing user configuration strings into strict application types.
 *
 * @version 1.1
 */
public enum ProjectStack {
    SPRING,

    REACT,

    DJANGO;

    /**
     * Safely wraps the framework lookup inside an Optional container.
     * Eliminates raw null returns from the ecosystem to prevent NullPointerExceptions.
     *
     * @param text The raw string configuration input from the settings file (e.g., "spring").
     * @return An {@link Optional} containing the matched {@link ProjectStack}, or an empty Optional if unsupported.
     */
    public static Optional<ProjectStack> fromString(String text) {
        if (text == null || text.trim().isEmpty()) {
            return Optional.empty();
        }
        try {
            return Optional.of(ProjectStack.valueOf(text.trim().toUpperCase()));
        } catch (IllegalArgumentException e) {
            return Optional.empty();
        }
    }
}