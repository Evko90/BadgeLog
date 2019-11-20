package Server.Servlets;

import Server.DB.BadgeDB;
import Server.Converter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class BadgeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        //Получение списка с заданной датой
        BadgeDB badge = new BadgeDB();
        resp.getWriter().write(String.valueOf(badge.read(req.getParameter("date"))));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        String message;

        BadgeDB badge = new BadgeDB();
        //Поиск существования в таблице записей с заданной датой
        if (req.getParameter("command").equals("find")){
            boolean isFound = badge.foundDate(req.getParameter("date"));

            resp.getWriter().write(Boolean.toString(isFound));
        }
        //Добавление пустых записей на заданный день
        if (req.getParameter("command").equals("add")) {
            Converter converter = new Converter();
            boolean isAdded = badge.addBadge(req.getParameter("date"),
                    converter.convertRequestToArray(req.getParameter("id")));
            if (isAdded) {
                message = "Записи успешно добавленны";
            } else message = "Не удалось добавить запись";

            resp.getWriter().write(message);
        }
        // Изменение количества пропусков
        if (req.getParameter("command").equals("update")) {
            Converter converter = new Converter();
            boolean isChanged = badge.updateBadge(req.getParameter("date"), converter.convertRequestToBadgeList(req.getParameter("badge")));
            if (isChanged) {
                message = "Записи успешно изменены";
            } else message = "Не удалось изменить запись";

            resp.getWriter().write(message);
        }
    }
}
