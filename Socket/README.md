# Socket-Contact-Manager

A client-server contact management application built with **Java Sockets** (TCP). Users can register, log in, and manage a personal contact list — all through a terminal interface, with data persisted on the server side.

---

## 📁 Project Structure

```
src/
└── Phone/
    ├── Server.java        — Listens on port 3333, handles client connections and requests
    ├── Client.java        — Terminal UI; connects to the server via socket and handles user input
    └── DataBase.java      — Data model: Users (name, email, pass) and Contact (name, phone number)
```

---

## ⚙️ How It Works

The server opens a `ServerSocket` on port `3333` and waits for incoming connections. Each client communicates with the server through a simple text-based protocol over the socket's input/output streams — sending command strings (`"log"`, `"reg"`, `"list"`, `"add"`, `"remove"`, etc.) and reading back the server's responses line by line.

Contact lists are sent back to the client as serialized `ArrayList<DataBase.Contact>` objects via `ObjectInputStream`, so the client can deserialize and display them directly.

Each connection is handled in its own thread (`ClientHandler`), and users and contacts are persisted by the server in `users.dat` and `cnts.dat` (Java serialization).

---

## 🚀 Running the Application

### Prerequisites
- Java 11 or later
- All source files compiled into the same package (`Phone`)

### 1. Compile

From the `src/` folder:

```bash
javac Phone/*.java
```

### 2. Start the Server

```bash
java Phone.Server
```

You should see:
```
Waiting for connection...3333
```

### 3. Start the Client (in a separate terminal)

```bash
java Phone.Client
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
- **Persistent Storage** — Users and contacts are saved server-side in `users.dat` and `cnts.dat`, so nothing is lost between sessions; contact lists are sent to the client on request.

---

## 📝 Notes & Known Limitations

- Phone numbers must be exactly **9 digits** long.
- Communication protocol relies on plain string commands (`"log"`, `"reg"`, `"exit1"`, `"exit2"`, etc.), so client and server must stay in sync on message order.
- The client always connects to **`localhost`** on port **`3333`**.
- Unlike the RMI version, all remote calls here are implemented manually over raw sockets (`PrintWriter`, `BufferedReader`, `ObjectInputStream`) instead of relying on Java's built-in remote-object mechanism.
