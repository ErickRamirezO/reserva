package com.taller.reserva.reserva.repository;

import com.taller.reserva.reserva.model.entity.Reserva;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservaRepository extends CrudRepository<Reserva, Long> {
}