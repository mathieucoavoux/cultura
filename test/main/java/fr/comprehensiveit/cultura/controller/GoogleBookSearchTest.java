package fr.comprehensiveit.cultura.controller;

import org.json.JSONObject;
import org.junit.Test;
import static org.junit.Assert.*;

public class GoogleBookSearchTest {
	@Test
	public void testDispatcherRequestIsbn() {
		String filterType = "isbn";
		String filterName = "0736813705";
		GoogleBookSearch gbsearch = new GoogleBookSearch();
		String result = gbsearch.dispatcherRequest(filterType,filterName);
		JSONObject resJsonIsbn = new JSONObject(result);
		String idISBN = resJsonIsbn.getJSONObject("0").getString("id").toString();
		assertEquals(idISBN,"9780736813709");
	}
	public void testDispatcherRequestTitle() {
		String filterType = "intitle";
		String filterName = "Phnom Penh: A Cultural and Literary History";
		GoogleBookSearch gbsearch = new GoogleBookSearch();
		String result = gbsearch.dispatcherRequest(filterType,filterName);
		JSONObject resJsonIsbn = new JSONObject(result);
		String idISBN = resJsonIsbn.getJSONObject("0").getString("id").toString();
		assertEquals(idISBN,"9781904955405");
	}
}
