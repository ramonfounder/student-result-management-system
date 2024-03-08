package io.shyftlabs.srms.repository;

import io.shyftlabs.srms.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
