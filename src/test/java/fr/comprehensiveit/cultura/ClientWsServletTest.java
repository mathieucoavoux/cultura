package fr.comprehensiveit.cultura;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.net.ConnectException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.web.context.request.WebRequest;
import org.xml.sax.SAXException;

import com.google.api.client.http.ByteArrayContent;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpContent;
import com.google.api.client.http.HttpMethods;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.UrlEncodedContent;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.testing.http.HttpTesting;
import com.google.api.client.testing.http.MockHttpContent;
import com.google.api.client.testing.http.MockHttpTransport;
import com.google.api.client.testing.http.MockLowLevelHttpResponse;
import com.meterware.httpunit.PostMethodWebRequest;
import com.meterware.httpunit.WebResponse;
import com.meterware.servletunit.ServletRunner;
import com.meterware.servletunit.ServletUnitClient;

@RunWith(MockitoJUnitRunner.class)
public class ClientWsServletTest {
	@Ignore
	public void testProcessRequest() throws ServletException, IOException {
		HttpSession session = mock(HttpSession.class);
		HttpServletRequest req = mock(HttpServletRequest.class);
		HttpServletResponse resp = mock(HttpServletResponse.class);
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		when(req.getParameter("assetType")).thenReturn("GoogleBookSearch");
		when(req.getParameter("param1")).thenReturn("value1");
		when(req.getParameter("assetname")).thenReturn("0736813705");
		when(resp.getWriter()).thenReturn(pw);
		new ClientWSServlet().doPost(req, resp);
		String result = sw.getBuffer().toString().trim();
		System.out.println("Result :"+result);
	}
	@Ignore
	public void testGetParameters() {
		HttpSession session = mock(HttpSession.class);
		HttpServletRequest req = mock(HttpServletRequest.class);
		when(req.getParameter("assetType")).thenReturn("GoogleBookSearch");
		when(req.getParameter("param1")).thenReturn("value1");
		when(req.getParameter("assetname")).thenReturn("0736813705");
		Object[] objRet = new ClientWSServlet().getParameters(req);
		System.out.println("Objet : "+objRet.toString());
	}
	@Test
	public void testDoPost() throws ServletException, IOException  {
		GenericUrl url = new GenericUrl("http://localhost:8080/cultura/searchAsset");
		
		HttpTransport transport = new NetHttpTransport();
		
		
		Map<String, String> tempParameters = new HashMap<>();
		tempParameters.put("filterName", "0736813705");
		tempParameters.put("assettype", "GoogleBookSearch");
		tempParameters.put("filterType", "isbn");
		HttpContent tempContent = new UrlEncodedContent(tempParameters);
		
		HttpRequest request = transport.createRequestFactory().buildPostRequest(url,tempContent);
		try {
			HttpResponse response = request.execute();
			JSONObject jsonResponse = new JSONObject(response.parseAsString());
			String idISBN = jsonResponse.getJSONObject("0").getString("id").toString();
			assertEquals(idISBN,"9780736813709");
		}catch (ConnectException e) {
			assertEquals("Connection refused: connect",e.getMessage());
			System.out.println("Server not available");
		}
		
		
	}
}
