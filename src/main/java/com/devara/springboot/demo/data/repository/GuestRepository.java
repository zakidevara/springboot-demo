package com.devara.springboot.demo.data.repository;


import com.devara.springboot.demo.data.entity.Guest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GuestRepository extends JpaRepository<Guest, Long> {

}
