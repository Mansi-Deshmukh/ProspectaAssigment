package com.demo.entities;

import java.util.List;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


// @Entity
// @Data
// @NoArgsConstructor
// @AllArgsConstructor
public class ApiData {
    
    private Integer count;

    private List<Entries> entries;

    public ApiData(Integer count, List<Entries> entries) {
        this.count = count;
        this.entries = entries;
    }

    
}
