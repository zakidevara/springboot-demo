package com.devara.springboot.demo.data.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="rooms")
@Data
public class Room {
  @Id
  @Column(name = "room_id")
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;
  @Column(name = "name")
  private String name;
  @Column(name = "room_number")
  private String roomNumber;
  @Column(name = "bed_info")
  private String bedInfo;
}
