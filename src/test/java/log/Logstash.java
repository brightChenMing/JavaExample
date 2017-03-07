package log;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by chenming on 17/3/3.
 */
public class Logstash {
    Logger logger= LoggerFactory.getLogger(Logstash.class);
    @Test
    public void test(){
        logger.error("error");
    }

}
