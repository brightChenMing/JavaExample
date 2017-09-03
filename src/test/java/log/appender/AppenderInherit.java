package log.appender;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * Created by chenming on 17/3/15.
 */
@Slf4j
public class AppenderInherit {
    @Test
    public void test(){
        log.debug("test error3");
    }
}
