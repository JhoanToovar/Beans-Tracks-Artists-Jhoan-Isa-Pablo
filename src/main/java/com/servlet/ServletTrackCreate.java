package com.servlet;

import com.Application;
import com.model.Artist;
import com.model.Track;
import com.service.IArtistService;
import com.service.ITrackService;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/tracks/create")
public class ServletTrackCreate extends HttpServlet {
    private ITrackService trackService;
    private IArtistService artistService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        trackService = Application.getContext().getBean(ITrackService.class);
        artistService = Application.getContext().getBean(IArtistService.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        resp.getWriter().println("<h1>Crear track</h1>");
        resp.getWriter().println("<form method=\"post\" action=\"" + req.getContextPath() + "/tracks/create\">");
        resp.getWriter().println("Titulo: <input type=\"text\" name=\"title\" required><br>");
        resp.getWriter().println("Genero: <input type=\"text\" name=\"genre\" required><br>");
        resp.getWriter().println("Duracion: <input type=\"text\" name=\"duration\" placeholder=\"mm:ss\" required><br>");
        resp.getWriter().println("Album: <input type=\"text\" name=\"albumTitle\" required><br>");
        resp.getWriter().println("Artistas:<br>");
        for (Artist artist : artistService.getAllArtists()) {
            resp.getWriter().println("<input type=\"checkbox\" name=\"artistIds\" value=\""
                    + artist.getId() + "\"> " + artist.getName() + "<br>");
        }
        resp.getWriter().println("<input type=\"submit\" value=\"Crear\">");
        resp.getWriter().println("</form>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Track track = new Track();
        track.setTitle(req.getParameter("title"));
        track.setGenre(req.getParameter("genre"));
        track.setDuration(req.getParameter("duration"));
        track.setAlbumTitle(req.getParameter("albumTitle"));

        String[] selectedIds = req.getParameterValues("artistIds");
        List<Integer> artistIds = new ArrayList<>();
        if (selectedIds != null) {
            for (String id : selectedIds) {
                artistIds.add(Integer.parseInt(id));
            }
        }

        Track saved = trackService.createTrack(track, artistIds);

        resp.setContentType("text/html");
        resp.getWriter().println("<h1>Track creado</h1>");
        resp.getWriter().println("<p>" + saved.getTitle() + " (id " + saved.getId() + ")</p>");
        resp.getWriter().println("<a href=\"" + req.getContextPath() + "/tracks\">Ver listado</a>");
    }
}
