package com.lovecode.user.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;


@Data
@Entity
public class BaseEntity {
    @Column(
            name = "created_at"
    )
    protected Timestamp createdAt;
    @Column(
            name = "updated_at"
    )
    protected Timestamp updatedAt;
    @Column(
            name = "created_by"
    )
    protected String createdBy;
    @Column(
            name = "updated_by"
    )
    protected String updatedBy;
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    @Column(
            name = "id"
    )
    protected Integer id;

    public BaseEntity() {
    }

    public BaseEntity(Timestamp createdAt, Timestamp updatedAt, String createdBy, String updatedBy) {
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
    }
}
