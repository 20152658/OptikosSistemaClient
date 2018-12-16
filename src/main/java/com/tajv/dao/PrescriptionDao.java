package com.tajv.dao;

import com.tajv.model.Prescription;

public interface PrescriptionDao {

	public Prescription getPrescriptionById(int id);

	public void savePrescription(Prescription prescription);

	public void deletePrescription(Prescription prescription);

	public void updatePrescription(Prescription prescription);

}
