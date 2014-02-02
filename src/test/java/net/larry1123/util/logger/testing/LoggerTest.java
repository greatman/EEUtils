package net.larry1123.util.logger.testing;

import net.larry1123.util.logger.EELogManager;
import net.larry1123.util.logger.EELogger;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;

/**
 * @author Larry1123
 * @since 1/30/14 - 7:33 AM
 */
public class LoggerTest {

    @Test
    public void testMakingLogger() {
        EELogManager.setLoggerSettings(new TestLoggerSettings());
        Assert.assertNotNull("Failed to create Sub-Logger.", EELogManager.getLogger("EEUtilTest"));
    }

    @Test
    public void testMakingSubLogger() {
        EELogManager.setLoggerSettings(new TestLoggerSettings());
        EELogger main = EELogManager.getLogger("EEUtilTest");
        Assert.assertNotNull("Failed to create Sub-Logger.", EELogManager.getSubLogger("SubTest", main));
    }

    @Test
    public void testFileCreation() {
        EELogManager.setLoggerSettings(new TestLoggerSettings());
        EELogger makeAFile = EELogManager.getLogger("EEUtilFileTest");
        File logFile = new File(makeAFile.logPath + "." + EELogManager.getLoggerSettings().getFileType());
        Assert.assertTrue("Logger File failed to be made.", logFile.exists());
    }

    @Test
    public void testLevelFileCreation() {
        EELogManager.setLoggerSettings(new TestLoggerSettings());
        EELogger makeAFile = EELogManager.getLogger("EEUtilFileTest");
        makeAFile.addLoggerLevelWFile("LevelTest");
        File logFile = new File(makeAFile.logPath + "-LevelTest." + EELogManager.getLoggerSettings().getFileType());
        Assert.assertTrue("Level File failed to be made.", logFile.exists());
    }

}
