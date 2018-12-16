package com.tajv.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tajv.model.Prescription;

@Repository
public class PrescriptionDaoImpl implements PrescriptionDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	@Transactional
	public Prescription getPrescriptionById(int id) {
		String hql = "from Prescription where id=" + id;
		Query query = sessionFactory.getCurrentSession().createQuery(hql);

		@SuppressWarnings("unchecked")
		List<Prescription> prescriptionList = query.list();

		if (prescriptionList != null && !prescriptionList.isEmpty()) {
			return prescriptionList.get(0);
		}

		return null;
	}

	@Override
	@Transactional
	public void savePrescription(Prescription prescription) {
		sessionFactory.getCurrentSession().save(prescription);
	}

	@Override
	@Transactional
	public void deletePrescription(Prescription prescription) {
		sessionFactory.getCurrentSession().delete(prescription);
	}

	@Override
	@Transactional
	public void updatePrescription(Prescription prescription) {
		sessionFactory.getCurrentSession().update(prescription);
	}

}
