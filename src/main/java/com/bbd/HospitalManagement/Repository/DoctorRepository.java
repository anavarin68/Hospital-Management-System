package com.bbd.HospitalManagement.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bbd.HospitalManagement.Model.DoctorDetails;

@Repository
public interface DoctorRepository extends JpaRepository<DoctorDetails, Long> {

}