package com.tajv.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
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
import com.tajv.dao.EmployeeDao;
import com.tajv.dao.ItemDao;
import com.tajv.dao.OrderDao;
import com.tajv.dao.SaleDao;
import com.tajv.model.Client;
import com.tajv.model.Employee;
import com.tajv.model.Item;
import com.tajv.model.Order;
import com.tajv.model.Sale;
import com.tajv.model.SalesFiltered;

@Controller
public class OrderController {

	@Autowired
	private OrderDao orderDao;

	@Autowired
	private SaleDao saleDao;

	@Autowired
	private ItemDao itemDao;

	@Autowired
	private EmployeeDao employeeDao;

	@Autowired
	private ClientDao clientDao;

	@RequestMapping(value = { "/newOrder" })
	public ModelAndView newOrder(HttpServletRequest request, HttpSession session) {
		if (session.getAttribute("Darbuotojas") == null) {
			return new ModelAndView("redirect:/login");
		} else {
			List<Item> items = new ArrayList<>();
			items = itemDao.getAllItems();
			if (items.size() != 0) {
				items.remove(0);
			}

			ModelAndView model = new ModelAndView("NewOrder");
			model.addObject("item", items);
			return model;
		}
	}

	@RequestMapping(value = { "/reviewOrders" })
	public ModelAndView reviewOrders(HttpServletRequest request, HttpSession session) {
		if (session.getAttribute("Darbuotojas") == null) {
			return new ModelAndView("redirect:/login");
		} else {
			ArrayList<Sale> sales = (ArrayList<Sale>) saleDao.getAllSales();
			if (sales == null) {
				sales = new ArrayList<>();
			}
			ModelAndView model = new ModelAndView("ReviewOrders");

			SalesFiltered salesFiltered = new SalesFiltered();
			salesFiltered.setShowCompletedOrders(true);
			salesFiltered.setShowOrders(true);
			salesFiltered.setShowOrdersInProgress(true);
			salesFiltered.setShowSales(true);
			session.setAttribute("listForPdf", sales);
			session.setAttribute("pdfDates", salesFiltered);
			model.addObject("sales", sales);
			model.addObject("salesFiltered", salesFiltered);
			return model;
		}
	}

	@RequestMapping(value = { "/sellingOrder" })
	public String sellingSaleOrOrder(@ModelAttribute Order newOrder, HttpSession session) {
		Sale newSale = (Sale) session.getAttribute("newSale");
		changeAmountOfItems(newSale.getItems().split(","), true, false);
		orderDao.saveOrder(newOrder);
		Employee seller = (Employee) session.getAttribute("Darbuotojas");
		newSale.setSeller(seller.getNickname());
		newSale.setOrders(newOrder.getId() + ",");
		saleDao.saveSale(newSale);

		return "redirect:/reviewOrders";
	}

	@RequestMapping(value = { "/sellingSale" })
	public String sellingSaleOrOrder(@ModelAttribute Sale sale, HttpSession session) {
		Sale newSale = (Sale) session.getAttribute("newSale");
		changeAmountOfItems(newSale.getItems().split(","), false, false);
		Employee seller = (Employee) session.getAttribute("Darbuotojas");
		newSale.setSeller(seller.getNickname());
		saleDao.saveSale(newSale);
		return "redirect:/reviewOrders";
	}

	private void changeAmountOfItems(String ids[], Boolean order, Boolean removeOrder) {
		for (int i = 0; i < ids.length; i++) {
			try {
				int id = Integer.parseInt(ids[i]);
				if (id != 0) {
					Item it = itemDao.getItemById(id);
					if (order) {
						if (removeOrder) {
							if (it.getType().equals("Remeliai") || it.getType().equals("Saules akiniai")) {
								System.out.println("deleting Reserved");
								int amount = it.getReserved() - 1;
								it.setReserved(amount);
								int amountt = it.getAmount() - 1;
								it.setAmount(amountt);
							}
						} else {
							if (it.getType().equals("Remeliai") || it.getType().equals("Saules akiniai")) {
								System.out.println("reserving");
								int amount = it.getReserved() + 1;
								it.setReserved(amount);
							} else {
								int amount = it.getAmount() - 1;
								it.setAmount(amount);
							}
						}
					} else {
						int amount = it.getAmount() - 1;
						it.setAmount(amount);
					}
					itemDao.updateItem(it);
				}
			} catch (Exception e) {
				System.out.println("Something happened while change amount of items. ");
			}
		}
	}

