package repository.impl;

import data.Booking;
import data.Room;
import data.User;
import repository.RoomRepository;
import services.exceptions.RoomNotFoundException;
import services.exceptions.RequiredFieldMissedException;
import utility.JBDCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class RoomRepositoryImpl implements RoomRepository {

    @Override
    public Room creatRoom(Room room) throws SQLException {

        validateRoom(room);
        room.setId(UUID.randomUUID().toString());
        try (Connection conn = JBDCUtil.getNewConnection()) {
            String queryInsertRoom = "insert into rooms values (?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(queryInsertRoom);
            statement.setString(1, room.getId());
            statement.setString(2, room.getRoom_number());
            statement.setInt(3, room.getFloor());
            statement.setString(4, room.getRoom_type());
            statement.setString(5, room.getDescription());
            statement.setInt(6, room.getPrice());
            statement.executeUpdate();
            statement.close();
        }
        return room;

    }


    @Override
    public Room updateRoom(Room room) throws SQLException {

        String id = room.getId();
        if (getRoomById(id) == null) throw new RoomNotFoundException("Room not found");
        validateRoom(room);
        Room updatedRoom;
        try (Connection conn = JBDCUtil.getNewConnection()) {
            String queryUpdateString = "update rooms set room_number = ?, floor = ?, room_type = ?, description = ?, price = ? where id_room = ?";
            PreparedStatement statement = conn.prepareStatement(queryUpdateString);
            statement.setString(1, room.getRoom_number());
            statement.setInt(2, room.getFloor());
            statement.setString(3, room.getRoom_number());
            statement.setString(4, room.getDescription());
            statement.setInt(5, room.getPrice());
            statement.setString(6, id);
            statement.executeUpdate();
            updatedRoom = getRoomById(id);
            statement.close();
        }
        return updatedRoom;

    }


    @Override
    public void deleteRoom(String id) throws SQLException {

        if (getRoomById(id) == null) throw new RoomNotFoundException();
        try (Connection conn = JBDCUtil.getNewConnection()) {
            String queryDeleteRoom = "delete from rooms where id_room = ?";
            PreparedStatement statement = conn.prepareStatement(queryDeleteRoom);
            statement.setString(1, id);
            statement.executeUpdate();
            statement.close();
            }

    }



    @Override
    public Room getRoomById(String id) {
        try (Connection conn = JBDCUtil.getNewConnection()) {
            Room room = new Room();
            List<Booking> bookings = new ArrayList<>();
            String queryGetRoom = "select * from rooms as r left join bookings as b on r.id_room = b.user_id left join users as u on u.id_user = b.user_id where id_room = ?";
            PreparedStatement statement = conn.prepareStatement(queryGetRoom);
            statement.setString(1, id);
            statement.executeQuery();
            ResultSet resultSet = statement.getResultSet();
            if (!resultSet.isBeforeFirst()) throw new RoomNotFoundException("room not found");
            while(resultSet.next()){
                room = new Room(resultSet);
                Booking booking = new Booking(resultSet);
                User user = new User(resultSet);
                booking.setUser(user);
                bookings.add(booking);
            }
            room.setBookings(bookings);
            resultSet.close();
            statement.close();
            return room;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public List<Room> getRooms(String roomNumber, Integer floor, String roomType, Integer price) {
        List<Room> roomsByParams = new ArrayList<>();
        try (Connection conn = JBDCUtil.getNewConnection()) {
            String queryGetRoom = "select * from rooms as r left join bookings as b on b.room_id  = r.id_room left join users as u on b.user_id  = u.id_user where room_number=? and floor=? and room_type=? and price=?";
            PreparedStatement statement = conn.prepareStatement(queryGetRoom);
            statement.executeQuery();
            ResultSet resultSet = statement.getResultSet();
            Map <Room, ArrayList<Booking>> roomBookings = new HashMap<>();
            while (resultSet.next()) {
                Room room = new Room(resultSet);
                User user = new User(resultSet);
                Booking booking = new Booking(resultSet);
                if (roomBookings.containsKey(room)) {
                    if (booking.getId() != null) {
                        booking.setUser(user);
                        roomBookings.get(room).add(booking);
                    }
                } else {
                    if (booking.getId() != null) {
                        booking.setUser(user);
                        roomBookings.put(room, new ArrayList<>(List.of(booking)));
                    } else {
                        roomBookings.put(room, new ArrayList<>());
                    }
                }
            }

            for (Map.Entry<Room, ArrayList<Booking>> rooms : roomBookings.entrySet()) {
                Room room = rooms.getKey();
                room.setBookings(rooms.getValue());
                roomsByParams.add(room);
            }
            return roomsByParams;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    private void validateRoom(Room room) {

        if (room.getFloor() == null) throw new RequiredFieldMissedException("floor's field is empty");
        if (room.getPrice() == null) throw new RequiredFieldMissedException("price's field is empty");
        if (room.getRoom_number() == null) throw new RequiredFieldMissedException("№ room's field is empty");

    }
}
