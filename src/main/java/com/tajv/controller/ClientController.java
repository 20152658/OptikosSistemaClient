package com.tajv.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tajv.model.Client;
import com.tajv.model.File;

@Controller
public class ClientController {

	private String URL = "http://localhost:8080/OptikosSistemaClient/";
	
	@RequestMapping(value = { "/", "/welcome" })
	public ModelAndView welcome(HttpServletRequest request) {

		ModelAndView model = new ModelAndView("Welcome");
		System.out.println("welcome");
		return model;
	}

	@RequestMapping(value = { "/404" })
	public ModelAndView error404(HttpServletRequest request) {

		ModelAndView model = new ModelAndView("ErrorLogedIn");
		return model;
	}

	@RequestMapping(value = { "/login" })
	public ModelAndView login(HttpServletRequest request) {

		ModelAndView model = new ModelAndView("Login");
		return model;
	}

	@RequestMapping(value = "/logingIn", method = RequestMethod.POST)
	public String logingIn(@ModelAttribute Client client, HttpSession session)
			throws JsonParseException, JsonMappingException, IOException {
		RestTemplate restTemplate = new RestTemplate();
		System.out.println(client.getEmail() + " " + client.getPassword());
		final String uri = new String(URL + "login");
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		try {
			ResponseEntity<String> responseEntity = restTemplate.postForEntity(uri, client, String.class);
			Client receivedClient = mapper.readValue(responseEntity.getBody(), Client.class);
			session.setAttribute("client", receivedClient);
			return "redirect:/home";
		} catch (HttpClientErrorException e) {
			System.out.println(e);
			return "redirect:/login";
		}

	}

	@RequestMapping(value = { "/register" })
	public ModelAndView register(HttpServletRequest request) {

		ModelAndView model = new ModelAndView("Registration");
		return model;
	}
	
	@RequestMapping(value = { "/newOrder" })
	public ModelAndView newOrder(HttpServletRequest request) {

		ModelAndView model = new ModelAndView("NewOrder");
		return model;
	}
	
	@RequestMapping(value = { "/reviewItems" })
	public ModelAndView reviewItems(HttpServletRequest request) {

		ModelAndView model = new ModelAndView("ReviewItems");
		return model;
	}
	
	@RequestMapping(value = { "/reviewOrders" })
	public ModelAndView reviewOrders(HttpServletRequest request) {

		ModelAndView model = new ModelAndView("ReviewOrders");
		return model;
	}

	@RequestMapping(value = { "/registering" })
	public String registering(@ModelAttribute Client client, HttpSession session)
			throws JsonParseException, JsonMappingException, IOException {

		RestTemplate restTemplate = new RestTemplate();
		final String uri = new String(URL + "addClient");
		try {
			if (client.getEmail() != null && client.getNewPassword() != null && client.getDateOfBirth() != null
					&& client.getHeight() != 0 && client.getWeight() != 0
					&& client.getNewPassword().equals(client.getRepeatPassword())) {
				client.setPassword(client.getNewPassword());
				System.out.println(client.getName() + " " + client.getSurname() + " " + client.getEmail() + " "
						+ client.getPassword() + " " + client.getRepeatPassword() + " " + client.getDateOfBirth() + " "
						+ client.getHeight() + " " + client.getWeight());
				ResponseEntity<String> responseEntity = restTemplate.postForEntity(uri, client, String.class);
				session.setAttribute("client", client);
				return "redirect:/home";
			} else {
				return "redirect:register";
			}
		} catch (HttpClientErrorException e) {
			System.out.println(e);
			return "redirect:/register";
		}
	}

	@RequestMapping(value = { "/home" })
	public ModelAndView home(HttpServletRequest request, HttpSession session) {
		if (session.getAttribute("client") != null) {
			ModelAndView model = new ModelAndView("Home");
			return model;
		} else {
			//return new ModelAndView("redirect:/login");
			ModelAndView model = new ModelAndView("Home");
			return model;
		}
	}

	@RequestMapping(value = { "/changeInformation" })
	public ModelAndView changeInformation(HttpServletRequest request, HttpSession session) {

		if (((Client) session.getAttribute("client")) != null) {
			ModelAndView model = new ModelAndView("ChangeInformation");
			System.out.println(((Client) session.getAttribute("client")).getEmail());
			model.addObject("client", ((Client) session.getAttribute("client")));
			return model;
		} else {
			return new ModelAndView("redirect:/login");
		}
	}

	@RequestMapping(value = { "/changingInformation" })
	public String changingInformation(@ModelAttribute Client client, HttpSession session)
			throws JsonParseException, JsonMappingException, IOException {

		RestTemplate restTemplate = new RestTemplate();
		final String uri = new String(URL + "updateClient");
		try {

			Client clientToUpdate = (Client) session.getAttribute("client");

			if (client.getNewPassword() != null && client.getNewPassword() != null
					&& client.getNewPassword().equals(client.getRepeatPassword())) {
				clientToUpdate.setPassword(client.getNewPassword());
			}

			if (client.getEmail().length() > 5) {
				clientToUpdate.setEmail(client.getEmail());
			}
			if (client.getPhone().length() >= 11) {
				clientToUpdate.setPhone(client.getPhone());
			}
			if (client.getHeight() != 0) {
				clientToUpdate.setHeight(client.getHeight());
			}
			if (client.getWeight() != 0) {
				clientToUpdate.setWeight(client.getWeight());
			}
			System.out.println(client.getName() + " " + client.getSurname() + " " + client.getEmail() + " "
					+ client.getPassword() + " " + client.getRepeatPassword() + " " + client.getDateOfBirth() + " "
					+ client.getHeight() + " " + client.getWeight());

			ResponseEntity<String> responseEntity = restTemplate.postForEntity(uri, clientToUpdate, String.class);
			session.setAttribute("client", clientToUpdate);
			// TODO: show "Updated successfully"
			return "redirect:/changeInformation";
		} catch (

		HttpClientErrorException e) {
			System.out.println(e);
			return "redirect:/changeInformation";
		}
	}

