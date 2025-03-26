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
        span{
        color:red;
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

    <!-- Registration Form -->
    <div class="form-container">
        <h1>Registration Form</h1>
        <form action="reg" method="post" enctype="multipart/form-data">
        <label for="multipartFile" >profile</label>
        <input type="file" name="multipartFile" id="profile" required>

            <div class="error">
            <c:out value="${nameError}"/>
             </div>
             <span id="nameerror"></span>
            <label for="name">Name:</label>
            <input type="text"  id="name" name="name" onblur="onname()"  value="${dto.name}" required>

            <span id="iderror"></span>
            <label for="login">Login ID:</label>
            <input type="text"  id="loginid" name="loginId" onChange="onloginid()" value="${dto.loginId}" required>

            <span id="emailerror"></span>
            <label for="email">Email:</label>
            <input type="email" onChange="onemail()" id="email" name="email" value="${dto.email}" required>

            <span id="ageerror"></span>
            <div class="error">
            <c:out value="${ageError}"/>
            </div>
            <span id="ageerror"></span>
            <label for="age">Age:</label>
            <input type="number"  id="age" name="age" onblur="onage()" value="${dto.age}" required>

            <div class="error">
            <c:out value="${phoneError}"/>
            </div>
            <span id="phoneerror"></span>
            <label for="phone">Phone Number:</label>
            <input type="number"  id="phoneNumber" name="phoneNumber" onblur="onnumber()"  value="${dto.phoneNumber}" required>


            <div class="error">
            <c:out value="${birthError}"/>
            </div>
            <label for="dob">Date of Birth:</label>
            <input type="date" id="dateofBirth" name="dateofBirth" value="${dto.dateofBirth}" required>

            <label for="location">Location:</label>
            <select id="dropdown" name="location" value="${dto.location}" required>
                <option value="${dto.location}"></option>
               <c:forEach items="${list}" var="location">
               <option>${location}</option>
               </c:forEach>
            </select>

            <button type="submit">Submit</button>
        </form>
    </div>
    <script>


function onname(){
var name=document.getElementById('name').value;
const patter=/^[A-Z][a-z]*$/;
if(patter.test(name)){
    document.getElementById('nameerror').innerHTML="";
}else{
    document.getElementById('nameerror').innerHTML="invalid pattern start the name with captial letter";
  }
}

function onage(){
var age=document.getElementById('age').value;
if(age<16){
    document.getElementById('ageerror').innerHTML="age must be above 16";
}else{
    document.getElementById('ageerror').innerHTML="";
}
}

function onnumber(){
var number=document.getElementById('phoneNumber').value;
const pattern=/^[98]\d{9}$/;
if(pattern.test(number)){
       document.getElementById('phoneerror').innerHTML=""
}else{
    document.getElementById('phoneerror').innerHTML="invalid number pattern and size must be 10";
}
}



function onemail() {
var email=document.getElementById('email')
var emailvalue=email.value;
console.log(name)
console.log(emailvalue)


    if (emailvalue!=="") {
    var xhttp = new XMLHttpRequest();
    xhttp.open("GET","http://localhost:8090/commmon-modules/email?email="+emailvalue);
    xhttp.send();

    xhttp.onload = function(){
     document.getElementById("emailerror").innerHTML = this.responseText;
     }
   }
}



function onloginid() {
var idvalue=document.getElementById('loginid').value;
console.log(idvalue)
var xhttp = new XMLHttpRequest();

    if (idvalue!=="") {
    xhttp.open("GET","http://localhost:8090/commmon-modules/loginid/"+idvalue);
    xhttp.send();

    xhttp.onload = function(){
     document.getElementById("iderror").innerHTML = this.responseText;
     }
   }
}


    </script>
</body>
</html>


