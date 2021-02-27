package srv;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

import javax.annotation.Resource;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.beans.CounterRemote;
import com.beans.UserBeanRemote;
import com.dto.UserDTO;
import com.exceptions.RecordNonTrovatoException;

public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public UserServlet() {
		super();
	}

	@Resource(mappedName = "java:jboss/exported/negozioejbear/negozioejb/Counter!com.beans.CounterRemote")
	private CounterRemote counterRemote;

	private UserBeanRemote test() {
		try {
			Properties properties = new Properties();
			properties.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");
			properties.put(Context.PROVIDER_URL, "remote+http://localhost:8080/");
			InitialContext context = new InitialContext(properties);
			UserBeanRemote user = (UserBeanRemote) context
					.lookup("/negozioejbear/negozioejb/UserBean!com.beans.UserBeanRemote");
			return user;

		} catch (NamingException e) {
			e.printStackTrace();
		}
		return null;
	}

	private UserBeanRemote userBeanRemote = test();

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
			if (op.equals("login")) {
				doPost(request, response);// qua gia l i hai sulla url e' sbagliato.. 

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
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("err", e.getMessage());
			request.getRequestDispatcher("/ErroreGenerico.jsp").forward(request, response);

		}

		request.getRequestDispatcher(paginaDestinazione).forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String paginaDestinazione = "";
	
		paginaDestinazione = doLogin(request, response);
		request.getRequestDispatcher(paginaDestinazione).forward(request, response);

	}

	private String doInsert(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String page = "/success.jsp";
		UserDTO u = new UserDTO();

		String usr = request.getParameter("username");
		System.out.println("l'username inserito è :" + usr);
		String psw = request.getParameter("password");
		System.out.println("la pswd inserita è :" + psw);

		u.setUsername(usr);
		u.setPassword(psw);

		try {
			userBeanRemote.add(u);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return page;

	}

	private String doLogin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String page = "/index.jsp";
		String login = "/login.jsp";
		UserDTO u1 = new UserDTO();

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		System.out.println("l username " + username);
		System.out.println("la pass è " + password);
		String errore = "";

		try {
			u1 = userBeanRemote.Login(username, password);
			if (username == null || password == null || username.equals("") || password.equals("")) {
				String err = "Login e pass obbligatori";
				request.setAttribute("err", err);
				return "/login.jsp";
			}

			if (u1.getPassword().equals(password) && u1.getUsername().equals(username)) {
				HttpSession session = request.getSession();
				session.setAttribute("utenteLoggato", u1);
				return page;
			}
		} catch (RecordNonTrovatoException e) {
			System.err.println("Credenziali Sbagliate");

			e.printStackTrace();
		} catch (NamingException e) {
			e.printStackTrace();
		}

		catch (SQLException e) {
			System.err.println("Errore di db");
			e.printStackTrace();
		}
		request.setAttribute("err", errore);

		return login;
	}

	private String doOpDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String page = "/success.jsp";
		String errore = "";

		try {
			String id = request.getParameter("id");

			userBeanRemote.delete(Integer.valueOf(id));

			request.setAttribute("idUser", id);
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
		UserDTO u = new UserDTO();
		String page = "/UpdateUtente.jsp";

		String idStr = request.getParameter("id");
		System.out.println(idStr);

		try {
			int u1 = Integer.parseInt(idStr);
			u = userBeanRemote.findById(u1);

			request.setAttribute("utenteSelezionato", u);

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
		UserDTO u = new UserDTO();
		String page = "/success.jsp";
		try {
			String id = request.getParameter("id");
			String usr = request.getParameter("username");
			System.out.println("l'username è " + usr);
			String pwd = request.getParameter("password");
			System.out.println(pwd);

			u.setUsername(usr);
			u.setPassword(pwd);

			int id2 = Integer.parseInt(id);
			u.setId(id2);

			System.out.println("l'id scelto è " + id2);

			userBeanRemote.update(u);

		} catch (Exception e) {
			errore = "Contattare l'amministratore";
			e.printStackTrace();
			page = "/ErroreGenerico.jsp";
		}
		request.setAttribute("err", errore);
		return page;
	}

}
