package SELENIUM;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class SeleniumTests {
    public static void main(String[] args) {
       // WebDriverManager.chromedriver().setup();//Подключение веб драйвера через библиотеки

        WebDriver driver = new ChromeDriver();//Обращение к объетку веб драйвера

        driver.get("https://aqa-admin.javacode.ru/login");


        //Actions actions=new Actions(driver);

        WebElement element = driver.findElement(By.xpath("//input[@placeholder='Логин']"));
        WebElement element1 = driver.findElement(By.xpath("//input[@placeholder='Пароль']"));
        WebElement element2 = driver.findElement(By.xpath("//button[@type='submit']"));

        element.sendKeys("davtyan_samvel");
        element1.sendKeys("s0DT%1^?{GwjMfs");
        element2.click();

        //  Тест кейс 1 портала развития



        /*driver.get("https://www.avito.ru/all/avtomobili");//Вызов метода get запуск веб странички

        WebElement webElement=driver.findElement(By.xpath("//input[@autocomplete='off']"));

        webElement.sendKeys("Kia Optima", Keys.ENTER);*/


        //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));//Задаем неявное ожидание

        // WebElement element=(new WebDriverWait(driver,Duration.ofSeconds(10))
        //  .until(ExpectedConditions.presenceOfElementLocated(By.id("123"))));


        // WebElement input = driver.findElement(By.xpath("//textarea[@aria-label='Найти']"));
        //Методы BY--:xpath(Мощный способ поиска чего либо на странице),cssSelector,className,id,linkText,partiaLinkText
        //WebElement inputs = driver.findElements(By.xpath("(//input[@aria-label='Найти'])"));

        //input.click();

    }
}