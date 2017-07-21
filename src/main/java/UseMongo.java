import com.mongodb.*;
import com.mongodb.util.JSON;

import java.util.List;
import net.sf.json.*;
/**
 * Created by jiajia on 2017/7/20/020.
 */
public class UseMongo {

    //插入文档操作
    public void mongo(List list) throws Exception{
        Mongo mongo = new Mongo();
        DB db =  mongo.getDB("test");
        DBCollection dbCollection = db.getCollection("site");
        //JSONArray jsonArray = JSONArray.fromObject(list);
        BasicDBObject doc = new BasicDBObject("list",list);
        dbCollection.save(doc);
    }


    public void searchData() {
        MongoClient mongoClient = new MongoClient("localhost", 27017);
        DB db = mongoClient.getDB("test");
        System.out.println("Connect to database successfully");
        DBCollection coll = db.getCollection("site");
        System.out.println("Collection site selected successfully");
        DBCursor cursor = coll.find();
        while (cursor.hasNext()) {
            System.out.println(cursor.next());
        }
    }
}
