package com.tajv.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.tajv.dao.ItemDao;
import com.tajv.model.Employee;
import com.tajv.model.Item;

@Controller
public class ItemController {

	@Autowired
	private ItemDao itemDao;

	@RequestMapping(value = { "/addNewItem" })
	public ModelAndView addItem(HttpServletRequest request) {
		ModelAndView model = new ModelAndView("AddNewItem");
		return model;
	}

	@RequestMapping(value = { "/addingNewItem" })
	public String addingNewItem(@ModelAttribute Item item, HttpSession session) {
		if (item.getType().length() != 0 && item.getTitle().length() != 0 && item.getPrice() != 0) {
			itemDao.saveItem(item);
		}
		return "redirect:/addNewItem";
	}

	@RequestMapping(value = { "/newOrder" })
	public ModelAndView newOrder(HttpServletRequest request) {
		List<Item> prekes = new ArrayList<>();
		prekes = itemDao.getAllItems();
		ArrayList<Item> akiniai = new ArrayList<>();
		ArrayList<Item> sAkiniai = new ArrayList<>();
		ArrayList<Item> lesiai = new ArrayList<>();
		ArrayList<Item> kitka = new ArrayList<>();

		if (!prekes.isEmpty()) {
			for (Item preke : prekes) {
				switch (preke.getType()) {
				case "akiniai":
					akiniai.add(preke);
					break;
				case "sAkiniai":
					sAkiniai.add(preke);
					break;
				case "lesiai":
					lesiai.add(preke);
					break;
				case "kitka":
					kitka.add(preke);
					break;
				}
			}
		}

		System.out.println(akiniai.size() + "," + sAkiniai.size() + "," + lesiai.size() + "," + kitka.size());
		ModelAndView model = new ModelAndView("NewOrder");
		model.addObject("akiniai", akiniai);
		model.addObject("sAkiniai", sAkiniai);
		model.addObject("lesiai", lesiai);
		model.addObject("kitka", kitka);
		return model;
	}

	@RequestMapping(value = { "/reviewItems" })
	public ModelAndView reviewItems(HttpServletRequest request) {
		List<Item> prekes = itemDao.getAllItems();
		ArrayList<Item> akiniai = new ArrayList<>();
		ArrayList<Item> sAkiniai = new ArrayList<>();
		ArrayList<Item> lesiai = new ArrayList<>();
		ArrayList<Item> kitka = new ArrayList<>();
		if (!prekes.isEmpty()) {
			for (Item preke : prekes) {
				switch (preke.getType()) {
				case "akiniai":
					akiniai.add(preke);
					break;
				case "sAkiniai":
					sAkiniai.add(preke);
					break;
				case "lesiai":
					lesiai.add(preke);
					break;
				case "kitka":
					kitka.add(preke);
					break;
				}
			}
		}

		System.out.println(akiniai.size() + "," + sAkiniai.size() + "," + lesiai.size() + "," + kitka.size());
		ModelAndView model = new ModelAndView("ReviewItems");
		model.addObject("akiniai", akiniai);
		model.addObject("sAkiniai", sAkiniai);
		model.addObject("lesiai", lesiai);
		model.addObject("kitka", kitka);
		return model;
	}

	@RequestMapping(value = { "/reviewOrders" })
	public ModelAndView reviewOrders(HttpServletRequest request) {

		ModelAndView model = new ModelAndView("ReviewOrders");
		return model;
	}

	@RequestMapping(value = { "/changeInformation" })
	public ModelAndView changeInformation(HttpServletRequest request, HttpSession session) {

		if (((Employee) session.getAttribute("Darbuotojas")) != null) {
			ModelAndView model = new ModelAndView("ChangeInformation");
			System.out.println(((Employee) session.getAttribute("Darbuotojas")).getNickname());
			model.addObject("Darbuotojas", (session.getAttribute("Darbuotojas")));
			return model;
		} else {
			return new ModelAndView("redirect:/login");
		}
	}

	@RequestMapping(value = { "/uploadFile" }, method = RequestMethod.GET)
	public ModelAndView uploadFile(HttpServletRequest request, HttpSession session) {
		if (session.getAttribute("Darbuotojas") != null) {
			ModelAndView model = new ModelAndView("UploadFile");
			return model;
		} else {
			return new ModelAndView("redirect:/login");
		}
	}

