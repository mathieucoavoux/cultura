package fr.comprehensiveit.cultura;

import static org.junit.Assert.*;

import org.junit.Test;

public class ClientWsControllerTest {
	@Test
	public void testSearchById() {
		ClientWSController cliWSCtl = new ClientWSController();
		String response = cliWSCtl.getAsset("Cambodia");
		System.out.println(response);
	}
}
