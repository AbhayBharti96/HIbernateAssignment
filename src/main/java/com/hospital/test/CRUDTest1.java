package com.hospital.test;

import com.hospital.entity.MedicalRecord;
import com.hospital.entity.Patient;
import com.hospital.util.JPAUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.time.LocalDate;

public class CRUDTest1 {

    public static void run() {

        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            // =========================
            // CREATE (Persist)
            // =========================

            MedicalRecord record = new MedicalRecord();
            record.setRecordDate(LocalDate.now());
            record.setDiagnosis("Flu");
            record.setNotes("Rest for 3 days");

            Patient patient = new Patient();
            patient.setName("Ali");
            patient.setDob(LocalDate.of(2000, 5, 10));
            patient.setBloodGroup("O+");
            patient.setPhone("9999999999");

            // Link One-to-One
            patient.setMedicalRecord(record);

            // Persist only Patient (Cascade saves record)
            em.persist(patient);

            tx.commit();
            System.out.println("Patient & MedicalRecord Saved Successfully!");

            Long patientId = patient.getId();
            Long recordId = record.getId();

            // =========================
            // READ
            // =========================

            em.clear(); // Clear cache

            Patient fetchedPatient = em.find(Patient.class, patientId);

            System.out.println("\n Patient Name: " + fetchedPatient.getName());
            System.out.println("Diagnosis: " +
                    fetchedPatient.getMedicalRecord().getDiagnosis());

            // Confirm MedicalRecord cannot navigate back to Patient
            MedicalRecord fetchedRecord =
                    em.find(MedicalRecord.class, recordId);

            System.out.println("Record Summary: " +
                    fetchedRecord.getSummary());

            // =========================
            // UPDATE (Dirty Checking)
            // =========================

            tx.begin();

            Patient updatePatient =
                    em.find(Patient.class, patientId);

            updatePatient.getMedicalRecord()
                    .setNotes("Updated: Follow-up required");

            tx.commit();

            System.out.println("MedicalRecord Updated Successfully!");

            // =========================
            // DELETE (Cascade Delete)
            // =========================

            tx.begin();

            Patient deletePatient =
                    em.find(Patient.class, patientId);

            em.remove(deletePatient);  // Cascade removes record

            tx.commit();

            System.out.println("Patient & MedicalRecord Deleted!");

        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
}