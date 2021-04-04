package com.example.med_id.med_id.models;

import javax.persistence.*;

@Entity
@Table(name = "t_doctor_treatment")
public class DoctorTreatment extends CommonEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long Id;

    @OneToOne
    @JoinColumn(name = "doctor_id", insertable = false, updatable = false)
    public Doctor doctor;

    @Column(name = "doctor_id", nullable = true)
    private long DoctorId;

    @Column(name = "name",length = 50, nullable = true)
    private String Name;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public long getDoctorId() {
        return DoctorId;
    }

    public void setDoctorId(long doctorId) {
        DoctorId = doctorId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
