package com.devara.springboot.demo.data.repository;

import com.devara.springboot.demo.data.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoomRepository extends JpaRepository<Room, Long> {
  Optional<Room> findByRoomNumberIgnoreCase(String roomNumber);
}
