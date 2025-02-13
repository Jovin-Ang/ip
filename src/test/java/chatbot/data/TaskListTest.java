package chatbot.data;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import chatbot.data.tasks.DeadlineTask;
import chatbot.data.tasks.EventTask;
import chatbot.data.tasks.ToDoTask;
import chatbot.exception.IllegalTaskStateChangeException;

class TaskListTest {
    private TaskList taskList;

    @BeforeEach
    void setup() {
        taskList = new TaskList();
    }

    @Test
    void addTask_success() {
        assertDoesNotThrow(() -> taskList.addTask(new ToDoTask("Test ToDo Task")));
        assertEquals(1, taskList.getTotalTasks());
        assertEquals("1. Test ToDo Task", taskList.getTaskDescriptions().trim());
    }

    @Test
    void removeTask_emptyList_exceptionThrown() {
        assertThrows(IndexOutOfBoundsException.class, () -> taskList.removeTask(0));
    }

    @Test
    void markTaskAsCompleted_emptyList_exceptionThrown() {
        assertThrows(IndexOutOfBoundsException.class, () -> taskList.markTaskAsCompleted(0));
    }

    @Test
    void markTaskAsIncomplete_emptyList_exceptionThrown() {
        assertThrows(IndexOutOfBoundsException.class, () -> taskList.markTaskAsIncomplete(0));
    }

    @Test
    void isEmpty() {
        assertEquals(0, taskList.getTotalTasks());
    }

    @Test
    void testDescriptionConversion_emptyList() {
        assertEquals("No tasks, yay!", taskList.getTaskDescriptions());
    }

    @Test
    void testDetailsConversion_emptyList() {
        assertEquals("No tasks, yay!", taskList.getTaskDetails());
    }

    @Test
    void testMatchingTaskDetailsConversion_emptyList() {
        assertEquals("No matching tasks found.", taskList.getMatchingTaskDetails("Test"));
    }

    @Test
    void testStringConversion_emptyList() {
        assertEquals("No tasks, yay!", taskList.toString());
    }

    @Nested
    @DisplayName("with tasks")
    class WithTasks {

        @BeforeEach
        void addTasks() throws IllegalTaskStateChangeException {
            taskList.addTask(new ToDoTask("Test ToDo Task"));
            DeadlineTask task2 = new DeadlineTask("Test Deadline Task", LocalDateTime.of(2025, 1, 30, 23, 59));
            task2.markAsCompleted();
            taskList.addTask(task2);
            taskList.addTask(new EventTask("Test Event Task", LocalDateTime.of(2025, 1, 1, 0, 0),
                    LocalDateTime.of(2025, 1, 31, 23, 59)));
        }

        @Test
        void addTask_success() {
            assertDoesNotThrow(() -> taskList.addTask(new ToDoTask("Test ToDo Task 2")));
            assertEquals(4, taskList.getTotalTasks());
            String details = taskList.getTaskDetails();
            assertTrue(details.contains("4. [T][ ] Test ToDo Task 2"));
        }

        @Test
        void removeTask_validIndex_success() {
            assertDoesNotThrow(() -> taskList.removeTask(1));
            assertEquals(2, taskList.getTotalTasks());
        }

        @Test
        void removeTask_invalidIndex_exceptionThrown() {
            assertThrows(IndexOutOfBoundsException.class, () -> taskList.removeTask(3));
        }

        @Test
        void markTaskAsCompleted_validIndexIncompleteTask_success() {
            assertDoesNotThrow(() -> taskList.markTaskAsCompleted(0));
            String details = taskList.getTaskDetails();
            assertTrue(details.contains("[X] Test ToDo Task"));
        }

        @Test
        void markTaskAsCompleted_validIndexCompleteTask_exceptionThrown() {
            assertThrows(IllegalTaskStateChangeException.class, () -> taskList.markTaskAsCompleted(1));
        }

        @Test
        void markTaskAsIncomplete_validIndexCompleteTask_success() {
            assertDoesNotThrow(() -> taskList.markTaskAsIncomplete(1));
            String details = taskList.getTaskDetails();
            assertTrue(details.contains("[ ] Test Deadline Task"));
        }

        @Test
        void markTaskAsIncomplete_validIndexIncompleteTask_exceptionThrown() {
            assertThrows(IllegalTaskStateChangeException.class, () -> taskList.markTaskAsIncomplete(0));
        }

        @Test
        void containsTasks() {
            assertEquals(3, taskList.getTotalTasks());
        }

        @Test
        void testDescriptionConversion() {
            assertEquals("1. Test ToDo Task\n2. Test Deadline Task\n3. Test Event Task",
                    taskList.getTaskDescriptions());
        }

        @Test
        void testDetailsConversion() {
            assertEquals("""
                            1. [T][ ] Test ToDo Task
                            2. [D][X] Test Deadline Task (by: Jan 30 2025, 11:59 pm)
                            3. [E][ ] Test Event Task (from: Jan 1 2025, 12:00 am to: Jan 31 2025, 11:59 pm)""",
                    taskList.getTaskDetails());
        }

        @Test
        void testMatchingTaskDetailsConversion_includedKeyword() {
            assertEquals("2. [D][X] Test Deadline Task (by: Jan 30 2025, 11:59 pm)",
                    taskList.getMatchingTaskDetails("Deadline"));
        }

        @Test
        void testMatchingTaskDetailsConversion_excludedKeyword() {
            assertEquals("No matching tasks found.", taskList.getMatchingTaskDetails("abcdef"));
        }

        @Test
        void testStringConversion() {
            assertEquals("1. Test ToDo Task\n2. Test Deadline Task\n3. Test Event Task", taskList.toString());
        }
    }
}
