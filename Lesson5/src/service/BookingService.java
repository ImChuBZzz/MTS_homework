package service;

import entity.Booking;
import service.exceptions.BookingNotFoundException;
import service.exceptions.RequiredFieldMissedException;
import service.exceptions.RoomNotFoundException;

public interface BookingService {
    Booking getBy(String id);
    Booking createBooking(Booking booking);
    Booking updateBooking(Booking booking);
    void deleteBooking(Booking booking);
}
