package seleniumTest;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Main {
	public static void main(String[] args) {

//		downloadUnsplashNatureImgs();
//		printPpomppuFreeLatesArticles();
		autoLogin();
		

	}
	
	// WebDriver
	private static WebDriver driver;

	private static WebElement webElement;

	// Properties
	public static final String WEB_DRIVER_ID = "webdriver.chrome.driver";
	public static final String WEB_DRIVER_PATH = "C:\\Program Files\\selenium-java-3.141.59\\chromedriver.exe";

	// 크롤링 할 URL
	private static String base_url;
	
	private static void autoLogin() {
		// System Property SetUp
		System.setProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH);

		// Driver SetUp
		ChromeOptions options = new ChromeOptions();
		options.setCapability("ignoreProtectedModeSettings", true);
		driver = new ChromeDriver(options);
		base_url = "https://www.daum.net";
		crawl();
	}

	public static void crawl() {
		try {
			// get page (= 브라우저에서 url을 주소창에 넣은 후 request한 것과 같다)
			driver.get(base_url);
//    		input();
			oper();
			
			Thread.sleep(600000);

//    		System.out.println(driver.getPageSource());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			driver.close();
		}
	}

	public static void oper() {
		try {
			driver.get(base_url);

			webElement = driver.findElement(By.className("link_login"));
			webElement.click();

			webElement = driver.findElement(By.id("id"));
			Thread.sleep(500);
			webElement.sendKeys("yugisky");

			webElement = driver.findElement(By.id("inputPwd"));
			webElement.sendKeys("dldbrl12");

			webElement = driver.findElement(By.className("btn_comm"));
			webElement.click();

			webElement = driver.findElement(By.className("link_basis"));
			webElement.click();
			Thread.sleep(500);

			webElement = driver.findElement(By.className("btn_write"));
			webElement.click();

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public static void input() {
		int idInput[] = { KeyEvent.VK_Y, KeyEvent.VK_U, KeyEvent.VK_G, KeyEvent.VK_I, KeyEvent.VK_S, KeyEvent.VK_K,
				KeyEvent.VK_Y };
		int pwInput[] = { KeyEvent.VK_D, KeyEvent.VK_L, KeyEvent.VK_D, KeyEvent.VK_B, KeyEvent.VK_R, KeyEvent.VK_L,
				KeyEvent.VK_1, KeyEvent.VK_2 };
		try {

			Robot robot = new Robot();
			for (int i = 0; i < idInput.length; i++) {
				robot.keyPress(idInput[i]);
				robot.keyRelease(idInput[i]);
				robot.delay(10);
			}

			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);

			for (int i = 0; i < pwInput.length; i++) {
				robot.keyPress(pwInput[i]);
				robot.keyRelease(pwInput[i]);
				robot.delay(10);
			}
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
			robot.delay(1000);

			webElement = driver.findElement(By.className("link_basis"));
			webElement.click();
			robot.delay(1000);

			webElement = driver.findElement(By.className("btn_write"));
			webElement.click();

//			robot.mousePress(InputEvent.BUTTON3_MASK);
//			robot.mouseRelease(InputEvent.BUTTON3_MASK);
		} catch (AWTException ae) {
			ae.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void printPpomppuFreeLatesArticles() {
		System.out.println(System.getProperty("user.dir"));

		Path path = Paths.get(System.getProperty("user.dir"), "src/main/resources/chromedriver.exe");

		// WebDriver 경로 설정
		System.setProperty("webdriver.chrome.driver", path.toString());
		// WebDriver 옵션 설정
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--start-maximized"); // 전체화면으로 실행
		options.addArguments("--disable-popup-blockion"); // 팝업 무시
		options.addArguments("--disable-default-apps"); // 기본앱 사용안함

		ChromeDriver driver = new ChromeDriver(options);

		// 빈 탭 생성
//		driver.executeScript("window.open('about:blank','_blank');");

		List<String> tabs = new ArrayList<String>(driver.getWindowHandles());

		// 첫번째 탭으로 전환
		driver.switchTo().window(tabs.get(0));
		driver.get("https://www.ppomppu.co.kr/zboard/zboard.php?id=freeboard");

		Util.sleep(1000);

		List<WebElement> elements = driver.findElements(
				By.cssSelector("#revolution_main_table tbody tr:not(.list_notice) > td:nth-child(3) > a"));

		for (WebElement element : elements) {
			String title = element.getText().trim();
			System.out.println(title);
		}

	}

	private static void downloadUnsplashNatureImgs() {
		System.out.println(System.getProperty("user.dir"));

		Path path = Paths.get(System.getProperty("user.dir"), "src/main/resources/chromedriver.exe");

		// WebDriver 경로 설정
		System.setProperty("webdriver.chrome.driver", path.toString());
		// WebDriver 옵션 설정
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--start-maximized"); // 전체화면으로 실행
		options.addArguments("--disable-popup-blockion"); // 팝업 무시
		options.addArguments("--disable-default-apps"); // 기본앱 사용안함

		ChromeDriver driver = new ChromeDriver(options);

		// 빈 탭 생성
//		driver.executeScript("window.open('about:blank','_blank');");

		List<String> tabs = new ArrayList<String>(driver.getWindowHandles());

		// 첫번째 탭으로 전환
		driver.switchTo().window(tabs.get(0));
		driver.get("https://unsplash.com/t/nature");

		File downloadsFolder = new File("downloads");
		if (downloadsFolder.exists() == false) {
			downloadsFolder.mkdir();
		}

		Util.sleep(1000);

		List<WebElement> imgElements = driver
				.findElements(By.cssSelector("[data-test=\"photo-grid-multi-col-figure\"] img"));

		for (WebElement imgElement : imgElements) {
			String src = imgElement.getAttribute("src");
			if (src.contains("images.unsplash.com/photo") == false) {
				continue;
			}
			BufferedImage saveImage = null;
			try {
				saveImage = ImageIO.read(new URL(src));
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (saveImage != null) {
				try {
					String fileName = src.split("/")[3];
					fileName = fileName.split("\\?")[0];
					ImageIO.write(saveImage, "jpg", new File("downloads/" + fileName + ".jpg"));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			System.out.println(src);
		}

		System.out.println(imgElements.size());
	}
}
