package srv;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.beans.CounterRemote;
import com.beans.ProductBeanRemote;
import com.dto.ProductDTO;

public class ProductServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public ProductServlet() {
		super();
	}

	@Resource(mappedName = "java:jboss/exported/negozioejbear/negozioejb/ProductBean!com.beans.ProductBeanRemote")
	private ProductBeanRemote productBeanRemote;

	@Resource(mappedName = "java:jboss/exported/negozioejbear/negozioejb/Counter!com.beans.CounterRemote")
	private CounterRemote counterRemote;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("--> stai nel doGet di UTENTE SRV <--");
		System.out.println("**************************");
		String op = request.getParameter("op");
		String paginaDestinazione = "";
		System.out.println("stai nel metodo ->" + op);
		System.out.println("");
		System.out.println("CONTA VISITE : ");
		counterRemote.inc();
		try {

			if (op.equals("delete")) {
				paginaDestinazione = doOpDelete(request, response);
			}
			if (op.equals("new")) {
				paginaDestinazione = doInsert(request, response);
			}
			
			if (op.equals("update")) {
				paginaDestinazione = doUpdate(request, response);
			}
			if (op.equals("preUpdate")) {
				paginaDestinazione = doPreUpdate(request, response);
			}
			if (op.equals("findByName")) {
				paginaDestinazione = doFindByName(request, response);
			}
			if (op.equals("findByDesc")) {
				paginaDestinazione = doFindByDesc(request, response);
			}
			if (op.equals("list")) {
				paginaDestinazione = doListAll(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("err", e.getMessage());
			request.getRequestDispatcher("/ErroreGenerico.jsp").forward(request, response);

		}

		request.getRequestDispatcher(paginaDestinazione).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	private String doInsert(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String page = "/Product?i&op=list";
		ProductDTO p = new ProductDTO();
		String name = request.getParameter("name");
		System.out.println("il nome inserito del prodotto :" + name);
		String desc = request.getParameter("description");
		String pricestr = request.getParameter("price");
		Double price = Double.parseDouble(pricestr);
		System.out.println("la descrizione  è :" + desc + ", il prezzo : " + price);
		p.setName(name);
		p.setDescription(desc);
		p.setPrezzo(price);

		try {
			productBeanRemote.add(p);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return page;

	}

	private String doOpDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String page = "/success.jsp";
		String errore = "";

		try {
			String id = request.getParameter("id");

			productBeanRemote.delete(Integer.valueOf(id));

			request.setAttribute("productId", id);
			return page;
		} catch (NumberFormatException e) {

			e.printStackTrace();
			page = "/ErroreGenerico.jsp";

		} catch (SQLException e) {
			errore = "Errore durante l'esecuzione della Query";
			e.printStackTrace();

			page = "/ErroreGenerico.jsp";
		} catch (Exception e) {
			e.printStackTrace();
			errore = "Contattare l'amministratore";
			page = "/ErroreGenerico.jsp";
		}
		request.setAttribute("err", errore);

		return page;

	}

	private String doPreUpdate(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String errore = "";
		ProductDTO p = new ProductDTO();
		String page = "/UpdateProduct.jsp";

		String idStr = request.getParameter("id");
		System.out.println(idStr);

		try {
			int u1 = Integer.parseInt(idStr);
			p = productBeanRemote.findById(u1);

			request.setAttribute("prodottoSelezionato", p);

		} catch (Exception e) {
			errore = "Contattare l'amministratore";
			page = "/ErroreGenerico.jsp"; // in questo caso uso un errore generico APPOSTA Perche' nn e' l update e' il
											// passsaggio prima
		}
		request.setAttribute("err", errore);

		return page;
	}

	private String doUpdate(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String errore = "";
		ProductDTO p = new ProductDTO();
		String page = "/success.jsp";
		try {
			String id = request.getParameter("id");
			String name = request.getParameter("name");
			String desc = request.getParameter("description");
			String pricestr = request.getParameter("price");
			Double price = Double.parseDouble(pricestr);

			int id2 = Integer.parseInt(id);
			p.setId(id2);
			System.out.println("id" + id2 + ", name= " + name + "descrizione " + desc + "prezzo: " + price);

			p.setName(name);
			p.setDescription(desc);
			p.setPrezzo(price);

			productBeanRemote.update(p);

		} catch (Exception e) {
			errore = "Contattare l'amministratore";
			e.printStackTrace();
			page = "/ErroreGenerico.jsp";
		}
		request.setAttribute("err", errore);
		return page;
	}

	private String doFindByName(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String page = "/foundproducts.jsp";
		String lista = "/productlist.jsp";
		try {
			String name = request.getParameter("name");
			List<ProductDTO> listaProducts;
			System.out.println("il nome che hai cercato " + name);
			listaProducts = productBeanRemote.findByName(name);
			request.setAttribute("prodottiTrovati", listaProducts);
			return page;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}

	private String doListAll(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String page = "";

		try {
			ArrayList<ProductDTO> listaProducts = productBeanRemote.findAll();

			System.out.println("prodotti presenti in totale =  " + listaProducts.size());
			request.setAttribute("elencoprodotti", listaProducts);
			return "/productlist.jsp";
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return page;
	}
	
	private String doFindByDesc(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String page = "/foundproducts.jsp";
		String lista = "/productlist.jsp";
		try {
			String desc = request.getParameter("description");
			List<ProductDTO> listaProducts;
			System.out.println("la desc che hai cercato " + desc);
			listaProducts = productBeanRemote.findByDesc(desc);
			request.setAttribute("prodottiTrovati", listaProducts);
			return page;
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("errore di DB");
		}
		return lista;
	}
	
	
	



}
