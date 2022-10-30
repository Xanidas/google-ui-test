package org.zawada.jan.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import java.io.File;
import java.io.IOException;
import org.apache.logging.log4j.Logger;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;

public class ScreenshotHelper {
    private static Logger logger = LogManager.getLogger(ScreenshotHelper.class);
    
    public static void takeScreenshot(WebDriver driver, String pathToFile, String fileName) {
        TakesScreenshot screanShot = ((TakesScreenshot) driver);
        File scrShotFile = screanShot.getScreenshotAs(OutputType.FILE);
        File destFile = new File(pathToFile.concat("/").concat(fileName));
        try {
            FileUtils.copyFile(scrShotFile, destFile);
        } catch (IOException ioe) {
            logger.error(ioe.getMessage());
        } catch (NullPointerException npe) {
            logger.error(npe.getMessage());
        }
        
    }
}
