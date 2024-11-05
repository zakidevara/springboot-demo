package com.devara.springboot.demo.guest;

import com.devara.springboot.demo.guest.data.entity.Guest;
import com.devara.springboot.demo.guest.data.repository.GuestRepository;
import com.devara.springboot.demo.exception.BadRequestException;
import com.devara.springboot.demo.exception.NotFoundException;
import com.devara.springboot.demo.guest.service.GuestService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/guests")
public class GuestApiController {
  private final GuestService guestService;

  public GuestApiController(GuestService guestService) {
    this.guestService = guestService;
  }

  @GetMapping
  public List<GuestDTO> getAllGuests() {
    return this.guestService.getAllGuests();
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public GuestDTO createGuest(@RequestBody GuestDTO guest) {
    return this.guestService.createGuest(guest);
  }

  @GetMapping("/{id}")
  public GuestDTO getGuest(@PathVariable("id") long id) {
    return this.guestService.getGuest(id);
  }

  @PutMapping("/{id}")
  public GuestDTO updateGuest(@PathVariable("id") long id, @RequestBody GuestDTO guest) {
    if (id != guest.getId()) {
      throw new BadRequestException("Id on path doesn't match body");
    }
    return this.guestService.updateGuest(id, guest);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.RESET_CONTENT)
  public void deleteGuest(@PathVariable("id") long id) {
    this.guestService.deleteGuest(id);
  }
}
