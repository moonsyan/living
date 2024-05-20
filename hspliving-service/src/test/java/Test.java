import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Ming
 * @Description
 * @projectName living
 * @create 2024-05-02 17:33
 */
public class Test {
    public static void main(String[] args) {
        String dir = "hspliving-service/";
        String format = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        dir = dir + format + "/";
        System.out.println(dir);
    }
}
