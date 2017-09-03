package lang;

import org.junit.Test;

/**
 * Created by chenming on 17/3/31.
 */
public class StringFormatterTest {
    @Test
    public void should_return_right_format(){
        System.out.println(String.format("服务蓝图目录下有%s个蓝图json文件",5));
    }
}
