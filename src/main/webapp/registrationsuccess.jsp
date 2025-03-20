<%@ page language="java" contentType="text/html; charset=US-ASCII" pageEncoding="US-ASCII"%>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Menu Bar with Links</title>
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
            font-family: Arial, sans-serif;
        }

        body {
            background-color: #f9f9f9;
        }

        /* Menu bar styling */
        .menu-bar {
            width: 100%;
            background-color: #0056b3;
            padding: 10px 20px;
            display: flex;
            justify-content: space-between;
            align-items: center;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
            color: white;
        }

        .menu-bar .logo {
            font-size: 24px;
            font-weight: bold;
            text-decoration: none;
            color: #ffd700;
        }

        .menu-bar .menu-links {
            display: flex;
            gap: 15px;
        }

        .menu-bar .menu-links a {
            text-decoration: none;
            color: white;
            font-size: 16px;
            padding: 8px 12px;
            border-radius: 5px;
            transition: background-color 0.3s ease;
        }

        .menu-bar .menu-links a:hover {
            background-color: #003d80;
        }

        .menu-bar .menu-right {
            display: flex;
            gap: 15px;
        }

        .menu-bar .menu-right a {
            text-decoration: none;
            background-color: #ffd700;
            color: #0056b3;
            padding: 8px 12px;
            font-size: 16px;
            font-weight: bold;
            border-radius: 5px;
            transition: background-color 0.3s ease;
        }

        .menu-bar .menu-right a:hover {
            background-color: #e6c200;
        }

        .content {
            padding: 20px;
            text-align: center;
        }
    </style>
</head>
<body>
    <!-- Menu Bar -->
    <div class="menu-bar">
        <!-- Logo Section -->
        <a href="#" class="logo">My Website</a>

        <!-- Left Links -->
        <div class="menu-links">
            <a href="index.jsp">Home</a>
            <a href="#">About</a>
            <a href="#">Contact</a>
        </div>

        <!-- Right Links -->
        <div class="menu-right">
            <a href="updateprofile?email=${dto.email}">Update Profile</a>
            <a href="index.jsp">logout</a>
            <a href="deletebyemail?email=${dto.email}">delete</a>
        </div>
    </div>

    <!-- Page Content -->
    <div class="content">
    ${data}
        <h1>Welcome ${email} ${dto.name} to the Dashboard</h1>
    </div>
</body>
</html>
