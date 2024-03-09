package io.shyftlabs.srms.repository;

import io.shyftlabs.srms.domain.Result;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * ResultRepository interface to handle database operations for Result
 */
public interface ResultRepository extends JpaRepository<Result, Long> {

}
