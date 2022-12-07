package com.assessment.appointment.application.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "Appointments")
public class Appointment {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int appointmentId;

	@NotEmpty
	@Column(name = "appointmentDate")
	private String appointmentDate;

	@NotEmpty
	@Column(name = "appointmentTime")
	private String appointmentTime;

	@NotEmpty
	@Column(name = "appointmentDuration")
	private String duration;

	@NotEmpty
	@Column(name = "appointmentpurpose")
	private String purpose;

	public Appointment() {
		super();
	}

	public Appointment(int appointmentId, @NotEmpty String appointmentDate, @NotEmpty String appointmentTime,
			@NotEmpty String duration, @NotEmpty String purpose) {
		super();
		this.appointmentId = appointmentId;
		this.appointmentDate = appointmentDate;
		this.appointmentTime = appointmentTime;
		this.duration = duration;
		this.purpose = purpose;
	}

	public int getAppointmentId() {
		return appointmentId;
	}

	public void setAppointmentId(int appointmentId) {
		this.appointmentId = appointmentId;
	}

	public String getAppointmentDate() {
		return appointmentDate;
	}

	public void setAppointmentDate(String appointmentDate) {
		this.appointmentDate = appointmentDate;
	}

	public String getAppointmentTime() {
		return appointmentTime;
	}

	public void setAppointmentTime(String appointmentTime) {
		this.appointmentTime = appointmentTime;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}
}
