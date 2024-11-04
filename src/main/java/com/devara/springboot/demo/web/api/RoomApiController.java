package com.devara.springboot.demo.web.api;

import com.devara.springboot.demo.data.entity.Room;
import com.devara.springboot.demo.data.repository.RoomRepository;
import com.devara.springboot.demo.web.exception.BadRequestException;
import com.devara.springboot.demo.web.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/rooms")
public class RoomApiController {
  private final RoomRepository roomRepository;

  public RoomApiController(RoomRepository roomRepository) {
    this.roomRepository = roomRepository;
  }

  @GetMapping
  public List<Room> getAllRooms() {
    return this.roomRepository.findAll();
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Room createRoom(@RequestBody Room room) {
    return this.roomRepository.save(room);
  }

  @GetMapping("/{id}")
  public Room getRoom(@PathVariable("id") long id) {
    Optional<Room> room = this.roomRepository.findById(id);
    if (room.isEmpty()) {
      throw new NotFoundException("Room not found with id: " + id);
    }
    return room.get();
  }

  @PutMapping("/{id}")
  public Room updateRoom(@PathVariable("id") long id, @RequestBody Room room) {
    if (id != room.getId()) {
      throw new BadRequestException("Id on path doesn't match body");
    }
    return this.roomRepository.save(room);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.RESET_CONTENT)
  public void deleteRoom(@PathVariable("id") long id) {
    this.roomRepository.deleteById(id);
  }
}
