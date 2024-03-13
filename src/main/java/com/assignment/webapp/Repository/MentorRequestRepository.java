package com.assignment.webapp.Repository;

import com.assignment.webapp.Entity.MentorRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MentorRequestRepository extends JpaRepository<MentorRequest,Long> {

    List<MentorRequest> findByApproved(boolean approved);
}
