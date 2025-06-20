package com.bw.task_manager.dto;

import lombok.Value;

import java.time.LocalDateTime;

@Value
public class TaskDTO {
    Long id;
    String title;
    String description;
    LocalDateTime timestamp;
    boolean completed;
}
