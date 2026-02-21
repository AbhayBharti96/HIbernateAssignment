package com.hospital.test;

import com.hospital.entity.*;
import com.hospital.util.JPAUtil;
import jakarta.persistence.*;

public class CRUDTest5 {

    public static void run() {

        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            Doctor d1 = new Doctor();
            d1.setName("Dr. Mehta");
            d1.setSpecialization("Cardiology");
            d1.setLicenseNo("LIC300");

            Doctor d2 = new Doctor();
            d2.setName("Dr. Singh");
            d2.setSpecialization("Neurology");
            d2.setLicenseNo("LIC301");

            Patient p1 = new Patient();
            p1.setName("Ali");

            Patient p2 = new Patient();
            p2.setName("Priya");

            Patient p3 = new Patient();
            p3.setName("Raj");

            d1.addPatient(p1);
            d1.addPatient(p2);
            d2.addPatient(p1);
            d2.addPatient(p3);

            em.persist(d1);
            em.persist(d2);

            tx.commit();
            System.out.println("Many-to-Many Relations Saved");

            // JPQL JOIN FETCH
            Doctor fetch = em.createQuery(
                            "SELECT d FROM Doctor d JOIN FETCH d.patients WHERE d.name = :name",
                            Doctor.class)
                    .setParameter("name", "Dr. Mehta")
                    .getSingleResult();

            System.out.println("Patients of Dr. Mehta:");
            fetch.getPatients().forEach(p ->
                    System.out.println(p.getName()));

        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
}