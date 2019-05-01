package com.tajv.controller;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
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
				System.out.println("Asd" + id);
				Item it = itemDao.getItemById(id);
				int amountt = it.getAmount() - 1;
				it.setAmount(amountt);
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
			System.out.println("set to null");
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

	@RequestMapping(value = "/downloadPDF")
	public void getLogFile(HttpSession session, HttpServletResponse response) throws Exception {

		// Sale sale = (Sale) session.getAttribute("newSale");
		//
		// Document document = new Document();
		// PdfWriter.getInstance(document,
		// new
		// FileOutputStream("C:\\Users\\saban\\Desktop\\mokslai\\iTextHelloWorld.pdf"));
		// document.open();
		// Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
		// Chunk chunk = new Chunk("Hello World", font);
		//
		// document.add(chunk);
		// document.close();
		//
		ArrayList<Sale> sales = (ArrayList<Sale>) session.getAttribute("listForPdf");
		if (sales != null) {
			Document document = new Document();
			try {
				response.setContentType("application/pdf");
				PdfWriter.getInstance(document, response.getOutputStream());
				document.open();

				document.add(new Paragraph("howtodoinjava.com"));
				document.add(new Paragraph(new Date().toString()));

				PdfPTable table = new PdfPTable(3); // 3 columns.
				table.setWidthPercentage(100); // Width 100%
				table.setSpacingBefore(10f); // Space before table
				table.setSpacingAfter(10f); // Space after table

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

				// To avoid having the cell border and the content overlap, if you are having
				// thick cell borders
				// cell1.setUserBorderPadding(true);
				// cell2.setUserBorderPadding(true);
				// cell3.setUserBorderPadding(true);

				table.addCell(cell1);
				table.addCell(cell2);
				table.addCell(cell3);
				DecimalFormat df = new DecimalFormat("#.##");

				for (Sale sale : sales) {
					System.out.println("heyy" + sale.getId() + " " + sale.getDate());
					String timestamp = sale.getDate();
					String[] datetime = timestamp.split(" ");
					cell1 = new PdfPCell(new Paragraph(datetime[0]));
					cell2 = new PdfPCell(new Paragraph(datetime[1].substring(0, 5)));
					cell3 = new PdfPCell(new Paragraph(String.format("%.2f", sale.getSum()) + " €"));
					table.addCell(cell1);
					table.addCell(cell2);
					table.addCell(cell3);
				}
				document.add(table);

			} catch (Exception e) {
				e.printStackTrace();
			}
			document.close();
		}
	}

}
