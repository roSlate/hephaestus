package config;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class ProjectStackTest {

    @Test
    void fromString_recognizesLowercaseSpring() {
        Optional<ProjectStack> result = ProjectStack.fromString("spring");
        assertTrue(result.isPresent());
        assertEquals(ProjectStack.SPRING, result.get());
    }

    @Test
    void fromString_isCaseInsensitive() {
        Optional<ProjectStack> result = ProjectStack.fromString("REACT");
        assertTrue(result.isPresent());
        assertEquals(ProjectStack.REACT, result.get());
    }

    @Test
    void fromString_trimsWhitespace() {
        Optional<ProjectStack> result = ProjectStack.fromString("  django  ");
        assertTrue(result.isPresent());
        assertEquals(ProjectStack.DJANGO, result.get());
    }

    @Test
    void fromString_returnsEmptyForUnsupportedValue() {
        Optional<ProjectStack> result = ProjectStack.fromString("flask");
        assertTrue(result.isEmpty());
    }

    @Test
    void fromString_returnsEmptyForNull() {
        Optional<ProjectStack> result = ProjectStack.fromString(null);
        assertTrue(result.isEmpty());
    }

    @Test
    void fromString_returnsEmptyForBlankString() {
        Optional<ProjectStack> result = ProjectStack.fromString("   ");
        assertTrue(result.isEmpty());
    }
}