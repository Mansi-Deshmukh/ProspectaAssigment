package com.demo.entities;

import java.util.List;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


// @Entity
@Data
public class ApiData {
    
    private Integer count;

    private List<Entries> entries;

   

    
}
