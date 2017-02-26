package fr.comprehensiveit.cultura;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.comprehensiveit.cultura.model.GoogleBookWsMap;

/**
 * Servlet implementation class ClientWSServlet
 */
public class ClientWSServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(ClientWSServlet.class);
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ClientWSServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		logger.info("Received a GET request");
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		Enumeration enumeration = request.getParameterNames();
	    if(enumeration == null) {
	    	logger.debug("No parameter received");
	    	response.getWriter().append("No parameters received");
	    	return;
	    }
	    

		String resProc = processRequest(request);
		logger.debug("Result of request :"+resProc);
		
		response.getWriter().append(resProc);
	}
	
	protected Object[] getParameters(HttpServletRequest request) {
		List<Object> listObject = new ArrayList<Object>();
		Map<String,String> map = new HashMap<String,String>();
		@SuppressWarnings("unchecked")
		Enumeration<String> parameterNames = request.getParameterNames();
		 while (parameterNames.hasMoreElements()) {
			 String paramName = parameterNames.nextElement();
			 logger.debug("     -> parameter : "+paramName);
			 if(paramName != "assetType") {
				 //listObject.add(request.getParameter(paramName));
				 map.put(paramName, request.getParameter(paramName));
			 }
		 }
		 listObject.add(map);
		 return listObject.toArray();
		
	}
	
	
	
	/**
	 * Process Request received by the Servlet.
	 * Requests must include a parameter named assetType.
	 * This parameter will be used to dispatch request to the appropriate Controller.
	 * All others parameters will be parsed to this controller. 
	 * 
	 * @param request All requests must contained at least assetType parameter.
	 * @param response JSON response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected String processRequest(HttpServletRequest request) {
		Enumeration enumeration = request.getParameterNames();
		 if(enumeration == null) {
		    	logger.debug("No parameter received");
		    	return "Erreur aucun parametre recu";
		    }
		String controllerName = request.getParameter("assettype");
		if(controllerName == null) {
			return "Erreur assettype non defini";
		}
		String controllerFullName = "fr.comprehensiveit.cultura.controller."+controllerName;
		logger.debug("controller name :"+controllerName);
		String result = "";
		Class<?>[] parameterTypes = new Class[]{Map.class};
		try {
			Class controllerClass = Class.forName(controllerFullName);
			
			Method methodController = controllerClass.getMethod("dispatcherRequest", parameterTypes);
			Object controllerInstance = controllerClass.newInstance();
			result = (String) methodController.invoke(controllerInstance, getParameters(request));
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			result = "Erreur l'objet chercher n'existe pas";
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			result = "Erreur l'objet demandé est mal formé";
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			result = "Erreur vous n'êtes pas autorisé à appeler l'objet demandé";
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			result = "Erreur l'objet demandé est mal concu";
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			result = "Erreur impossible d acceder a l'objet demandé";
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			result = "Erreur l'objet ne peut être appelé avec les paramètres demandés";
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			result = "Erreur l'objet a retourné une erreur";
			logger.error("InvocationTarget exception :",e);
		}
		return result;
	}

}
