package com.smartnotes.servlet;

import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import com.smartnotes.dao.NoteDAO;
import com.smartnotes.model.User;

@WebServlet("/deleteNote")
public class DeleteNoteServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        User user = (User) session.getAttribute("user");
        int noteId = Integer.parseInt(request.getParameter("id"));

        NoteDAO noteDAO = new NoteDAO();
        noteDAO.deleteNote(noteId, user.getId());

        response.sendRedirect("home");
    }
}
