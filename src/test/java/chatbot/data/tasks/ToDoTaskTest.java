package chatbot.data.tasks;

import chatbot.exception.IllegalTaskStateChangeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTaskTest {

    @Test
    void constructorThrowsExceptionForNullTask() {
        assertThrows(IllegalArgumentException.class, () -> new ToDoTask(null));
    }

    @Test
    void constructorThrowsExceptionForEmptyTask() {
        assertThrows(IllegalArgumentException.class, () -> new ToDoTask(""));
    }

    @Test
    void completeTask_notCompleteTask_success() {
        ToDoTask task = new ToDoTask("Sample Task");
        assertDoesNotThrow(task::complete);
    }

    @Test
    void incompleteTask_completedTask_success() throws IllegalTaskStateChangeException {
        ToDoTask task = new ToDoTask("Sample Task");
        task.complete();
        assertDoesNotThrow(task::incomplete);
    }

    @Test
    void completeTask_completedTask_exceptionThrown() throws IllegalTaskStateChangeException {
        ToDoTask task = new ToDoTask("Sample Task");
        task.complete();
        Exception exception = assertThrows(IllegalTaskStateChangeException.class, task::complete);
        assertEquals("Unable to change \"Sample Task\" from \"completed\" to \"completed\"", exception.getMessage());
    }

    @Test
    void incompleteTask_notCompleteTask_exceptionThrown() {
        ToDoTask task = new ToDoTask("Sample Task");
        Exception exception = assertThrows(IllegalTaskStateChangeException.class, task::incomplete);
        assertEquals("Unable to change \"Sample Task\" from \"incomplete\" to \"incomplete\"", exception.getMessage());
    }

    @Test
    void testDetailsConversion() throws IllegalTaskStateChangeException {
        ToDoTask task = new ToDoTask("Sample Task");
        assertEquals("[T][ ] Sample Task", task.getDetails());
        task.complete();
        assertEquals("[T][X] Sample Task", task.getDetails());
    }

    @Test
    void testStringConversion() {
        ToDoTask task = new ToDoTask("Sample Task");
        assertEquals("Sample Task", task.toString());
    }
}