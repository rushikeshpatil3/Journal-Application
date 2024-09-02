package com.rushikeshpatil.journalApp.cache;

import com.rushikeshpatil.journalApp.entity.ConfigJournalAppEntity;
import com.rushikeshpatil.journalApp.repository.ConfigJournalAppRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class AppCache {

    public enum keys{
        WEATHER_API;
    }

    @Autowired
    private ConfigJournalAppRepository configJournalAppRepository;
    public Map<String, String> APP_CACHE;


    @PostConstruct
    public void init() {
        APP_CACHE=new HashMap<>();
        System.out.println("@PostConstruct called");
        List<ConfigJournalAppEntity> all = configJournalAppRepository.findAll();
        System.out.println("@PostConstruct called....1");
        for (ConfigJournalAppEntity configJournalAppEntity:all){
            APP_CACHE.put(configJournalAppEntity.getKey(),configJournalAppEntity.getValue());
        }

    }
}