	@RequestMapping(value = { "/uploadFile" }, method = RequestMethod.GET)
	public ModelAndView uploadFile(HttpServletRequest request, HttpSession session) {
		if (session.getAttribute("client") != null) {
			ModelAndView model = new ModelAndView("UploadFile");
			return model;
		} else {
			return new ModelAndView("redirect:/login");
		}
	}

	@RequestMapping(value = { "/saveFile" }, method = RequestMethod.POST)
	public String saveFile(HttpServletRequest request, HttpSession session,
			@RequestParam CommonsMultipartFile[] fileUpload) throws Exception {

		RestTemplate restTemplate = new RestTemplate();

		final String uri = new String(URL + "addFile");
		try {
			if (fileUpload != null && fileUpload.length > 0) {
				for (CommonsMultipartFile aFile : fileUpload) {

					File file = new File();
					file.setFileName(aFile.getOriginalFilename());
					file.setData(aFile.getBytes());
					file.setClientId(((Client) session.getAttribute("client")).getId());
					ResponseEntity<String> responseEntity = restTemplate.postForEntity(uri, file, String.class);

				}
				// show that uploaded successfully
				return "redirect:/reviewData";
			} else {
				// show that not uploaded
				return "redirect:uploadFile";
			}
		} catch (

		HttpClientErrorException e) {
			System.out.println(e);
			return "redirect:/home";
		}
	}

	@RequestMapping(value = { "/reviewData" })
	public ModelAndView reviewData(HttpServletRequest request, HttpSession session) throws IOException {
		if (session.getAttribute("client") != null) {
			try {
				RestTemplate restTemplate = new RestTemplate();
				// if (session.getAttribute("clientFiles") == null) {
				final String uriForFileList = new String(URL + "getFileList");

				Client client = (Client) session.getAttribute("client");

				ResponseEntity<File[]> responseEntity = restTemplate.postForEntity(uriForFileList, client,
						File[].class);

				File[] files = (File[]) responseEntity.getBody();

				ArrayList<File> filesList = new ArrayList<>();

				for (int i = 0; i < files.length; i++) {
					filesList.add(files[i]);
				}

				client.setClientFiles(filesList);

				session.setAttribute("clientFiles", filesList);

				int newestFileId = filesList.size();
				System.out.println("newest id: " + newestFileId);
				return new ModelAndView("redirect:/reviewData/" + filesList.get(--newestFileId).getFileId() + "");

				// }
				// if (session.getAttribute("clientFiles") != null) {

				/*
				 * ArrayList<File> clientFiles = (ArrayList<File>)
				 * session.getAttribute("clientFiles");
				 * 
				 * int newestFileId = clientFiles.size();
				 * 
				 * System.out.println("newest id: " + newestFileId); return new
				 * ModelAndView("redirect:/" + clientFiles.get(--newestFileId).getFileId() +
				 * "/reviewData");
				 */
				// }

				// ModelAndView model = new ModelAndView("ReviewData");

				// return model;
			} catch (Exception e) {
				return new ModelAndView("redirect:/uploadFile");
			}
		} else {
			return new ModelAndView("redirect:/login");
		}
	}

	@RequestMapping(value = { "/reviewData/{id}" })
	public ModelAndView reviewFile(HttpServletRequest request, HttpSession session,
			@PathVariable(value = "id") String id) throws IOException {
		int fileId = Integer.parseInt(id);
		if (session.getAttribute("client") != null) {

			if (session.getAttribute("clientFiles") != null) {

				ArrayList<File> clientFiles = (ArrayList<File>) session.getAttribute("clientFiles");
				for (int i = 0; i < clientFiles.size(); i++) {
					if (clientFiles.get(i).getFileId() == fileId) {
						if (clientFiles.get(i).getHeartRateData() == null) {
							RestTemplate restTemplate = new RestTemplate();
							final String uriForFileDataList = new String(URL + "getFileDataList");
							ResponseEntity<String> responseEntity = restTemplate.postForEntity(uriForFileDataList,
									clientFiles.get(i), String.class);
							String heartRateData = responseEntity.getBody();

							clientFiles.get(i).setHeartRateData(heartRateData);
							session.setAttribute("clientFiles", clientFiles);

							ModelAndView model = new ModelAndView("ReviewData");
							model.addObject("clientFiles", clientFiles);
							model.addObject("heartRateData", heartRateData);

							return model;
						} else {
							String heartRateData = clientFiles.get(i).getHeartRateData();

							ModelAndView model = new ModelAndView("ReviewData");
							model.addObject("clientFiles", clientFiles);
							model.addObject("heartRateData", heartRateData);

							return model;
						}

					}
				}
				return new ModelAndView("redirect:/reviewData");

			} else {
				return new ModelAndView("redirect:/reviewData");
			}
		} else {
			return new ModelAndView("redirect:/login");
		}

	}

	@RequestMapping(value = { "/logout" })
	public ModelAndView logout(HttpServletRequest request, HttpSession session) {
		if (!session.isNew()) {
			session.invalidate();
		}
		return new ModelAndView("redirect:/");
	}

	@RequestMapping(value = { "/error" })
	public ModelAndView error(HttpServletRequest request) {

		ModelAndView model = new ModelAndView("ErrorLogedOut");
		return model;
	}

}
