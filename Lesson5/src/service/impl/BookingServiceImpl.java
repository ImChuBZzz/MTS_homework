package service.impl;

import entity.Booking;

import entity.Room;
import repository.BookingRepository;
import service.BookingService;
import service.RoomService;
import service.exceptions.BookingNotFoundException;
import service.exceptions.RequiredFieldMissedException;
import service.exceptions.RoomNotFoundException;


import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

public class BookingServiceImpl implements BookingService {
    private final RoomService roomService;
    private final BookingRepository bookingRepository;

    public BookingServiceImpl(RoomService roomservice, BookingRepository bookingRepository) {
        this.roomService = roomservice;
        this.bookingRepository = bookingRepository;
    }

    @Override
    public Booking getBy(String id) {
        if(bookingRepository.getBy(id) == null) throw new BookingNotFoundException();
        return bookingRepository.getBy(id);
    }


    @Override
    public Booking createBooking(Booking booking)  {
        if(booking.getCheckIn() == null || booking.getCheckOut() == null || booking.getGuest() == null || booking.getRoom() == null) {
            throw new RequiredFieldMissedException();
        }
        booking.setId(UUID.randomUUID().toString());

        String roomId = booking.getRoom().getId();
        if(roomService.getBy(roomId) == null) throw new RoomNotFoundException();

        Set<Booking> addBookingToRoom = roomService.getBy(roomId).getBookings();
        addBookingToRoom.add(booking);
        roomService.getBy(roomId).setBookings(addBookingToRoom);

        return bookingRepository.save(booking);
    }

    @Override
    public Booking updateBooking(Booking booking) {
        if(booking.getId() == null) throw new RequiredFieldMissedException("id not found");
        if(bookingRepository.getBy(booking.getId()) == null) throw new BookingNotFoundException("booking not found");


        if(booking.getRoom() != null) {
            Room roomFromBooking = booking.getRoom();
            Room roomFromService = roomService.getBy(roomFromBooking.getId());
            if (roomFromService == null) throw new RoomNotFoundException("room not found");
            Set<Booking> oldBookings = roomFromService.getBookings();
            Set<Booking> newBookings = oldBookings.stream().filter(x -> !(x.getId().equals(booking.getId()))).collect(Collectors.toSet());
            newBookings.add(booking);
            roomFromService.setBookings(newBookings);
        }
        return  bookingRepository.save(booking);
    }

    @Override
    public void deleteBooking(Booking booking) {
        bookingRepository.delete(booking);

        Room roomFromService = roomService.getBy(booking.getRoom().getId());

        Set<Booking> oldBookingsBeforeDelete = roomFromService.getBookings();

        roomService.getBy(booking.getRoom().getId()).
                    setBookings(oldBookingsBeforeDelete.stream().
                            filter(x -> !(x.getId().equals(booking.getId()))).
                            collect(Collectors.toSet()));
    }
}
