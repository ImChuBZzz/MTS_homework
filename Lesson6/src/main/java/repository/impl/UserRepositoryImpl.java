package repository.impl;


import data.Booking;
import data.Room;
import data.User;

import repository.UserRepository;
import services.exceptions.RequiredFieldMissedException;
import services.exceptions.UserNotFoundException;
import utility.JBDCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.*;

public class UserRepositoryImpl implements UserRepository {

    @Override
    public User createUser(User user) throws SQLException {

        validateUser(user);
        user.setId(UUID.randomUUID().toString());
        try (Connection conn = JBDCUtil.getNewConnection()) {
            String queryInsertUser = "insert into users values (?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(queryInsertUser);
            statement.setString(1, user.getId());
            statement.setString(2, user.getPhone());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getFirst_name());
            statement.setString(5, user.getLast_name());
            statement.setString(6, user.getMid_name());
            statement.executeUpdate();
            statement.close();
        }
        return user;
    }

    @Override
    public User updateUser(User user) throws SQLException {

        String id = user.getId();
        if (getUserById(id) == null) throw new UserNotFoundException(String.format("User with %s not found", id));
        validateUser(user);
        User updatedUser;
        try (Connection conn = JBDCUtil.getNewConnection()) {
            String queryUpdateUser = "update users set phone = ?, email = ?, first_name = ?, last_name = ?, mid_name = ? where id_user = ?";
            PreparedStatement statement = conn.prepareStatement(queryUpdateUser);
            statement.setString(1, user.getPhone());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getFirst_name());
            statement.setString(4, user.getLast_name());
            statement.setString(5, user.getMid_name());
            statement.setString(6, id);
            statement.executeUpdate();
            updatedUser = getUserById(id);
            statement.close();
        }
        return updatedUser;
    }

    @Override
    public void deleteUser(String id) throws SQLException {

        if (getUserById(id) == null) throw new UserNotFoundException();
        try (Connection conn = JBDCUtil.getNewConnection()) {
            String queryDeleteUser = "delete from users where id_user = ?";
            PreparedStatement statement = conn.prepareStatement(queryDeleteUser);
            statement.setString(1, id);
            statement.executeUpdate();
            statement.close();
        }
    }

    @Override
    public User getUserById(String id) {
        try (Connection conn = JBDCUtil.getNewConnection()) {
            User user = new User();
            List<Booking> bookings = new ArrayList<>();
            String queryGetUser = "select * from users as u left join bookings as b on u.id_user = b.user_id left join rooms as r on r.id_room = b.room_id where id_user = ?";
            PreparedStatement statement = conn.prepareStatement(queryGetUser);
            statement.setString(1, id);
            statement.execute();
            ResultSet resultSet = statement.getResultSet();
            if (!resultSet.isBeforeFirst()) throw new UserNotFoundException();
            while(resultSet.next()){
                user = new User(resultSet);
                Booking booking = new Booking(resultSet);
                Room room = new Room(resultSet);
                booking.setRoom(room);
                bookings.add(booking);
            }
            user.setBookings(bookings);
            resultSet.close();
            statement.close();
            return user;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<User> getUsers(String first_name, String last_name, String mid_name) {

        List<User> userByParams = new ArrayList<>();
        try (Connection conn = JBDCUtil.getNewConnection()) {
            String queryAllUsers = "select * from users as u left join bookings as b on u.id_user = b.user_id left join rooms as r on r.id_room = b.room_id where first_name = ? and last_name = ? and mid_name = ?";
            PreparedStatement statement = conn.prepareStatement(queryAllUsers);
            statement.setString(1, first_name);
            statement.setString(2, last_name);
            statement.setString(3, mid_name);
            statement.executeQuery();
            ResultSet resultSet = statement.getResultSet();
            Map<User, ArrayList<Booking>> userBookings = new HashMap<>();
            while (resultSet.next()) {
                User user = new User(resultSet);
                Room room = new Room(resultSet);
                Booking booking = new Booking(resultSet);
                if (userBookings.containsKey(user)) {
                    if (booking.getId() != null) {
                        booking.setRoom(room);
                        userBookings.get(user).add(booking);
                    }
                }
                else {
                    if (booking.getId() != null) {
                        booking.setRoom(room);
                        userBookings.put(user, new ArrayList<>(List.of(booking)));
                    }
                    else {
                        userBookings.put(user, new ArrayList<>());
                    }

                }
            }
            for (Map.Entry<User, ArrayList<Booking>> roomBooking : userBookings.entrySet()){
                User user = roomBooking.getKey();
                user.setBookings(roomBooking.getValue());
                userByParams.add(user);
            }
            resultSet.close();
            statement.close();
            return userByParams;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void validateUser(User user) {

        if (user.getEmail().isBlank()) throw new RequiredFieldMissedException("email's field is empty");
        if (user.getPhone().isBlank()) throw new RequiredFieldMissedException("phone's field is empty");
        if (user.getFirst_name().isBlank()) throw new RequiredFieldMissedException("first name's field is empty");
        if (user.getLast_name().isBlank()) throw new RequiredFieldMissedException("last name's field is empty");
        if (user.getMid_name().isBlank()) throw new RequiredFieldMissedException("middle name's field is empty");
    }

}
