package spiderMans.mongodb;

import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.sapling.spiderMans.pojo.test.User;

public class InsertTest extends MongoDbBaseTest   {
    @Autowired
    private MongoTemplate mongoTemplate;
    @Test
    public void mongoDbInsert(){
        mongoTemplate.insert(new User(new Date().getTime(),"zhangsan","20","男"));
    }
}
