package Selenium.test;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import Selenium.TestComponents.BaseTest;
import Selenium.pageObject.CheckOutPage;
import Selenium.pageObject.cartPage;
import Selenium.pageObject.conformationPage;
import Selenium.pageObject.productCatalog;
import Selenium.TestComponents.Retry;

public class errorValidationTest extends BaseTest {
	
	@Test(groups = {"LoginError"},retryAnalyzer=Retry.class)
	public void LoginErrorValidation() throws IOException, InterruptedException {
		
	l1.logIn("krutikagoud3@gmail.com", "@Rahullll8700");
	
	Assert.assertEquals("Incorrect email or password.", l1.getErrMsg());
	}
	
	
	@Test(groups = {"OrderPlace"})
	public void ProductErrorValidation() throws InterruptedException {
		
		String item = "ADIDAS ORIGINAL";
		String country ="India";
		
		
		productCatalog p1 = l1.logIn("krutikagoud3@gmail.com", "@Rahul8700");
		p1.getProdList();
		p1.getProdByName(item);
		p1.addProdToCart(item);
		cartPage c1 = p1.goToCart();

		boolean match = c1.varifyProductDisplay(item);
		Assert.assertTrue(match);
		CheckOutPage ck1 = c1.goToCheckOut();
		
		ck1.selectCountry(country);
		conformationPage cm1 = ck1.submitOrder();		
		String cnfMsg = cm1.varifyCnfMsg();				
		Assert.assertTrue(cnfMsg.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		
		
	}
	
}
