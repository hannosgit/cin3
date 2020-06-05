package at.technikumwien;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;

import io.github.bonigarcia.wdm.WebDriverManager;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@Tag("e2e-test")
public class IndexTest {

	@LocalServerPort
	private long port;
	private WebDriver driver;
	private Wait<WebDriver> wait;

	@BeforeAll
	public static void setUpBeforeClass() {
		WebDriverManager.chromedriver().setup();
	}

	@BeforeEach
	public void setUp() {
		driver = new ChromeDriver(new ChromeOptions().setHeadless(false)); // TODO set true
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		wait = new WebDriverWait(driver, 3);
	}

	@AfterEach
	public void tearDown() {
		driver.quit();
	}

	@Test
	public void testIndex() throws Exception {
		driver.get("http://localhost:" + port);

		WebElement button = driver.findElement(By.tagName("button"));

		assertEquals("alle Personen anzeigen", button.getText());

		var lis = driver.findElements(By.tagName("li"));
		assertEquals(1, lis.size());

		button.submit();
		
		wait.until(
				ExpectedConditions.urlToBe("http://localhost:" + port + "/?all=true"));

		
	}

	@Test
	public void testIndexWithAll() throws Exception {
		driver.get("http://localhost:" + port + "/?all=true");

		WebElement button = driver.findElement(By.tagName("button"));

		assertEquals("nur aktivierte Personen anzeigen", button.getText());

		var lis = driver.findElements(By.tagName("li"));
		assertEquals(2, lis.size());

		button.submit();
		
		wait.until(
				ExpectedConditions.urlToBe("http://localhost:" + port + "/?"));

		
	}
	
	// TODO add more tests

}