	/*
	 * @RequestMapping(value = { "/saveFile" }, method = RequestMethod.POST) public
	 * String saveFile(HttpServletRequest request, HttpSession session,
	 * 
	 * @RequestParam CommonsMultipartFile[] fileUpload) throws Exception {
	 * 
	 * RestTemplate restTemplate = new RestTemplate();
	 * 
	 * final String uri = new String(URL + "addFile"); try { if (fileUpload != null
	 * && fileUpload.length > 0) { for (CommonsMultipartFile aFile : fileUpload) {
	 * 
	 * File file = new File(); file.setFileName(aFile.getOriginalFilename());
	 * file.setData(aFile.getBytes()); file.setDarbuotojasId(((Darbuotojas)
	 * session.getAttribute("Darbuotojas")).getId()); ResponseEntity<String>
	 * responseEntity = restTemplate.postForEntity(uri, file, String.class);
	 * 
	 * } // show that uploaded successfully return "redirect:/reviewData"; } else {
	 * // show that not uploaded return "redirect:uploadFile"; } } catch (
	 * HttpClientErrorException e) { System.out.println(e); return "redirect:/home";
	 * } } /*
	 * 
	 * @RequestMapping(value = { "/reviewData" }) public ModelAndView
	 * reviewData(HttpServletRequest request, HttpSession session) throws
	 * IOException { if (session.getAttribute("Darbuotojas") != null) { try {
	 * RestTemplate restTemplate = new RestTemplate(); // if
	 * (session.getAttribute("DarbuotojasFiles") == null) { final String
	 * uriForFileList = new String(URL + "getFileList");
	 * 
	 * Darbuotojas Darbuotojas = (Darbuotojas) session.getAttribute("Darbuotojas");
	 * 
	 * ResponseEntity<File[]> responseEntity =
	 * restTemplate.postForEntity(uriForFileList, Darbuotojas, File[].class);
	 * 
	 * File[] files = (File[]) responseEntity.getBody();
	 * 
	 * ArrayList<File> filesList = new ArrayList<>();
	 * 
	 * for (int i = 0; i < files.length; i++) { filesList.add(files[i]); }
	 * 
	 * Darbuotojas.setDarbuotojasFiles(filesList);
	 * 
	 * session.setAttribute("DarbuotojasFiles", filesList);
	 * 
	 * int newestFileId = filesList.size(); System.out.println("newest id: " +
	 * newestFileId); return new ModelAndView("redirect:/reviewData/" +
	 * filesList.get(--newestFileId).getFileId() + "");
	 * 
	 * // } // if (session.getAttribute("DarbuotojasFiles") != null) {
	 * 
	 * /* ArrayList<File> DarbuotojasFiles = (ArrayList<File>)
	 * session.getAttribute("DarbuotojasFiles");
	 * 
	 * int newestFileId = DarbuotojasFiles.size();
	 * 
	 * System.out.println("newest id: " + newestFileId); return new
	 * ModelAndView("redirect:/" + DarbuotojasFiles.get(--newestFileId).getFileId()
	 * + "/reviewData");
	 * 
	 * // }
	 * 
	 * // ModelAndView model = new ModelAndView("ReviewData");
	 * 
	 * // return model; } catch (Exception e) { return new
	 * ModelAndView("redirect:/uploadFile"); } } else { return new
	 * ModelAndView("redirect:/login"); } }
	 * 
	 * @RequestMapping(value = { "/reviewData/{id}" }) public ModelAndView
	 * reviewFile(HttpServletRequest request, HttpSession session,
	 * 
	 * @PathVariable(value = "id") String id) throws IOException { int fileId =
	 * Integer.parseInt(id); if (session.getAttribute("Darbuotojas") != null) {
	 * 
	 * if (session.getAttribute("DarbuotojasFiles") != null) {
	 * 
	 * ArrayList<File> DarbuotojasFiles = (ArrayList<File>)
	 * session.getAttribute("DarbuotojasFiles"); for (int i = 0; i <
	 * DarbuotojasFiles.size(); i++) { if (DarbuotojasFiles.get(i).getFileId() ==
	 * fileId) { if (DarbuotojasFiles.get(i).getHeartRateData() == null) {
	 * RestTemplate restTemplate = new RestTemplate(); final String
	 * uriForFileDataList = new String(URL + "getFileDataList");
	 * ResponseEntity<String> responseEntity =
	 * restTemplate.postForEntity(uriForFileDataList, DarbuotojasFiles.get(i),
	 * String.class); String heartRateData = responseEntity.getBody();
	 * 
	 * DarbuotojasFiles.get(i).setHeartRateData(heartRateData);
	 * session.setAttribute("DarbuotojasFiles", DarbuotojasFiles);
	 * 
	 * ModelAndView model = new ModelAndView("ReviewData");
	 * model.addObject("DarbuotojasFiles", DarbuotojasFiles);
	 * model.addObject("heartRateData", heartRateData);
	 * 
	 * return model; } else { String heartRateData =
	 * DarbuotojasFiles.get(i).getHeartRateData();
	 * 
	 * ModelAndView model = new ModelAndView("ReviewData");
	 * model.addObject("DarbuotojasFiles", DarbuotojasFiles);
	 * model.addObject("heartRateData", heartRateData);
	 * 
	 * return model; }
	 * 
	 * } } return new ModelAndView("redirect:/reviewData");
	 * 
	 * } else { return new ModelAndView("redirect:/reviewData"); } } else { return
	 * new ModelAndView("redirect:/login"); }
	 * 
	 * }
	 */

}
