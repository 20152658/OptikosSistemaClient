package com.tajv.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.tajv.dao.ClientDao;
import com.tajv.dao.PrescriptionDao;
import com.tajv.model.Client;
import com.tajv.model.Prescription;

@Controller
public class ClientController {

	@Autowired
	private ClientDao clientDao;

	@Autowired
	private PrescriptionDao prescriptionDao;

	@RequestMapping(value = { "/clients" })
	public ModelAndView clients(HttpServletRequest request) {
		System.out.println("hey client 24");
		ArrayList<Client> clients = (ArrayList<Client>) clientDao.getAllClients();
		System.out.println("clients list size is: " + clients.size());
		ModelAndView model = new ModelAndView("Clients");
		model.addObject("clients", clients);
		return model;
	}

	@RequestMapping(value = { "/addNewClient" })
	public ModelAndView addNewClient(HttpServletRequest request) {
		ModelAndView model = new ModelAndView("AddNewClient");
		return model;
	}

	@RequestMapping(value = { "/addingNewClient" })
	public String addingNewClient(@ModelAttribute Client client, HttpSession session) {

		String clientName = client.getName();
		String clientSurname = client.getSurname();
		String clientEmail = client.getEmail();
		String clientPhone = client.getPhoneNumber();
		System.out.println("xxl" + clientName + " " + clientSurname + " " + clientEmail + " " + clientPhone);

		Client newClient = new Client();
		newClient.setName(clientName);
		newClient.setSurname(clientSurname);
		newClient.setEmail(clientEmail);
		newClient.setPhoneNumber(clientPhone);

		if (clientName != null)
			clientDao.saveClient(newClient);

		System.out.println("Client was saved succesfully: " + clientName + " " + clientSurname + " " + clientEmail + " "
				+ clientPhone);

		return "redirect:/addPrescription";
	}

	@RequestMapping(value = { "/editClient" })
	public ModelAndView editClient(@RequestParam("clientId") int clientId, HttpServletRequest request) {
		ModelAndView model = new ModelAndView("EditClient");
		Client client = clientDao.getClientById(clientId);
		model.addObject("client", client);
		Prescription prescription = prescriptionDao.getPrescriptionByClientId(clientId);
		if (prescription == null) {
			prescription = new Prescription();
			prescription.setClientId(clientId);
		}
		model.addObject("prescription", prescription);
		return model;
	}

	@RequestMapping(value = { "/edittingClient" }, method = RequestMethod.POST)
	public String edittingClient(@ModelAttribute Client client, HttpSession session) {
		System.out.println("hello " + client.getId() + " " + client.getName() + " " + client.getSurname() + " "
				+ client.getPhoneNumber() + " " + client.getEmail());
		if (client.getName() != null && client.getName().length() < 20) {
			clientDao.updateClient(client);
		}
		return "redirect:/clients";
	}

	@RequestMapping(value = { "/addingPrescription" }, method = RequestMethod.POST)
	public String addingPrescription(@ModelAttribute Prescription prescription, HttpSession session) {

		System.out.println(prescription.getClientId() + " " + prescription.getDistanceBetweenPupils());
		try {
			prescriptionDao.updatePrescription(prescription);
		} catch (Exception e) {
			prescriptionDao.savePrescription(prescription);
		}

		return "redirect:/clients";
	}
}
