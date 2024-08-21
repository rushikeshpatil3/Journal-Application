package com.rushikeshpatil.journalApp.controller;

import com.rushikeshpatil.journalApp.entity.JournalEntity;
import com.rushikeshpatil.journalApp.entity.User;
import com.rushikeshpatil.journalApp.serviceImpl.JournalEntryService;
import com.rushikeshpatil.journalApp.serviceImpl.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {

    @Autowired
    private JournalEntryService journalEntryService;

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<?> getAllJournalsByUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userService.findUserByName(username);
        List<JournalEntity> journalEntries = user.getJournalEntities();
        if (journalEntries != null && !journalEntries.isEmpty()) {
            return new ResponseEntity<>(journalEntries, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{id}")
    public ResponseEntity<JournalEntity> getJournalById(@PathVariable ObjectId id) {
//        return journalEntryService.getJournalEntryById(id).orElse(null);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userService.findUserByName(username);
        List<JournalEntity> collect = user.getJournalEntities().stream().filter(x -> x.getId().equals(id)).collect(Collectors.toList());
        if (!collect.isEmpty()) {
            Optional<JournalEntity> journalEntryById = journalEntryService.getJournalEntryById(id);
            if (journalEntryById.isPresent()) {
                return new ResponseEntity<>(journalEntryById.get(), HttpStatus.OK);
            }

        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<JournalEntity> createJournalEntry(@RequestBody JournalEntity journalEntity) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();
            journalEntryService.saveEntries(journalEntity, username);
            return new ResponseEntity<>(journalEntity, HttpStatus.CREATED);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
//        return journalEntryService.saveEntries(journalEntity);
//        return journalEntity;
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateJournalEntry(@RequestBody JournalEntity newEntry, @RequestParam ObjectId id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userService.findUserByName(username);
//        JournalEntity oldEntry = journalEntryService.getJournalEntryById(id).orElse(null);
        List<JournalEntity> collect = user.getJournalEntities().stream().filter(x -> x.getId().equals(id)).collect(Collectors.toList());
        if (!collect.isEmpty()) {
            Optional<JournalEntity> journalEntity = journalEntryService.getJournalEntryById(id);
            if (journalEntity.isPresent()) {
                JournalEntity oldEntry = journalEntity.get();
                oldEntry.setTitle(newEntry.getTitle() != null && !newEntry.getTitle().equals("") ? newEntry.getTitle() : oldEntry.getTitle());
                oldEntry.setContent(newEntry.getContent() != null && !newEntry.getContent().equals("") ? newEntry.getContent() : oldEntry.getContent());
                journalEntryService.saveEntries(oldEntry);
                return new ResponseEntity<>(oldEntry, HttpStatus.OK);
            }

        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteJournalById(@PathVariable ObjectId id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        boolean isRemoved = journalEntryService.deleteById(id, username);
        if (isRemoved) {

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }
}
