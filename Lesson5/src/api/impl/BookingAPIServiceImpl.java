package api.impl;

import api.BookingAPIService;
import entity.Booking;
import service.BookingService;
import service.RoomService;
import service.exceptions.BookingNotFoundException;
import service.exceptions.RequiredFieldMissedException;
import service.exceptions.RoomNotFoundException;

public class BookingAPIServiceImpl implements BookingAPIService {
    private final BookingService bookingService;
    private final RoomService roomService;

    public BookingAPIServiceImpl(BookingService bookingService, RoomService roomService) {
        this.bookingService = bookingService;
        this.roomService = roomService;
    }

    @Override
    public Booking createBooking(Booking booking) {
        try {
            bookingService.createBooking(booking);
        } catch (RequiredFieldMissedException | RoomNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return bookingService.createBooking(booking);
    }

    @Override
    public Booking updateBooking(String id, Booking booking) {
        booking.setId(id);

        try {
            bookingService.updateBooking(booking);
        } catch (RequiredFieldMissedException | RoomNotFoundException | BookingNotFoundException e) {
            System.out.println(e.getMessage());
        }

        return bookingService.updateBooking(booking);
    }

    @Override
    public Booking getBooking(String id) {
        try {
            bookingService.getBy(id);
        } catch (BookingNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return bookingService.getBy(id);
    }

    @Override
    public void deleteBooking(String id) {

        bookingService.deleteBooking(bookingService.getBy(id));

    }
}
