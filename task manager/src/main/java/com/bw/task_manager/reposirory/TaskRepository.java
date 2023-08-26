package com.bw.task_manager.reposirory;

import com.bw.task_manager.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
