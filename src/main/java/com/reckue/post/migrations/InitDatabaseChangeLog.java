package com.reckue.post.migrations;

import com.github.mongobee.changeset.ChangeLog;
import com.github.mongobee.changeset.ChangeSet;

@ChangeLog(order = "1")
@SuppressWarnings("unused")
public class InitDatabaseChangeLog {

    @ChangeSet(id = "1", author = "egnaf", order = "1")
    public void init() {
    }
}
