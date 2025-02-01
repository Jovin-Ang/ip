package chatbot.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.LinkedHashMap;

import org.junit.jupiter.api.Test;

import chatbot.ui.IoHandlerStub;

class HelpCommandTest {
    private final IoHandlerStub ioHandlerStub = new IoHandlerStub();

    /**
     * Returns a LinkedHashMap of dummy commands for testing purposes.
     *
     * @return A LinkedHashMap of dummy commands.
     */
    private static LinkedHashMap<String, Command> getDummyCommands() {
        LinkedHashMap<String, Command> commands = new LinkedHashMap<>();
        commands.put("test1", new Command("test1", "test command 1", "test1") {
            @Override
            public void execute(String arguments) {}
        });
        commands.put("test2", new Command("test2", "test command 2", "test2 <arguments>") {
            @Override
            public void execute(String arguments) {}
        });
        commands.put("test3", new Command("test3", "test command 3", "test3 <arguments> [optional]") {
            @Override
            public void execute(String arguments) {}
        });
        return commands;
    }

    @Test
    void testExecute_noCommands() {
        // Setup
        HelpCommand helpCommand = new HelpCommand(ioHandlerStub, new LinkedHashMap<>());

        // Execute
        helpCommand.execute("");

        // Verify
        assertEquals("Here is what I can do:\n>> Opps, no commands available <<", ioHandlerStub.getLastMessageSent());
    }

    @Test
    void testExecute_multipleCommands() {
        // Setup dummy commands
        LinkedHashMap<String, Command> commands = getDummyCommands();
        HelpCommand helpCommand = new HelpCommand(ioHandlerStub, commands);

        // Execute
        helpCommand.execute("");

        // Verify
        assertEquals("""
                Here is what I can do:
                `test1` test command 1
                `test2 <arguments>` test command 2
                `test3 <arguments> [optional]` test command 3""", ioHandlerStub.getLastMessageSent());
    }
}
