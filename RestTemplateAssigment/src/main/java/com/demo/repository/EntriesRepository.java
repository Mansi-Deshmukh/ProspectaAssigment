package com.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.entities.Entries;

@Repository
public interface EntriesRepository extends JpaRepository<Entries, Long> {
    public Entries findByLink(String link);
    public List<Entries> findByCategory(String category);
}
