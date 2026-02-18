package com.abdelrahman.taskflow.repository;

import com.abdelrahman.taskflow.model.TaskList;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;


/**
 * Avoid N+1 Problem (Lazy Loading issue)
 *
 * Problem:
 * When TaskList has a @OneToMany relationship with tasks (Lazy by default),
 * calling findAll() will:
 *   1 query → to fetch all task lists
 *   + N queries → to fetch tasks for each task list
 * This is called the N+1 Problem.
 *
 * Solution (Two Ways):k
 * ✅ First Way: Using @EntityGraph (Recommended)
 *   - cleaner & more readable
 *   - attributePaths = {"tasks"}
 *    → tells JPA(Hibernate) to fetch the tasks collection eagerly in the same query (1 optimized query).
 *
 * ✅ Second Way: Using JOIN FETCH in JPQL
 *   → SELECT tl FROM TaskList tl LEFT JOIN FETCH tl.tasks
 *   → Forces Hibernate to fetch tasks using a single join query.
 *
 * Both approaches reduce multiple queries into ONE optimized query.
 */
@Repository
public interface TaskListRepo extends JpaRepository<TaskList, UUID> {

    @EntityGraph(attributePaths = {"tasks"})
    @Query("SELECT tl FROM TaskList tl")

//    @Query("SELECT tl FROM TaskList tl LEFT JOIN FETCH tl.tasks")
    List<TaskList> findAllWithTasks();
}
