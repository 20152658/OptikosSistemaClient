package com.tajv.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.tajv.dao.ItemDao;
import com.tajv.dao.SaleDao;
import com.tajv.model.Item;
import com.tajv.model.Sale;

@Controller
public class OrderController {

	@Autowired
	private SaleDao saleDao;

	@Autowired
	private ItemDao itemDao;

	// @RequestMapping(value = { "/sellingItem" })
	// public String sellingItem(@ModelAttribute Item item, HttpSession session) {
	// String newString = item.getTitle();
	// Double amount = item.getPrice();
	// String ids[] = newString.split(",");
	// Sale newSale = new Sale();
	// newSale.setItems(newString);
	// newSale.setSum(amount);
	// saleDao.saveSale(newSale);
	//
	// for (int i = 0; i < ids.length; i++) {
	// try {
	// int id = Integer.parseInt(ids[i]);
	// System.out.println("Asd" + id);
	// Item it = itemDao.getItemById(id);
	// int amountt = it.getAmount() - 1;
	// it.setAmount(amountt);
	// } catch (Exception e) {
	// }
	// }
	// return "redirect:/home";
	// }

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

		ModelAndView model = new ModelAndView("NewOrder");
		model.addObject("akiniai", akiniai);
		model.addObject("sAkiniai", sAkiniai);
		model.addObject("lesiai", lesiai);
		model.addObject("kitka", kitka);
		return model;
	}

	@RequestMapping(value = { "/reviewOrders" })
	public ModelAndView reviewOrders(HttpServletRequest request) {
		ArrayList<Sale> sales = (ArrayList<Sale>) saleDao.getAllSales();
		if (sales == null) {
			sales = new ArrayList<>();
		}
		ModelAndView model = new ModelAndView("ReviewOrders");
		model.addObject("sales", sales);
		return model;
	}

	@RequestMapping(value = { "/sellingItem" })
	public ModelAndView sellingItem2(@ModelAttribute Item item, HttpSession session) {
		ModelAndView model = new ModelAndView("AddPrescriptionToSale");
		String newString = item.getTitle();
		Double amount = item.getPrice();
		String ids[] = newString.split(",");
		Sale newSale = new Sale();
		newSale.setItems(newString);
		newSale.setSum(amount);
		/*
		 * sita gal nukelt po CONFIRM. kuri dar reik sukurt saleDao.saveSale(newSale);
		 * 
		 * for (int i = 0; i < ids.length; i++) { try { int id =
		 * Integer.parseInt(ids[i]); System.out.println("Asd" + id); Item it =
		 * itemDao.getItemById(id); int amountt = it.getAmount() - 1;
		 * it.setAmount(amountt); } catch (Exception e) { } }
		 */
		model.addObject("newSale", newSale);
		return model;
	}

	@RequestMapping(value = { "/reviewOrder" })
	public ModelAndView reviewOrder(@RequestParam("saleId") int id, HttpSession session) {
		ModelAndView model = new ModelAndView("ReviewOrder");
		Sale sale = saleDao.getSale(id);
		ArrayList<Item> items = new ArrayList<Item>();
		String[] itemIds = sale.getItems().split(",");
		for (String itemIdString : itemIds) {
			try {
				int itemId = Integer.parseInt(itemIdString);
				Item item = itemDao.getItemById(itemId);
				items.add(item);
			} catch (Exception e) {
				System.out.println("Something wrong in Order Controller reviewOrder try/catch");
			}
		}
		model.addObject("sale", sale);
		model.addObject("items", items);
		return model;
	}

}
