package handle;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import org.apache.logging.log4j.*;

public class LoadServlet extends javax.servlet.http.HttpServlet {
    private static final Logger LOGGER = LogManager.getLogger("test");
    private Connection conn;
    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext context = config.getServletContext();
        String databaseURL = context.getInitParameter("databaseURL");
        String user = context.getInitParameter("user");
        String pwd = context.getInitParameter("pwd");

        context.setAttribute("databaseURL", databaseURL);
        context.setAttribute("user", user);
        context.setAttribute("pwd",pwd);
        context.setAttribute("Conn", conn);
        System.out.println(LOGGER);
        LOGGER.error("mysql database user:"+user);
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(databaseURL,user,pwd);

        }catch (ClassNotFoundException | SQLException ex){
            ex.printStackTrace();
        }
    }
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        try{
            out.println("<!DOCTYPE html>");
            out.println("<html><head>");
            out.println("<meta charset=\"UTF-8\">");
            out.println("<title>load</title></head>");
            out.println("<body>");
            String event_category = request.getParameter("event_category");
            String event_name = request.getParameter("event_name");
            String level_rank = request.getParameter("level_rank");
            String time_use = request.getParameter("time_use");
            out.println("<div>");
            out.println("<p>input data as below</p>");
            out.println("<p>"+event_category+"</p>");
            out.println("<p>"+event_name+"</p>");
            out.println("<p>"+level_rank+"</p>");
            out.println("<p>"+time_use+"</p>");
            out.println("</div>");
            try{
                Statement statement = conn.createStatement();
                StringBuilder sql = new StringBuilder();
                sql.append("INSERT INTO event_info(event_category,event_name,level_rank,time_use) VALUES(");
                sql.append("\"").append(event_category).append("\",").append("\"").append(event_name).append("\",");
                sql.append(level_rank).append(",").append("\"").append(time_use).append("\")");
                String sqlstr = sql.toString();
                out.println("<p>"+sqlstr+"</p>");
                statement.executeUpdate(sqlstr);
                out.println("<p>load success");
                LOGGER.debug(sqlstr);
            }catch (SQLException ex){
                ex.printStackTrace();
                out.println("<p>load fail,message:"+ex.getMessage()+"</p>");
            }
            out.println("<a href=\"/timeRecorder\">HomePage</p>");

            out.println("</body>");
            out.println("</html>");

        }finally {
            out.close();
        }
    }
    public void destory(){
        if (conn!=null){
            try{
                conn.close();
            }catch (SQLException ex){
                ex.printStackTrace();
            }

        }
    }
}
