package com.bbd.HospitalManagement.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bbd.HospitalManagement.Model.AppointmentDetails;

@Repository
public interface AppointmentRepository extends JpaRepository<AppointmentDetails, Long> {

	List<AppointmentDetails> findByPatientId(Long patientId);

	List<AppointmentDetails> findByDoctorId(Long doctorId);

	@Query(value = "SELECT * FROM appointment_details a " +
		       "JOIN doctor_details d ON a.doctor_id = d.id " +
		       "JOIN patient_details p ON a.patient_id = p.id " +
		       "WHERE (:doctorName IS NULL OR LOWER(d.name) LIKE LOWER(CONCAT('%', :doctorName, '%'))) " +
		       "AND (:patientName IS NULL OR LOWER(p.name) LIKE LOWER(CONCAT('%', :patientName, '%'))) " +
		       "AND (:date IS NULL OR DATE(a.appointment_date_time) = :date) " +
		       "AND (:time IS NULL OR TIME(a.appointment_date_time) = :time)",
		       nativeQuery = true)
		List<AppointmentDetails> searchAppointments(
		    @Param("doctorName") String doctorName,
		    @Param("patientName") String patientName,
		    @Param("date") LocalDate date,
		    @Param("time") LocalTime time);

}
