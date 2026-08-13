package com.smartnotes.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import com.smartnotes.dao.NoteDAO;
import com.smartnotes.model.Note;
import com.smartnotes.model.User;

@WebServlet("/addNote")
public class AddNoteServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        User user = (User) session.getAttribute("user");
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        String tags = request.getParameter("tags");
        boolean pinned = "on".equals(request.getParameter("pinned"));

        Note note = new Note(user.getId(), title, content, tags);
        note.setPinned(pinned);

        NoteDAO noteDAO = new NoteDAO();
        noteDAO.addNote(note);

        response.sendRedirect("home");
    }
}
