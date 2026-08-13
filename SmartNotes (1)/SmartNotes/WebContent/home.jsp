<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List, com.smartnotes.model.Note, com.smartnotes.model.User" %>
<%
    User user = (User) session.getAttribute("user");
    if (user == null) {
        response.sendRedirect("login.jsp");
        return;
    }
    List<Note> notes = (List<Note>) request.getAttribute("notes");
    String search = (String) request.getAttribute("search");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Smart Notes - Home</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
    <header class="header">
        <div class="container header-inner">
            <h2>✦ Smart Notes</h2>
            <div class="header-right">
                <span>Hello, <%= user.getName() %></span>
                <a href="logout" class="btn-logout">Logout</a>
            </div>
        </div>
    </header>

    <main class="container">
        <div class="toolbar">
            <form action="home" method="get" class="search-form">
                <input type="text" name="search" placeholder="Search notes..." value="<%= search %>">
                <button type="submit">Search</button>
            </form>
            <button class="btn-primary" onclick="document.getElementById('addModal').style.display='flex'">+ New Note</button>
        </div>

        <div class="notes-grid">
            <% if (notes == null || notes.isEmpty()) { %>
                <div class="empty">No notes found. Create your first note!</div>
            <% } else {
                for (Note note : notes) { %>
                <div class="note-card <%= note.isPinned() ? "pinned" : "" %>">
                    <% if (note.isPinned()) { %><span class="pin-badge">Pinned</span><% } %>
                    <h3><%= note.getTitle() %></h3>
                    <p><%= note.getContent() != null && note.getContent().length() > 150 ? note.getContent().substring(0, 150) + "..." : note.getContent() %></p>
                    <% if (note.getTags() != null && !note.getTags().isEmpty()) { %>
                        <div class="tags">
                            <% for (String tag : note.getTags().split(",")) { %>
                                <span class="tag"><%= tag.trim() %></span>
                            <% } %>
                        </div>
                    <% } %>
                    <div class="note-actions">
                        <a href="deleteNote?id=<%= note.getId() %>" class="btn-delete" onclick="return confirm('Delete this note?')">Delete</a>
                    </div>
                </div>
            <% } } %>
        </div>
    </main>

    <!-- Add Note Modal -->
    <div id="addModal" class="modal">
        <div class="modal-content">
            <h3>Add New Note</h3>
            <form action="addNote" method="post">
                <div class="form-group">
                    <label>Title</label>
                    <input type="text" name="title" required>
                </div>
                <div class="form-group">
                    <label>Content</label>
                    <textarea name="content" rows="5" required></textarea>
                </div>
                <div class="form-group">
                    <label>Tags (comma separated)</label>
                    <input type="text" name="tags" placeholder="work, personal, ideas">
                </div>
                <div class="form-group checkbox">
                    <label><input type="checkbox" name="pinned"> Pin this note</label>
                </div>
                <div class="modal-actions">
                    <button type="button" class="btn-cancel" onclick="document.getElementById('addModal').style.display='none'">Cancel</button>
                    <button type="submit" class="btn-primary">Save Note</button>
                </div>
            </form>
        </div>
    </div>
</body>
</html>
