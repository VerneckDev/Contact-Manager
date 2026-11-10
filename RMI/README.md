# RMI-Contact-Manager

A client-server contact management application built with **Java RMI** (Remote Method Invocation). Users can register, log in, and manage a personal contact list — all through a terminal interface, with data persisted on the server side.
 
---
 
## 📁 Project Structure
 
```
src/
├── module-info.java   — Java module descriptor (module TheServer: exports general, requires java.rmi)
└── general/
    ├── Server.java        — Starts the RMI registry and exposes the remote object
    ├── Client.java        — Terminal UI; connects to the server and handles user input
    ├── Interface.java     — RMI remote interface (defines available remote methods)
    ├── Implement.java     — Server-side implementation of the remote interface
    ├── User.java          — Represents a registered user (name, password, email, contacts)
    ├── Contatos.java      — Represents a single contact (name + phone number)
    ├── RegUser.java       — Registry/collection of all users
    └── FileMnager.java    — Handles serialization: saves and loads user data from disk
```
 
---
 
## ⚙️ How It Works
 
The server registers a remote object (`Implement`) under the name `"houseWife"` on port `1099`. The client looks it up via `localhost:1099` and calls methods on it as if they were local — that's Java RMI in action.
 
User data is persisted in a file called `users.data` (Java serialization), so registered users and their contacts survive server restarts.
 
---
 
## 🚀 Running the Application
 
### Prerequisites
- Java 11 or later
- All source files compiled into the same package (`general`)

### 1. Compile
 
From the `RMI/` folder:
 
```bash
javac -d out src/module-info.java src/general/*.java
```
 
Or, if compiling without modules:
 
```bash
javac -d out src/general/*.java
```
 
### 2. Start the Server
 
```bash
java -cp out general.Server
```
 
You should see:
```
Server ready
```
 
### 3. Start the Client (in a separate terminal)
 
```bash
java -cp out general.Client
```
 
---
 
## 🖥️ Client Menus
 
### Main Menu (unauthenticated)
```
╔═══════════════════════════════╗
║         Contact Admin         ║
║                               ║
║          [1] Login            ║
║          [2] Register         ║
║          [3] Exit             ║
║                               ║
╚═══════════════════════════════╝
```
 
### Contact Menu (after login)
```
╔═══════════════════════════════╗
║         Contact Admin         ║
║                               ║
║      [1] List of Contacts     ║
║      [2] Insert Contact       ║
║      [3] Remove Contact       ║
║      [4] Exit                 ║
║                               ║
╚═══════════════════════════════╝
```
 
---
 
## ✅ Features
 
- **Register** — Create an account with a username, password, and email. Duplicate usernames and emails are rejected; basic email format is validated.
- **Login** — Authenticate with username and password.
- **List Contacts** — View all contacts saved under your account.
- **Add Contact** — Save a contact by name and 9-digit phone number. Duplicate numbers are rejected.
- **Remove Contact** — Delete a contact by phone number.
- **Persistent Storage** — All data is saved to `users.data` on every write, so nothing is lost between sessions.
---
 
## 📝 Notes & Known Limitations
 
- Phone numbers must be exactly **9 digits** long.
- Passwords are stored in **plain text** — not suitable for production use.
- The client always connects to **`localhost`**.
- If `users.data` is corrupted or missing, the server automatically starts with an empty user registry.
