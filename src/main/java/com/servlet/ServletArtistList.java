package com.servlet;

import com.Application;
import com.model.Artist;
import com.service.IArtistService;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/artists")
public class ServletArtistList extends HttpServlet {
    private IArtistService artistService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        artistService = Application.getContext().getBean(IArtistService.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        resp.getWriter().println("<h1>Artistas registrados</h1>");
        resp.getWriter().println("<ul>");
        for (Artist artist : artistService.getAllArtists()) {
            resp.getWriter().println("<li>" + artist.getId() + " - " + artist.getName()
                    + " (" + artist.getNationality() + ")</li>");
        }
        resp.getWriter().println("</ul>");
        resp.getWriter().println("<a href=\"" + req.getContextPath() + "/artists/create\">Crear artista</a> | ");
        resp.getWriter().println("<a href=\"" + req.getContextPath() + "/artists/search\">Buscar artista</a> | ");
        resp.getWriter().println("<a href=\"" + req.getContextPath() + "/artists/delete\">Eliminar artista</a>");
    }
}
