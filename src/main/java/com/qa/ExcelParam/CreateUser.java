package com.qa.ExcelParam;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.ExcelParam.ExcelParamTest;

public class CreateUser {

	@FindBy(xpath="/html/body/div/center/table/tbody/tr[2]/td/div/center/table/tbody/tr/td[2]/p/small/a[3]")
	private WebElement addUserTab;
	
	@FindBy(name="username")
	private WebElement username;
	
	@FindBy(name="password")
	private WebElement password;
	
	@FindBy(name="FormsButton2")
	private WebElement createButton;
	
	

	

	public void createUser(ExcelParamTest excelParam) {
		addUserTab.click();
		username.sendKeys(excelParam.getUsername());
		password.sendKeys(excelParam.getPassword());
		createButton.click();
		
	}
}
