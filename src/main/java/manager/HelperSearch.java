package manager;

import models.Car;
import models.Search;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.TextStyle;
import java.util.Locale;
import java.util.Random;

public class HelperSearch extends HelperBase{
    public HelperSearch(WebDriver wd) {
        super(wd);
    }

    public void openSearchForm() {
        pause(5000);
        click(By.xpath("//a[.=' Search ']"));
    }
    public void fillSearchForm(Search search){
        if(isSearchFormPresent() == false) return;
        typeCity(search.getCity());
        typeDates(search.getDates1(),search.getDates2());
    }
    public void fillSearchFormWithYear(Search search){
        if(isSearchFormPresent() == false) return;
        typeCity(search.getCity());

        LocalDate today = LocalDate.now();
        Random random = new Random();

        int currentMonth = today.getMonthValue();
        int currentYear = today.getYear();
        int targetMonth = currentMonth + random.nextInt(12 - currentMonth) + 1;

        int daysInTargetMonth = YearMonth.of(currentYear, targetMonth).lengthOfMonth();
        int randomDay1 = random.nextInt(daysInTargetMonth) + 1;
        int randomDay2 = random.nextInt(daysInTargetMonth - randomDay1 + 1) + randomDay1;

        String targetMonthLabel = today.getMonth().plus(targetMonth - currentMonth).getDisplayName(TextStyle.FULL_STANDALONE, Locale.US);
        String targetYearLabel = String.valueOf(currentYear);

        wd.findElement(By.xpath("//input[@id='dates']")).click();
        wd.findElement(By.xpath("//button[@aria-label='Choose month and year']")).click();
        wd.findElement(By.xpath("//td[@aria-label='2023']")).click();
        wd.findElement(By.xpath("//td[@aria-label='" + targetMonthLabel + " 2023']")).click();
        pause(1000);
        String dates = "td[aria-label='" + targetMonthLabel + " " + randomDay1 + ", " + targetYearLabel +"'] div.mat-calendar-body-cell-content";
        WebElement dateElement = wd.findElement(By.cssSelector(dates));
        dateElement.click();
        pause(1000);
        String dates2 = "td[aria-label='" + targetMonthLabel + " " + randomDay2 + ", " + targetYearLabel +"'] div.mat-calendar-body-cell-content";
        WebElement dateElement2 = wd.findElement(By.cssSelector(dates2));
        dateElement2.click();
    }

    public void fillSearchFormByType(Search search){
        if(isSearchFormPresent() == false) return;
        typeCity(search.getCity());
        typeCalend(By.id("dates"),search.getDates());
    }

    public void typeCity(String city){
        type(By.id("city"), city);
        click(By.cssSelector("div.pac-item"));
    }
    public void typeDates(String date, String date2) {
        wd.findElement(By.xpath("//input[@id='dates']")).click();
        String dates = "td[aria-label='" + date + "'] div.mat-calendar-body-cell-content";
        WebElement dateElement = wd.findElement(By.cssSelector(dates));
        dateElement.click();
        String dates2 = "td[aria-label='" + date2 + "'] div.mat-calendar-body-cell-content";
        WebElement dateElement2 = wd.findElement(By.cssSelector(dates2));
        dateElement2.click();
    }
    public void typeCalend(By locator, String text){
        WebElement element = wd.findElement(locator);
        element.clear();
        element.sendKeys(text);
        click(By.cssSelector("#city")); //cuz button is not available

    }
    public void submitSearch(){
            click(By.cssSelector("button[type='submit']"));
        }
    public boolean isSearchSuccess() {
        return isElementPresent(By.xpath("//div[@class='car-card']"));
    }

    public boolean isSearchFormPresent(){
        return new WebDriverWait(wd, 10)
                .until(ExpectedConditions
                        .textToBePresentInElement(
                                wd.findElement(By.cssSelector("h2")),
                                "your data"));
    }


}
