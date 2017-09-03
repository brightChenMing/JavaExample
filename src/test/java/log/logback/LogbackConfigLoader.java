package log.logback;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.core.joran.spi.JoranException;
import ch.qos.logback.core.util.StatusPrinter;
import org.slf4j.LoggerFactory;

import java.io.File;

/**
 * Created by chenming on 17/3/15.
 */
public class LogbackConfigLoader {
    public static void load(String path){
        File configFile=new File(path);
        if(!configFile.exists()||!configFile.isFile()){
            throw new IllegalArgumentException("配置文件不存在或不正确");
        }
        LoggerContext context= (LoggerContext)LoggerFactory.getILoggerFactory();
        JoranConfigurator joranConfigurator=new JoranConfigurator();
        joranConfigurator.setContext(context);
        context.reset();
        try {
            joranConfigurator.doConfigure(configFile);
        } catch (JoranException e) {
            e.printStackTrace();
        }
        StatusPrinter.printInCaseOfErrorsOrWarnings(context);
    }
}
