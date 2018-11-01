/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import database.NoteDB;
import java.util.Date;
import java.util.List;
import models.Note;

/**
 *
 * @author 758243
 */
public class NoteService {
    private NoteDB noteDB;

    public NoteService() {
        noteDB = new NoteDB();
    }

    public Note get(int noteId) throws Exception {
        return noteDB.getNote(noteId);
    }

    public List<Note> getAll() throws Exception {
        return noteDB.getAll();
    }

    public int update(int noteId, Date dateCreated, String contents) throws Exception {
        Note note = get(noteId);
        note.setNoteId(noteId);
        note.setDateCreated(dateCreated);
        note.setContents(contents);
        return noteDB.update(note);
    }

    public int delete(int noteId) throws Exception {
        Note deletedNote = get(noteId);
        return noteDB.delete(deletedNote);
    }

    public int insert(int noteId, Date dateCreated, String contents) throws Exception {
        Note note = new Note(noteId, dateCreated, contents);
        return noteDB.insert(note);
    }
}
