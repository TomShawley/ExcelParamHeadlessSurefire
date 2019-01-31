package com.qa.ExcelParam;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.ExcelParam.ExcelParamTest;

public class Login {
	
	@FindBy(xpath="/html/body/div/center/table/tbody/tr[2]/td/div/center/table/tbody/tr/td[2]/p/small/a[4]")
	private WebElement loginTab;
	
	@FindBy(name="username")
	private WebElement username;
	
	@FindBy(name="password")
	private WebElement password;
	
	@FindBy(name="FormsButton2")
	private WebElement loginButton;
	
	@FindBy(xpath="/html/body/table/tbody/tr/td[1]/big/blockquote/blockquote/font/center/b")
	private WebElement checkLogin;
	
	public String getCheckLogin() {
		return checkLogin.getText();
	}
	

	

	

	public void login(ExcelParamTest excelParam) {
		loginTab.click();
		username.sendKeys(excelParam.getUsername());
		password.sendKeys(excelParam.getPassword());
		loginButton.click();
		
	
	
	}

}
