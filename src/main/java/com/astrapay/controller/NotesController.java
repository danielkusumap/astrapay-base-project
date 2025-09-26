package com.astrapay.controller;

import com.astrapay.dto.NoteRequestDto;
import com.astrapay.dto.RespDto;
import com.astrapay.service.NotesService;
import com.astrapay.util.BuildResponse;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@Api(value = "NotesController")
@Slf4j
@RequestMapping("/notes")
public class NotesController {
    private final NotesService notesService;

    @Autowired
    public NotesController(NotesService notesService) {
        this.notesService = notesService;
    }

    @PostMapping
    public ResponseEntity<RespDto<Object>> addNote(@Valid @RequestBody NoteRequestDto noteRequestDto) {
        return BuildResponse.created("Success create new note", notesService.saveNote(noteRequestDto));
    }

    @GetMapping
    public ResponseEntity<RespDto<Object>> getNotes(){
        return BuildResponse.success("Success get notes", notesService.getAllNotes());
    }

    @PutMapping("/{id}")
    public ResponseEntity<RespDto<Object>> updateNote(
            @PathVariable String id,
            @RequestBody NoteRequestDto noteRequestDto){
        return BuildResponse.success("Success update note", notesService.updateNote(id, noteRequestDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<RespDto<Object>> deleteNote(@PathVariable String id) {
        notesService.deleteNote(id);
        return BuildResponse.success("Success delete note", null);
    }

}
