package com.pranav.vms.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import com.pranav.vms.model.EMERGENCY_CONTACT;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Document(collection = "users")
public class User {

    @Id
    private String id;

    @NotBlank(message = "Full name is mandatory")
    private String fullName;

    @NotBlank(message = "Email is mandatory")
    @Email(message = "Email should be valid")
    private String email;

    @NotBlank(message = "Phone number is mandatory")
    private String phoneNumber;

    @NotBlank(message = "Address is mandatory")
    private String address;

    @NotNull(message = "Date of Birth is mandatory")
    private LocalDate dateOfBirth;

    private List<EmergencyContact> emergencyContacts;  // List of emergency contacts (name & phone number)

    private List<String> preferredTeams;  // List of preferred team names (multi-select)

    private String availability;  // Availability (e.g., weekdays, weekends, hours)

    private List<String> skills;  // List of skills the volunteer has

    private String additionalComments;  // Any additional comments the user wants to provide

    // Default constructor
    public User() {}

    // Full constructor
    public User(String fullName, String email, String phoneNumber, String address, LocalDate dateOfBirth,
                List<EmergencyContact> emergencyContacts, List<String> preferredTeams,
                String availability, List<String> skills, String additionalComments) {
        this.fullName = fullName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.dateOfBirth = dateOfBirth;
        this.emergencyContacts = emergencyContacts;
        this.preferredTeams = preferredTeams;
        this.availability = availability;
        this.skills = skills;
        this.additionalComments = additionalComments;
    }

    // Getters and setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public List<EmergencyContact> getEmergencyContacts() {
        return emergencyContacts;
    }

    public void setEmergencyContacts(List<EmergencyContact> emergencyContacts) {
        this.emergencyContacts = emergencyContacts;
    }

    public List<String> getPreferredTeams() {
        return preferredTeams;
    }

    public void setPreferredTeams(List<String> preferredTeams) {
        this.preferredTeams = preferredTeams;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    public List<String> getSkills() {
        return skills;
    }

    public void setSkills(List<String> skills) {
        this.skills = skills;
    }

    public String getAdditionalComments() {
        return additionalComments;
    }

    public void setAdditionalComments(String additionalComments) {
        this.additionalComments = additionalComments;
    }
}
