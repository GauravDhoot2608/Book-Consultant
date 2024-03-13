package com.assignment.webapp.Repository;

import com.assignment.webapp.Entity.Statistics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatisticsRepository extends JpaRepository<Statistics,Long> {
}
