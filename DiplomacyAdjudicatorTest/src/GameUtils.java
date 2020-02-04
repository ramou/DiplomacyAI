import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.logging.Level;

import com.gargoylesoftware.htmlunit.HttpMethod;
import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebRequest;
import com.gargoylesoftware.htmlunit.util.NameValuePair;

public class GameUtils {

	public static  String register(WebClient webClient, String username, String password)
			throws MalformedURLException, IOException {
		WebRequest requestSettings = new WebRequest(new URL(TestSuite.URL_BASE+"Dip/Player/Register"), HttpMethod.POST);
	
		requestSettings.setRequestParameters(new ArrayList<NameValuePair>());
		requestSettings.getRequestParameters().add(new NameValuePair("username", username));
		requestSettings.getRequestParameters().add(new NameValuePair("password", password));
		
		Page page = webClient.getPage(requestSettings);
		String jsonText = page.getWebResponse().getContentAsString();
		TestSuite.testLogger.log(Level.INFO, jsonText);
		return jsonText;
	}

	public static  String login(WebClient webClient, String username, String password)
			throws MalformedURLException, IOException {
		WebRequest requestSettings = new WebRequest(new URL(TestSuite.URL_BASE+"Dip/Player/Login"), HttpMethod.POST);
	
		requestSettings.setRequestParameters(new ArrayList<NameValuePair>());
		requestSettings.getRequestParameters().add(new NameValuePair("username", username));
		requestSettings.getRequestParameters().add(new NameValuePair("password", password));
	
		Page page = webClient.getPage(requestSettings);
		String jsonText = page.getWebResponse().getContentAsString();
		TestSuite.testLogger.log(Level.INFO, jsonText);
		return jsonText;
	}

	public static  String logout(WebClient webClient)
			throws MalformedURLException, IOException {
		WebRequest requestSettings = new WebRequest(new URL(TestSuite.URL_BASE+"Dip/Player/Logout"), HttpMethod.POST);
	
		Page page = webClient.getPage(requestSettings);
		String jsonText = page.getWebResponse().getContentAsString();
		TestSuite.testLogger.log(Level.INFO, jsonText);
		return jsonText;
	}

}
