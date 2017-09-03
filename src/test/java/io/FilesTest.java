package io;

import com.google.common.io.Files;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Created by chenming on 17/3/8.
 */

public class FilesTest {
    Logger logger= LoggerFactory.getLogger(FilesTest.class);
    @Test
    public void fetchExtension() throws IOException {
        File f=new File("1.tar.gz");
        f.createNewFile();
        logger.info(Files.getFileExtension(f.getName()));
        assertThat(Files.getFileExtension(f.getName()),is("gz"));
    }




}
