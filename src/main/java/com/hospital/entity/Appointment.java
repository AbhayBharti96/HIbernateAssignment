package com.hospital.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime appointDate;
    private String status;
    private String reason;

    @OneToOne(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            optional = true)
    @JoinColumn(name = "prescription_id")
    private Prescription prescription;

    // Getters & Setters
    public Long getId() { return id; }

    public LocalDateTime getAppointDate() { return appointDate; }
    public void setAppointDate(LocalDateTime appointDate) {
        this.appointDate = appointDate;
    }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getReason() { return reason; }
    public void setReason(String reason) { this.reason = reason; }

    public Prescription getPrescription() { return prescription; }
    public void setPrescription(Prescription prescription) {
        this.prescription = prescription;
    }
}