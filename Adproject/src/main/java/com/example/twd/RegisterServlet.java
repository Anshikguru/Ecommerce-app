package com.example.twd;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RegisterServlet extends HttpServlet {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/user_dbs";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String fname = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        if (fname == null || fname.trim().isEmpty() ||
                email == null || email.trim().isEmpty() ||
                password == null || password.trim().isEmpty()) {
            out.println("<h2>All fields are required!</h2>");
            return;
        }
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS)) {
                String sql = "INSERT INTO users (fname, email, password) VALUES (?, ?, ?)";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, fname);
                ps.setString(2, email);
                ps.setString(3, password);

                int rowsInserted = ps.executeUpdate();

                if (rowsInserted > 0) {
                    out.println("<h2>Registration successful!</h2>");
                    out.println("<a href='login.html'>Go to Login</a>");
                } else {
                    out.println("<h2>Registration failed. Please try again.</h2>");
                }

            } catch (SQLException e) {
                out.println("<h2>Database error occurred!</h2>");
                e.printStackTrace(out);
            }

        } catch (ClassNotFoundException e) {
            out.println("<h2>MySQL JDBC Driver not found!</h2>");
            e.printStackTrace(out);
        }
    }
}
