import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class EmployeeServlet extends HttpServlet {
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    response.setContentType("text/html");
    PrintWriter out = response.getWriter();

    String id = request.getParameter("id");

    try {
      Class.forName("com.mysql.cj.jdbc.Driver");
      Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/company", "root", "password");
      Statement stmt = con.createStatement();

      String query = (id != null && !id.isEmpty()) ?
          "SELECT * FROM employee WHERE id=" + id :
          "SELECT * FROM employee";

      ResultSet rs = stmt.executeQuery(query);

      out.println("<table border='1'><tr><th>ID</th><th>Name</th><th>Department</th></tr>");
      while (rs.next()) {
        out.println("<tr><td>" + rs.getInt("id") + "</td><td>" + rs.getString("name") +
            "</td><td>" + rs.getString("department") + "</td></tr>");
      }
      out.println("</table>");

      con.close();
    } catch (Exception e) {
      out.println("Error: " + e.getMessage());
    }
  }
}
