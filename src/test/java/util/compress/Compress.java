package util.compress;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveInputStream;
import org.apache.commons.compress.compressors.gzip.GzipCompressorInputStream;
import org.junit.Test;

import java.io.*;

/**
 * Created by chenming on 17/3/8.
 */
@Slf4j
public class Compress {
    public static int BUFFER=8092;
    private static void unTarGz(String input,String output) throws IOException {
        if(!output.endsWith("/")){
            output+="/";
        }
        FileInputStream fin = new FileInputStream(input);
        BufferedInputStream in = new BufferedInputStream(fin);
        GzipCompressorInputStream gzIn = new GzipCompressorInputStream(in);
        TarArchiveInputStream tarIn = new TarArchiveInputStream(gzIn);
        TarArchiveEntry entry = null;
        while ((entry = (TarArchiveEntry) tarIn.getNextEntry()) != null) {
            log.debug("Extracting: " + entry.getName());
            if (entry.isDirectory()) {
                File f = new File(output + entry.getName());
                f.mkdirs();
            } else {
                int count;
                byte data[] = new byte[BUFFER];

                FileOutputStream fos = new FileOutputStream(output
                        + entry.getName());
                BufferedOutputStream dest = new BufferedOutputStream(fos,
                        BUFFER);
                while ((count = tarIn.read(data, 0, BUFFER)) != -1) {
                    dest.write(data, 0, count);
                }
                dest.close();
            }
        }
        tarIn.close();
        log.info("untar {} to {} completed successfully!!",input,output);
    }

    @Test
    public void testUnTarGz() throws IOException {
        File input=new File(this.getClass().getResource("/test.tar.gz").getPath());
        File output=new File(this.getClass().getResource("/").getPath());
        unTarGz(input.getAbsolutePath(),output.getAbsolutePath());
    }
}
