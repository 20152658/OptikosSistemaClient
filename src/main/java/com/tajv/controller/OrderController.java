package com.tajv.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.tajv.dao.ClientDao;
import com.tajv.dao.ItemDao;
import com.tajv.dao.OrderDao;
import com.tajv.dao.SaleDao;
import com.tajv.model.Client;
import com.tajv.model.Item;
import com.tajv.model.Order;
import com.tajv.model.Sale;
import com.tajv.model.SalesDate;

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
	public ModelAndView reviewOrders(HttpServletRequest request, HttpSession session) {
		ArrayList<Sale> sales = (ArrayList<Sale>) saleDao.getAllSales();
		if (sales == null) {
			sales = new ArrayList<>();
		}
		ModelAndView model = new ModelAndView("ReviewOrders");

		session.setAttribute("listForPdf", sales);
		session.setAttribute("pdfDates", null);
		model.addObject("sales", sales);
		return model;
	}

	@RequestMapping(value = { "/sellingOrder" })
	public String sellingSaleOrOrder(@ModelAttribute Order newOrder, HttpSession session) {
		Sale newSale = (Sale) session.getAttribute("newSale");
		changeAmountOfItems(newSale.getItems().split(","));
		orderDao.saveOrder(newOrder);
		newSale.setOrders(newOrder.getId() + ",");
		saleDao.saveSale(newSale);

		return "redirect:/reviewOrders";
	}

	@RequestMapping(value = { "/sellingSale" })
	public String sellingSaleOrOrder(@ModelAttribute Sale sale, HttpSession session) {
		Sale newSale = (Sale) session.getAttribute("newSale");
		changeAmountOfItems(newSale.getItems().split(","));
		saleDao.saveSale(newSale);

		return "redirect:/reviewOrders";
	}

	private void changeAmountOfItems(String ids[]) {
		for (int i = 0; i < ids.length; i++) {
			try {
				int id = Integer.parseInt(ids[i]);
				if (id != 0) {
					Item it = itemDao.getItemById(id);
					int amountt = it.getAmount() - 1;
					it.setAmount(amountt);
				}
			} catch (Exception e) {
			}
		}
	}

	@RequestMapping(value = { "/sellingItem" })
	public ModelAndView sellingItem(@ModelAttribute Sale newSale, HttpSession session) {
		ModelAndView model = new ModelAndView("AddPrescriptionToSale");

		ArrayList<Client> clients = new ArrayList<>();
		clients = (ArrayList<Client>) clientDao.getAllClients();
		// gal pasisortint pagal abecele pavarde.

		System.out.println("fine then " + newSale.getOrders());
		if (newSale.getOrders().equals("")) {
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
		Double sum = new Double("0");
		for (String itemIdString : itemIds) {
			try {
				int itemId = Integer.parseInt(itemIdString);
				Item item = itemDao.getItemById(itemId);
				items.add(item);
				sum += item.getPrice();
			} catch (Exception e) {
				System.out.println("Something wrong in Order Controller reviewOrder try/catch");
			}
		}
		Order order = null;
		Client client = null;
		if (sale.getOrders() != null && !sale.getOrders().equals("null")) {
			Item item2 = itemDao.getItemById(0);
			item2.setPrice(sale.getSum() - sum);
			items.add(item2);
			try {
				int orderId = Integer.parseInt(sale.getOrders().split(",")[0]);
				order = orderDao.getOrderById(orderId);
				client = clientDao.getClientById(order.getClientId());
			} catch (Exception e) {
				System.out.println("Something wrong in Order Controller reviewOrder try/catch2");
			}
		}
		model.addObject("order", order);
		model.addObject("client", client);
		model.addObject("sale", sale);
		model.addObject("items", items);
		return model;
	}

	@RequestMapping(value = { "/changingOrderStatus" })
	public String changingOrderStatus(@ModelAttribute Order order, HttpSession session) {
		ModelAndView model = new ModelAndView("reviewOrders");
		if (order != null) {
			try {
				orderDao.updateOder(order);
				if (order.isInProgress()) {
					sendEmail();
				}
			} catch (Exception e) {
				System.out.println("Something wrong with changingOrderStatus try/catch");
			}
		}

		return "redirect:/reviewOrders";
	}

	public void sendEmail() {

	}

	@RequestMapping(value = { "/reviewOrdersFiltered" })
	public ModelAndView reviewOrder(@ModelAttribute SalesDate salesDate, HttpSession session) {
		ArrayList<Sale> sales = (ArrayList<Sale>) saleDao.getAllSales();
		if (sales == null) {
			sales = new ArrayList<>();
		}
		ArrayList<Sale> salesFiltered = new ArrayList<>();
		ModelAndView model = new ModelAndView("ReviewOrders");
		if (salesDate.getDateFrom().trim().equals("")) {
			salesDate.setDateFrom("1700-01-01");
		}
		if (salesDate.getDateTo().trim().equals("")) {
			salesDate.setDateTo("4000-12-31");
		}

		if (salesDate.getDateFrom() != null && salesDate.getDateTo() != null) {
			for (Sale sale : sales) {
				String dateS[] = sale.getDate().split(" ")[0].split("-");
				String yearS = dateS[0];
				String monthS = dateS[1];
				String dayS = dateS[2];
				try {
					int year = Integer.parseInt(yearS);
					int month = Integer.parseInt(monthS);
					int day = Integer.parseInt(dayS);

					int yearTo = Integer.parseInt(salesDate.getDateTo().split("-")[0]);
					int monthTo = Integer.parseInt(salesDate.getDateTo().split("-")[1]);
					int dayTo = Integer.parseInt(salesDate.getDateTo().split("-")[2]);

					int yearFrom = Integer.parseInt(salesDate.getDateFrom().split("-")[0]);
					int monthFrom = Integer.parseInt(salesDate.getDateFrom().split("-")[1]);
					int dayFrom = Integer.parseInt(salesDate.getDateFrom().split("-")[2]);

					int date = day + month * 100 + year * 10000;
					int dateTo = dayTo + monthTo * 100 + yearTo * 10000;
					int dateFrom = dayFrom + monthFrom * 100 + yearFrom * 10000;

					if (date >= dateFrom && date <= dateTo) {
						salesFiltered.add(sale);
					}

				} catch (Exception e) {
					System.out.println("Something really wrong with dates :)");
				}

			}
			session.setAttribute("listForPdf", salesFiltered);
			session.setAttribute("pdfDates", salesDate);
			model.addObject("sales", salesFiltered);
		}

		return model;
	}

	@RequestMapping(value = "/downloadPDF")
	public void getLogFile(HttpSession session, HttpServletResponse response) throws Exception {

		ArrayList<Sale> sales = (ArrayList<Sale>) session.getAttribute("listForPdf");
		SalesDate saleDates = (SalesDate) session.getAttribute("pdfDates");

		Document document = new Document();
		Double sum = new Double("0");
		try {
			response.setContentType("application/pdf");
			PdfWriter.getInstance(document, response.getOutputStream());
			document.open();
			if (sales != null && sales.size() != 0) {
				Font heading = new Font(FontFamily.TIMES_ROMAN, 30.0f, Font.BOLD);

				Paragraph ataskaita = new Paragraph("Ataskaita", heading);
				ataskaita.setAlignment(Element.ALIGN_CENTER);
				document.add(ataskaita);
				if (saleDates != null && !(saleDates.getDateFrom().equals("1700-01-01")
						|| saleDates.getDateTo().equals("4000-12-31"))) {
					Paragraph period = new Paragraph(
							"Laikotarpis " + saleDates.getDateFrom() + " - " + saleDates.getDateTo());
					period.setAlignment(Element.ALIGN_CENTER);
					document.add(period);
				}

				PdfPTable table = new PdfPTable(3); // 3 columns.
				table.setWidthPercentage(100); // Width 100%
				table.setSpacingBefore(20f); // Space before table

				// Set Column widths
				float[] columnWidths = { 1f, 1f, 1f };
				table.setWidths(columnWidths);

				PdfPCell cell1 = new PdfPCell(new Paragraph("Data"));
				cell1.setPaddingLeft(10);
				cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell1.setVerticalAlignment(Element.ALIGN_MIDDLE);

				PdfPCell cell2 = new PdfPCell(new Paragraph("Laikas"));
				cell2.setPaddingLeft(10);
				cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);

				PdfPCell cell3 = new PdfPCell(new Paragraph("Suma"));
				cell3.setPaddingLeft(10);
				cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell3.setVerticalAlignment(Element.ALIGN_MIDDLE);

				cell1.setUseBorderPadding(true);
				cell2.setUseBorderPadding(true);
				cell3.setUseBorderPadding(true);

				table.addCell(cell1);
				table.addCell(cell2);
				table.addCell(cell3);

				for (Sale sale : sales) {
					String timestamp = sale.getDate();
					String[] datetime = timestamp.split(" ");
					cell1 = new PdfPCell(new Paragraph(datetime[0]));
					cell2 = new PdfPCell(new Paragraph(datetime[1].substring(0, 5)));
					cell3 = new PdfPCell(new Paragraph(String.format("%.2f", sale.getSum()) + " €"));
					table.addCell(cell1);
					table.addCell(cell2);
					table.addCell(cell3);
					sum += sale.getSum();
				}

				document.add(table);

				Font totalF = new Font(FontFamily.TIMES_ROMAN, 12f, Font.BOLD);
				Paragraph totalP = new Paragraph("Iš viso: " + String.format("%.2f", sum) + " €", totalF);
				totalP.setAlignment(Element.ALIGN_RIGHT);
				document.add(totalP);

			} else {
				String laikotarpis = "";
				if (saleDates != null && !(saleDates.getDateFrom().equals("1700-01-01")
						|| saleDates.getDateTo().equals("4000-12-31"))) {
					laikotarpis = "laikotarpiu " + saleDates.getDateFrom() + " - " + saleDates.getDateTo() + " ";

				}
				Paragraph noInfo = new Paragraph("Pardavimų " + laikotarpis + "nerasta");
				document.add(noInfo);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		document.close();

	}

}
