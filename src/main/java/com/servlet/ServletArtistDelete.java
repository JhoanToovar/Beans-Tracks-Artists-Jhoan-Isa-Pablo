package com.servlet;

import com.Application;
import com.service.IArtistService;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/artists/delete")
public class ServletArtistDelete extends HttpServlet {
    private IArtistService artistService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        artistService = Application.getContext().getBean(IArtistService.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        resp.getWriter().println("<h1>Eliminar artista</h1>");
        resp.getWriter().println("<form method=\"post\" action=\"" + req.getContextPath() + "/artists/delete\">");
        resp.getWriter().println("Id del artista: <input type=\"number\" name=\"id\" required>");
        resp.getWriter().println("<input type=\"submit\" value=\"Eliminar\">");
        resp.getWriter().println("</form>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        boolean deleted = artistService.deleteArtist(id);

        resp.setContentType("text/html");
        if (deleted) {
            resp.getWriter().println("<h1>Artista con id " + id + " eliminado</h1>");
        } else {
            resp.getWriter().println("<h1>No existe un artista con id " + id + "</h1>");
        }
        resp.getWriter().println("<a href=\"" + req.getContextPath() + "/artists\">Ver listado</a>");
    }
}
