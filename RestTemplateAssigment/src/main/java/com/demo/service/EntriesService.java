package com.demo.service;

import java.util.List;

import com.demo.entities.Entries;
import com.demo.entities.EntriesDTO;
import com.demo.exception.EntryNotFoundException;
import com.demo.exception.InvalidInputException;

public interface EntriesService {
    
    public List<EntriesDTO> getTitleDescription(List<Entries> data,String category) throws EntryNotFoundException ;
    public String createApi(Entries entries) throws InvalidInputException;

    public List<Entries> getAll();
}
