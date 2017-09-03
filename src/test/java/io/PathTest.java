package io;

import org.junit.Test;

import java.nio.file.Path;
import java.nio.file.Paths;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Created by chenming on 17/4/20.
 */
public class PathTest {
    @Test
    public void test_path_resolve(){
        Path parent= Paths.get("/","Users","chenming");
        Path child=parent.resolve("test");
        assertThat(child.toAbsolutePath().toString(),is("/Users/chenming/test"));
    }
}
