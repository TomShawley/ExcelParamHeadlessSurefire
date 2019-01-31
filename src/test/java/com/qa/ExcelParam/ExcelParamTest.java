package com.qa.ExcelParam;
import static org.junit.Assert.assertEquals;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.support.PageFactory;

@RunWith(Parameterized.class)
public class ExcelParamTest {

	@Parameters
	public static Collection<Object[]> data() throws IOException {
		FileInputStream file = new FileInputStream("C:\\Users\\Admin\\Downloads\\DemoSiteDDT.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(file);
		XSSFSheet sheet = workbook.getSheetAt(0);

		Object[][] ob = new Object[sheet.getPhysicalNumberOfRows()][4];

		for (int rowNum = 1; rowNum < sheet.getPhysicalNumberOfRows(); rowNum++) {
			ob[rowNum - 1][0] = sheet.getRow(rowNum).getCell(0).getStringCellValue();
			ob[rowNum - 1][1] = sheet.getRow(rowNum).getCell(1).getStringCellValue();
			ob[rowNum - 1][2] = sheet.getRow(rowNum).getCell(2).getStringCellValue();
			ob[rowNum - 1][3] = rowNum;
		}
		return Arrays.asList(ob);
	}

	private String username;
	private String password;
	private String expected;
	private int rowNum;
	private WebDriver driver;

	public ExcelParamTest(String username, String password, String expected, int rowNum) {
		this.username = username;
		this.password = password;
		this.expected = expected;
		this.rowNum = rowNum;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	@Before
	public void setup() {
		System.setProperty("phantomjs.binary.path", Constantt.PHANTOM);
		driver = new PhantomJSDriver();
		Constantt url = new Constantt();
		driver.get(url.URL);
		driver.manage().window().maximize();
	}

	@After
	public void teardown() {
		driver.quit();
	}

	@Test
	public void login() throws IOException {
		CreateUser createUser = PageFactory.initElements(driver, CreateUser.class);
		Login login = PageFactory.initElements(driver, Login.class);
		createUser.createUser(this);
		login.login(this);

		FileInputStream file = new FileInputStream("C:\\Users\\Admin\\Downloads\\DemoSiteDDT.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(file);
		XSSFSheet sheet = workbook.getSheetAt(0);
		XSSFRow row = sheet.getRow(rowNum);
		XSSFCell cell = row.getCell(3);

		if (cell == null) {
			cell = row.createCell(3);
		}
		cell.setCellValue(login.getCheckLogin());

		try {
			assertEquals("Login unsuccessful", expected, login.getCheckLogin());
			cell = row.getCell(4);
			if (cell == null) {
				cell = row.createCell(4);
			}
			cell.setCellValue("Pass");

		} catch (AssertionError e) {
			cell = row.getCell(4);
			if (cell == null) {
				cell = row.createCell(4);
			}
			cell.setCellValue("Fail");
			Assert.fail();
		}finally {

		FileOutputStream fileOut = new FileOutputStream("C:\\Users\\Admin\\Downloads\\DemoSiteDDT.xlsx");
		workbook.write(fileOut);
		fileOut.flush();
		fileOut.close();
		file.close();

	}
}
}
