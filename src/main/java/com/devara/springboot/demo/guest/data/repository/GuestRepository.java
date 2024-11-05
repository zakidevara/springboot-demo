package com.devara.springboot.demo.guest.data.repository;


import com.devara.springboot.demo.guest.data.entity.Guest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GuestRepository extends JpaRepository<Guest, Long> {

}
