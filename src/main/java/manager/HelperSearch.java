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
import java.time.format.DateTimeFormatter;
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

    public void fillSearchFormCW15(String city, String dateForm, String dateTo) {          //CW15
        fillCity(city);
//        selectPeriodDays(dateForm, dateTo);
//        selectPeriodDaysDatePicker(dateForm, dateTo);
//        selectPeriodMonthDatePicker(dateForm, dateTo);
        selectPeriodYearsDatePicker(dateForm, dateTo);

    }
    public void fillCity(String city){           //CW15
        type(By.id("city"), city);
        click(By.cssSelector("div.pac-item"));
    }
    public void selectPeriodDays(String dateForm, String dateTo){          //CW15
        //click(By.id("dates"));
        type(By.id("dates"),dateForm + " - " + dateTo);
        pause(3000);
        click(By.cssSelector("#city"));
    }

    public void selectPeriodDaysDatePicker(String dateForm, String dateTo){ //CW15
        String[] startDate = dateForm.split("/");
        String[] endDate = dateTo.split("/");
        //      7/15/2023
        //index 0  1  2
        click(By.id("dates"));
//        click(By.xpath("//div[.=' " + startDate[1] + " ']"));
//        click(By.xpath("//div[.=' " + endDate[1] + " ']"));
        String locatorStartDate = String.format("//div[.=' %s ']", startDate[1]);
        String locatorEndDate = String.format("//div[.=' %s ']", endDate[1]);
        click(By.xpath(locatorStartDate));
        click(By.xpath(locatorEndDate));
        pause(3000);
    }
    public void selectPeriodMonthDatePicker(String dateForm, String dateTo){ //CW15
        int fromNowToStart = 0, fromStartToEnd = 0;
        String[] startDate = dateForm.split("/");
        String[] endDate = dateTo.split("/");

        click(By.id("dates"));

        fromStartToEnd = Integer.parseInt(endDate[0]) - Integer.parseInt(startDate[0]);
        if(LocalDate.now().getMonthValue() != Integer.parseInt(startDate[0])){
            fromNowToStart = Integer.parseInt(startDate[0]) - LocalDate.now().getMonthValue();
        }
        for(int i = 0; i < fromNowToStart; i++){
            click(By.xpath("//button[@aria-label='Next month']"));
            pause(1000);
        }
        String locatorStartDate = String.format("//div[.=' %s ']", startDate[1]);
        String locatorEndDate = String.format("//div[.=' %s ']", endDate[1]);
        click(By.xpath(locatorStartDate));
        pause(1000);
        for(int i = 0; i < fromStartToEnd; i++){
            click(By.xpath("//button[@aria-label='Next month']"));
            pause(1000);
        }
        click(By.xpath(locatorEndDate));
        pause(3000);

    }

    public void selectPeriodYearsDatePicker(String dateForm, String dateTo) {
        LocalDate startDate = LocalDate.parse(dateForm, DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        LocalDate endDate = LocalDate.parse(dateTo, DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        LocalDate nowDate = LocalDate.now();
        String locatorStartDate = String.format("//div[.=' %s ']", startDate.getDayOfMonth());
        String locatorEndDate = String.format("//div[.=' %s ']", endDate.getDayOfMonth());
        click(By.id("dates"));

        int startToEndMonth = startDate.getYear() - nowDate.getYear() == 0 ?
                startDate.getMonthValue() - nowDate.getMonthValue() :
                12 - nowDate.getMonthValue() + startDate.getMonthValue();

        for(int i = 0; i < startToEndMonth; i++){
            click(By.xpath("//button[@aria-label='Next month']"));
            pause(1000);
        }

        click(By.xpath(locatorStartDate));

        startToEndMonth = endDate.getYear() - startDate.getYear() == 0 ?
                endDate.getMonthValue() - startDate.getMonthValue() :
                12 - startDate.getMonthValue() + endDate.getMonthValue();

        click(By.xpath(locatorEndDate));

        for(int i = 0; i < startToEndMonth; i++){
            click(By.xpath("//button[@aria-label='Next month']"));
            pause(1000);
        }
        click(By.xpath(locatorEndDate));


    }

    //////////


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
       // wd.findElement(By.xpath("//button[@type='submit']")).click();
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
