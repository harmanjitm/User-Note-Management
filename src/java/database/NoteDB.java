/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Note;
import models.User;

/**
 *
 * @author harma
 */
public class NoteDB
{
    ConnectionPool pool;
    Connection connection;
    
    public int insert(Note note) throws NotesDBException
    {
        pool = ConnectionPool.getInstance();
        connection = pool.getConnection();

        try {
            String preparedQuery = "INSERT INTO notes (noteId, dateCreated, contents) VALUES (?, ?, ?)";
            PreparedStatement ps = connection.prepareStatement(preparedQuery);
            ps.setInt(1, note.getNoteId());
            ps.setDate(2, (Date) note.getDateCreated());
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
        pool = ConnectionPool.getInstance();
        connection = pool.getConnection();
        
        try{
            String preparedQuery = "UPDATE notes SET noteId = ?, dateCreated = ?, contents = ? WHERE noteId = ?";
            PreparedStatement ps = connection.prepareStatement(preparedQuery);
            ps.setInt(1, note.getNoteId());
            ps.setDate(2, (Date) note.getDateCreated());
            ps.setString(3, note.getContents());
            ps.setInt(4, note.getNoteId());
            int rows = ps.executeUpdate();
            return rows;
        } catch (SQLException ex)
        {
            Logger.getLogger(NoteDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            pool.freeConnection(connection);
        }
    }
    
    public List<Note> getAll() throws NotesDBException
    {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = connection.prepareStatement("SELECT * FROM notes;");
            rs = ps.executeQuery();
            List<Note> notes = new ArrayList<>();
            while (rs.next()) {
                notes.add(new Note(rs.getInt("noteId"),
                        rs.getDate("dateCreated"),
                        rs.getString("contents")));
            }
            pool.freeConnection(connection);
            return notes;
        } catch (SQLException ex) {
            Logger.getLogger(UserDB.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                rs.close();
                ps.close();
            } catch (SQLException ex) {
            }
            pool.freeConnection(connection);
        }
    }
    
    public Note getNote(int noteId) throws NotesDBException
    {
        pool = ConnectionPool.getInstance();
        connection = pool.getConnection();
    }
    
    public int delete(Note note) throws NotesDBException
    {
        pool = ConnectionPool.getInstance();
        connection = pool.getConnection();
    }
}
