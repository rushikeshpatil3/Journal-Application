package com.rushikeshpatil.journalApp.serviceImpl;

import com.rushikeshpatil.journalApp.entity.JournalEntity;
import com.rushikeshpatil.journalApp.entity.User;
import com.rushikeshpatil.journalApp.repository.JournalEntryRepository;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class JournalEntryService {


    @Autowired
    private JournalEntryRepository journalEntryRepository;

    @Autowired
    private UserService userService;

//    private static final Logger logger= LoggerFactory.getLogger(JournalEntryService.class);
    public JournalEntity saveEntries(JournalEntity journalEntity) {
        log.debug("Hello");
        journalEntryRepository.save(journalEntity);
        return journalEntity;
    }

    @Transactional
    public JournalEntity saveEntries(JournalEntity journalEntity, String username) {
        User user = userService.findUserByName(username);
        journalEntity.setDate(LocalDate.now());
        JournalEntity savedJournalEntry = journalEntryRepository.save(journalEntity);
        if (user.getJournalEntities() != null) {
            user.getJournalEntities().add(savedJournalEntry);

        } else {
            user.setJournalEntities(Arrays.asList(savedJournalEntry));
        }
        userService.saveUserEntries(user);
        return journalEntity;
    }

    public List<JournalEntity> getAllJournalEntries() {
        return journalEntryRepository.findAll();
    }

    public Optional<JournalEntity> getJournalEntryById(ObjectId id) {
        return journalEntryRepository.findById(id);
    }

    @Transactional
    public boolean deleteById(ObjectId id, String username) {
        boolean removeIf = false;
        try {
            User user = userService.findUserByName(username);
            removeIf = user.getJournalEntities().removeIf(journalEntry -> journalEntry.getId().equals(id));
            if (removeIf) {
                userService.saveUserEntries(user);
                journalEntryRepository.deleteById(id);
            }
        } catch (Exception e) {
            throw new RuntimeException("Error While deleting Entry " + e);
        }
        return removeIf;
    }

}
