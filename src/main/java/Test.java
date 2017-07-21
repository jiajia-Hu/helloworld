import java.io.File;
import java.util.List;

/**
 * Created by jiajia on 2017/7/20/020.
 */
public class Test {

    public static void main(String args[]) throws Exception{
        ReadMdb readMdb = new ReadMdb();
        UseMongo useMongo = new UseMongo();
        readMdb.readFileACCESS(new File("D://sifan//Commons.mdb"));
        useMongo.searchData();
    }

}
