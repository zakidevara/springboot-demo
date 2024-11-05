package com.devara.springboot.demo.guest.service;

import com.devara.springboot.demo.exception.BadRequestException;
import com.devara.springboot.demo.exception.NotFoundException;
import com.devara.springboot.demo.guest.GuestDTO;
import com.devara.springboot.demo.guest.data.entity.Guest;
import com.devara.springboot.demo.guest.data.repository.GuestRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GuestService {
  private final GuestRepository guestRepository;

  public GuestService(GuestRepository guestRepository) {
    this.guestRepository = guestRepository;
  }

  public List<GuestDTO> getAllGuests() {
    return this.guestRepository.findAll().stream().map(
      this::convertToDto
    ).collect(Collectors.toList());
  }

  public GuestDTO createGuest(GuestDTO guest) {
    return convertToDto(this.guestRepository.save(convertToEntity(guest)));
  }

  public GuestDTO getGuest(long id) {
    Optional<Guest> guest = this.guestRepository.findById(id);
    if (guest.isEmpty()) {
      throw new NotFoundException("Guest not found with id: " + id);
    }
    return convertToDto(guest.get());
  }

  public GuestDTO updateGuest(long id, GuestDTO guest) {
    if (id != guest.getId()) {
      throw new BadRequestException("Id on path doesn't match body");
    }
    return convertToDto(this.guestRepository.save(convertToEntity(guest)));
  }

  public void deleteGuest(long id) {
    this.guestRepository.deleteById(id);
  }

  public GuestDTO convertToDto(Guest guest) {
    return new GuestDTO(guest.getId(), guest.getFirstName(), guest.getLastName(), guest.getEmailAddress(), guest.getAddress(), guest.getCountry(), guest.getState(), guest.getPhoneNumber());
  }

  public Guest convertToEntity(GuestDTO guestDTO) {
    return new Guest(guestDTO.getId(), guestDTO.getFirstName(), guestDTO.getLastName(), guestDTO.getEmailAddress(), guestDTO.getAddress(), guestDTO.getCountry(), guestDTO.getState(), guestDTO.getPhoneNumber());
  }


}
