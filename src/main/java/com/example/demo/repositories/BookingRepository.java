package com.example.demo.repositories;

import com.example.demo.domain.BookingEntity;
import org.springframework.data.repository.CrudRepository;

public interface BookingRepository extends CrudRepository<BookingEntity,Integer> {
}
