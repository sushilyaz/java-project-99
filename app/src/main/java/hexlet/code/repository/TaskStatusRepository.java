package hexlet.code.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import hexlet.code.model.TaskStatus;

import java.util.Optional;

public interface TaskStatusRepository extends JpaRepository<TaskStatus, Long> {
    Optional<TaskStatus> findBySlug(String slug);
}
