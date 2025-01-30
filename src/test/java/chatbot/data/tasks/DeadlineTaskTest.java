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

class DeadlineTaskTest {
    private final DeadlineTask task = new DeadlineTask("Test Deadline Task", LocalDateTime.of(2025, 1, 30, 23, 59));

    @Test
    @DisplayName("constructor throws exception for null deadline task")
    void constructorThrowsExceptionForNullTask() {
        assertThrows(IllegalArgumentException.class, () -> new DeadlineTask(null, null));
    }

    @Test
    @DisplayName("constructor throws exception for empty deadline task")
    void constructorThrowsExceptionForEmptyTask() {
        assertThrows(IllegalArgumentException.class, () -> new DeadlineTask("", LocalDateTime.of(2025, 1, 30, 23, 59)));
    }

    @Test
    void completeTask_notCompleteTask_success() {
        assertDoesNotThrow(task::complete);
    }

    @Test
    void incompleteTask_notCompleteTask_exceptionThrown() {
        Exception exception = assertThrows(IllegalTaskStateChangeException.class, task::incomplete);
        assertEquals("Unable to change \"Test Deadline Task\" from \"incomplete\" to \"incomplete\"",
                exception.getMessage());
    }

    @Test
    void testDetailsConversion() {
        assertEquals("[D][ ] Test Deadline Task (by: Jan 30 2025, 11:59 pm)", task.getDetails());
    }

    @Test
    void testStringConversion() {
        assertEquals("Test Deadline Task", task.toString());
    }

    @Nested
    @DisplayName("when task is complete")
    class CompleteToDoTask {

        @BeforeEach
        void completeTask() throws IllegalTaskStateChangeException {
            task.complete();
        }

        @Test
        void incompleteTask_completedTask_success() {
            assertDoesNotThrow(task::incomplete);
        }

        @Test
        void completeTask_completedTask_exceptionThrown() {
            Exception exception = assertThrows(IllegalTaskStateChangeException.class, task::complete);
            assertEquals("Unable to change \"Test Deadline Task\" from \"completed\" to \"completed\"",
                    exception.getMessage());
        }

        @Test
        void testDetailsConversion() {
            assertEquals("[D][X] Test Deadline Task (by: Jan 30 2025, 11:59 pm)", task.getDetails());
        }
    }
}
