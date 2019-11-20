package Server.Servlets;

import Server.DB.StudentDB;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class StudentServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        String message;

        StudentDB studentDB = new StudentDB();
        if (req.getParameter("command").equals("add")) {
                boolean isAdd = studentDB.addStudent(req.getParameter("name"));
                if (isAdd) {
                    message = "Запись " + req.getParameter("name") + " успешно добавлена" ;
                } else {
                    message= "Не удалось добавить запись";
                }
                resp.getWriter().write(message);
            }
        if (req.getParameter("command").equals("update")) {
                boolean isUpdate = studentDB.updateStudent(req.getParameter("name"),
                        Integer.parseInt(req.getParameter("id")));
                if (isUpdate) {
                    message = "Запись успешно изменена";
                } else {
                    message= "Не удалось изменить запись";
                }
                resp.getWriter().write(message);
            }
        if (req.getParameter("command").equals("delete")) {
                boolean isDelete = studentDB.deleteStudent(Integer.parseInt(req.getParameter("id")));
                if (isDelete) {
                    message = "Запись успешно удалена";
                } else {
                    message= "Не удалось удалить запись";
                }
                resp.getWriter().write(message);
            }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");

        StudentDB studentDB = new StudentDB();
		resp.getWriter().write("Yes");
       // resp.getWriter().write(String.valueOf(studentDB.read()));
    }
}
