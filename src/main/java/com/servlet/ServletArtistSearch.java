package com.servlet;

import com.Application;
import com.model.Artist;
import com.model.Track;
import com.service.IArtistService;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/artists/search")
public class ServletArtistSearch extends HttpServlet {
    private IArtistService artistService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        artistService = Application.getContext().getBean(IArtistService.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String name = req.getParameter("name");

        resp.setContentType("text/html");
        resp.getWriter().println("<h1>Buscar artista por nombre</h1>");
        resp.getWriter().println("<form method=\"get\" action=\"" + req.getContextPath() + "/artists/search\">");
        resp.getWriter().println("Nombre: <input type=\"text\" name=\"name\" value=\""
                + (name == null ? "" : name) + "\" required>");
        resp.getWriter().println("<input type=\"submit\" value=\"Buscar\">");
        resp.getWriter().println("</form>");

        if (name != null && !name.isBlank()) {
            Artist artist = artistService.findArtistByName(name);
            if (artist == null) {
                resp.getWriter().println("<p>No se encontro un artista llamado " + name + "</p>");
            } else {
                resp.getWriter().println("<h2>" + artist.getName() + " (" + artist.getNationality()
                        + ") - id " + artist.getId() + "</h2>");
                resp.getWriter().println("<ul>");
                for (Track track : artistService.getTracksByArtist(artist.getId())) {
                    resp.getWriter().println("<li>" + track.getTitle() + " - " + track.getGenre()
                            + " - " + track.getDuration() + " - " + track.getAlbumTitle() + "</li>");
                }
                resp.getWriter().println("</ul>");
            }
        }
    }
}
