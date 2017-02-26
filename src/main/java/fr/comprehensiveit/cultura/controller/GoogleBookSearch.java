package fr.comprehensiveit.cultura.controller;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.comprehensiveit.cultura.model.GoogleBookWsMap;

public class GoogleBookSearch {
	
	public static Logger logger = LoggerFactory.getLogger(GoogleBookSearch.class);
	
	public String dispatcherRequest(Map map) {
		GoogleBookWsMap gBookWsMap;
		String result = "";
		String filterType = (String) map.get("filterType");
		String filterName = (String) map.get("filterName");
		if(filterType == null || filterName == null) 
			return "Erreur l objet est inconnu";
		
		try {
			gBookWsMap = new GoogleBookWsMap();
			switch(filterType) {
				case "isbn" :
					result = gBookWsMap.searchById(filterName);
					break;
				case "intitle" :
					result = gBookWsMap.searchByTitle(filterName);
					break;
				default:
					result = "filtre non connu";
			}
			
		} catch (GeneralSecurityException e1) {
			// TODO Auto-generated catch block
			result = "Erreur de securite";
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			result = "Erreur IO";
		}
		return result;
	}
	
	public String dispatcherRequest(String filterType, String filterName) {
		GoogleBookWsMap gBookWsMap;
		String result = "";
		try {
			gBookWsMap = new GoogleBookWsMap();
			switch(filterType) {
				case "isbn" :
					result = gBookWsMap.searchById(filterName);
					break;
				case "intitle" :
					result = gBookWsMap.searchByTitle(filterName);
					break;
			}
			
		} catch (GeneralSecurityException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return result;
	}
}
