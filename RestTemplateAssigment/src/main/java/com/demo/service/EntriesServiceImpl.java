package com.demo.service;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.entities.Entries;
import com.demo.entities.EntriesDTO;
import com.demo.exception.EntryNotFoundException;
import com.demo.exception.InvalidInputException;
import com.demo.repository.EntriesRepository;

@Service
public class EntriesServiceImpl implements EntriesService{


    @Autowired
    private EntriesRepository eRepository;

    @Override
    public List<EntriesDTO> getTitleDescription(List<Entries> data,String category) throws EntryNotFoundException{
        
       if(data == null){
         throw new EntryNotFoundException("Category not found.");
       }

       List<EntriesDTO> list = new ArrayList<>();
      for(Entries e : data){
        if(category.equals(((Entries) data).getCategory())){
         EntriesDTO entryDto = new EntriesDTO();
         entryDto.setTitle(e.getApi());
         entryDto.setDescription(e.getDescription());
         list.add(entryDto);
        }
      }
       return list;
    }

    /*
     * input :Api - check id api is save if same link is found that means api is same
     * throws : cannot save one api twice throw exception if invalid input found
     */
    @Override
    public String createApi(Entries entries) throws InvalidInputException {
        Entries existingEntry = eRepository.findByLink(entries.getLink());
        if(existingEntry != null){
            throw new InvalidInputException("Invalid input , Entry already registered with the link : "+ entries.getLink());
        }

        eRepository.save(entries);
       return "Entry Created..";
    }
    
    public List<Entries> getAll(){
      List<Entries> list=  eRepository.findAll();
      return list;
    }
}
