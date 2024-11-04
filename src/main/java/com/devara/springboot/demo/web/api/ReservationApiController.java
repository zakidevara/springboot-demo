package com.devara.springboot.demo.web.api;

import com.devara.springboot.demo.data.entity.Reservation;
import com.devara.springboot.demo.data.repository.ReservationRepository;
import com.devara.springboot.demo.web.exception.BadRequestException;
import com.devara.springboot.demo.web.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/reservations")
public class ReservationApiController {
  private final ReservationRepository reservationRepository;

  public ReservationApiController(ReservationRepository reservationRepository) {
    this.reservationRepository = reservationRepository;
  }

  @GetMapping
  public List<Reservation> getAllReservations() {
    return this.reservationRepository.findAll();
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Reservation createReservation(@RequestBody Reservation reservation) {
    return this.reservationRepository.save(reservation);
  }

  @GetMapping("/{id}")
  public Reservation getReservation(@PathVariable("id") long id) {
    Optional<Reservation> reservation = this.reservationRepository.findById(id);
    if (reservation.isEmpty()) {
      throw new NotFoundException("Reservation not found with id: " + id);
    }
    return reservation.get();
  }

  @PutMapping("/{id}")
  public Reservation updateReservation(@PathVariable("id") long id, @RequestBody Reservation reservation) {
    if (id != reservation.getId()) {
      throw new BadRequestException("Id on path doesn't match body");
    }
    return this.reservationRepository.save(reservation);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.RESET_CONTENT)
  public void deleteReservation(@PathVariable("id") long id) {
    this.reservationRepository.deleteById(id);
  }
}
