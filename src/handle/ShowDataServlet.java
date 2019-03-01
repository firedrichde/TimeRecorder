package handle;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet(name = "ShowDataServlet")
public class ShowDataServlet extends HttpServlet {
    String databaseURL;
    String user;
    String pwd;
    Connection conn;


    @Override
    public void init(ServletConfig config) {
        ServletContext context = config.getServletContext();
        conn = (Connection) context.getAttribute("Conn");
        if (conn == null) {
            databaseURL = (String) context.getAttribute("databaseURL");
            if (databaseURL == null) {
                databaseURL = context.getInitParameter("databaseURL");
                context.setAttribute("databaseURL", databaseURL);
            }
            user = (String) context.getAttribute("user");
            if (user == null) {
                user = context.getInitParameter("user");
                context.setAttribute("user", user);
            }
            pwd = (String) context.getAttribute("pwd");
            if (pwd == null) {
                pwd = context.getInitParameter("pwd");
                context.setAttribute("pwd", pwd);
            }
            try {
                Class.forName("com.mysql.jdbc.Driver");
                conn = DriverManager.getConnection(databaseURL, user, pwd);
                context.setAttribute("Conn",conn);
            } catch (ClassNotFoundException | SQLException ex) {
                ex.printStackTrace();
            }
        } else {
            context.setAttribute("Conn", conn);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        try {
            out.println("<!DOCTYPE html>");
            out.println("<html><head><meta charset=\"utf-8\"><title>Show Details</title></head>");
            out.println("<body>");
            out.println("<h2>Event Info</h2>");
//            out.println("<table>");
            String sqlStr = "SELECT * from event_info";
            try {
                Statement statement = conn.createStatement();
                ResultSet rs = statement.executeQuery(sqlStr);
                out.println("<p>id  event_category  event_name  level_rank time_use  record_moment</p>");
                out.println("<form method=\"get\" action=\".//delete\">");
                while (rs.next()) {
                    String event_category = rs.getString("event_category");
                    String event_name = rs.getString("event_name");
                    int level_rank = rs.getInt("level_rank");
                    String time_use = rs.getString("time_use");
                    int id = rs.getInt("id");
                    String record_moment=rs.getString("record_moment");

                    StringBuilder temp = new StringBuilder();
                    out.println("<table>");
                    out.println("<tr>");
                    temp.append("<td><p>").append(id).append("  ").append(event_category).append("  ").append(event_name).append("  ").append(level_rank).append("  ").append(time_use).append("  ").append(record_moment).append("</p></td>");
                    out.println(temp.toString());
                    out.println("<td><input type=\"checkbox\" name=\""+id+"\">"+"</td></tr>");
                    out.println("</table>");


                }
                out.println("<input type=\"submit\" value=\"delete data\">");
                out.println("<form>");
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            out.close();
        }

    }
}
