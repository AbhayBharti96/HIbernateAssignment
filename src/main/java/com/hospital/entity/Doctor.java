package com.hospital.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String specialization;
    private String licenseNo;

    // Bi 1:N (Many side)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    private Department department;

    // Uni 1:N
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id")
    private List<Appointment> appointments = new ArrayList<>();

    // Bi ManyToMany (Owner)
    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(
            name = "patient_doctors",
            joinColumns = @JoinColumn(name = "doctor_id"),
            inverseJoinColumns = @JoinColumn(name = "patient_id")
    )
    private List<Patient> patients = new ArrayList<>();

    public void addPatient(Patient patient) {
        patients.add(patient);
        patient.getDoctors().add(this);
    }

    public void removePatient(Patient patient) {
        patients.remove(patient);
        patient.getDoctors().remove(this);
    }

    // Getters & Setters
    public Long getId() { return id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getSpecialization() { return specialization; }
    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getLicenseNo() { return licenseNo; }
    public void setLicenseNo(String licenseNo) {
        this.licenseNo = licenseNo;
    }

    public Department getDepartment() { return department; }
    public void setDepartment(Department department) {
        this.department = department;
    }

    public List<Appointment> getAppointments() { return appointments; }

    public List<Patient> getPatients() { return patients; }
}