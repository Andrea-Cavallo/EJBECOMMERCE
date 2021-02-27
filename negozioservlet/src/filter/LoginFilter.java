package filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dto.UserDTO;

public class LoginFilter implements Filter {

	public void init(FilterConfig arg0) throws ServletException {

	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		HttpSession session = req.getSession();

		if (session.getAttribute("utenteLoggato") == null) {
			req.getRequestDispatcher("login.jsp").include(req, res);
			return;
		}

		try {
			UserDTO us = (UserDTO) session.getAttribute("utenteLoggato");

			String param = request.getParameter("name");
			// filtro che intercetta le jsp *jsp e qua lo applichi solo sulle srv
			// unop applicato all amm uno all utente se entro sull srv come utente e chiamo diretto la srv d admi 
			// mi fa il return o mi ributta sull apagina di login e viceversa - nn te faccio vedere niente al contrario
			// separando logicamente l accesso alle Srvlt . -. jsp bloccante su tt e filtro stesso filtro slle 2 srvlt
			// 
			String url = ((HttpServletRequest) request).getRequestURL().toString();
			if (url.equals("quelle che l amm puo vedere di jsp") && param.equals("admin")) {
				chain.doFilter(request, response);// e vai avanti .

			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	public void destroy() {

	}

}