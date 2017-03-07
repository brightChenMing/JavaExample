package log.logback;

import ch.qos.logback.classic.Level;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Created by chenming on 17/3/2.
 */
public class Child {
    private static ch.qos.logback.classic.Logger logger = (ch.qos.logback.classic.Logger)
            LoggerFactory.getLogger(Child.class);
    public void setLevel(Level level){
        logger.setLevel(level);
    }

    public void printInfo(){
        logger.info("child info");
    }

    public void debug(){
        logger.info("child debug");
    }
}
