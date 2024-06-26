package com.bluefox.client_crud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bluefox.client_crud.model.Client;
import com.bluefox.client_crud.service.ClientService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;
import java.util.NoSuchElementException;

/**
 * Controller for managing Client entities.
 */
@RestController
@RequestMapping("/customers")
public class ClientRestController {

    @Autowired 
    private ClientService clientService;

    @GetMapping
    public ResponseEntity<Iterable<Client>> getSearchAll() {
        return ResponseEntity.ok(clientService.searchAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Client> getSearchById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(clientService.searchById(id));
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping
    public ResponseEntity<Client> postInsertClient(@RequestBody Client client) {
        clientService.insert(client);
        return ResponseEntity.ok(client);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Client> putUpdateClient(@PathVariable Long id, @RequestBody Client client) {
        try {
            clientService.update(id, client);
            return ResponseEntity.ok(client);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable Long id) {
        try {
            clientService.delete(id);
            return ResponseEntity.ok().build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    } 

    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void handleNoSuchElementException() {
        System.out.println("Not Find Element");
    }
}
