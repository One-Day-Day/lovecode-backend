package com.lovecode.user.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.sql.Timestamp;

@Entity
public class User extends BaseEntity {

    @Column(name = "user_name")
    private String name;

    @Column(name = "email")
    private String email;

    /**
     * Y 表示 Active
     * N 表示 Deactivate
     */
    @Column(name = "user_status")
    private Character status;

    @Column(
            name = "last_login_time"
    )
    private Timestamp lastLoginTime;

    public User() {
        super();
    }

    public User(Timestamp createdAt, Timestamp updatedAt,
                String createdBy, String updatedBy,
                Integer id, String name,
                String email, Character status,
                Timestamp lastLoginTime) {
        super(createdAt, updatedAt, createdBy, updatedBy);
        this.id = id;
        this.name = name;
        this.email = email;
        this.status = status;
        this.lastLoginTime = lastLoginTime;
    }

}
