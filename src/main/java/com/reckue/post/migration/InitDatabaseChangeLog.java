package com.reckue.post.migration;

import com.github.mongobee.changeset.ChangeLog;
import com.github.mongobee.changeset.ChangeSet;

@ChangeLog(order = "1")
@SuppressWarnings("unused")
public class InitDatabaseChangeLog {

    @ChangeSet(id = "1", author = "mamadaliev", order = "1")
    public void init() {
    }
}
