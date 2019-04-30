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

import com.tajv.dao.ClientDao;
import com.tajv.dao.ItemDao;
import com.tajv.dao.OrderDao;
import com.tajv.dao.SaleDao;
import com.tajv.model.Client;
import com.tajv.model.Item;
import com.tajv.model.Order;
import com.tajv.model.Sale;

@Controller
public class OrderController {

	@Autowired
	private OrderDao orderDao;

	@Autowired
	private SaleDao saleDao;

	@Autowired
	private ItemDao itemDao;

	@Autowired
	private ClientDao clientDao;

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

	@RequestMapping(value = { "/sellingSaleOrOrder" })
	public String something(@ModelAttribute Order newOrder, HttpSession session) {
		Sale newSale = (Sale) session.getAttribute("newSale");
		System.out.println("Somehow I managed to get 85 too :o :O ");

		System.out
				.println("ORDER: estimatedDate: " + newOrder.getEstimatedDate() + " deposit: " + newOrder.getDeposit());
		System.out.println("SALE: items: " + newSale.getItems() + " sum: " + newSale.getSum());

		// visu pirma numazinam item'u kieki
		String ids[] = newSale.getItems().split(",");
		for (int i = 0; i < ids.length; i++) {
			try {
				int id = Integer.parseInt(ids[i]);
				System.out.println("Asd" + id);
				Item it = itemDao.getItemById(id);
				int amountt = it.getAmount() - 1;
				it.setAmount(amountt);
			} catch (Exception e) {
			}
		}

		// saugom sale ir order
		saleDao.saveSale(newSale);
		if (!newSale.getOrders().equals("null")) {
			orderDao.saveOrder(newOrder);
		}

		return "redirect:/reviewOrders";

	}

	@RequestMapping(value = { "/sellingItem" })
	public ModelAndView sellingItem(@ModelAttribute Sale newSale, HttpSession session) {
		ModelAndView model = new ModelAndView("AddPrescriptionToSale");

		ArrayList<Client> clients = new ArrayList<>();
		clients = (ArrayList<Client>) clientDao.getAllClients();
		// gal pasisortint pagal abecele pavarde.

		if (newSale.getOrders() == null) {
			newSale.setOrders("null");
		}
		session.setAttribute("newSale", newSale);

		model.addObject("newSale", newSale);
		model.addObject("clients", clients);
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
