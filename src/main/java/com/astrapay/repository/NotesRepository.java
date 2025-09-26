package com.astrapay.repository;

import com.astrapay.entity.Notes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.LinkedList;
import java.util.Optional;

public interface NotesRepository extends JpaRepository<Notes, String> {
    @Query("select n from Notes n")
    LinkedList<Notes> getNotes();

    Optional<Notes> findById(String id);
}
