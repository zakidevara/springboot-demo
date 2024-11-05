package com.devara.springboot.demo.room.service;

import com.devara.springboot.demo.exception.BadRequestException;
import com.devara.springboot.demo.exception.NotFoundException;
import com.devara.springboot.demo.room.RoomDTO;
import com.devara.springboot.demo.room.data.entity.Room;
import com.devara.springboot.demo.room.data.repository.RoomRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RoomService {
  private final RoomRepository roomRepository;

  public RoomService(RoomRepository roomRepository) {
    this.roomRepository = roomRepository;
  }

  public List<RoomDTO> getAllRooms() {
    return this.roomRepository.findAll().stream().map(
      this::convertToDto
    ).collect(Collectors.toList());
  }

  public RoomDTO createRoom(RoomDTO room) {
    return convertToDto(this.roomRepository.save(convertToEntity(room)));
  }

  public RoomDTO getRoom(long id) {
    Optional<Room> room = this.roomRepository.findById(id);
    if (room.isEmpty()) {
      throw new NotFoundException("Room not found with id: " + id);
    }
    return convertToDto(room.get());
  }

  public RoomDTO updateRoom(long id, RoomDTO room) {
    if (id != room.getId()) {
      throw new BadRequestException("Id on path doesn't match body");
    }
    return convertToDto(this.roomRepository.save(convertToEntity(room)));
  }

  public void deleteRoom(long id) {
    this.roomRepository.deleteById(id);
  }

  public RoomDTO convertToDto(Room room) {
    return new RoomDTO(room.getId(), room.getName(), room.getRoomNumber(), room.getBedInfo());
  }

  public Room convertToEntity(RoomDTO roomDTO) {
    return new Room(roomDTO.getId(), roomDTO.getName(), roomDTO.getRoomNumber(), roomDTO.getBedInfo());
  }
}
