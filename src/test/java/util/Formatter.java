package util;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Created by chenming on 17/3/7.
 */

public class Formatter {
    @Test
    public void string_format(){
        String command="-o=upload -i=%s -m=%s -t=service -n=%s -v=%s " +
                "-p=%s -b=no";
        assertThat(String.format(command,"tenant","bp","serviceName","version","path"),
                is("-o=upload -i=tenant -m=bp -t=service -n=serviceName -v=version -p=path -b=no"));
    }
}
