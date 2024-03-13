package com.assignment.webapp;

import com.assignment.webapp.Entity.*;
import com.assignment.webapp.Repository.*;
import com.assignment.webapp.Request.MentorDetailsRequest;
import com.assignment.webapp.Service.AdminDashBoardService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AdminDashBoardServiceTest {

    @Mock
    private MentorRepository mentorRepository;
    @Mock
    private MentorRequestRepository mentorRequestRepository;
    @Mock
    private StatisticsRepository statisticsRepository;
    @Mock
    private ClientRepository clientRepository;
    @Mock
    private SessionRepository sessionRepository;

    @InjectMocks
    private AdminDashBoardService adminDashBoardService;

    @Test
    public void testGetConsultantRequests() {
        // Mock data
        List<MentorRequest> mockRequests = new ArrayList<>();
        mockRequests.add(new MentorRequest());
        when(mentorRequestRepository.findByApproved(false)).thenReturn(mockRequests);

        // Test
        List<MentorRequest> requests = adminDashBoardService.getConsultantRequests();
        assertEquals(1, requests.size());
    }

    @Test
    public void testApproveConsultantRequest() {
        // Mock data
        MentorRequest request = new MentorRequest();
        request.setId(1L);
        request.setName("Test");
        request.setEmail("test@example.com");
        Optional<MentorRequest> optionalRequest = Optional.of(request);
        when(mentorRequestRepository.findById(1L)).thenReturn(optionalRequest);

        // Test
        String result = adminDashBoardService.approveConsultantRequest(1L, true);
        assertEquals("Consultant request updated successfully.", result);
    }

    @Test
    public void testAddConsultantDetails() {
        // Mock data
        Mentor mentor = new Mentor();
        mentor.setId(1L);
        Optional<Mentor> optionalMentor = Optional.of(mentor);
        when(mentorRepository.findById(1L)).thenReturn(optionalMentor);
        MentorDetailsRequest details = new MentorDetailsRequest();
        details.setAge(30);
        details.setPhoneNo("1234567890");
        details.setCv("Test CV");
        details.setJobRole("Tester");

        // Test
        String result = adminDashBoardService.addConsultantDetails(1L, details);
        assertEquals("Consultant details added successfully.", result);
    }

    @Test
    public void testGetAllConsultants() {
        // Mock data
        List<Mentor> mockMentors = new ArrayList<>();
        mockMentors.add(new Mentor());
        when(mentorRepository.findAll()).thenReturn(mockMentors);

        // Test
        List<Mentor> consultants = adminDashBoardService.getAllConsultants();
        assertEquals(1, consultants.size());
    }

    @Test
    public void testGetConsultantByName() {
        // Mock data
        Mentor mentor = new Mentor();
        mentor.setName("Test");
        Optional<Mentor> optionalMentor = Optional.of(mentor);
        when(mentorRepository.findByName("Test")).thenReturn(optionalMentor);

        // Test
        Mentor result = adminDashBoardService.getConsultantByName("Test");
        assertNotNull(result);
        assertEquals("Test", result.getName());
    }

    @Test
    public void testGetConsultantByRole() {
        // Mock data
        Mentor mentor1 = new Mentor();
        mentor1.setJobRole("Developer");
        Mentor mentor2 = new Mentor();
        mentor2.setJobRole("Tester");
        List<Mentor> mockMentors = new ArrayList<>();
        mockMentors.add(mentor1);
        mockMentors.add(mentor2);
        when(mentorRepository.findAll()).thenReturn(mockMentors);

        // Test
        List<Mentor> result = adminDashBoardService.getConsultantByRole("Developer");
        assertEquals(1, result.size());
        assertEquals("Developer", result.get(0).getJobRole());
    }

    @Test
    public void testGetWebAppStatistics() {
        // Mock data
        List<Mentor> mockConsultants = new ArrayList<>();
        mockConsultants.add(new Mentor());
        List<Client> mockClients = new ArrayList<>();
        mockClients.add(new Client());
        List<Session> mockSessions = new ArrayList<>();
        mockSessions.add(new Session());
        when(mentorRepository.findAll()).thenReturn(mockConsultants);
        when(clientRepository.findAll()).thenReturn(mockClients);
        when(sessionRepository.findAll()).thenReturn(mockSessions);

        // Test
        Statistics result = adminDashBoardService.getWebAppStatistics();
        assertNotNull(result);
        assertEquals(1, result.getTotalConsultants());
        assertEquals(1, result.getTotalClients());
        assertEquals(1, result.getTotalSessions());
    }
}
