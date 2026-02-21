package com.hospital.test;

import com.hospital.entity.*;
import com.hospital.util.JPAUtil;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class CRUDTest4 {

    public static void run() {

        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            Appointment a1 = new Appointment();
            a1.setAppointDate(LocalDateTime.now());
            a1.setStatus("COMPLETED");
            a1.setReason("Cold");

            Prescription p1 = new Prescription();
            p1.setMedicines("Paracetamol");
            p1.setDosage("2 times daily");
            p1.setIssuedDate(LocalDate.now());
            p1.setActive(true);

            a1.setPrescription(p1);

            Appointment a2 = new Appointment();
            a2.setAppointDate(LocalDateTime.now());
            a2.setStatus("SCHEDULED");
            a2.setReason("Routine Checkup");

            em.persist(a1);
            em.persist(a2);

            tx.commit();
            System.out.println("Appointments Saved (With & Without Prescription)");

            // Read null-safe
            Appointment fetch = em.find(Appointment.class, a1.getId());
            if (fetch.getPrescription() != null) {
                System.out.println("Prescription: "
                        + fetch.getPrescription().getMedicines());
            }

        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
}