package com.devara.springboot.demo.room.data.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Table(name="rooms")
@Data
@AllArgsConstructor
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
