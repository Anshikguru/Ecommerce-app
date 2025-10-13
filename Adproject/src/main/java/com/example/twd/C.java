package com.example.twd;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class C extends HttpServlet {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/user_db";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        try (PrintWriter out = resp.getWriter()) { // try-with-resources for PrintWriter

            String uname = req.getParameter("uname");
            String uemail = req.getParameter("uemail");
            String phone = req.getParameter("phone");
            String password = req.getParameter("password");
            String gender = req.getParameter("gender");
            String[] languages = req.getParameterValues("languages");
            String langStr = "";
            if (languages != null) {
                langStr = String.join(",", languages);
            }

            try {
                Class.forName("com.mysql.cj.jdbc.Driver");

                // try-with-resources for Connection and PreparedStatement
                try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
                     PreparedStatement ps = conn.prepareStatement(
                             "INSERT INTO users (uname, uemail, phone, password, gender, languages) VALUES (?, ?, ?, ?, ?, ?)")
                ) {
                    ps.setString(1, uname);
                    ps.setString(2, uemail);
                    ps.setString(3, phone);
                    ps.setString(4, password);
                    ps.setString(5, gender);
                    ps.setString(6, langStr);

                    int rows = ps.executeUpdate();
                    if (rows > 0) {
                        out.println("<h2>Registration Successful!</h2>");
                        out.println("<p>Stored in Database yesssss</p>");
                    } else {
                        out.println("<h2>Failed to Register</h2>");
                    }
                }

            } catch (SQLException | ClassNotFoundException e) { // multi-catch for specific exceptions
                e.printStackTrace(out);
            }

        } // PrintWriter automatically closed here
    }
}
