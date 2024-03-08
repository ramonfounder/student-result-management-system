package io.shyftlabs.srms.repository;

import io.shyftlabs.srms.domain.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
