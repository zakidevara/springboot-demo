package com.devara.springboot.demo.guest;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GuestDTO {
  private long id;
  private String firstName;
  private String lastName;
  private String emailAddress;
  private String address;
  private String country;
  private String state;
  private String phoneNumber;
}
