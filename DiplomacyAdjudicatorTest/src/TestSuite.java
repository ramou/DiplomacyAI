
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.smartcardio.Card;

import org.dipai.ser.RenewDatabase;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.gargoylesoftware.htmlunit.WebClient;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;

import net.minidev.json.JSONArray;

public class TestSuite {
	public static String URL_BASE = "http://localhost:8080/poke/";

	final static Logger testLogger = Logger.getLogger(TestSuite.class.getName());

	@BeforeClass
	public static void setup() {
		RenewDatabase.main(null);
	}
	
	@Test
	public void testRegisterNoInfo() {
		try {
			WebClient webClient = new WebClient();
			String jsonText = GameUtils.register(webClient, "", "");
			DocumentContext dc = JsonPath.parse(jsonText);
			
			Assert.assertEquals("fail", dc.read("$['status']"));

			jsonText = GameUtils.register(webClient, "", "fred2");
			dc = JsonPath.parse(jsonText);
			Assert.assertEquals("fail", dc.read("$['status']"));
			
			jsonText = GameUtils.register(webClient, "bob2", "");
			dc = JsonPath.parse(jsonText);
			Assert.assertEquals("fail", dc.read("$['status']"));
			
		} catch (IOException e) {
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
	
	@Test
	public void testRegisterSuccess() {
		try {
			WebClient webClient = new WebClient();
			String jsonText = GameUtils.register(webClient, "bob", "fred");
			DocumentContext dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
		} catch (IOException e) {
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
	
	@Test
	public void testRegisterDuplicate() {
		try {
			WebClient webClient = new WebClient();
			String jsonText = GameUtils.register(webClient, "bob1", "fred1");
			DocumentContext dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
			jsonText = GameUtils.register(webClient, "bob1", "fred1");
			dc = JsonPath.parse(jsonText);
			Assert.assertEquals("fail", dc.read("$['status']"));
			
		} catch (IOException e) {
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}


	
	
	@Test
	public void testLoginSuccess() {
		try {
			WebClient webClient = new WebClient();
			String jsonText = GameUtils.register(webClient, "bob3", "fred3");
			DocumentContext dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
			GameUtils.logout(webClient);
			
			jsonText = GameUtils.login(webClient, "bob3", "fred3");
			dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
			
		} catch (IOException e) {
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
	
	@Test
	public void testLoginFail() {
		try {
			WebClient webClient = new WebClient();
			String jsonText = GameUtils.login(webClient, "bob5", "fred3");
			DocumentContext dc = JsonPath.parse(jsonText);
			Assert.assertEquals("fail", dc.read("$['status']"));
			
		} catch (IOException e) {
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
	
	@Test
	public void testLogoutFail() {
		try {
			WebClient webClient = new WebClient();
			String jsonText = GameUtils.logout(webClient);
			DocumentContext dc = JsonPath.parse(jsonText);
			Assert.assertEquals("fail", dc.read("$['status']"));
		} catch (IOException e) {
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
	
	@Test
	public void testLogoutSuccess() {
		try {
			WebClient webClient = new WebClient();
			String jsonText = GameUtils.register(webClient, "bob6", "fred6");
			DocumentContext dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
			jsonText = GameUtils.logout(webClient);
			dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
			
		} catch (IOException e) {
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}

	
}
