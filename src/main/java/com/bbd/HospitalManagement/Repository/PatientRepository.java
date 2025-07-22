package com.bbd.HospitalManagement.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bbd.HospitalManagement.Model.PatientDetails;

@Repository
public interface PatientRepository extends JpaRepository<PatientDetails, Long>{

}