	@RequestMapping(value = { "/sellingItem" })
	public ModelAndView sellingItem(@ModelAttribute Sale newSale, HttpSession session) {
		if (session.getAttribute("Darbuotojas") == null) {
			return new ModelAndView("redirect:/login");
		} else {
			ModelAndView model = new ModelAndView("AddPrescriptionToSale");

			ArrayList<Client> clients = new ArrayList<>();
			clients = (ArrayList<Client>) clientDao.getAllClients();
			// gal pasisortint pagal abecele pavarde.
			if (newSale.getOrders().equals("")) {
				newSale.setOrders("null");
			}

			ArrayList<Item> items = new ArrayList<Item>();
			String[] itemIds = newSale.getItems().split(",");
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
			if (newSale.getOrders() != null && !newSale.getOrders().equalsIgnoreCase("null")) {
				Item item2 = itemDao.getItemById(0);
				item2.setPrice(newSale.getSum() - sum);
				items.add(item2);
			}

			session.setAttribute("newSale", newSale);

			model.addObject("items", items);
			model.addObject("newSale", newSale);
			model.addObject("clients", clients);
			return model;
		}
	}

	@RequestMapping(value = { "/reviewOrder" })
	public ModelAndView reviewOrder(@RequestParam("saleId") int id, HttpSession session) {
		if (session.getAttribute("Darbuotojas") == null) {
			return new ModelAndView("redirect:/login");
		} else {
			ModelAndView model = new ModelAndView("ReviewOrder");
			Sale sale = saleDao.getSale(id);
			Employee emp = employeeDao.getEmployeeByNickname(sale.getSeller());
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
			if (sale.getOrders() != null && !sale.getOrders().equalsIgnoreCase("NULL")) {
				System.out.println("uzejau i if id: " + sale.getId());
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
			model.addObject("employee", emp);
			model.addObject("order", order);
			model.addObject("client", client);
			model.addObject("sale", sale);
			model.addObject("items", items);
			return model;
		}
	}

	private Order getOrderFromSale(Sale sale) {
		Order order = null;
		if (sale.getOrders() != null && !sale.getOrders().equalsIgnoreCase("null")) {
			try {
				int orderId = Integer.parseInt(sale.getOrders().split(",")[0]);
				order = orderDao.getOrderById(orderId);
			} catch (Exception e) {
				System.out.println("Cannot get orderFromSale (OrderController)");
			}
		}
		return order;
	}

	@RequestMapping(value = { "/changingOrderStatus" })
	public String changingOrderStatus(@ModelAttribute Order order, HttpSession session) {
		ModelAndView model = new ModelAndView("reviewOrders");
		if (order != null) {
			try {
				orderDao.updateOder(order);
				System.out.println("progress: " + order.isInProgress());
				if (order.isInProgress()) {
					String recipient = clientDao.getClientById(order.getClientId()).getEmail();
					if (recipient != null && recipient.contains("@"))
						sendEmail(recipient);
				}
				if (order.isCompleted()) {
					removeReserved(order.getId() + ",");
				}
			} catch (Exception e) {
				System.out.println("Something wrong with changingOrderStatus try/catch");
			}
		}

		return "redirect:/reviewOrders";
	}

	private void sendEmail(String recipient) {
		final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
		// Get a Properties object
		Properties props = System.getProperties();
		props.setProperty("mail.smtp.host", "smtp.gmail.com");
		props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
		props.setProperty("mail.smtp.socketFactory.fallback", "false");
		props.setProperty("mail.smtp.port", "465");
		props.setProperty("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.auth", "true");
		props.put("mail.debug", "true");
		props.put("mail.store.protocol", "pop3");
		props.put("mail.transport.protocol", "smtp");
		final String username = "akiuoptikabaigiamasis@gmail.com";//
		final String password = "bakalauras";
		try {
			Session session = Session.getDefaultInstance(props, new Authenticator() {
				@Override
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(username, password);
				}
			});

			// -- Create a new message --
			Message msg = new MimeMessage(session);

			// -- Set the FROM and TO fields --
			msg.setFrom(new InternetAddress("akiuoptikabaigiamasis@gmail.com"));
			msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient, false));
			msg.setSubject("Pagamintas užsakymas optikoje ");
			msg.setText("Sveiki,\n" + "Užsakymas buvo pagamintas, galite atsiimti prekes adresu Pavyzdine g.123A.\n"
					+ "Darbo laikas: darbo dienomis 9:00 - 17:00." + " \n" + "Geros dienos! ");
			msg.setSentDate(new Date());
			Transport.send(msg);
			System.out.println("Message sent.");
		} catch (MessagingException e) {
			System.out.println("Erreur d'envoi, cause: " + e);
		}

	}

	private void removeReserved(String orderNo) {
		List<Sale> sales = saleDao.getAllSales();
		for (Sale sale : sales) {
			if (sale.getOrders().equals(orderNo)) {
				changeAmountOfItems(sale.getItems().split(","), true, true);
			}
		}
	}

	private Boolean nvl(Boolean maybeNull, Boolean val) {
		if (maybeNull == null) {
			return val;
		} else {
			return maybeNull;
		}
	}

	@RequestMapping(value = { "/reviewOrdersFiltered" })
	public ModelAndView reviewOrder(@ModelAttribute SalesFiltered salesFiltered, HttpSession session) {
		if (session.getAttribute("Darbuotojas") == null) {
			return new ModelAndView("redirect:/login");
		} else {
			ArrayList<Sale> sales = (ArrayList<Sale>) saleDao.getAllSales();
			if (sales == null) {
				sales = new ArrayList<>();
			}
			String dateFrom = salesFiltered.getDateFrom().trim();
			String dateTo = salesFiltered.getDateTo().trim();
			ArrayList<Sale> salesAndOrdersFiltered = new ArrayList<>();
			ModelAndView model = new ModelAndView("ReviewOrders");
			if (salesFiltered.getDateFrom().trim().equals("")) {
				dateFrom = "1700-01-01";
			}
			if (salesFiltered.getDateTo().trim().equals("")) {
				dateTo = "4000-12-31";
			}
			Boolean showSales = nvl(salesFiltered.getShowSales(), false);
			Boolean showOrders = nvl(salesFiltered.getShowOrders(), false);
			Boolean showDone = nvl(salesFiltered.getShowCompletedOrders(), false);
			Boolean showInProgress = nvl(salesFiltered.getShowOrdersInProgress(), false);
			System.out.print("showSales: " + showSales + " showOrders " + showOrders + " showDone " + showDone
					+ " showInProgress " + showInProgress);

			for (Sale sale : sales) {
				String dateS[] = sale.getDate().split(" ")[0].split("-");
				String yearS = dateS[0];
				String monthS = dateS[1];
				String dayS = dateS[2];
				try {
					int year = Integer.parseInt(yearS);
					int month = Integer.parseInt(monthS);
					int day = Integer.parseInt(dayS);

					int yearTo = Integer.parseInt(dateTo.split("-")[0]);
					int monthTo = Integer.parseInt(dateTo.split("-")[1]);
					int dayTo = Integer.parseInt(dateTo.split("-")[2]);

					int yearFrom = Integer.parseInt(dateFrom.split("-")[0]);
					int monthFrom = Integer.parseInt(dateFrom.split("-")[1]);
					int dayFrom = Integer.parseInt(dateFrom.split("-")[2]);

					int date = day + month * 100 + year * 10000;
					int dateToInt = dayTo + monthTo * 100 + yearTo * 10000;
					int dateFromInt = dayFrom + monthFrom * 100 + yearFrom * 10000;

					if (date >= dateFromInt && date <= dateToInt) {
						if (showSales) { // tada rodom pardavimus - t.y. order is null
							if (sale.getOrders() == null || sale.getOrders().equalsIgnoreCase("null")) {
								salesAndOrdersFiltered.add(sale);
							}
						}
						if (showOrders) {// tada rodom orders - t.y. order ne null
							Order order = getOrderFromSale(sale);
							if (order != null) {
								if (showDone) {// ar tik completed rodyt.
									if (order.isCompleted()) {
										salesAndOrdersFiltered.add(sale);
									}
								}
								if (showInProgress) { // patikrint ar tuos in progress
									if (!order.isCompleted()) {
										salesAndOrdersFiltered.add(sale);
									}
								}
							}

						}
					}

				} catch (Exception e) {
					System.out.println("Something really wrong with dates :)");
				}

			}
			session.setAttribute("listForPdf", salesAndOrdersFiltered);
			session.setAttribute("pdfDates", salesFiltered);
			model.addObject("salesFiltered", salesFiltered);
			model.addObject("sales", salesAndOrdersFiltered);

			return model;
		}
	}

	@RequestMapping(value = "/downloadPDF")
	public void getPDFReport(HttpSession session, HttpServletResponse response) throws Exception {

		ArrayList<Sale> sales = (ArrayList<Sale>) session.getAttribute("listForPdf");
		SalesFiltered saleDates = (SalesFiltered) session.getAttribute("pdfDates");

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
				if (saleDates != null && saleDates.getDateTo() != null && saleDates.getDateFrom() != null
						&& !(saleDates.getDateFrom().trim().equals("") || saleDates.getDateTo().trim().equals(""))) {
					Paragraph period = new Paragraph(
							"Laikotarpis " + saleDates.getDateFrom() + " - " + saleDates.getDateTo());
					period.setAlignment(Element.ALIGN_CENTER);
					document.add(period);
				}
				Boolean showOrders = nvl(saleDates.getShowOrders(), false);
				Boolean showSales = nvl(saleDates.getShowSales(), false);
				Boolean showProgress = nvl(saleDates.getShowOrdersInProgress(), false);
				Boolean showCompleted = nvl(saleDates.getShowCompletedOrders(), false);
				if (!showOrders) {
					if (showSales) {
						Paragraph noOrders = new Paragraph("Pardavimu ataskaita (be uzsakymu)");
						noOrders.setAlignment(Element.ALIGN_CENTER);
						document.add(noOrders);
					} else {
						Paragraph noOrdersNoSales = new Paragraph("Nepasirinkta nei pardavimu, nei uzsakymu");
						noOrdersNoSales.setAlignment(Element.ALIGN_CENTER);
						document.add(noOrdersNoSales);
					}
				} else {
					if (showSales) { // yr orderiu, yr sales
						if (showCompleted) {
							if (showProgress) {
								// yra sale, visi orderiai.
								Paragraph noOrdersNoSales = new Paragraph("Visi pardavimai ir uzsakymai");
								noOrdersNoSales.setAlignment(Element.ALIGN_CENTER);
								document.add(noOrdersNoSales);
							} else {
								// yra sale, tik completed orders.
								Paragraph noOrdersNoSales = new Paragraph("Visi pardavimai ir baigti uzsakymai");
								noOrdersNoSales.setAlignment(Element.ALIGN_CENTER);
								document.add(noOrdersNoSales);
							}
						} else { //
							if (showProgress) {
								// yra sale, tik in progress orders
								Paragraph noOrdersNoSales = new Paragraph(
										"Visi pardavimai ir pagaminti, bet nebaigti uzsakymai");
								noOrdersNoSales.setAlignment(Element.ALIGN_CENTER);
								document.add(noOrdersNoSales);
							} else {
								// yra sale, jokiu orders.
								Paragraph noOrdersNoSales = new Paragraph("Visi pardavimai(be uzsakymu)");
								noOrdersNoSales.setAlignment(Element.ALIGN_CENTER);
								document.add(noOrdersNoSales);
							}
						}
					} else { // yr orderiu, ner sales
						if (showCompleted) {
							if (showProgress) {
								// jokiu sale, visi orderiai.
								Paragraph noOrdersNoSales = new Paragraph("Visi uzsakymai(be pardavimu)");
								noOrdersNoSales.setAlignment(Element.ALIGN_CENTER);
								document.add(noOrdersNoSales);
							} else {
								// jokiu sale, tik completed orders.
								Paragraph noOrdersNoSales = new Paragraph("Visi uzbaigti uzsakymai(be pardavimu)");
								noOrdersNoSales.setAlignment(Element.ALIGN_CENTER);
								document.add(noOrdersNoSales);
							}
						} else { //
							if (showProgress) {
								// jokiu sale, tik in progress orders
								Paragraph noOrdersNoSales = new Paragraph("Visi gaminami uzsakymai(be pardavimu)");
								noOrdersNoSales.setAlignment(Element.ALIGN_CENTER);
								document.add(noOrdersNoSales);
							} else {
								Paragraph noOrdersNoSales = new Paragraph("Nepasirinkta nei pardavimu, nei uzsakymu");
								noOrdersNoSales.setAlignment(Element.ALIGN_CENTER);
								document.add(noOrdersNoSales);
								// jokiu sale, jokiu orders.
							}
						}
					}
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
				if (saleDates != null && saleDates.getDateTo() != null && saleDates.getDateFrom() != null
						&& !(saleDates.getDateFrom().trim().equals("") || saleDates.getDateTo().trim().equals(""))) {
					laikotarpis = "laikotarpiu " + saleDates.getDateFrom() + " - " + saleDates.getDateTo() + " ";

				}
				Paragraph noInfo = new Paragraph("Pardavimu " + laikotarpis + "nerasta");
				document.add(noInfo);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		document.close();

	}

}
