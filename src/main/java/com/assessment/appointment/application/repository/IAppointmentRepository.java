package com.assessment.appointment.application.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.assessment.appointment.application.entity.Appointment;

@Repository
public interface IAppointmentRepository extends JpaRepository<Appointment, Integer> {

	List<Appointment> findAllByAppointmentDateBetween(String startDate, String endDate);
}
