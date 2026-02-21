package com.hospital.test;

import com.hospital.entity.*;
import com.hospital.util.JPAUtil;
import jakarta.persistence.*;

import java.time.LocalDateTime;

public class CRUDTest3 {

    public static void run() {

        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            Doctor doctor = new Doctor();
            doctor.setName("Dr. Kapoor");
            doctor.setSpecialization("General Physician");
            doctor.setLicenseNo("LIC200");

            Appointment a1 = new Appointment();
            a1.setAppointDate(LocalDateTime.now());
            a1.setStatus("SCHEDULED");
            a1.setReason("Fever");

            Appointment a2 = new Appointment();
            a2.setAppointDate(LocalDateTime.now().plusDays(1));
            a2.setStatus("COMPLETED");
            a2.setReason("Headache");

            doctor.getAppointments().add(a1);
            doctor.getAppointments().add(a2);

            em.persist(doctor);
            tx.commit();

            System.out.println("Doctor & Appointments Saved");

            // Update Appointment
            tx.begin();
            Doctor fetched = em.find(Doctor.class, doctor.getId());
            fetched.getAppointments().forEach(app -> {
                if (app.getStatus().equals("SCHEDULED")) {
                    app.setStatus("COMPLETED");
                }
            });
            tx.commit();

            System.out.println("Appointment Updated");

        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
}