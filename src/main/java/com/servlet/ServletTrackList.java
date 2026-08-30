package com.servlet;

import com.Application;
import com.model.Artist;
import com.model.Track;
import com.service.ITrackService;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.stream.Collectors;

@WebServlet("/tracks")
public class ServletTrackList extends HttpServlet {
    private ITrackService trackService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        trackService = Application.getContext().getBean(ITrackService.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        resp.getWriter().println("<h1>Canciones registradas</h1>");
        resp.getWriter().println("<ul>");
        for (Track track : trackService.getAllTracks()) {
            String artistNames = track.getArtists().stream()
                    .map(Artist::getName)
                    .collect(Collectors.joining(", "));
            resp.getWriter().println("<li>id " + track.getId() + " - " + track.getTitle() + " - "
                    + track.getGenre() + " - " + track.getDuration() + " - " + track.getAlbumTitle()
                    + " | Artistas: " + artistNames + "</li>");
        }
        resp.getWriter().println("</ul>");
        resp.getWriter().println("<a href=\"" + req.getContextPath() + "/tracks/create\">Crear track</a> | ");
        resp.getWriter().println("<a href=\"" + req.getContextPath() + "/tracks/delete\">Eliminar track</a>");
    }
}
