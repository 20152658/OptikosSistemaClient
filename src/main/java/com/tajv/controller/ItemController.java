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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.tajv.dao.ItemDao;
import com.tajv.model.Item;

@Controller
public class ItemController {

	@Autowired
	private ItemDao itemDao;

	@RequestMapping(value = { "/addNewItem" })
	public ModelAndView addItem(HttpServletRequest request, HttpSession session) {
		if (session.getAttribute("Darbuotojas") == null) {
			return new ModelAndView("redirect:/login");
		} else {
			ModelAndView model = new ModelAndView("AddNewItem");
			return model;
		}
	}

	@RequestMapping(value = { "/addingNewItem" })
	public String addingNewItem(@ModelAttribute Item item, HttpSession session) {
		if (item.getType().length() != 0 && item.getTitle().length() != 0 && item.getPrice() != 0) {
			itemDao.saveItem(item);
		}
		return "redirect:/reviewItems";
	}

	@RequestMapping(value = { "/deletingItem" })
	public String deletingItem(@ModelAttribute Item item, HttpSession session) {
		Item goingToBeDeleted = itemDao.getItemById(item.getId());
		itemDao.deleteItem(goingToBeDeleted);
		return "redirect:/reviewItems";
	}

	@RequestMapping(value = { "/edittingItem" }, method = RequestMethod.POST)
	public String edittingItem(@ModelAttribute Item item, HttpSession session) {
		if (item.getType().length() != 0 && item.getTitle().length() != 0 && item.getPrice() != 0) {
			itemDao.updateItem(item);
		}
		return "redirect:/reviewItems";
	}

	@RequestMapping(value = { "/editItem" })
	public ModelAndView editItem(@RequestParam("itemId") int id, HttpSession session) {
		if (session.getAttribute("Darbuotojas") == null) {
			return new ModelAndView("redirect:/login");
		} else {
			ModelAndView model = new ModelAndView("EditItem");
			Item item = itemDao.getItemById(id);
			model.addObject("item", item);
			return model;
		}
	}

	@RequestMapping(value = { "/updateItem" })
	public ModelAndView updateItem(HttpServletRequest request, HttpSession session) {
		if (session.getAttribute("Darbuotojas") == null) {
			return new ModelAndView("redirect:/login");
		} else {
			ModelAndView model = new ModelAndView("UpdateItem");
			return model;
		}
	}

	@RequestMapping(value = { "/reviewItems" })
	public ModelAndView reviewItems(HttpServletRequest request, HttpSession session) {
		if (session.getAttribute("Darbuotojas") == null) {
			return new ModelAndView("redirect:/login");
		} else {
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
	}

}
