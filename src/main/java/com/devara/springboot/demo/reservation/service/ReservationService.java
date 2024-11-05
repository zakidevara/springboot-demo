package com.devara.springboot.demo.reservation.service;

import com.devara.springboot.demo.exception.BadRequestException;
import com.devara.springboot.demo.exception.NotFoundException;
import com.devara.springboot.demo.reservation.ReservationDTO;
import com.devara.springboot.demo.reservation.data.entity.Reservation;
import com.devara.springboot.demo.reservation.data.repository.ReservationRepository;
import io.micrometer.common.util.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReservationService {
  private final ReservationRepository reservationRepository;

  public ReservationService(ReservationRepository reservationRepository) {
    this.reservationRepository = reservationRepository;
  }

  public List<ReservationDTO> getAllReservations() {
    return this.reservationRepository.findAll().stream().map(
      this::convertToDto
    ).collect(Collectors.toList());
  }

  public List<ReservationDTO> getReservationsByDate(String date) {
    Date dateObj = null;
    if (StringUtils.isNotEmpty(date)) {
      dateObj = Date.valueOf(date);
    } else {
      dateObj = new Date(new java.util.Date().getTime());
    }

    return this.reservationRepository.findAllByReservationDate(dateObj).stream().map(
      this::convertToDto
    ).collect(Collectors.toList());
  }

  public ReservationDTO createReservation(ReservationDTO reservation) {
    return convertToDto(this.reservationRepository.save(convertToEntity(reservation)));
  }

  public ReservationDTO getReservation(long id) {
    Optional<Reservation> reservation = this.reservationRepository.findById(id);
    if (reservation.isEmpty()) {
      throw new NotFoundException("Reservation not found with id: " + id);
    }
    return convertToDto(reservation.get());
  }

  public ReservationDTO updateReservation(long id, ReservationDTO reservation) {
    if (id != reservation.getId()) {
      throw new BadRequestException("Id on path doesn't match body");
    }
    return convertToDto(this.reservationRepository.save(convertToEntity(reservation)));
  }

  public void deleteReservation(long id) {
    this.reservationRepository.deleteById(id);
  }

  public ReservationDTO convertToDto(Reservation reservation) {
    return new ReservationDTO(reservation.getId(), reservation.getRoomId(), reservation.getGuestId(), reservation.getReservationDate());
  }

  public Reservation convertToEntity(ReservationDTO reservationDTO) {
    return new Reservation(reservationDTO.getId(), reservationDTO.getRoomId(), reservationDTO.getGuestId(), reservationDTO.getReservationDate());
  }
}
