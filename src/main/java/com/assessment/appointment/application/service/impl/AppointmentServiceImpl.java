package com.assessment.appointment.application.service.impl;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import com.assessment.appointment.application.entity.Appointment;
import com.assessment.appointment.application.exceptions.AppointmentNotFoundException;
import com.assessment.appointment.application.repository.IAppointmentRepository;
import com.assessment.appointment.application.service.IAppointmentService;

@Service
public class AppointmentServiceImpl implements IAppointmentService {

	private final static Logger logger = LoggerFactory.getLogger(AppointmentServiceImpl.class);

	@Autowired
	IAppointmentRepository appointmentRepository;

	/**
	 * @param appointment
	 * 
	 * @return Appointment
	 * 
	 *         - saves the appointment details in DB
	 * 
	 */
	@Override
	public Appointment addAppointmentDetails(Appointment appointment) {
		logger.info("Inside AppointmentServiceImpl.addAppointmentDetails");
		return appointmentRepository.save(appointment);
	}

	/**
	 * @param fromDate, ToDate
	 * 
	 * @return Appointment
	 * 
	 *         - fetches the appointment details based on date range
	 * 
	 */

	@Override
	public List<Appointment> getAppointmentDetails(String fromDate, String toDate) {
		logger.info("Inside AppointmentServiceImpl.getAppointmentDetails");

		List<Appointment> listOfAppointments = appointmentRepository.findAllByAppointmentDateBetween(fromDate, toDate);
		if (!(listOfAppointments.isEmpty()))
			return listOfAppointments;
		else
			return null;
	}

	/**
	 * @param appointmentId, updateAppointment
	 * 
	 * @return Appointment
	 * 
	 *         - update the appointment details if appointmentId is present
	 * 
	 */
	@Override
	public Appointment updateAppointmentDetails(int appointmentId, Appointment updatedAppointment) {
		logger.info("Inside AppointmentServiceImpl.updateAppointmentDetails");
		Appointment appointment = appointmentRepository.findById(appointmentId)
				.orElseThrow(() -> new AppointmentNotFoundException());
		if (appointment != null)
			updatedAppointment.setAppointmentId(appointmentId);
		return appointmentRepository.save(updatedAppointment);
	}

	/**
	 * @param appointmentId
	 * 
	 * @return Appointment
	 * 
	 *         - delete the appointment detail if appointmentId is present
	 * 
	 */
	@Override
	public void deleteAppointment(int appointmentId) {
		logger.info("Inside AppointmentServiceImpl.deleteAppointment");
		appointmentRepository.findById(appointmentId).orElseThrow(() -> new AppointmentNotFoundException());
		appointmentRepository.deleteById(appointmentId);
	}

	/**
	 * @param
	 * 
	 * @return Appointment
	 * 
	 *         - get all appointment details
	 * 
	 */
	@Override
	public List<Appointment> getAllAppointmentDetails() {
		logger.info("Inside AppointmentServiceImpl.getAllAppointmentDetails");
		return appointmentRepository.findAll();
	}
	
	
	/**
	 * @param
	 * 
	 * @return Appointment
	 * 
	 *         - update the appointment details partially if appointmentId is present
	 * 
	 */
	
	@Override
	public Appointment updatePartialAppointmentDetails(int appointmentId, Map<String, Object> partialFields){
		logger.info("Inside AppointmentServiceImpl.updatePartialAppointmentDetails");
		Appointment appointment = appointmentRepository.findById(appointmentId)
				.orElseThrow(() -> new AppointmentNotFoundException());
		if (appointment != null) {
			partialFields.forEach((key, value) -> {
				Field field = ReflectionUtils.findField(Appointment.class, key);
				field.setAccessible(true);
				ReflectionUtils.setField(field, appointment, value);
			});
		}
		return appointmentRepository.save(appointment);
	}

}
