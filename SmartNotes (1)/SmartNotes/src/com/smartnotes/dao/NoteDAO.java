package com.smartnotes.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.smartnotes.model.Note;
import com.smartnotes.util.DBConnection;

public class NoteDAO {

    public boolean addNote(Note note) {
        String sql = "INSERT INTO notes (user_id, title, content, tags, is_pinned) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, note.getUserId());
            ps.setString(2, note.getTitle());
            ps.setString(3, note.getContent());
            ps.setString(4, note.getTags());
            ps.setBoolean(5, note.isPinned());
            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Note> getNotesByUser(int userId, String search) {
        List<Note> list = new ArrayList<>();
        String sql = "SELECT * FROM notes WHERE user_id = ?";

        if (search != null && !search.trim().isEmpty()) {
            sql += " AND (title LIKE ? OR content LIKE ? OR tags LIKE ?)";
        }
        sql += " ORDER BY is_pinned DESC, updated_at DESC";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, userId);
            if (search != null && !search.trim().isEmpty()) {
                String q = "%" + search + "%";
                ps.setString(2, q);
                ps.setString(3, q);
                ps.setString(4, q);
            }

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Note note = new Note();
                note.setId(rs.getInt("id"));
                note.setUserId(rs.getInt("user_id"));
                note.setTitle(rs.getString("title"));
                note.setContent(rs.getString("content"));
                note.setTags(rs.getString("tags"));
                note.setPinned(rs.getBoolean("is_pinned"));
                note.setCreatedAt(rs.getTimestamp("created_at"));
                note.setUpdatedAt(rs.getTimestamp("updated_at"));
                list.add(note);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public Note getNoteById(int id, int userId) {
        String sql = "SELECT * FROM notes WHERE id = ? AND user_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.setInt(2, userId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Note note = new Note();
                note.setId(rs.getInt("id"));
                note.setUserId(rs.getInt("user_id"));
                note.setTitle(rs.getString("title"));
                note.setContent(rs.getString("content"));
                note.setTags(rs.getString("tags"));
                note.setPinned(rs.getBoolean("is_pinned"));
                return note;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean updateNote(Note note) {
        String sql = "UPDATE notes SET title=?, content=?, tags=?, is_pinned=? WHERE id=? AND user_id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, note.getTitle());
            ps.setString(2, note.getContent());
            ps.setString(3, note.getTags());
            ps.setBoolean(4, note.isPinned());
            ps.setInt(5, note.getId());
            ps.setInt(6, note.getUserId());
            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteNote(int id, int userId) {
        String sql = "DELETE FROM notes WHERE id = ? AND user_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.setInt(2, userId);
            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
