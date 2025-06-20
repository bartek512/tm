package com.bw.task_manager.repository;

import com.bw.task_manager.entity.Task;
import com.bw.task_manager.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findAllByUser(User user);

    Optional<Task> findByIdAndUser(Long id, User user);

    @Query("SELECT t FROM Task t WHERE t.user = :user ORDER BY t.timestamp DESC")
    List<Task> findAllByUserOrderByTimestampDesc(@Param("user") User user);

    List<Task> findAllByUserAndCompleted(User user, boolean completed);

}
