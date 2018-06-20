package com.cybertek;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class PorscheCheckout {

	public static void main(String[] args) throws InterruptedException {

		//

		
		
		
		
		
		// 26.Verify that total price is the sum of base price + Price for Equipment +
		// Delivery,
		// Processing and Handling Fee

		// 1. Open browser
		WebDriverManager.chromedriver().setup();
		// 2. Go to url “https://www.porsche.com/usa/modelstart/”
		WebDriver driver = new ChromeDriver();
		// 3. Select model 718
		driver.get("https://www.porsche.com/usa/modelstart/");
		driver.findElement(By.xpath("//img[@alt='Porsche - 718']")).click();
		String textPrice = driver.findElement(By.xpath("(//div[@class='m-14-model-price'])[1]")).getText();
		String price = textPrice.substring(7, 16);
		// 4. Remember the price of 718 Cayman
		double actualBasePrice = convert(price);
		// 5. Click on Build & Price under 718 Cayman
		driver.findElement(By.xpath("(//a[@class='m-01-link m-14-build'])[1]")).click();
		// 5. Click on Build & Price under 718 Cayman
		driver.findElement(By.xpath("(//a[@class='m-01-link m-14-build'])[1]")).click();
		// Switch to new window opened
		for (String winHandle : driver.getWindowHandles()) {
			driver.switchTo().window(winHandle);
		}

		// 6. Verify that Base price displayed on the page is same as the price from
		// step 4
		Thread.sleep(2000);
		// String textPrice1 =
		// driver.findElement(By.xpath("(//div[@class='ccaPrice'])[1]")).getText();
		String textPrice1 = driver.findElement(By.xpath("//*[@id=\"s_price\"]/div[1]/div[1]/div[2]")).getText();
		Thread.sleep(2000);
		String price1 = textPrice1.substring(1);
		double basePrice = convert(price1);
		if (actualBasePrice == basePrice) {
			System.out.println("PASS");
		} else {
			System.out.println("FAIL");
		}
		Thread.sleep(2000);
		// 7. Verify that Price for Equipment is 0
		String equipmentTextPrice = driver.findElement(By.xpath("//*[@id=\"s_price\"]/div[1]/div[2]/div[2]")).getText();
		String equipmentTextPrice1 = equipmentTextPrice.substring(1);
		double equipmentPrice = convert(equipmentTextPrice1);
		if (equipmentPrice == 0.0)
			System.out.println("PASS");
		else
			System.out.println("FAIL");
		Thread.sleep(2000);
		// 8. Verify that total price is the sum of base price + Delivery, Processing
		String deliveryTextFee = driver.findElement(By.xpath("//*[@id=\"s_price\"]/div[1]/div[3]/div[2]")).getText();
		String deliveryTextFee1 = deliveryTextFee.substring(1);
		double deliveryFee = convert(deliveryTextFee1);
		Thread.sleep(2000);
		String totalTextPrice = driver.findElement(By.xpath("//*[@id=\"s_price\"]/div[1]/div[4]/div[2]")).getText();
		String totalTextPrice1 = totalTextPrice.substring(1);
		double totalPrice = convert(totalTextPrice1);
		if (totalPrice == (deliveryFee + equipmentPrice + basePrice)) {
			System.out.println("PASS");
		} else {
			System.out.println("FAIL");
			System.out.println("expected\t" + 57950.0);
			System.out.println("actual\t" + totalPrice);
		}
		// 9. Select color “Miami Blue”
		driver.findElement(By.xpath("//*[@id=\"s_exterieur_x_FJ5\"]/span")).click();
		// 10.Verify that Price for Equipment is Equal to Miami Blue price
		Thread.sleep(2000);
		String miamiBluePriceText = driver.findElement(By.xpath("//li[@id='s_exterieur_x_FJ5']"))
				.getAttribute("data-price");
		String miamiBluePriceText1 = miamiBluePriceText.substring(1);
		double miamiBluePrice = convert(miamiBluePriceText1);
		String equipmentTextPrice2 = driver.findElement(By.xpath("//*[@id=\"s_price\"]/div[1]/div[2]/div[2]"))
				.getText();
		String equipmentTextPrice3 = equipmentTextPrice2.substring(1);
		double equipmentPrice2 = convert(equipmentTextPrice3);
		if (miamiBluePrice == equipmentPrice2) {
			System.out.println("PASS");
		} else {
			System.out.println("FAIL");
			System.out.println("expected " + 2580.0);
			System.out.println("actual " + equipmentPrice2);
		}
		// 11.Verify that total price is the sum of base price + Price for Equipment +
		// Delivery
		String newTotalText = driver.findElement(By.xpath("//*[@id=\"s_price\"]/div[1]/div[4]/div[2]")).getText()
				.substring(1);
		double newTotal = convert(newTotalText);
		String deliveryFeeText2 = driver.findElement(By.xpath("//*[@id=\"s_price\"]/div[1]/div[3]/div[2]")).getText()
				.substring(1);
		double deliveryFee2 = convert(deliveryFeeText2);
		if (newTotal == (equipmentPrice2 + deliveryFee2 + basePrice)) {
			System.out.println("PASS");
		} else {
			System.out.println("FAIL");
			System.out.println("expected " + 60530.0);
			System.out.println("actual " + newTotal);
		}
		// 12.Select 20" Carrera Sport Wheels
		Thread.sleep(2000);
		driver.findElement(By.xpath("//section[@id='s_conf_submenu']//div[@class='flyout-label-value']")).click();
		Thread.sleep(1000);
		// click on Wheels link
		driver.findElement(By.partialLinkText("Wheels")).click();
		Thread.sleep(1000);
		// click on Exterior Color link to hide colors
		driver.findElement(By.id("IAF_subHdl")).click();
		Thread.sleep(1000);
		// click on wheel you want
		driver.findElement(By.xpath("//li[@id='s_exterieur_x_MXRD']//span[@class='img-element']")).click();
		// 13.Verify that Price for Equipment is the sum of Miami Blue price + 20"
		// Carrera Sport
		String miamiColorBluePriceText = driver.findElement(By.xpath("//*[@id=\"s_exterieur_x_FJ5\"]"))
				.getAttribute("data-price").substring(1);
		double miamiColorBluePrice = convert(miamiColorBluePriceText);
		String sportWheelPriceText = driver.findElement(By.xpath("//*[@id=\"s_exterieur_x_MXRD\"]"))
				.getAttribute("data-price").substring(1);
		double sportWheelPrice = convert(sportWheelPriceText);
		String equipmentTextPrice4 = driver.findElement(By.xpath("//*[@id=\"s_price\"]/div[1]/div[2]/div[2]")).getText()
				.substring(1);
		double equipmentPrice4 = convert(equipmentTextPrice4);
		if (equipmentPrice4 == (sportWheelPrice + miamiColorBluePrice)) {
			System.out.println("PASS");
		} else {
			System.out.println("FAIL");
			System.out.println("expected " + 6330.0);
			System.out.println("actual " + equipmentPrice4);
		}
		Thread.sleep(2000);
		// 14.Verify that total price is the sum of base price + Price for Equipment +
		// Delivery,
		String newTotalText1 = driver.findElement(By.xpath("//*[@id=\"s_price\"]/div[1]/div[4]/div[2]")).getText()
				.substring(1);
		double newTotal1 = convert(newTotalText1);
		if (newTotal1 == (equipmentPrice4 + deliveryFee2 + basePrice)) {
			System.out.println("PASS");
		} else {
			System.out.println("FAIL");
			System.out.println("expected " + 64280.0);
			System.out.println("actual " + newTotal1);
		}
		Thread.sleep(2000);
		// 15.Select seats ‘Power Sport Seats (14-way) with Memory Package’
		driver.findElement(By.xpath("//section[@id='s_conf_submenu']//div[@class='flyout-label-value']")).click();
		Thread.sleep(1000);
		// click on Interior Colors and Seats link
		driver.findElement(By.id("submenu_interieur_x_AI_submenu_x_submenu_parent")).click();
		Thread.sleep(1000);
		// click on Seats link
		driver.findElement(By.xpath("//a[@class='subitem-entry'][.='Seats']")).click();
		Thread.sleep(1000);
		// click on that seat
		driver.findElement(By.xpath("(//div[@class='seat'])[2]")).click();
		// 16.Verify that Price for Equipment is the sum of Miami Blue price + 20"
		// Carrera Sport+
		// Wheels + Power Sport Seats (14-way) with Memory Package
		String powerSportSeatText = driver.findElement(By.xpath("//*[@id=\"seats_73\"]/div[2]/div[1]/div[3]/div"))
				.getText().substring(1);
		Thread.sleep(2000);
		double powerSportSeatPrice = convert(powerSportSeatText);
		String equipmentTextPrice5 = driver.findElement(By.xpath("//*[@id=\"s_price\"]/div[1]/div[2]/div[2]")).getText()
				.substring(1);
		Thread.sleep(2000);
		double equipmentPrice5 = convert(equipmentTextPrice5);
		if (equipmentPrice5 == (powerSportSeatPrice + sportWheelPrice + miamiColorBluePrice)) {
			System.out.println("PASS");
		} else {
			System.out.println("FAIL");
			System.out.println("expected " + 8660.0);
			System.out.println("actual " + equipmentPrice5);
		}

		Thread.sleep(2000);

		// 17.Verify that total price is the sum of base price + Price for Equipment +
		// Delivery,
		// Processing and Handling Fee
		String newTotalText2 = driver.findElement(By.xpath("//*[@id=\"s_price\"]/div[1]/div[4]/div[2]")).getText()
				.substring(1);
		double newTotal2 = convert(newTotalText2);
		if (newTotal2 == (equipmentPrice5 + deliveryFee2 + basePrice)) {
			System.out.println("PASS");
		} else {
			System.out.println("FAIL");
			System.out.println("expected " + 66610.0);
			System.out.println("actual " + newTotal2);
		}
		Thread.sleep(2000);
		// 18.Click on Interior Carbon Fiber
		driver.findElement(By.xpath("//section[@id='s_conf_submenu']//div[@class='flyout-label-value']")).click();
		Thread.sleep(2000);
		// click on Interior Colors and Seats link
		driver.findElement(By.id("submenu_individualization_x_individual_submenu_x_submenu_parent")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id=\"submenu_individualization_x_individual_submenu_x_IIC\"]")).click();
		Thread.sleep(2000);
		// 19.Select Interior Trim in Carbon Fiber i.c.w. Standard Interior 
		WebElement check = driver.findElement(By.xpath("//*[@id=\"vs_table_IIC_x_PEKH_x_c01_PEKH\"]"));
		check.click();
		Thread.sleep(2000);
		// 20.Verify that Price for Equipment is the sum of Miami Blue price + 20"
		// Carrera Sport
		// Wheels + Power Sport Seats (14-way) with Memory Package + Interior Trim in
		// Carbon Fiber i.c.w. Standard Interior
		String carbonFiberInterior = driver.findElement(By.xpath("//*[@id=\"vs_table_IIC_x_PEKH\"]/div[1]/div[2]/div"))
				.getText().substring(1);
		double carbonFiberInteriorPrice = convert(carbonFiberInterior);
		String equipmentPriceText6 = driver.findElement(By.xpath("//*[@id=\"s_price\"]/div[1]/div[2]/div[2]")).getText()
				.substring(1);
		double equipmentPrice6 = convert(equipmentPriceText6);
		if (equipmentPrice6 == (carbonFiberInteriorPrice + powerSportSeatPrice + sportWheelPrice
				+ miamiColorBluePrice)) {
			System.out.println("PASS");
		} else {
			System.out.println("FAIL");
			System.out.println("expected " + 10200.0);
			System.out.println("actual " + equipmentPrice6);
		}
		Thread.sleep(2000);
		// 21.Verify that total price is the sum of base price + Price for Equipment +
		// Delivery,
		// Processing and Handling Fee
		String newTotal3 = driver.findElement(By.xpath("//*[@id=\"s_price\"]/div[1]/div[4]/div[2]")).getText().substring(1);
		double newTotalPrice3 = convert(newTotal3);
		if(newTotalPrice3==(equipmentPrice6+deliveryFee2+basePrice)) {
			System.out.println("PASS");
		}else {
			System.out.println("FAIL");
			System.out.println("expected " + 68150.0);
			System.out.println("actual " + newTotalPrice3);
		}
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		// 22.Click on Performance
		driver.findElement(By.xpath("//*[@id=\"s_conf_submenu\"]/div/div")).click();
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//*[@id=\"submenu_individualization_x_individual_submenu_x_IMG\"]/a")).click();
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		// 23.Select 7-speed Porsche Doppelkupplung (PDK)
		
		driver.findElement(By.id("search_x_inp")).sendKeys("7-speed Porsche Doppelkupplung"+Keys.ENTER);
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		WebElement check4 = driver.findElement(By.xpath("//*[@id=\"search_x_M250_x_c11_M250\"]"));
		check4.click();
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		//driver.findElement(By.xpath("//*[@id=\"conflict_x_dialogbuttons_x_buttonOK\"]/a")).click();
		driver.findElement(By.xpath("//*[@id=\"search_x_inp\"]")).clear();
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		//driver.findElement(By.id("conflict_x_dialogbuttons_x_buttonOK")).click();
		// 24.Select Porsche Ceramic Composite Brakes (PCCB)
		driver.findElement(By.xpath("//*[@id=\"search_x_inp\"]")).sendKeys("Porsche Ceramic"+Keys.ENTER);
		//driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//*[@id=\"search_x_M450_x_c91_M450\"]")).click();
		//check5.click();
		// 25.Verify that Price for Equipment is the sum of Miami Blue price + 20"
		// Carrera Sport
		// Wheels + Power Sport Seats (14-way) with Memory Package + Interior Trim in
		// Carbon Fiber i.c.w. Standard Interior + 7-speed Porsche Doppelkupplung (PDK)
		// Porsche Ceramic Composite Brakes (PCCB)
		Thread.sleep(2000);
		String porscheDoppel = driver.findElement(By.xpath("//*[@id=\"vs_table_IMG_x_M250\"]/div[1]/div[2]/div")).getText().substring(1);
		double porscheDoppelPrice = convert(porscheDoppel);
		Thread.sleep(2000);
		String ceramicCompositeBrake = driver.findElement(By.xpath("//*[@id=\"vs_table_IMG_x_M450\"]/div[1]/div[2]/div")).getText().substring(1);
		Thread.sleep(2000);
		double ceramicCompositeBrakePrice = convert(ceramicCompositeBrake);
		
		String equipmentPriceText7 = driver.findElement(By.xpath("//*[@id=\"s_price\"]/div[1]/div[2]/div[2]")).getText().substring(1);
		double equipmentPrice7 = convert(equipmentPriceText7);
		//System.out.println(equipmentPrice7);
		if(equipmentPrice7==(carbonFiberInteriorPrice + powerSportSeatPrice + sportWheelPrice
				+ miamiColorBluePrice+porscheDoppelPrice+ceramicCompositeBrakePrice)) {
			System.out.println("PASS");
		}else {
			System.out.println("FAIL");
			System.out.println("expected " + 20820.0);
			System.out.println("actual " + equipmentPrice7);
		}
		Thread.sleep(2000);
		// 26.Verify that total price is the sum of base price + Price for Equipment +
		// Delivery+Processing and Handling Fee
		String newTotal4 = driver.findElement(By.xpath("//*[@id=\"s_price\"]/div[1]/div[4]/div[2]")).getText().substring(1);
		double grandTotal = convert(newTotal4);
		if(grandTotal==(equipmentPrice7+deliveryFee2+basePrice)) {
			System.out.println("PASS");
		}else {
			System.out.println("FAIL");
			System.out.println("expected " + 78770.0);
			System.out.println("actual " + grandTotal);
		}
	

	}

	public static double convert(String s) {

		double price = Double.parseDouble(s.replace(",", ""));
		return price;

	}

}