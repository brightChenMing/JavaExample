package io;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Created by chenming on 17/3/14.
 */
@Slf4j
public class Regex {
    @Test
    public void testIgnoreCaseSenstive() {
        Pattern p = Pattern.compile(".*dockerfile$", Pattern.CASE_INSENSITIVE);
        assertThat(p.matcher("dockerfile").matches(), is(true));
        assertThat(p.matcher("dockerFile").matches(), is(true));
        assertThat(p.matcher("dockerFiles").matches(), is(false));
        assertThat(p.matcher("sdfdockerFile").matches(), is(true));
    }

    @Test
    public void testSpaceMatch() {
        assertThat("   FROM".matches("\\s*FROM"), is(true));
        assertThat("FROM".matches("\\s*FROM"), is(true));
    }

    @Test
    public void test() {
        String sql = "select * from build " +
                "left join passRatio on build.id=passRatio.buildId left join coverageRatio on build.id=coverageRatio.buildId left join staticCheck on build.id=staticCheck.buildId";
      Pattern pattern=Pattern.compile(".*(from)(.*)(left join)");
        Matcher matcher=pattern.matcher(sql);
        if(matcher.find()){
            for(int i=0;i<matcher.groupCount();i++){
                log.info(matcher.group(i));
            }
        }
    }

    @Test
    public void should_return_right_group_content(){
        String regex="\"image\"\\s*:\\s*.*:v(.+)\"";
        String s="\"image\": \"/ranoss/sus-adapter-vlte-sl:v1.17.10B04\",";
        Pattern pattern=Pattern.compile(regex);
        Matcher matcher=pattern.matcher(s);
        if(matcher.find()){
            for(int i=0;i<matcher.groupCount();i++){
                System.out.println(matcher.group(i));
            }
        }
    }

    @Test
    public void test_vnfd_name(){
        String s="ztevnf__sus-vnfd.yaml";

        String s2="ns0__tosca.nodes.paas.Service.yaml";
        String regex=".*__[^\\.]+\\.yaml$";
        System.out.println(s.matches(regex));
        System.out.println(s2.matches(regex));
    }


}
