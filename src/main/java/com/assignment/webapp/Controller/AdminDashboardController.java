package com.assignment.webapp.Controller;

import com.assignment.webapp.Entity.Mentor;
import com.assignment.webapp.Entity.MentorRequest;
import com.assignment.webapp.Entity.Statistics;
import com.assignment.webapp.Request.MentorDetailsRequest;
import com.assignment.webapp.Service.AdminDashBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminDashboardController {

    @Autowired
    private AdminDashBoardService adminDashBoardService;

    // API to get all consultant requests
    @GetMapping("/consultant-requests")
    public ResponseEntity<List<MentorRequest>> getConsultantRequests() {
        List<MentorRequest> mentorRequests = adminDashBoardService.getConsultantRequests();
        return ResponseEntity.ok(mentorRequests);
    }

    // API to approve/reject consultant requests
    @PostMapping("/approve-consultant-request/{requestId}")
    public ResponseEntity<String> approveConsultantRequest(@PathVariable Long requestId, @RequestParam boolean approve) {
        String result = adminDashBoardService.approveConsultantRequest(requestId,approve);
        return ResponseEntity.ok(result);
    }

    // API to add additional details to the consultant account
    @PostMapping("/add-consultant-details/{mentorId}")
    public ResponseEntity<String> addConsultantDetails(@PathVariable Long mentorId, @RequestBody MentorDetailsRequest details) {
         String result = adminDashBoardService.addConsultantDetails(mentorId,details);
         return ResponseEntity.ok(result);
    }

    // API to get the list of all consultants
    @GetMapping("/consultants")
    public ResponseEntity<List<Mentor>> getAllConsultants() {
        List<Mentor> mentors = adminDashBoardService.getAllConsultants();
        return ResponseEntity.ok(mentors);
    }

    // API to get specific consultant details by name
    @GetMapping("/consultant-details/name")
    public ResponseEntity getConsultantDetailsByName(@RequestParam(required = false) String name) {
        Mentor mentor = adminDashBoardService.getConsultantByName(name);
        return ResponseEntity.ok(mentor);
    }

    // API to get specific consultant details by job role
    @GetMapping("/consultant-details/role")
    public ResponseEntity<List<Mentor>> getConsultantDetailsByJobRole(@RequestParam(required = false) String jobRole) {
        List<Mentor> mentors = adminDashBoardService.getConsultantByRole(jobRole);
        return ResponseEntity.ok(mentors);
    }

    // API to fetch web app statistics
    @GetMapping("/web-app-statistics")
    public ResponseEntity<Statistics> getWebAppStatistics() {
        Statistics statistics = adminDashBoardService.getWebAppStatistics();
        return ResponseEntity.ok(statistics);
    }
}
