package com.assignment.webapp.Repository;

import com.assignment.webapp.Entity.Mentor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MentorRepository extends JpaRepository<Mentor,Long> {
    Optional<Mentor> findByName(String name);

}
