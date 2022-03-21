package api.impl;

import api.RoomAPIService;
import entity.Booking;
import entity.Room;
import entity.RoomType;
import service.BookingService;
import service.RoomService;
import service.exceptions.RequiredFieldMissedException;
import service.exceptions.RoomNotFoundException;


import java.util.List;
import java.util.Map;

public class RoomAPIServiceImpl implements RoomAPIService {
    private final BookingService bookingService;
    private final RoomService roomService;

    public RoomAPIServiceImpl(BookingService bookingService, RoomService roomService) {
        this.bookingService = bookingService;
        this.roomService = roomService;
    }

    @Override
    public Room createRoom(Room room) {
        try {
            roomService.createRoom(room);
        } catch (RequiredFieldMissedException e) {
            System.out.println(e.getMessage());
        }
        return room;
    }

    @Override
    public Room updateRoom(String id, Room room) {
        try {
            roomService.updateRoom(id, room);
        } catch (RoomNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return roomService.updateRoom(id, room);
    }

    @Override
    public Room getRoom(String id) {
        try {
            roomService.getBy(id);
        } catch (RoomNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return roomService.getBy(id);
    }

    @Override
    public Map<RoomType, List<Room>> getRoomsGroupedByType() {
        return roomService.getRoomsGroupByType();
    }

    @Override
    public void deleteRoom(String id) {
        try {
            Room room = roomService.getBy(id);
        } catch (RoomNotFoundException e) {
            System.out.println(e.getMessage());
        }
        for (Booking b: roomService.getBy(id).getBookings()) {
            bookingService.deleteBooking(b);
        }
        roomService.deleteRoom(roomService.getBy(id));

    }
}
