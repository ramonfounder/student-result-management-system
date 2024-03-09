package io.shyftlabs.srms.repository;

import io.shyftlabs.srms.domain.Course;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * CourseRepository interface to handle database operations for Course
 */
public interface CourseRepository extends JpaRepository<Course, Long> {
}
