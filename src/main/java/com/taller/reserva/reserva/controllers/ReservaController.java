package com.taller.reserva.reserva.controllers;

import com.taller.reserva.reserva.model.entity.Reserva;
import com.taller.reserva.reserva.services.ReservaService;

import jakarta.validation.Valid;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reservas")
public class ReservaController {

    private ReservaService service;

    @PostMapping
    public ResponseEntity<?> createReserva(@Valid @RequestBody Reserva reserva, BindingResult result) {
        ResponseEntity<?> errors = validar(result);
        if (errors != null) return errors;
        return ResponseEntity.status(201).body(service.save(reserva));    
    }


    @GetMapping
    public List<Reserva> listar() {
        return service.findAll();
    }

    // Method to retrieve a Reserva by its ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getReserva(@PathVariable Long id, @Valid @RequestBody Reserva reserva, BindingResult result) {
        Optional<Reserva> reservaOptional = service.findById(id);
        if (reservaOptional.isPresent()) {
            return ResponseEntity.ok().body(reservaOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Method to update an existing Reserva
    @PutMapping("/{id}")
    public ResponseEntity<?> updateReserva(@PathVariable Long id, @Valid @RequestBody Reserva reserva, BindingResult result) {
        ResponseEntity<?> errors = validar(result);
        if (errors != null) return errors;

        Optional<Reserva> reservaOptional = service.findById(id);
        if (reservaOptional.isPresent()) {
            Reserva reservaDB = reservaOptional.get();
            reservaDB.setCliente(reserva.getCliente());
            reservaDB.setPersonas(reserva.getPersonas());
            reservaDB.setServicio(reserva.getServicio());
            return ResponseEntity.status(HttpStatus.CREATED).body(service.save(reservaDB));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Method to delete a Reserva
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReserva(@PathVariable Long id) {
        Optional<Reserva> reservaOptional = service.findById(id);
        if (reservaOptional.isPresent()) {
            service.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }    
    }

    private ResponseEntity<?> validar(BindingResult result) {
        if (result.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            for (FieldError error : result.getFieldErrors()) {
                errors.put(error.getField(), error.getDefaultMessage());
            }
            return ResponseEntity.badRequest().body(errors);
        }
        return null;
    }
}