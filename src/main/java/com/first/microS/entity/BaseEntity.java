package com.first.microS.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@MappedSuperclass
//@MappedSuperClass is used to denote a class that this class will work as super class.
//We need this when we have same columns in different entities.
@Getter
@Setter
public class BaseEntity {
    @CreationTimestamp
    @Column(updatable = false)//The purpose is the column will not be updated when ever
    //the entity is updated
    private LocalDateTime createdAt;
    private String createdBy;
    @UpdateTimestamp
    @Column(insertable = false)//The purpose is the column will not be inserted when the entity is inserted.
    private LocalDateTime updatedAt;
    private String updatedBy;

}
