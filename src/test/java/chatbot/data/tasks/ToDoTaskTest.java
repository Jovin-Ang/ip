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
    void completeTask_notCompleteTask_success() {
        assertDoesNotThrow(task::complete);
    }

    @Test
    void incompleteTask_notCompleteTask_exceptionThrown() {
        Exception exception = assertThrows(IllegalTaskStateChangeException.class, task::incomplete);
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
            assertEquals("Unable to change \"Test ToDo Task\" from \"completed\" to \"completed\"",
                    exception.getMessage());
        }

        @Test
        void testDetailsConversion() {
            assertEquals("[T][X] Test ToDo Task", task.getDetails());
        }
    }
}
