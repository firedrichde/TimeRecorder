package handle;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Enumeration;

public class DeleteServlet extends HttpServlet {
    private ServletContext context;
    private static final Logger LOGGER = LogManager.getLogger("test");
    public void init(ServletConfig config){
        context = config.getServletContext();

    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection conn = (Connection) context.getAttribute("Conn");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        ArrayList<String> pList = new ArrayList<String>();
        Enumeration<String> parameters = request.getParameterNames();
        while(parameters.hasMoreElements()){
            String x = parameters.nextElement();
            System.out.println(x);
            pList.add(x);

        }
        try{
            out.println("<!DOCTYPE html>");
            out.println("<html><head><title>Request Parameters</title></head>");
            out.println("<body>");
//            out.println("<p>"+conn+"</p>");
            int flag = 0;
            try{
                Statement statement = conn.createStatement();

                for(String id:pList){
                    out.println("<p>delete id= "+id+"</p>");
                    StringBuilder sql = new StringBuilder();
                    sql.append("DELETE from event_info WHERE id=");
                    sql.append(id);
                    statement.executeUpdate(sql.toString());
                    LOGGER.debug(sql.toString());
                }
            }catch (SQLException ex){
                flag = 1;
                out.println("<p>Delete Fail</p>");
                ex.printStackTrace();
            }
            if (flag == 0){
                out.println("<p>Delete Success</p>");
            }
            out.println("<a href=\"/timeRecorder\">HomePage</p>");
            out.println("</body>");
            out.println("</html>");
        }finally {
            out.close();
        }

    }

}
