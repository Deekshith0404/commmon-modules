<%@ page language="java" contentType="text/html; charset=US-ASCII" pageEncoding="US-ASCII"%>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Login Page</title>
  <style>
    body {
      font-family: Arial, sans-serif;
      margin: 0;
      padding: 0;
      background-color: #f4f4f4;
    }
    .button-style {
      display: inline-block;
      width: 95%;
      text-align: center;
      padding: 10px;
      background-color: #007bff;
      color: white;
      text-decoration: none;
      border-radius: 4px;
      border: none;
      cursor: pointer;
      transition: background-color 0.3s ease;
    }

    .button-style:hover {
      background-color: #0056b3;
    }
    .button-style:hover {
          background-color: #0056b3;
        }

    .menu-bar {
      background-color: #333;
      color: white;
      padding: 10px 20px;
      display: flex;
      justify-content: space-between;
      align-items: center;
    }
    .menu-bar .menu-links {
      display: flex;
      gap: 15px;
    }
    .menu-bar a {
      color: white;
      text-decoration: none;
    }
    .login-container {
      max-width: 400px;
      margin: 50px auto;
      padding: 20px;
      background: white;
      border-radius: 8px;
      box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    }
    .login-container h2 {
      margin-bottom: 20px;
      text-align: center;
    }
    .form-group {
      margin-bottom: 15px;
    }
    .form-group label {
      display: block;
      margin-bottom: 5px;
    }
    .form-group input {
      width: 95%;
      padding: 8px;
      border: 1px solid #ccc;
      border-radius: 4px;
    }
    .form-group button {
      width: 100%;
      padding: 10px;
      background-color: #007bff;
      border: none;
      color: white;
      border-radius: 4px;
      cursor: pointer;
    }
    .form-group button:hover {
      background-color: #0056b3;
    }
    .right-link {
      float: right;
    }
    .right-link a {
      color: #007bff;
      text-decoration: none;
    }
    .right-link a:hover {
      text-decoration: underline;
    }
    .data{
        color:red;
    }
  </style>
</head>
<body>
  <div class="menu-bar">
    <div class="menu-links">
      <a href="#">Home</a>
      <a href="#">About</a>
      <a href="#">Contact</a>
    </div>
    <div class="right-link">
      <a href="register">Register</a>
    </div>
  </div>

  <div class="login-container">

    <h2>Login</h2>
    <div class="data">
    ${pass}
    ${time}
    </div>
    <form action="login" method>
      <div class="form-group">
        <label for="email">Email</label>
        <input type="email" id="email" name="email" required>
      </div>
      <div class="form-group">
        <label for="password">Password</label>
        <input type="password" id="password" name="password" required>
      </div>

        <div class="form-group">
          <label for="captcha">
          <input type="text" name="usercap" value="${captcha}" hidden </input>
            captcha:
            <span style="cursor: pointer;" onclick="fetch('startlogin', { method: 'GET' }).then(() => location.reload())">
              &#x21bb;
            </span>
            ${captcha}
          </label>
          <input type="text" id="captcha" name="captcha" required>
        </div>
          <div class="form-group">
              <button type="submit" class="button-style" value="submit">Login</button>
              </div>
              <div class="form-group">
                  <button type="button" onclick="" style="background-color: light-blue ; color: white;">Forgot Password</button>
                </div>
    </form>
  </div>
</body>
</html>
