package com.devara.springboot.demo.room;

import com.devara.springboot.demo.room.data.entity.Room;
import com.devara.springboot.demo.room.data.repository.RoomRepository;
import com.devara.springboot.demo.exception.BadRequestException;
import com.devara.springboot.demo.exception.NotFoundException;
import com.devara.springboot.demo.room.service.RoomService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/rooms")
public class RoomApiController {
  private final RoomService roomService;

  public RoomApiController(RoomService roomService) {
    this.roomService  = roomService;
  }

  @GetMapping
  public List<RoomDTO> getAllRooms() {
    return roomService.getAllRooms();
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public RoomDTO createRoom(@RequestBody RoomDTO room) {
    return this.roomService.createRoom(room);
  }

  @GetMapping("/{id}")
  public RoomDTO getRoom(@PathVariable("id") long id) {
    return this.roomService.getRoom(id);
  }

  @PutMapping("/{id}")
  public RoomDTO updateRoom(@PathVariable("id") long id, @RequestBody RoomDTO room) {
    if (id != room.getId()) {
      throw new BadRequestException("Id on path doesn't match body");
    }
    return roomService.updateRoom(id, room);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.RESET_CONTENT)
  public void deleteRoom(@PathVariable("id") long id) {
    roomService.deleteRoom(id);
  }
}
