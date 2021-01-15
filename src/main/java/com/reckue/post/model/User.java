package com.reckue.post.model;

import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class User {

    private String id;

    private String nickName;

    private String firstName;

    private String lastName;

    private Set<Role> roles;

}
