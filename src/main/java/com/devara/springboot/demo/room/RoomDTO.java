package com.devara.springboot.demo.room;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RoomDTO {
  private long id;
  private String name;
  private String roomNumber;
  private String bedInfo;
}
