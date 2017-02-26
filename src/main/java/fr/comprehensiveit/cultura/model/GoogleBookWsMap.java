package fr.comprehensiveit.cultura.model;


import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;

import javax.json.Json;

import org.json.JSONObject;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.books.Books;
import com.google.api.services.books.BooksRequestInitializer;
import com.google.api.services.books.Books.Volumes.List;
import com.google.api.services.books.model.Volume;
import com.google.api.services.books.model.Volume.LayerInfo.Layers;
import com.google.api.services.books.model.Volume.VolumeInfo.ImageLinks;
import com.google.api.services.books.model.Volume.VolumeInfo.IndustryIdentifiers;
import com.google.api.services.books.model.Volumes;


public class GoogleBookWsMap {
	
	private static String fileName = "config.properties";
	private InputStream inputStream;
	
	private static String APPLICATION_NAME;
	private static final NumberFormat CURRENCY_FORMATTER = NumberFormat.getCurrencyInstance();
	private static final NumberFormat PERCENT_FORMATTER = NumberFormat.getPercentInstance();
	
	private final Books books;
	
	
	public GoogleBookWsMap() throws GeneralSecurityException, IOException {
		JsonFactory jsonFactory = JacksonFactory.getDefaultInstance();
		Properties prop = new Properties();
		inputStream = getClass().getClassLoader().getResourceAsStream(fileName);
		if(inputStream != null) {
			prop.load(inputStream);
		}
		APPLICATION_NAME = prop.getProperty("appName");
		
		books = new Books.Builder(GoogleNetHttpTransport.newTrustedTransport(), jsonFactory, null)
		        .setApplicationName(APPLICATION_NAME)
		        .setGoogleClientRequestInitializer(new BooksRequestInitializer())
		        .build();
	}
	
	public String searchByTitle(String title) throws IOException {
		List volumesList; 

			volumesList = books.volumes().list("intitle:"+title);
			//volumesList.setFilter("ebooks");
			return searchVolume(volumesList);
		 
	}
	
	public String searchById(String id) throws IOException {
		List volumesList; 

		volumesList = books.volumes().list("isbn:"+id);
		
		return searchVolume(volumesList);
	}
	
	public String searchVolume(List volumesList) throws IOException {
		JSONObject tableJson = new JSONObject();
		
	    // Execute the query.
	    Volumes volumes = volumesList.execute();
	    if (volumes.getTotalItems() == 0 || volumes.getItems() == null) {
	      return "empty";
	    }
	    
	    int ind = 0;
        JSONObject jCol;
	    for (Volume volume : volumes.getItems()) {
	    	Volume.VolumeInfo volumeInfo = volume.getVolumeInfo();
	    	
	    	java.util.List<IndustryIdentifiers> listIndustryIds = volumeInfo.getIndustryIdentifiers();
	    	
	    	String bookIsbn = null;
	    	String defaultBookId = null;
	    	for(IndustryIdentifiers item : listIndustryIds) {
	    		
	    		if (item.getType() == "ISBN_13") {
	    			bookIsbn = item.getIdentifier();
	    		}
	    		defaultBookId=item.getIdentifier();
	    		
	    	}
	    	if(bookIsbn == null && defaultBookId != null) {
	    		bookIsbn = defaultBookId;
	    	}
			jCol = new JSONObject();
			jCol.put("id",bookIsbn);
			jCol.put("title", volumeInfo.getTitle());
			jCol.put("cover", volumeInfo.getImageLinks().getSmallThumbnail());
			tableJson.put(Integer.toString(ind), jCol);
	    	
	    	ind++;
	    }

	 return tableJson.toString();
	}
}
