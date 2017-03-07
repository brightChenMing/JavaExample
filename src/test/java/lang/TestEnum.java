package lang;

import org.junit.Test;

/**
 * Created by chenming on 17/3/7.
 */
public class TestEnum {
    public enum MODULE{
        BP("bp"),IMAGE("image");
        private String moduleName;
        private MODULE(String moduleName){
            this.moduleName=moduleName;
        }

        @Override
        public String toString(){
            return moduleName;
        }
    }

    @Test
    public void test(){
        System.out.println(MODULE.BP.toString());
    }
}
