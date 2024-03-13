package com.assignment.webapp.Service;

import com.assignment.webapp.Entity.*;
import com.assignment.webapp.Exception.ConsultantNotFoundException;
import com.assignment.webapp.Exception.ConsultantRequestNotFoundException;
import com.assignment.webapp.Repository.*;
import com.assignment.webapp.Request.MentorDetailsRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AdminDashBoardService {

    @Autowired
    private MentorRepository mentorRepository;
    @Autowired
    private MentorRequestRepository mentorRequestRepository;
    @Autowired
    private StatisticsRepository statisticsRepository;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private SessionRepository sessionRepository;

    // get all consultant requests
    public List<MentorRequest> getConsultantRequests() {
        return mentorRequestRepository.findByApproved(false);
    }

    // approve/reject consultant requests
    public String approveConsultantRequest(Long requestId, boolean approve) {
        Optional<MentorRequest> optionalRequest = mentorRequestRepository.findById(requestId);
        if (optionalRequest.isEmpty()) {
            throw new ConsultantRequestNotFoundException("Consultant request Id not found.");
        }
        MentorRequest request = optionalRequest.get();
        request.setApproved(approve);
        mentorRequestRepository.save(request);
        if(approve){
            Mentor mentor = new Mentor();
            mentor.setName(request.getName());
            mentor.setEmail(request.getEmail());
            mentorRepository.save(mentor);
        }
        return "Consultant request updated successfully.";
    }


    // add additional details to the consultant account
    public String addConsultantDetails(Long mentorId, MentorDetailsRequest details) {
        Optional<Mentor> optionalMentor = mentorRepository.findById(mentorId);
        if (optionalMentor.isEmpty()) {
            throw new ConsultantNotFoundException("Consultant not found.");
        }
        Mentor mentor = optionalMentor.get();
        // Set additional details from the request
        mentor.setAge(details.getAge());
        mentor.setPhoneNo(details.getPhoneNo());
        mentor.setCv(details.getCv());
        mentor.setJobRole(details.getJobRole());
        mentorRepository.save(mentor);
        return "Consultant details added successfully.";
    }


    // get the list of all consultants
    public List<Mentor> getAllConsultants() {
        return mentorRepository.findAll();
    }

    // get consultant details by name
    public Mentor getConsultantByName(String name){
        Optional<Mentor> optionalMentor = mentorRepository.findByName(name);
        if(optionalMentor.isEmpty()){
            throw new ConsultantNotFoundException("Consultant not found.");
        }
        Mentor mentor = optionalMentor.get();
        return mentor;
    }

    // get consultant details by job role
    public List<Mentor> getConsultantByRole(String role){
        List<Mentor> mentors = mentorRepository.findAll();
        List<Mentor> result = new ArrayList<>();
        for(Mentor mentor : mentors){
            if(role.equals(mentor.getJobRole())){
                result.add(mentor);
            }
        }
        return result;
    }

    // get web app statistics
    public Statistics getWebAppStatistics() {
        // Total number of consultants
        List<Mentor> consultants = mentorRepository.findAll();
        Long totalConsultants = consultants != null ? (long) consultants.size() : 0;

        // Total number of clients
        List<Client> clients = clientRepository.findAll();
        Long totalClients = clients != null ? (long) clients.size() : 0;

        // Total number of sessions booked
        List<Session> sessions = sessionRepository.findAll();
        Long totalSessions = sessions != null ? (long) sessions.size() : 0;

        // Save statistics to database
        Statistics statistics = new Statistics();
        statistics.setTotalConsultants(totalConsultants);
        statistics.setTotalClients(totalClients);
        statistics.setTotalSessions(totalSessions);
        statisticsRepository.save(statistics);
        return statistics;
    }

}
