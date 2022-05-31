package repository.impl;

import data.Booking;
import data.Room;
import data.User;
import repository.BookingRepository;
import services.exceptions.BookingNotFoundException;
import services.exceptions.RequiredFieldMissedException;


import utility.JBDCUtil;


import java.sql.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class BookingRepositoryImpl implements BookingRepository {
    @Override
    public Booking createBooking(Booking booking) {
        validateBooking(booking);
        booking.setId(UUID.randomUUID().toString());
        try (Connection conn = JBDCUtil.getNewConnection()) {
            String queryInsert = "insert into bookings values(?,?,?,?)";
            PreparedStatement statement = conn.prepareStatement(queryInsert);
            statement.setDate(1, Date.valueOf(booking.getCheck_in()));
            statement.setDate(2, Date.valueOf(booking.getCheck_out()));
            statement.setString(3, booking.getUser().getId());
            statement.setString(4, booking.getRoom().getId());
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Booking updateBooking(Booking booking) throws SQLException {
        String id = booking.getId();
        if(getBookingById(id) == null) throw  new BookingNotFoundException();
        validateBooking(booking);
        Booking updateBooking;
        try (Connection conn = JBDCUtil.getNewConnection()) {
            String queryUpdate = "update bookings set check_in = ?, check_out = ?, user_id = ?, room_id = ? where id_booking = ?";
            PreparedStatement statement = conn.prepareStatement(queryUpdate);
            statement.setDate(1, Date.valueOf(booking.getCheck_in()));
            statement.setDate(2, Date.valueOf(booking.getCheck_out()));
            statement.setString(3, booking.getUser().getId());
            statement.setString(4, booking.getRoom().getId());
            statement.setString(5, id);
            statement.executeUpdate();
            updateBooking = getBookingById(id);
            statement.close();
        }
        return updateBooking;
    }

    @Override
    public void deleteBooking(String id) {
        if (getBookingById(id) == null) throw new BookingNotFoundException();
        try (Connection conn = JBDCUtil.getNewConnection()) {
            String queryDeleteUser = "delete from bookings where id_booking = ?";
            PreparedStatement statement = conn.prepareStatement(queryDeleteUser);
            statement.setString(1, id);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Booking getBookingById(String id) {
        try (Connection conn = JBDCUtil.getNewConnection()) {
            Booking booking = new Booking();
            String query = "Select  * from bookings as b left join users as u on b.user_id  = u.id_user left join rooms as r on b.room_id  = r.id_room where b.id_booking = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, id);
            statement.execute();
            ResultSet resultSet = statement.getResultSet();
            if (!resultSet.isBeforeFirst()) throw new BookingNotFoundException();
            while (resultSet.next()) {
                User user = new User(resultSet);
                Room room = new Room(resultSet);
                booking = new Booking(resultSet);
                booking.setRoom(room);
                booking.setUser(user);
            }
            return booking;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Booking> getBookings(LocalDate checkInForm, LocalDate checkInTo, LocalDate checkOutForm, LocalDate checkOutTo)  {

        List<Booking> bookings = new ArrayList<>();
        try (Connection conn = JBDCUtil.getNewConnection()) {
            PreparedStatement statement = null;
            if (checkInForm != null && checkInTo != null) {
                String query = "Select  * from bookings as b left join users as u on b.user_id  = u.id_user left join rooms as r on b.room_id  = r.id_room where b.check_in >= ? and b.check_in <= ?";
                statement = conn.prepareStatement(query);
                statement.setDate(1, Date.valueOf(checkInForm));
                statement.setDate(2, Date.valueOf(checkInTo));
            } else if (checkOutForm != null && checkOutTo != null) {
                String query = "Select  * from bookings as b left join users as u on b.user_id  = u.id_user left join rooms as r on b.room_id  = r.id_room where b.check_out >= ? and b.check_out <= ?";
                statement = conn.prepareStatement(query);
                statement.setDate(1, Date.valueOf(checkOutForm));
                statement.setDate(2, Date.valueOf(checkOutTo));
            } else {
                return null;
            }
            statement.execute();
            ResultSet resultSet = statement.getResultSet();
            while (resultSet.next()) {
                User user = new User(resultSet);
                Room room = new Room(resultSet);
                Booking booking = new Booking(resultSet);
                booking.setRoom(room);
                booking.setUser(user);
                bookings.add(booking);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookings;
    }

    private void validateBooking(Booking booking) {
        if(booking.getRoom() == null) throw new RequiredFieldMissedException();
        if(booking.getUser() == null) throw new RequiredFieldMissedException();
        if(booking.getCheck_in() == null) throw new RequiredFieldMissedException();
        if(booking.getCheck_out() == null) throw new RequiredFieldMissedException();
    }
}
