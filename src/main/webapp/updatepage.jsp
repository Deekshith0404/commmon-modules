<%@ page language="java" contentType="text/html; charset=US-ASCII" pageEncoding="US-ASCII"%>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Moderately Styled Registration Form</title>
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
        }

        body {
            background-color: #f7f9fc;
            display: flex;
            flex-direction: column;
            align-items: center;
        }

        /* Menu Bar Styling */
        .menu-bar {
            width: 100%;
            background-color: #0056b3;
            padding: 10px 20px;
            display: flex;
            justify-content: space-between;
            align-items: center;
            color: white;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
        }
        .error{
            color:red;
        }

        .menu-bar .logo {
            font-size: 22px;
            font-weight: bold;
            text-decoration: none;
            color: #ffd700;
        }

        .menu-bar .menu-links a {
            color: white;
            text-decoration: none;
            margin-left: 20px;
            font-size: 16px;
            transition: color 0.3s ease;
        }

        .menu-bar .menu-links a:hover {
            color: #ffd700;
        }

        /* Form Container Styling */
        .form-container {
            background: #ffffff;
            padding: 25px;
            margin-top: 40px;
            border-radius: 12px;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
            width: 420px;
        }

        h1 {
            margin-bottom: 20px;
            color: #333;
            font-size: 24px;
            text-align: center;
        }

        form label {
            display: block;
            margin: 10px 0 5px;
            font-weight: bold;
            color: #444;
        }

        form input,
        form select {
            width: 100%;
            padding: 10px;
            margin-bottom: 15px;
            border: 1px solid #ddd;
            border-radius: 8px;
            font-size: 14px;
            transition: border-color 0.3s ease;
        }

        form input:focus,
        form select:focus {
            border-color: #0056b3;
            outline: none;
        }

        form button {
            width: 100%;
            padding: 12px;
            background: #0056b3;
            color: white;
            border: none;
            border-radius: 8px;
            cursor: pointer;
            font-size: 16px;
            font-weight: bold;
            transition: background 0.3s ease;
        }

        form button:hover {
            background: #003d80;
        }
    </style>
</head>
<body>
    <!-- Menu Bar -->
    <div class="menu-bar">
        <a href="#" class="logo">My Website</a>
        <div class="menu-links">
            <a href="index.jsp">Home</a>
            <a href="#">Sign In</a>
        </div>
    </div>


    <div class="form-container">
        <h1>Update Form</h1>
        <form action="updateform" method="post">
            <div class="error">
            <c:out value="${nameError}"/>
             </div>
            <label for="name">Name:</label>
            <input type="text" id="name" name="name" value="${dto.name}" required>

            <label for="login">Login ID:</label>
            <input type="text" id="loginId" name="loginId" value="${dto.loginId}" required>

            <label for="email">Email:</label>
            <input type="email" id="email" name="email" value="${dto.email}" required>

            <div class="error">
            <c:out value="${ageError}"/>
            </div>
            <label for="age">Age:</label>
            <input type="number" id="age" name="age" value="${dto.age}" required>

            <div class="error">
            <c:out value="${phoneError}"/>
            </div>
            <label for="phone">Phone Number:</label>
            <input type="number" id="phoneNumber" name="phoneNumber" value="${dto.phoneNumber}" required>


            <div class="error">
            <c:out value="${birthError}"/>
            </div>
            <label for="dob">Date of Birth:</label>
            <input type="date" id="dateofBirth" name="dateofBirth" value="${dto.dateofBirth}" required>

            <label for="location">Location:</label>
            <select id="dropdown" name="location" value="${dto.location}" required>
                <option value="${dto.location}">${dto.location}</option>
               <c:forEach items="${list}" var="location">
               <option>${location}</option>
               </c:forEach>
            </select>

            <button type="submit">Update</button>
        </form>
    </div>
</body>
</html>


