package compress;


import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.model.ZipParameters;
import org.junit.Test;

import java.io.File;

/**
 * Created by chenming on 17/3/17.
 */
public class CompressTest {
    @Test
    public void test() throws Exception {
        String folder = "/var/folders/8v/f4vmzmyx63n79fp0k2zsl28c0000gn/T/" +
                "1489732530124-0/ft_on_paas/pm-offlineanalys/testcase";
        ZipFile zipFile = new ZipFile("/tmp/test.zip");
        zipFile.setFileNameCharset("UTF-8");
        zipFile.addFolder(folder, new ZipParameters());
    }
}
