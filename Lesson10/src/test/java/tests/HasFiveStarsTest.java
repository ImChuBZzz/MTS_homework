package tests;

import org.junit.Test;
import pages.BookingPage;
import pages.StartPage;

import java.time.LocalDate;
import java.util.Random;

public class HasFiveStarsTest extends BaseTest {

    @Test
    public void hasFiveStarsTest() {
        Random rnd = new Random();
        String city = "Барселона";
        LocalDate dayIn = LocalDate.now();
        LocalDate dayOut = dayIn.plusDays(7);
        int adultCount = rnd.nextInt(3) + 3;
        int childrenCount = rnd.nextInt(5);
        int roomCount = rnd.nextInt(3) + 3;
        int hotelRating = 5;

        StartPage startPage = new StartPage();
        startPage.openPage()
                .chooseCity(city)
                .checkInAndCheckOut(dayIn.toString(), dayOut.toString())
                .setAdultCount(adultCount)
                .setChildrenCount(childrenCount)
                .setRoomsCount(roomCount)
                .searchResults();

        BookingPage bookingPage = new BookingPage();
        bookingPage.setFiveStarCheckBox()
                .checkPropertyCount()
                .checkHotelsRating(hotelRating)
                .checkFiveStarsHotelsCount();
    }
}
