# Contact Manager — Java (RMI & Socket)

Two client-server contact management applications built in Java, both offering the same terminal-based experience (register, login, and manage a personal contact list) but implemented with different communication technologies.

---

## Projects

### [RMI-Contact-Manager](./RMI)
Built with **Java RMI** (Remote Method Invocation). The client calls methods on a remote server object as if they were local, with user data persisted via serialization in `users.data`.

### [Socket-Contact-Manager](./Socket)
Built with **Java Sockets** (TCP) on port `3333`. The client and server exchange plain-text commands (`"log"`, `"add"`, `"remove"`, etc.) over raw input/output streams, with contact lists sent back as serialized objects.

---

## Shared Features

- Register (username, password, and email) and log in (username and password)
- List, add, and remove contacts (9-digit phone numbers)
- Server-side data persistence
- Simple terminal UI with boxed menus

See each project's own README for setup instructions and implementation details.

---

## Authors

**João Pedro Verneck** — [@VerneckDev](https://github.com/VerneckDev)

**João Vitor Andrade** — [@Jonhi7139](https://github.com/Jonhi7139)