package com.smartnotes.servlet;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import com.smartnotes.dao.NoteDAO;
import com.smartnotes.model.Note;
import com.smartnotes.model.User;

@WebServlet("/home")
public class NoteServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        User user = (User) session.getAttribute("user");
        String search = request.getParameter("search");

        NoteDAO noteDAO = new NoteDAO();
        List<Note> notes = noteDAO.getNotesByUser(user.getId(), search);

        request.setAttribute("notes", notes);
        request.setAttribute("search", search != null ? search : "");
        request.getRequestDispatcher("home.jsp").forward(request, response);
    }
}
