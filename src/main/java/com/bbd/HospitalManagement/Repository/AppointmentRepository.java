package com.bbd.HospitalManagement.Repository;

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

	@Query("SELECT a FROM AppointmentDetails a "
			+ "WHERE (:doctorName IS NULL OR LOWER(a.doctor.name) LIKE LOWER(CONCAT('%', :doctorName, '%'))) "
			+ "AND (:patientName IS NULL OR LOWER(a.patient.name) LIKE LOWER(CONCAT('%', :patientName, '%'))) "
			+ "AND (:date IS NULL OR FUNCTION('DATE', a.appointmentDateTime) = :date) "
			+ "AND (:time IS NULL OR FUNCTION('TIME', a.appointmentDateTime) = :time)")
	
	List<AppointmentDetails> searchAppointments(@Param("doctorName") String doctorName,
			@Param("patientName") String patientName, @Param("date") String date, @Param("time") String time);
}
