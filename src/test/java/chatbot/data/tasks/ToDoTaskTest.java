package chatbot.data.tasks;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import chatbot.exception.IllegalTaskStateChangeException;

class ToDoTaskTest {
    private final ToDoTask task = new ToDoTask("Test ToDo Task");

    @Test
    @DisplayName("constructor throws exception for null todo task")
    void constructorThrowsExceptionForNullTask() {
        assertThrows(IllegalArgumentException.class, () -> new ToDoTask(null));
    }

    @Test
    @DisplayName("constructor throws exception for empty todo task")
    void constructorThrowsExceptionForEmptyTask() {
        assertThrows(IllegalArgumentException.class, () -> new ToDoTask(""));
    }

    @Test
    void markAsComplete_notCompleteTask_success() {
        assertDoesNotThrow(task::markAsCompleted);
    }

    @Test
    void markAsIncomplete_notCompleteTask_exceptionThrown() {
        Exception exception = assertThrows(IllegalTaskStateChangeException.class, task::markAsIncomplete);
        assertEquals("Unable to change \"Test ToDo Task\" from \"incomplete\" to \"incomplete\"",
                exception.getMessage());
    }

    @Test
    void testDetailsConversion() {
        assertEquals("[T][ ] Test ToDo Task", task.getDetails());
    }

    @Test
    void testStringConversion() {
        assertEquals("Test ToDo Task", task.toString());
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
            assertEquals("Unable to change \"Test ToDo Task\" from \"completed\" to \"completed\"",
                    exception.getMessage());
        }

        @Test
        void testDetailsConversion() {
            assertEquals("[T][X] Test ToDo Task", task.getDetails());
        }
    }
}
