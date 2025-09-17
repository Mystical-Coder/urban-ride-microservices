package com.urbanride.review.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;


@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
@Getter
@Setter
public abstract class BaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Identity means auto_increment
    protected Long id;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP) // this annotation tell spring about the format or date of object to be stored i.e. Date/Time ? Timestamp
    @CreatedDate //This annotation tells spring that only handle it for object created
    protected Date createdAt;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate //This annotation tells spring that only handle it for object update
    protected Date updatedAt;

}
