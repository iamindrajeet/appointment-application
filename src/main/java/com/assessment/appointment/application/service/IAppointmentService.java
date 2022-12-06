package com.assessment.appointment.application.service;

import java.util.List;

import com.assessment.appointment.application.entity.Appointment;

public interface IAppointmentService {

	public Appointment addAppointmentDetails(Appointment appointment);

	public List<Appointment> getAppointmentDetails(String fromDate, String toDate);

	public Appointment updateAppointmentDetails(int appointmentId, Appointment appointment);

	public void deleteAppointment(int appointmentId);

	public List<Appointment> getAllAppointmentDetails();
}
