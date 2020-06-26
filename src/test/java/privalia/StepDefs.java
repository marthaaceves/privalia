package privalia;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.commons.lang3.ObjectUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class StepDefs {

    public WebDriver driver;
    public WebDriverWait wait;

    @Before
    public void before() {
        ChromeOptions opt = new ChromeOptions();
        opt.addArguments("--disable-notifications");
        String driverPath = System.getProperty("user.dir") + "/src/test/java/selenium/webDrivers/chromedriver.exe";
        System.setProperty("webdriver.chrome.driver",driverPath);
        driver = new ChromeDriver(opt);
        wait = new WebDriverWait(driver, 30);
        driver.manage().window().setSize(new Dimension(900, 550));
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @After
    public void tearDownTest() {
        driver.quit();
    }

    @When("I am in Privalia Home Page")
    public void iAmInPrivaliaHomePage() {
        //navigate to privalia
        driver.get("https://mex.privalia.com/public/");
        //Esperar hasta que el titulo de la pagina privalia es visible
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("claim")));
    }

    @Then("All the featured ads are up to date")
    public void allTheFeaturedAdsAreUpToDate() {
        //h2[ text() = 'Destacados']/following-sibling::article

        List<WebElement> artDestacados = driver.findElements(By.xpath("//h2[ text() = 'Destacados']/following-sibling::article"));
        for(WebElement artDestacado: artDestacados) {
            WebElement vigencia = artDestacado.findElement(By.cssSelector(".item-dataInfo"));
            if(vigencia.getText() != null) {
                System.out.println(vigencia.getText());
                //WebElement nombreC = driver.findElement(By.cssSelector("[data-prv-campaigntype='highlight_block']"));
                //System.out.println(nombreC.getText());
            }
        }

    }

    @Then("All the current ads are up to date")
    public void allTheCurrentAdsAreUpToDate() {
        List<WebElement> artsActuales = driver.findElements(By.xpath("//h2[ text() = 'Actualmente']/following-sibling::article"));
        for(WebElement actual: artsActuales) {
            WebElement vigencia = actual.findElement(By.cssSelector(".item-dataInfo"));
            if(vigencia.getText() != null) {
                System.out.println(vigencia.getText());
            }
        }
    }

    @Then("Coming up promotions don't have a due date")
    public void comingUpPromotionsDonTHaveADueDate() {
        List<WebElement> artsActuales = driver.findElements(By.xpath("//h2[ text() = 'Actualmente']/following-sibling::article"));
        for(WebElement actual: artsActuales) {
            WebElement vigencia = actual.findElement(By.cssSelector(".item-dataInfo"));
            if(vigencia.getText() != null) {
                System.out.println(vigencia.getText());
            }
        }
    }
}

