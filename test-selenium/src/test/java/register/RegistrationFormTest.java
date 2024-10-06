package register;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RegistrationFormTest {

    private WebDriver driver;

    @BeforeMethod
    public void setup() {
        // Thiết lập ChromeDriver
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();
        // Mở trang Demoblaze
        driver.get("https://www.demoblaze.com/");
        driver.manage().window().maximize();
    }

    @Test
    public void testRegistrationWithExistingUsername() throws InterruptedException {
        // Trường hợp: Đăng ký với tên người dùng đã tồn tại
        WebElement signUpButton = driver.findElement(By.id("signin2"));
        signUpButton.click();
        Thread.sleep(2000);

        WebElement usernameField = driver.findElement(By.id("sign-username"));
        WebElement passwordField = driver.findElement(By.id("sign-password"));
        WebElement signUpSubmitButton = driver.findElement(By.xpath("//button[text()='Sign up']"));

        // Nhập thông tin với tên người dùng đã tồn tại
        usernameField.sendKeys("testuser123");  // Tên người dùng đã tồn tại
        passwordField.sendKeys("password123");
        signUpSubmitButton.click();

        Thread.sleep(2000);

        // Kiểm tra thông báo lỗi cho tên người dùng đã tồn tại
        String alertMessage = driver.switchTo().alert().getText();
        Assert.assertEquals(alertMessage, "This user already exist.");
        driver.switchTo().alert().accept();
    }

//    @Test
//    public void testRegistrationWithWeakPassword() throws InterruptedException {
//        // Trường hợp: Đăng ký với mật khẩu không đủ mạnh
//        WebElement signUpButton = driver.findElement(By.id("signin2"));
//        signUpButton.click();
//        Thread.sleep(2000);
//
//        WebElement usernameField = driver.findElement(By.id("sign-username"));
//        WebElement passwordField = driver.findElement(By.id("sign-password"));
//        WebElement signUpSubmitButton = driver.findElement(By.xpath("//button[text()='Sign up']"));
//
//        // Nhập thông tin với mật khẩu yếu (mật khẩu ngắn)
//        usernameField.sendKeys("testuserNew");
//        passwordField.sendKeys("123");  // Mật khẩu yếu
//        signUpSubmitButton.click();
//
//        Thread.sleep(2000);
//
//        // Kiểm tra thông báo về mật khẩu yếu (giả sử hệ thống có logic kiểm tra)
//        String alertMessage = driver.switchTo().alert().getText();
//        Assert.assertEquals(alertMessage, "Password too weak.");
//        driver.switchTo().alert().accept();
//    }

    @Test
    public void testSuccessfulRegistration() throws InterruptedException {
        // Trường hợp: Đăng ký thành công với dữ liệu hợp lệ
        WebElement signUpButton = driver.findElement(By.id("signin2"));
        signUpButton.click();
        Thread.sleep(2000);

        WebElement usernameField = driver.findElement(By.id("sign-username"));
        WebElement passwordField = driver.findElement(By.id("sign-password"));
        WebElement signUpSubmitButton = driver.findElement(By.xpath("//button[text()='Sign up']"));

        // Sử dụng tên người dùng và mật khẩu hợp lệ
        String randomUsername = "testuser" + System.currentTimeMillis();  // Tạo tên người dùng duy nhất
        usernameField.sendKeys(randomUsername);
        passwordField.sendKeys("password123");
        signUpSubmitButton.click();

        Thread.sleep(2000);

        // Kiểm tra thông báo đăng ký thành công
        String alertMessage = driver.switchTo().alert().getText();
        Assert.assertEquals(alertMessage, "Sign up successful.");
        driver.switchTo().alert().accept();
    }

    @AfterMethod
    public void teardown() {
        // Đóng trình duyệt sau mỗi test
        if (driver != null) {
            driver.quit();
        }
    }
}
