/*
 * package com.example.twd.controller;
 * 
 * import org.springframework.stereotype.Controller;
 * import org.springframework.web.bind.annotation.GetMapping;
 * 
 * @Controller
 * public class HomeController {
 * 
 * @GetMapping("/")
 * public String home() {
 * return "form"; // loads cookies.jsp
 * }
 * 
 * /* @GetMapping("/login")
 * public String login() {
 * return "validate"; // loads include.jsp
 * }
 * 
 * @GetMapping("/home")
 * public String homePage() {
 * return "home"; // loads home.jsp
 * }
 * }
 * 
 * package com.example.twd.controller;
 * 
 * import org.springframework.stereotype.Controller;
 * import org.springframework.web.bind.annotation.GetMapping;
 * import org.springframework.web.bind.annotation.PostMapping;
 * import jakarta.servlet.http.HttpServletRequest;
 * 
 * @Controller
 * public class HomeController {
 * 
 * @GetMapping("/")
 * public String showLoginPage() {
 * return "login"; // loads /WEB-INF/jsp/login.jsp
 * }
 * 
 * @PostMapping("/handleLogin")
 * public String handleLogin(HttpServletRequest request) {
 * String username = request.getParameter("username");
 * String role = request.getParameter("role");
 * 
 * request.setAttribute("username", username);
 * 
 * if ("Admin".equalsIgnoreCase(role)) {
 * return "admin";
 * } else if ("User".equalsIgnoreCase(role)) {
 * return "user";
 * } else if ("Guest".equalsIgnoreCase(role)) {
 * return "guest";
 * } else {
 * request.setAttribute("errorMessage", "Invalid role selected!");
 * return "login";
 * }
 * }
 * }
 * package com.example.twd.controller;
 * import org.springframework.stereotype.Controller;
 * import org.springframework.web.bind.annotation.GetMapping;
 * import org.springframework.web.bind.annotation.PostMapping;
 * import jakarta.servlet.http.HttpServletRequest;
 * 
 * @Controller
 * public class HomeController {
 * // Show the login page
 * 
 * @GetMapping("/")
 * public String showLoginPage() {
 * return "login";
 * }
 * // Handle login form submission
 * 
 * @PostMapping("/handleLogin")
 * public String handleLogin(HttpServletRequest request) {
 * String username = request.getParameter("username");
 * String password = request.getParameter("password");
 * String role = request.getParameter("role");
 * // Validation
 * if (username == null || username.trim().isEmpty() ||
 * password == null || password.trim().isEmpty() ||
 * role == null || role.trim().isEmpty()) {
 * request.setAttribute("errorMessage", "All fields are required!");
 * return "login"; // stay on login page
 * }
 * request.setAttribute("username", username);
 * // Redirect based on role
 * if ("Admin".equalsIgnoreCase(role)) {
 * return "home";
 * } else if ("User".equalsIgnoreCase(role)) {
 * return "cart";
 * } else if ("Guest".equalsIgnoreCase(role)) {
 * return "checkout";
 * } else {
 * request.setAttribute("errorMessage", "Invalid role selected!");
 * return "login";
 * }
 * }
 * }
 */
package com.example.twd.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {

    // Display login page
    @GetMapping("/")
    public String showLoginPage() {
        return "login"; // loads /WEB-INF/jsp/login.jsp
    }

    // Handle login form submission
    @PostMapping("/handleLogin")
    public String handleLogin(HttpServletRequest request) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Simple form validation
        if (username == null || username.trim().isEmpty() ||
                password == null || password.trim().isEmpty()) {
            request.setAttribute("errorMessage", "Username and password are required!");
            request.setAttribute("username", username);
            return "login";
        }

        try {
            // Load MySQL driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Connect to the database
            String url = "jdbc:mysql://localhost:3306/ecommerce";
            String dbUser = "root"; // update if different
            String dbPass = "Anshikguru9938"; // replace with your MySQL password
            Connection conn = DriverManager.getConnection(url, dbUser, dbPass);

            // Check if the user exists
            String sql = "SELECT id FROM users WHERE username=? AND password=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                // Valid user -> store in session
                int userId = rs.getInt("id");
                HttpSession session = request.getSession();
                session.setAttribute("userId", userId);
                session.setAttribute("username", username);

                // Close DB resources
                rs.close();
                ps.close();
                conn.close();

                // Redirect to home page controller
                return "redirect:/home";
            } else {
                // Invalid login
                request.setAttribute("errorMessage", "Invalid username or password!");
                request.setAttribute("username", username);

                // Close DB resources
                rs.close();
                ps.close();
                conn.close();

                return "login";
            }
        } catch (ClassNotFoundException | SQLException e) {
            request.setAttribute("errorMessage", "Database error: " + e.getMessage());
            request.setAttribute("username", username);
            return "login";
        }
    }
}
