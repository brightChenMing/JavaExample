/**
 * Created by chenming on 17/2/21.
 */
public class CharsetUtils {
    public  static void getDefaultCharset(){
        System.out.println(System.getProperty("file.encoding"));
    }

    public static void main(String[] args){
        getDefaultCharset();
    }

}
