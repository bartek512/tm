package com.bw.task_manager.entity;

import lombok.Value;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Task {

    @Id
    private Long id;

    private String title;

    private String description;

    private String timestamp;

}
