package com.astrapay.service;

import com.astrapay.dto.NoteRequestDto;
import com.astrapay.entity.Notes;
import com.astrapay.exception.NoteNotFoundException;
import com.astrapay.repository.NotesRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class NotesService {
    private final NotesRepository notesRepository;

    @Autowired
    public NotesService(NotesRepository notesRepository) {
        this.notesRepository = notesRepository;
    }

    public Notes saveNote(NoteRequestDto noteRequestDto) {
        Notes note = new Notes();
        note.setTitle(noteRequestDto.getTitle());
        note.setDescription(noteRequestDto.getDescription());
        return notesRepository.save(note);
    }

    public List<Notes> getAllNotes(){
        return notesRepository.getNotes();
    }

    public Notes updateNote(String id, NoteRequestDto noteRequestDto){
        Notes note = notesRepository.findById(id)
                .orElseThrow(() -> new NoteNotFoundException("Note not found"));
        note.setTitle(noteRequestDto.getTitle());
        note.setDescription(noteRequestDto.getDescription());
        return notesRepository.save(note);
    }

    public void deleteNote(String id) {
        Notes note = notesRepository.findById(id)
                .orElseThrow(() -> new NoteNotFoundException("Note not found"));
        notesRepository.delete(note);
    }
}
