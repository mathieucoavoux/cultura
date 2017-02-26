package fr.comprehensiveit.cultura;

import java.io.IOException;
import java.security.GeneralSecurityException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.comprehensiveit.cultura.model.GoogleBookWsMap;

/**
 * Servlet implementation class ClientWS
 */
public class ClientWS extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(ClientWS.class);   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ClientWS() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		logger.debug("Entered in ClientWS doGet");
		GoogleBookWsMap gBookWsMap;
		
		try {
			gBookWsMap = new GoogleBookWsMap();
			//response.getWriter().append("Served at: ").append(request.getContextPath());
			response.getWriter().append(gBookWsMap.searchById("Cambodia"));
			//response = gBookWsMap.searchById("Cambodia");
		} catch (GeneralSecurityException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
