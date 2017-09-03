package lang;

import org.junit.Test;

import java.io.UnsupportedEncodingException;

/**
 * Created by chenming on 17/3/9.
 */
public class TestCharset {
    @Test
    public void test() throws UnsupportedEncodingException {
        String s="\\u2f\\u66\\u74\\u70\\u75\\u73\\u65\\u72\\u2f\\u61\\u6e\\u6f\\u6e\\u79\\u6d\\u6f\\u75\\u73";
        System.out.println(new String(s.getBytes("UTF-8"), "ascii"));
    }
}
