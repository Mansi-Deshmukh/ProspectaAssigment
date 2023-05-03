package com.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.demo.entities.ApiData;
import com.demo.entities.Entries;
import com.demo.entities.EntriesDTO;
import com.demo.exception.EntryNotFoundException;
import com.demo.exception.InvalidInputException;
import com.demo.service.EntriesService;

@RestController
public class EntriesController {

    @Autowired(required = false)
    private RestTemplate restTemplate;
    
    @Autowired
    private EntriesService eService;

    /**
     * @param category
     * @return  EntriesDTo : title , Description.
     * @throws EntryNotFoundException
     * 
     * localhost:8080/{Animals}
     */
    @GetMapping("/{category}")
    public ResponseEntity<List<EntriesDTO>> getTitleDescription(@PathVariable("category") String category) throws EntryNotFoundException {
        ApiData temp = restTemplate.getForObject("https://api.publicapis.org/entries", ApiData.class);
        List<Entries> entries = ((Object) temp).getEntries();
        List<EntriesDTO> list = eService.getTitleDescription(entries, category);
        return new ResponseEntity<List<EntriesDTO>>(list, HttpStatus.OK);
    }

    // localhost:8080/entries
    @PostMapping("/entries")
    public ResponseEntity<String> createApiHandler(@RequestBody Entries entries) throws InvalidInputException{
        
        String message = eService.createApi(entries);
        return new ResponseEntity<String>(message, HttpStatus.CREATED);
        
    }
}
