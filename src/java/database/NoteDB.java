/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Note;

/**
 *
 * @author harma
 */
public class NoteDB
{
    
    public int insert(Note note) throws NotesDBException
    {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();

        try {
            String preparedQuery = "INSERT INTO notes (noteId, dateCreated, contents) VALUES (?, ?, ?)";
            PreparedStatement ps = connection.prepareStatement(preparedQuery);
            ps.setString(1, String.valueOf(note.getNoteId()));
            ps.setString(2, String.valueOf(note.getDateCreated()));
            ps.setString(3, note.getContents());
            int rows = ps.executeUpdate();
            return rows;
        } catch (SQLException ex) {
            Logger.getLogger(UserDB.class.getName()).log(Level.SEVERE, "Cannot insert " + note.getNoteId(), ex);
            throw new NotesDBException("Error inserting note");
        } finally {
            pool.freeConnection(connection);
        }
    }
    
    public int update(Note note) throws NotesDBException
    {
        
    }
    
    public List<Note> getAll() throws NotesDBException
    {
        
    }
    
    public Note getNote(int noteId) throws NotesDBException
    {
        
    }
    
    public int delete(Note note) throws NotesDBException
    {
        
    }
}
