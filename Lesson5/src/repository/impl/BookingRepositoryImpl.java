package repository.impl;

import entity.Booking;
import repository.BookingRepository;

import java.util.HashSet;
import java.util.Set;

public class BookingRepositoryImpl implements BookingRepository {
    private static final Set<Booking> bookings = new HashSet<Booking>();

    @Override
    public Booking save(Booking booking) {
        for(Booking b: bookings){
            String bId = b.getId();
            if(bId.equals(booking.getId())) {
                bookings.remove(b);
                break;
            }
        }
        bookings.add(booking);
        return booking;
    }

    @Override
    public void delete(Booking booking) {
        bookings.remove(booking);
    }

    @Override
    public Booking getBy(String id) {
        Booking foundBooking = new Booking();
        for(Booking b: bookings) {
            if(b.getId().equals(id)){
                foundBooking = b;
                break;
            }
        }
        return foundBooking;
    }

    @Override
    public Set<Booking> getAll() {
        return bookings;
    }
}
