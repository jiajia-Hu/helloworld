import  com.entity.*;
import sun.reflect.annotation.ExceptionProxy;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.*;

/**
 * Created by jiajia on 2017/7/20/020.
 */
public class ReadMdb {
    public void readFileACCESS(File mdbFile) {
        ReadMdb readMdb = new ReadMdb();
        List list = new ArrayList();
        Properties prop = new Properties();
        prop.put("charSet", "gb2312"); // 这里是解决中文乱码
        prop.put("user", "");
        prop.put("password", "");
        String url = "jdbc:ucanaccess://"
                + mdbFile.getAbsolutePath();
        Statement stmt = null;
        ResultSet rs = null;

        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            // 连接到mdb文件
        } catch (Exception e) {
            System.out.println(e);
        }
        try {
            Connection conn = DriverManager.getConnection(url, prop);
            ResultSet tables = conn.getMetaData().getTables(mdbFile.getAbsolutePath(), null, null, new String[]{"TABLE"});


            while (tables.next()) {
                String tableName = tables.getString(3);// getXXX can only be used once
                System.out.println(tableName);
                stmt = (Statement) conn.createStatement();
                rs = stmt.executeQuery("select * from " + tableName);
                ResultSetMetaData data = rs.getMetaData();
                if(tableName.equals("AreaCode")){
                    UseMongo useMongo = new UseMongo();
                    useMongo.mongo( readMdb.getAreaCodeData(rs,data));

                }else if(tableName.equals("BankType")){
                    UseMongo useMongo = new UseMongo();
                    useMongo.mongo(readMdb.getBankTypedata(rs,data));
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List getAreaCodeData(ResultSet rs ,ResultSetMetaData data) throws Exception{
        List list = new ArrayList();
        AreaCode areaCode =null;
        Map<String,String> map =null;
        while (rs.next()) {
            for (int i = 1; i <= data.getColumnCount(); i++) {
                map= new HashMap<String ,String>();
                map.put("ID",rs.getString("ID"));
                map.put("City",rs.getString("City"));
                map.put("Code",rs.getString("Code"));
              /*  areaCode =  new AreaCode();
                areaCode.setId(Integer.parseInt(rs.getString("ID")));
                areaCode.setCity(rs.getString("City"));
                areaCode.setCode(rs.getString("Code"));
                areaCode.setDistrict(rs.getString("District"));
                areaCode.setPhonecode(rs.getString("phonecode"));
                areaCode.setProvince(rs.getString("Province"));
                areaCode.setPostcode(rs.getString("postcode"));*/
            }
            list.add(map);
        }
        System.out.println("----------areacode-----------");
        return list;
    }



    public List getBankTypedata(ResultSet rs ,ResultSetMetaData data) throws Exception{
        List list = new ArrayList();
        BankType bankType = null;
        Map<String,String> map =null;
        while (rs.next()) {
            for (int i = 1; i <= data.getColumnCount(); i++) {
                map= new HashMap<String ,String>();
                map.put("ID",rs.getString("ID"));
                map.put("BankName",rs.getString("BankName"));
               /* bankType =  new BankType();
                bankType.setId(Integer.parseInt(rs.getString("ID")));
                bankType.setBankName(rs.getString("BankName"));*/
            }
            list.add(map);
        }
        System.out.println("----------BankType-----------");
        return list;
    }


}
