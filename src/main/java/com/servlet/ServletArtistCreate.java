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

@WebServlet("/artists/create")
public class ServletArtistCreate extends HttpServlet {
    private IArtistService artistService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        artistService = Application.getContext().getBean(IArtistService.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        resp.getWriter().println("<h1>Crear artista</h1>");
        resp.getWriter().println("<form method=\"post\" action=\"" + req.getContextPath() + "/artists/create\">");
        resp.getWriter().println("Nombre: <input type=\"text\" name=\"name\" required><br>");
        resp.getWriter().println("Nacionalidad: <input type=\"text\" name=\"nationality\" required><br>");
        resp.getWriter().println("<input type=\"submit\" value=\"Crear\">");
        resp.getWriter().println("</form>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Artist artist = new Artist();
        artist.setName(req.getParameter("name"));
        artist.setNationality(req.getParameter("nationality"));
        artistService.addArtist(artist);

        resp.setContentType("text/html");
        resp.getWriter().println("<h1>Artista creado</h1>");
        resp.getWriter().println("<p>" + artist.getName() + " (" + artist.getNationality()
                + ") con id " + artist.getId() + "</p>");
        resp.getWriter().println("<a href=\"" + req.getContextPath() + "/artists\">Ver listado</a>");
    }
}
