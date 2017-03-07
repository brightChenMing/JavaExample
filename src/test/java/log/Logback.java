package log;

import ch.qos.logback.classic.Level;
import log.logback.Child;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * Created by chenming on 17/3/2.
 */
public class Logback {
    private ch.qos.logback.classic.Logger logger;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Before
    public void setUpStreams() {
        logger = (ch.qos.logback.classic.Logger)
                LoggerFactory.getLogger("log.logback");
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void cleanUpStreams() {
        System.setOut(null);
        System.setErr(null);
    }
    @Test
    public void testBaseOpreation() {
        logger.setLevel(Level.WARN);
        logger.error("error");
        Assert.assertTrue(outContent.toString().contains("error"));
        logger.warn("warn");
        Assert.assertTrue(outContent.toString().contains("warn"));
        logger.info("info");
        Assert.assertFalse(outContent.toString().contains("info"));
        logger.debug("debug");
        Assert.assertFalse(outContent.toString().contains("debug"));
    }

    @Test
    public void child_cannot_print_info_when_parent_logger_level_is_warn() {
        logger.setLevel(Level.WARN);
        Child child = new Child();
        child.printInfo();
        Assert.assertFalse(outContent.toString().contains("info"));
    }

    @Test
    public void child_print_info_when_set_child_logger_level_to_info() {
        Child child = new Child();
        child.setLevel(Level.INFO);
        child.printInfo();
        Assert.assertTrue(outContent.toString().contains("info"));
    }

    public void format_output(){
        logger.info("this is {} and {}","first","second");
        Assert.assertTrue(outContent.toString().contains("this is first and second"));
    }
}
