package book1.com;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Servlet implementation class Display
 */
public class Display extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Display() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Set content type
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		// Start of HTML content
		out.println("<html><head><title>Book Details</title>");
		out.println("<style>");
		out.println("body { font-family: Arial, sans-serif; background-color: #f4f4f9; margin: 0; padding: 0; }");
		out.println(".container { width: 80%; margin: 0 auto; padding: 20px; }");
		out.println("h1 { color: #333; text-align: center; margin-bottom: 20px; background: linear-gradient(to right, #4CAF50, #2E8B57); -webkit-background-clip: text; color: transparent; }");
		out.println("table { width: 100%; border-collapse: collapse; margin-bottom: 20px; }");
		out.println("table, th, td { border: 1px solid #ddd; padding: 12px; text-align: left; }");
		out.println("th { background: linear-gradient(to right, #4CAF50, #2E8B57); color: white; }");
		out.println("tr:nth-child(even) { background: linear-gradient(to right, #f2f2f2, #dcdcdc); }");
		out.println("tr:hover { background-color: #ddd; }");
		out.println("td { font-size: 1.1em; color: #333; background: linear-gradient(to right, #ffffff, #f7f7f7); }");
		out.println("button { padding: 10px 20px; margin-top: 20px; background-color: #4CAF50; color: white; border: none; border-radius: 5px; cursor: pointer; font-size: 1em; }");
		out.println("button:hover { background-color: #45a049; }");
		out.println("</style>");
		out.println("</head><body>");
		out.println("<div class='container'><h1>Book List</h1>");

		// Table header
		out.println("<table>");
		out.println("<tr>");
		out.println("<th>Book Name</th>");
		out.println("<th>Price ($)</th>");
		out.println("<th>Author Name</th>");
		out.println("</tr>");

		// Database connection and querying
		String bname = "", bprice = "", author_name = "";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/neha", "root", "abcd");
			PreparedStatement pst = con.prepareStatement("SELECT * FROM book1");
			ResultSet rs = pst.executeQuery();
			
			while (rs.next()) {
				bname = rs.getString(1);
				bprice = rs.getString(2);
				author_name = rs.getString(3);

				// Display each book's details in table rows
				out.println("<tr>");
				out.println("<td>" + bname + "</td>");
				out.println("<td>" + bprice + "</td>");
				out.println("<td>" + author_name + "</td>");
				out.println("</tr>");
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			out.println("<p>Error fetching data!</p>");
		}

		// Close the table
		out.println("</table>");

		// Add button for redirection to index page
		out.println("<form action='index.html'>");
		out.println("<button type='submit'>Go to Index</button>");
		out.println("</form>");

		// Close the HTML content
		out.println("</div></body></html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Handle POST requests by calling doGet
		doGet(request, response);
	}
}
