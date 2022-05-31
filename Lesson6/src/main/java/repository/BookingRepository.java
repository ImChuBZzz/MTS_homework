package repository;


import data.Booking;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface BookingRepository {

    Booking createBooking(Booking booking);
    Booking updateBooking(Booking booking) throws SQLException;
    void deleteBooking(String id);
    Booking getBookingById(String id);
    List<Booking> getBookings(LocalDate checkInForm, LocalDate checkInTo, LocalDate checkOutForm, LocalDate checkOutTo);
}
