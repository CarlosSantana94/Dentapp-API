package com.bucapps.dentapp.models.entity;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.sql.Timestamp;

@Getter
@Setter
@MappedSuperclass
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseEntity {

    @Id
    @GeneratedValue
    private Long id;

    @UpdateTimestamp
    @Column(name = "updated_date")
    private Timestamp updatedDate;

    @CreationTimestamp
    @Column(name = "created_date")
    private Timestamp createdDate;


}
