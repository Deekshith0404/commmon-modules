<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Index Page</title>
    <style>
    /* Reset and basic styling */
    body {
        margin: 0;
        font-family: Arial, sans-serif;
        background-color: #f4f4f9;
        color: #333;
    }

    /* Menu bar styling */
    .menu-bar {
        display: flex;
        justify-content: space-between;
        align-items: center;
        background-color: #007bff;
        padding: 10px 20px;
        color: white;
    }

    .menu-left, .menu-right {
        display: flex;
        gap: 15px;
    }

    .menu-item {
        text-decoration: none;
        color: white;
        padding: 8px 12px;
        border-radius: 4px;
        transition: background-color 0.3s;
    }

    .menu-item:hover {
        background-color: #0056b3;
    }

    /* Main content area styling */
    main {
        padding: 20px;
        text-align: center;
    }

    </style>
</head>
<body>
    <header>
        <nav class="menu-bar">
            <div class="menu-left">
                <a href="#home" class="menu-item">Home</a>
                <a href="#about" class="menu-item">About</a>
                <a href="#services" class="menu-item">Services</a>
            </div>
            <div class="menu-right">
                <a href="register" class="menu-item">Register</a>
                <a href="startlogin" class="menu-item">Login</a>
            </div>
        </nav>
    </header>
    <main>
        <h1>Welcome to the Index Page</h1>
        <p>This is the main content area. Click "Register" or "Login" to proceed.</p>
    </main>
</body>
</html>
