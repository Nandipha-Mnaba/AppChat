

 AppChat – Java Console Chat Application
 Overview

AppChat is a simple Java console-based chat application that allows users to register, log in, send messages, and store them in a JSON-like format.

It demonstrates basic object-oriented programming concepts such as classes, constructors, and methods.



 Features

User Registration & Login

  * Enter first name, last name, username, password, and cell number.
  * Login with username and password.

  Messaging System

  * Send messages to recipients (South African numbers starting with `+27`).
  * Messages limited to 250 characters.
  * Messages get a unique ID and a simple hash summary.

  Message Storage

  * Store messages in a JSON-like format.
  * Display sent messages in the console.

  Menu-Driven Interface

Send messages, view sent messages, or exit the app.

---

 How to Run

1. Compile the project:

```bash
javac AppChat.java Login.java Messages.java MessageManager.java
```

 Run the application:

```bash
java AppChat

Classes

* **AppChat** – Main program with menu and user interaction.
* **Login** – Handles user registration and authentication.
* **Messages** – Creates and validates messages, generates IDs and hashes, stores messages in JSON format.
* **MessageManager** – (Optional) Manages sending and storing messages.


 Future Improvements

* Support multiple users with persistent storage.
* Use a JSON library like Gson for proper message storage.
* Add password encryption and message deletion/edi
