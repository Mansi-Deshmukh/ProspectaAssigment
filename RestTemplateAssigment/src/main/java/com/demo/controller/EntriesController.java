package com.demo.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
import com.demo.repository.EntriesRepository;
import com.demo.service.EntriesService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class EntriesController {

    @Autowired
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

   //http:localhost:8080/Animals
	
	@GetMapping("/entries/{category}")
	public ResponseEntity<List<EntriesDTO>> getHandler(@PathVariable("category") String category) {

		ApiData data = restTemplate.getForObject("https://api.publicapis.org/entries", ApiData.class);
		
		List<Entries> entries = data.getEntries();
     	List<EntriesDTO> dtoList = new ArrayList<>();

		for (Entries e : entries) {
			if(category.equals(e.getCategory())) {
				EntriesDTO addDto = new EntriesDTO();
				addDto.setTitle(e.getApi());
				addDto.setDescription(e.getDescription());
				dtoList.add(addDto);
			}
		}
		return new ResponseEntity<List<EntriesDTO>>(dtoList, HttpStatus.ACCEPTED);
	}

    
    // localhost:8080/entries
    @PostMapping("/entries")
    public ResponseEntity<String> createApiHandler(@RequestBody Entries entries) throws InvalidInputException{
        
        String message = eService.createApi(entries);
        return new ResponseEntity<String>(message, HttpStatus.CREATED);
        
    }


}
