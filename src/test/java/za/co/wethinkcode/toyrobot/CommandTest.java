package za.co.wethinkcode.toyrobot;

import org.junit.jupiter.api.Test;
import za.co.wethinkcode.toyrobot.Robot;
import za.co.wethinkcode.toyrobot.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CommandTest {

    @Test
    void testExecuteShutdown() {
        Robot robot = new Robot("CrashTestDummy");
        Command shutdown = Command.create("shutdown");
        assertTrue(shutdown.execute(robot));
        assertEquals("Shutting down...", robot.getStatus());
    }

    @Test
    void testForwardCommand() {
        ForwardCommand test = new ForwardCommand("100");
        assertEquals("forward", test.getName());
        assertEquals("100", test.getArgument());
    }

    @Test
    void testShutdownCommand() {
        Command test = new ShutdownCommand();
        assertEquals("off", test.getName());
    }

    @Test
    void testExecuteForward() {
        Robot robot = new Robot("CrashTestDummy");
        Command forward100 = Command.create("forward 10");
        assertTrue(forward100.execute(robot));
        Position expectedPosition = new Position(Robot.CENTRE.getX(), Robot.CENTRE.getY() + 10);
        assertEquals(expectedPosition, robot.getPosition());
        assertEquals("Moved forward by 10 steps.", robot.getStatus());
    }

    @Test
    void testHelp() {
        Robot robot = new Robot("CrashTestDummy");
        Command help = Command.create("help");
        assertTrue(help.execute(robot));
        assertEquals("I can understand these commands:\n" +
                "OFF  - Shut down robot\n" +
                "HELP - provide information about commands\n" +
                "FORWARD - move forward by specified number of steps, e.g. 'FORWARD 10'", robot.getStatus());
    }

    @Test
    void TestHelpName() {
        Command test = new HelpCommand();
        assertEquals("help", test.getName());
    }

    @Test
    void dump() {
        Robot robot = new Robot("CrashTestDummy");
        assertEquals("[0,0] {NORTH} CrashTestDummy> Ready", robot.toString());
    }
}
