package com.devara.springboot.demo.reservation;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReservationDTO {
  private long id;
  private long roomId;
  private long guestId;
  private Date reservationDate;
}

