package com.assessment.appointment.application.controller;

import java.text.ParseException;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.assessment.appointment.application.entity.Appointment;
import com.assessment.appointment.application.service.IAppointmentService;

@RestController
@RequestMapping("/appointment")
public class AppointmentController {

	private final static Logger logger = LoggerFactory.getLogger(AppointmentController.class);

	@Autowired
	IAppointmentService appointmentService;

	/** POST request to add new appointments **/
	@PostMapping("/add")
	public ResponseEntity<Appointment> addAppointmentDetails(@Valid @RequestBody Appointment appointment) {
		logger.info("Inside AppointmentController.addAppointmentDetails");
		return new ResponseEntity<Appointment>(appointmentService.addAppointmentDetails(appointment),
				HttpStatus.CREATED);

	}

	/** GET request to return all appointments **/
	@GetMapping("/fetchAll")
	public ResponseEntity<List<Appointment>> getAllAppointmentDetails() {
		logger.info("Inside AppointmentController.getAllAppointmentDetails");
		return new ResponseEntity<List<Appointment>>(appointmentService.getAllAppointmentDetails(), HttpStatus.OK);

	}

	/** GET request to return specific appointments based on date range **/
	@GetMapping("/fetch/from/{from-date}/to/{to-date}")
	public ResponseEntity<Object> getAppointmentDetails(@PathVariable("from-date") String fromDate,
			@PathVariable("to-date") String toDate) throws ParseException {
		logger.info("Inside AppointmentController.getAppointmentDetails");
		List<Appointment> appointmentDetails = appointmentService.getAppointmentDetails(fromDate, toDate);
		if (appointmentDetails != null)
			return new ResponseEntity<Object>(appointmentDetails, HttpStatus.OK);
		else
			return new ResponseEntity<Object>("Appointment details not found in given date range", HttpStatus.OK);
	}

	/** PUT request to update appointments **/
	@PutMapping("/update/{appointmentId}")
	public ResponseEntity<Appointment> update(@PathVariable int appointmentId, @RequestBody Appointment appointment) {
		logger.info("Inside AppointmentController.update");
		return new ResponseEntity<Appointment>(appointmentService.updateAppointmentDetails(appointmentId, appointment),
				HttpStatus.CREATED);
	}

	/** DELETE request to delete specific appointments **/
	@DeleteMapping("/delete/{appointmentId}")
	public ResponseEntity<String> deleteById(@PathVariable int appointmentId) {
		appointmentService.deleteAppointment(appointmentId);
		logger.info("Inside AppointmentController.deleteById");
		return new ResponseEntity<String>("Appointment details deleted auccessfully", HttpStatus.OK);
	}
}
