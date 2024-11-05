package com.devara.springboot.demo.web.controller;

import com.devara.springboot.demo.room.data.repository.RoomRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/website/rooms")
public class RoomController {
//  private final RoomRepository roomRepository;

  public RoomController() {
//    this.roomRepository = roomRepository;
  }

  @GetMapping
  public String getRooms(Model model) {
//    model.addAttribute("rooms", this.roomRepository.findAll());
    return "room-list";
  }
}

