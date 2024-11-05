package com.devara.springboot.demo.reservation;

import com.devara.springboot.demo.reservation.data.entity.Reservation;
import com.devara.springboot.demo.reservation.data.repository.ReservationRepository;
import com.devara.springboot.demo.exception.BadRequestException;
import com.devara.springboot.demo.exception.NotFoundException;
import com.devara.springboot.demo.reservation.service.ReservationService;
import io.micrometer.common.util.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/reservations")
public class ReservationApiController {
  private final ReservationService reservationService;

  public ReservationApiController(ReservationService reservationService) {
    this.reservationService = reservationService;
  }

  @GetMapping
  public List<ReservationDTO> getAllReservations() {
    return this.reservationService.getAllReservations();
  }

  @GetMapping("/{date}")
  public List<ReservationDTO> getReservationsByDate(@PathVariable("date") String date) {
    return this.reservationService.getReservationsByDate(date);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public ReservationDTO createReservation(@RequestBody ReservationDTO reservation) {
    return this.reservationService.createReservation(reservation);
  }

  @GetMapping("/{id}")
  public ReservationDTO getReservation(@PathVariable("id") long id) {
    return this.reservationService.getReservation(id);
  }

  @PutMapping("/{id}")
  public ReservationDTO updateReservation(@PathVariable("id") long id, @RequestBody ReservationDTO reservation) {
    if (id != reservation.getId()) {
      throw new BadRequestException("Id on path doesn't match body");
    }
    return this.reservationService.updateReservation(id, reservation);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.RESET_CONTENT)
  public void deleteReservation(@PathVariable("id") long id) {
    this.reservationService.deleteReservation(id);
  }
}
