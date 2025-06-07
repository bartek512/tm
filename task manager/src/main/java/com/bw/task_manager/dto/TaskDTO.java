package com.bw.task_manager.dto;

import lombok.Value;

@Value
public class TaskDTO {
    Long id;
    String title;
    String description;
    String timestamp;
}
