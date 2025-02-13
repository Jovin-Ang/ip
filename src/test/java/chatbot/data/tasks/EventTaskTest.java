package chatbot.data.tasks;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import chatbot.exception.IllegalTaskStateChangeException;

class EventTaskTest {
    private final EventTask task = new EventTask("Test Event Task", LocalDateTime.of(2025, 1, 1, 0, 0),
            LocalDateTime.of(2025, 1, 31, 23, 59));

    @Test
    @DisplayName("constructor throws exception for null event task")
    void constructorThrowsExceptionForNullTask() {
        assertThrows(IllegalArgumentException.class, () -> new EventTask(null, null, null));
    }

    @Test
    @DisplayName("constructor throws exception for empty event task")
    void constructorThrowsExceptionForEmptyTask() {
        assertThrows(IllegalArgumentException.class, () -> new EventTask("", LocalDateTime.of(2025, 1, 1, 0, 0),
                LocalDateTime.of(2025, 1, 31, 23, 59)));
    }

    @Test
    void markAsCompleted_notCompleteTask_success() {
        assertDoesNotThrow(task::markAsCompleted);
    }

    @Test
    void markAsIncomplete_notCompleteTask_exceptionThrown() {
        Exception exception = assertThrows(IllegalTaskStateChangeException.class, task::markAsIncomplete);
        assertEquals("Unable to change \"Test Event Task\" from \"incomplete\" to \"incomplete\"",
                exception.getMessage());
    }

    @Test
    void testDetailsConversion() {
        assertEquals("[E][ ] Test Event Task (from: Jan 1 2025, 12:00 am to: Jan 31 2025, 11:59 pm)",
                task.getDetails());
    }

    @Test
    void testStringConversion() {
        assertEquals("Test Event Task", task.toString());
    }

    @Nested
    @DisplayName("when task is complete")
    class CompleteToDoTask {

        @BeforeEach
        void markAsCompleted() throws IllegalTaskStateChangeException {
            task.markAsCompleted();
        }

        @Test
        void markAsIncomplete_completedTask_success() {
            assertDoesNotThrow(task::markAsIncomplete);
        }

        @Test
        void markAsCompleted_completedTask_exceptionThrown() {
            Exception exception = assertThrows(IllegalTaskStateChangeException.class, task::markAsCompleted);
            assertEquals("Unable to change \"Test Event Task\" from \"completed\" to \"completed\"",
                    exception.getMessage());
        }

        @Test
        void testDetailsConversion() {
            assertEquals("[E][X] Test Event Task (from: Jan 1 2025, 12:00 am to: Jan 31 2025, 11:59 pm)",
                    task.getDetails());
        }
    }
}
