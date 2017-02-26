package fr.comprehensiveit.cultura;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.logging.Logger;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import fr.comprehensiveit.cultura.model.GoogleBookWsMap;

@RestController
public class ClientWSController {
	static Logger logger = Logger.getLogger(ClientWSController.class.getName());
	
	@RequestMapping(value="/cultura/clientws",method=RequestMethod.GET,produces = "application/json")
	public @ResponseBody String getAsset(@PathVariable String param) {
		logger.warning("Entered in searchAsset");
		GoogleBookWsMap gBookWsMap;
		String response = "";
		try {
			gBookWsMap = new GoogleBookWsMap();
			response = gBookWsMap.searchById("Cambodia");
		} catch (GeneralSecurityException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		return response;
	}
	//public ModelAndView getAsset() {
	//	ModelAndView mav = new ModelAndView("ebookList");
		
	//}
	
		
}
