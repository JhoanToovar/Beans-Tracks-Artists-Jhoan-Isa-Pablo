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

@WebServlet("/tracks/delete")
public class ServletTrackDelete extends HttpServlet {
    private ITrackService trackService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        trackService = Application.getContext().getBean(ITrackService.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        resp.getWriter().println("<h1>Eliminar track</h1>");
        resp.getWriter().println("<form method=\"post\" action=\"" + req.getContextPath() + "/tracks/delete\">");
        resp.getWriter().println("Id del track: <input type=\"number\" name=\"id\" required>");
        resp.getWriter().println("<input type=\"submit\" value=\"Eliminar\">");
        resp.getWriter().println("</form>");

        resp.getWriter().println("<ul>");
        for (Track track : trackService.getAllTracks()) {
            String artistNames = track.getArtists().stream()
                    .map(Artist::getName)
                    .collect(Collectors.joining(", "));
            resp.getWriter().println("<li>id " + track.getId() + " - " + track.getTitle() + " - "
                    + track.getGenre() + " - " + track.getDuration() + " - " + track.getAlbumTitle()
                    + " | Artistas: " + artistNames + "</li>");
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        boolean deleted = trackService.deleteTrack(id);

        resp.setContentType("text/html");
        if (deleted) {
            resp.getWriter().println("<h1>Track con id " + id + " eliminado</h1>");
        } else {
            resp.getWriter().println("<h1>No existe un track con id " + id + "</h1>");
        }
        resp.getWriter().println("<a href=\"" + req.getContextPath() + "/tracks\">Ver listado</a>");
    }
}
