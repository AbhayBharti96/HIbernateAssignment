package com.hospital.test;

import com.hospital.entity.*;
import com.hospital.util.JPAUtil;
import jakarta.persistence.*;

public class CRUDTest2 {

    public static void run() {

        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            Department cardio = new Department();
            cardio.setName("Cardiology");
            cardio.setLocation("Block A");
            cardio.setHeadDoctorName("Dr. Mehta");

            Department neuro = new Department();
            neuro.setName("Neurology");
            neuro.setLocation("Block B");
            neuro.setHeadDoctorName("Dr. Singh");

            Doctor d1 = new Doctor();
            d1.setName("Dr. Sharma");
            d1.setSpecialization("Heart Specialist");
            d1.setLicenseNo("LIC123");

            Doctor d2 = new Doctor();
            d2.setName("Dr. Verma");
            d2.setSpecialization("Cardio Surgeon");
            d2.setLicenseNo("LIC124");

            Doctor d3 = new Doctor();
            d3.setName("Dr. Smith");
            d3.setSpecialization("Brain Specialist");
            d3.setLicenseNo("LIC125");

            Doctor d4 = new Doctor();
            d4.setName("Dr. Roy");
            d4.setSpecialization("Neuro Surgeon");
            d4.setLicenseNo("LIC126");

            cardio.addDoctor(d1);
            cardio.addDoctor(d2);
            neuro.addDoctor(d3);
            neuro.addDoctor(d4);

            em.persist(cardio);
            em.persist(neuro);

            tx.commit();
            System.out.println("✅ Departments & Doctors Saved");

            // Navigation test
            Department dept = em.find(Department.class, cardio.getId());
            System.out.println("Doctors in Cardiology:");
            dept.getDoctors().forEach(d -> System.out.println(d.getName()));

            // Transfer doctor
            tx.begin();
            Doctor transferDoc = em.find(Doctor.class, d3.getId());
            neuro.removeDoctor(transferDoc);
            cardio.addDoctor(transferDoc);
            tx.commit();

            System.out.println("Doctor Transferred");

        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
}