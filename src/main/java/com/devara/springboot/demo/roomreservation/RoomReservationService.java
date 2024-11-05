package com.devara.springboot.demo.roomreservation;

import com.devara.springboot.demo.guest.GuestApiController;
import com.devara.springboot.demo.guest.GuestDTO;
import com.devara.springboot.demo.guest.data.entity.Guest;
import com.devara.springboot.demo.reservation.ReservationApiController;
import com.devara.springboot.demo.reservation.ReservationDTO;
import com.devara.springboot.demo.reservation.data.entity.Reservation;
import com.devara.springboot.demo.room.RoomApiController;
import com.devara.springboot.demo.room.RoomDTO;
import com.devara.springboot.demo.room.data.entity.Room;
import com.devara.springboot.demo.roomreservation.model.RoomReservation;
import io.micrometer.common.util.StringUtils;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class RoomReservationService {
  private GuestApiController guestApiController;
  private RoomApiController roomApiController;
  private ReservationApiController reservationApiController;

  public List<RoomReservation> getRoomReservationForDate(String reservationDate) {
    Map<Long, RoomReservation> roomReservations = new HashMap<>();
    List<RoomDTO> rooms = this.roomApiController.getAllRooms();
    rooms.forEach(room -> {
      RoomReservation roomReservation = new RoomReservation();
      roomReservation.setRoomId(room.getId());
      roomReservation.setRoomName(room.getName());
      roomReservation.setRoomNumber(room.getRoomNumber());
      roomReservations.put(roomReservation.getRoomId(), roomReservation);
    });

    List<ReservationDTO> reservations = reservationApiController.getReservationsByDate(reservationDate);
    reservations.forEach(reservation -> {
      RoomReservation roomReservation = roomReservations.get(reservation.getRoomId());
      roomReservation.setReservationId(reservation.getId());
      roomReservation.setReservationDate(reservation.getReservationDate().toString());

      GuestDTO guest = this.guestApiController.getGuest(reservation.getGuestId());
      roomReservation.setGuestId(guest.getId());
      roomReservation.setFirstName(guest.getFirstName());
      roomReservation.setLastName(guest.getLastName());
    });

    return roomReservations.values().stream().toList();
  }
}
