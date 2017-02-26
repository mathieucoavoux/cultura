package fr.comprehensiveit.cultura.model;

import static org.junit.Assert.*;

import java.io.IOException;
import java.security.GeneralSecurityException;

import org.json.JSONObject;
import org.junit.Test;

public class GoogleBookWsMapTest {
	@Test
	public void testSearchId() throws IOException, GeneralSecurityException {
		GoogleBookWsMap wsMapping = new GoogleBookWsMap();
		String resultJson = wsMapping.searchById("0736813705");
		JSONObject resJsonIsbn = new JSONObject(resultJson);
		String idISBN = resJsonIsbn.getJSONObject("0").getString("id").toString();
		assertEquals(idISBN,"9780736813709");
	}
	@Test
	public void testSearchByTitle() throws GeneralSecurityException, IOException {
		GoogleBookWsMap wsMapping = new GoogleBookWsMap();
		String resultJson = wsMapping.searchByTitle("Phnom Penh: A Cultural and Literary History");
		JSONObject resJsonIsbn = new JSONObject(resultJson);
		String idISBN = resJsonIsbn.getJSONObject("0").getString("id").toString();
		assertEquals(idISBN,"9781904955405");
	}
}
