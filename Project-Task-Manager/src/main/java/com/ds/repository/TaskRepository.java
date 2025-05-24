package com.ds.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ds.entity.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {

}
