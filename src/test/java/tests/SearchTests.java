package tests;

import models.Car;
import models.Search;
import models.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class SearchTests extends TestBase{


    @BeforeMethod
    public void precondition(){

    }

    @Test()
    public void FindCarPositiveByPickingDates(){

        Search search = Search.builder()
                .city("Tel Aviv")
                .dates1("July 19, 2023")
                .dates2("July 28, 2023")
                .build();

        app.getSearch().openSearchForm();
        app.getSearch().fillSearchForm(search);
        app.getSearch().submitSearch();
        app.getSearch().pause(5000);
        Assert.assertTrue(app.getSearch().isSearchSuccess());
    }
    @Test()
    public void FindCarPositiveByTypingDates(){

        Search search = Search.builder()
                .city("Tel Aviv")
                .dates("7/20/2023 - 7/28/2023")
                .build();

        app.getSearch().openSearchForm();
        app.getSearch().fillSearchFormByType(search);
        app.getSearch().submitSearch();
        app.getSearch().pause(5000);
        Assert.assertTrue(app.getSearch().isSearchSuccess());
    }
    @Test()
    public void FindCarPositiveByByTypingRandomDatesWithinOneYear(){
        LocalDate today = LocalDate.now();
        Random random = new Random();

        int daysToAdd1 = random.nextInt(365) + 1;  // Генерация случайного числа от 1 до 365
        LocalDate dates1 = today.plusDays(daysToAdd1);

        int daysToAdd2 = random.nextInt(365) + 1;  // Генерация случайного числа от 1 до 365
        LocalDate dates2 = dates1.plusDays(daysToAdd2);

        String formattedDates = dates1.format(DateTimeFormatter.ofPattern("M/d/yyyy")) + " - " + dates2.format(DateTimeFormatter.ofPattern("M/d/yyyy"));

        Search search = Search.builder()
                .city("Tel Aviv")
                .dates(formattedDates)
                .build();

        app.getSearch().openSearchForm();
        app.getSearch().fillSearchFormByType(search);
        app.getSearch().submitSearch();
        app.getSearch().pause(5000);
        Assert.assertTrue(app.getSearch().isSearchSuccess());
    }
    @Test()
    public void FindCarPositiveByPickingDatesInAnyMonthOfThePresentYear(){

        Search search = Search.builder()
                .city("Tel Aviv")
                .build();

        app.getSearch().openSearchForm();
        app.getSearch().fillSearchFormWithYear(search);
        app.getSearch().submitSearch();
        app.getSearch().pause(5000);
        Assert.assertTrue(app.getSearch().isSearchSuccess());
    }
}
