package com.reckue.post.db.migrations;

import com.github.mongobee.changeset.ChangeLog;
import com.github.mongobee.changeset.ChangeSet;
import org.springframework.data.mongodb.core.MongoTemplate;

@ChangeLog(order = "001")
public class InitialSetup {

    @ChangeSet(order = "001", author = "Artur", id = "initial setup")
    public void setupUsers(MongoTemplate mongoTemplate) {
    }
}
