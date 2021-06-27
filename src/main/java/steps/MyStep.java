package steps;

import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class MyStep {
    public MyStep() {
        System.setProperty("webdriver.chrome.driver", "D:\\WebDriver\\chromedriver.exe");
        driver = new ChromeDriver();
        js = (JavascriptExecutor) driver;
    }

    private WebDriver driver;
    private JavascriptExecutor js;

    @Step("Открыта страница Avito")
    public void openURL(String url) {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(url);
        driver.manage().window().maximize();
    }

    @Step("В выпадающем списке категорий выбрана Оргтехника и расходники")
    public void openCategory(String category) {

        WebElement elementСategory = driver.findElement(By.id("category"));
        Select select = new Select(elementСategory);
        select.selectByVisibleText(category);
    }

    @Step("В поле поиска введено значение Принтер")
    public void elementSearch(String stringForSearch) throws InterruptedException {
        Thread.sleep(1500);
        WebElement searchSearch = driver.findElement(By.id("search"));
        searchSearch.sendKeys(stringForSearch);
        searchSearch.sendKeys(Keys.RETURN);
    }

    @Step("Кликнуть по выпадающему списку региона")
    public void clickRegion() {
        driver.findElement(By.className("main-text-2PaZG")).click();
    }

    @Step("В поле регион ввести Владивосток")
    public void enterCity(String city) throws InterruptedException {
        Thread.sleep(1500);
        driver.findElement(By.className("suggest-input-3p8yi")).sendKeys(city);
        Thread.sleep(1000);
        driver.findElement(By.className("popup-buttons-NqjQ3")).click();
    }

    @Step("Нажата кнопка показать объявление")
    public void pressButtonShow() {
        WebElement checkDelivery = driver.findElement(By.xpath("//div[@data-marker=\"delivery-filter/container\"]"));
        js.executeScript("arguments[0].scrollIntoView();", checkDelivery);
        driver.findElement(By.xpath("//button[contains(@data-marker,'search-filters')]")).click();
    }


    @Step("Активирован чекбокс только с фотографией")
    public void activateСheckbox() throws InterruptedException {
        Thread.sleep(1000);
        if (!driver.findElement(By.xpath("//span[contains(text(), 'только с фото')]")).isSelected()) {
            driver.findElement(By.xpath("//span[contains(text(), 'только с фото')]")).click();
        }
    }

    @Step("В выпадающем списке сортировка выбрано значение Дороже")
    public void activateSort(String sort) {
        WebElement elementSelect = driver.findElement(By.xpath("//select[contains(@class,'select-select-3CHiM')]"));
        elementSelect.findElement(By.xpath("//option[. = '" + sort + "']")).click();
    }

    @Step("В консоль выведено значение название цены 3 первых товаров")
    public void outputValues(int quantity) {
        List<WebElement> name = driver.findElements(By.xpath("//h3[contains(@itemprop,'name')]"));
        List<WebElement> cost = driver.findElements(By.xpath("//span[contains(@class,'price-text-1HrJ_ text-text-1PdBw text-size-s-1PUdo')]"));
        for (int i = 0; i < quantity; i++) {
            System.out.println(name.get(i).getText() + " цена " + cost.get(i).getText());
        }
    }

    @Attachment
    @Step("Make screen shot page")
    public byte[] makeScreenShot() {

        Screenshot screenshot = new AShot().takeScreenshot(driver);

        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        try {
            ImageIO.write(screenshot.getImage(), "PNG", buffer);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return buffer.toByteArray();
    }

}
