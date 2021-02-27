package srv;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.beans.OrderBeanRemote;
import com.beans.ProductBeanRemote;
import com.beans.UserBeanRemote;
import com.dto.OrderDTO;
import com.dto.ProductDTO;
import com.dto.UserDTO;

public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CartServlet() {
		super();
	}

	@Resource(mappedName = "java:jboss/exported/negozioejbear/negozioejb/OrderBean!com.beans.OrderBeanRemote")
	private OrderBeanRemote orderBeanRemote;

	@Resource(mappedName = "java:jboss/exported/negozioejbear/negozioejb/ProductBean!com.beans.ProductBeanRemote")
	private ProductBeanRemote productBeanRemote;

	@Resource(mappedName = "java:jboss/exported/negozioejbear/negozioejb/UserBean!com.beans.UserBeanRemote")
	private UserBeanRemote userBeanRemote;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("--> stai nel doGet di CART SERVLET <--");
		System.out.println("**************************");
		String op = request.getParameter("op");
		String paginaDestinazione = "";
		System.out.println("stai nel metodo ->" + op);
		System.out.println("");
		try {
			if (op.equals("delete")) {
				paginaDestinazione = doOpDelete(request, response);
			}
			if (op.equals("aggiungi")) {
				paginaDestinazione = doInsert(request, response);
			}
			if (op.equals("new")) {
				paginaDestinazione = doAdd(request, response);

			}
			if (op.equals("discount")) {
				paginaDestinazione = doDiscount(request, response);

			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("err", e.getMessage());
			request.getRequestDispatcher("/ErroreGenerico.jsp").forward(request, response);
		}
		response.sendRedirect(request.getContextPath() + paginaDestinazione);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

	@SuppressWarnings("unchecked")
	private String doInsert(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String errore = "";
		String page = "/cart.jsp";
		// creo qua il carrello
		ArrayList<ProductDTO> ap = new ArrayList<>();

		if (request.getSession().getAttribute("carrelloutente") != null) {
			// se esiste la prendo senno la creo io
			ap = (ArrayList<ProductDTO>) request.getSession().getAttribute("carrelloutente");
		}
		// cerco il prodotto p per id .-
		String id = request.getParameter("id");
		Integer id2 = Integer.parseInt(id);
		System.out.println("L Id del prodotto è, " + id2);

		ProductDTO p = productBeanRemote.findById(id2);
		System.out.println(p.getId());
		ap.add(p);
		System.out.println("Ho trovato il prodotto con descrizione: " + p.getDescription());

		request.getSession().setAttribute("carrelloutente", ap);

		double tot = doTot(ap);
		request.getSession().setAttribute("totale", tot);

		request.setAttribute("err", errore);

		return page;
	}

	private String doAdd(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

//		String errore = "";
		String page = "/success.jsp";
		try {
			OrderDTO o = new OrderDTO();
			UserDTO u = (UserDTO) request.getSession().getAttribute("utenteLoggato");
//			User u = (User) session.getAttribute("utenteLoggato");

			@SuppressWarnings("unchecked")
			ArrayList<ProductDTO> ap = (ArrayList<ProductDTO>) request.getSession().getAttribute("carrelloutente");
			double p = doTot(ap);
			o.setId_user(u.getId());
			System.out.println("l id trovato con la REQUEST..." + u.getId());

			o.setTotalprice(p);
			orderBeanRemote.add(o);
		} catch (SQLException e) {
			e.printStackTrace();
		}

//		request.setAttribute("err", errore);

		return page;
	}

	@SuppressWarnings("unchecked")
	private String doOpDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

//		String errore = "";
		String page = "/cart.jsp"; // la Jsp in caso di successo

		ArrayList<ProductDTO> ap = new ArrayList<>();
		if (request.getSession().getAttribute("carrelloutente") != null) {
			ap = (ArrayList<ProductDTO>) request.getSession().getAttribute("carrelloutente");
		}
		String id = request.getParameter("id");
		Integer id2 = (Integer.valueOf(id));
		System.out.println("l id e' " + id2);
		ProductDTO p = productBeanRemote.findById(id2);

		System.out.println(ap);
		System.out.println("la dimensione dell arraylist per verifica prima della rimozione " + ap.size());
		double tot = doTot(ap) - p.getPrice();
		System.out.println(tot);
		ap.remove(p);
		System.out.println("la dimensione dell arraylist per verifica dopo la rimozione " + ap.size());
		request.getSession().setAttribute("carrelloutente", ap);
//		request.setAttribute("err", errore);

		request.getSession().setAttribute("totale", tot);

		return page;
	}

	private double doTot(ArrayList<ProductDTO> ap) {
		int qnt = ap.size();
		double tot = 0;
		for (int i = 0; i < qnt; i++) {
			tot += ap.get(i).getPrice();
		}
		System.out.println("il totale è" + tot);
		return tot;
	}

	private String doDiscount(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

//		String errore = "";
		String page = "/success.jsp";
		try {
			OrderDTO o = new OrderDTO();
			UserDTO u = (UserDTO) request.getSession().getAttribute("utenteLoggato");
//			User u = (User) session.getAttribute("utenteLoggato");
			@SuppressWarnings("unchecked")
			ArrayList<ProductDTO> ap = (ArrayList<ProductDTO>) request.getSession().getAttribute("carrelloutente");
			int d = 0;
			double totale = doTot(ap);

			String discount = request.getParameter("discount");
			System.out.println("il codice inserito di discount e' " +  discount);

			if (discount.equalsIgnoreCase("20off")) {
				d = 20;
			}
			if (discount.equalsIgnoreCase("50off")) {
				d = 50;
			}
			else d = 1;
			double p = (totale/(100)) * d;
			double totaleScontato = totale-p;
			
			System.out.println("il nuovo prezzo con il discount è" +totaleScontato);
			o.setId_user(u.getId());
			System.out.println("l id trovato con la REQUEST..." + u.getId());

			o.setTotalprice(totaleScontato);
			request.getSession().setAttribute("totaleScontato", totaleScontato);

			orderBeanRemote.add(o);
		} catch (SQLException e) {
			e.printStackTrace();
		}

//		request.setAttribute("err", errore);

		return page;
	}

}
