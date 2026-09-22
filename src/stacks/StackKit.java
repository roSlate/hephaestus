package stacks;

import java.io.IOException;
import java.nio.file.Path;

/**
 * Defines the strict operational contract for all framework scaffolding generation toolkits.
 * Every supported architectural stack must implement this interface.
 *
 * @version 1.0
 */
public interface StackKit {
    /**
     * Executes the generation of framework-specific boilerplate directories and files.
     *
     * @param targetRoot The target path to the root folder of the project being generated.
     * @throws IOException If any physical disk write processes fail.
     */
    void generateBlueprint(Path targetRoot) throws IOException;
}