# File Sharing Client

Distributed Java application client using Corba and Swing.

---

## Instituto Federal de Educação, Ciência e Tecnologia do Ceará

### Computer Engineering - Parallel and Distributed Programming 
### Project of CORBA

- Objective: Implement a P2P file sharing system in CORBA

- Basic Features: 
  - Each Client is a server that shares files stored in a local folder.
  - When a Client connects to the system (connection to the central server) it sends the list of its files (can only be new files) to the central server.
  - The Central Server has the list of all files and all clients that are currently connected.
  - Clients can search the server that returns the list of other clients that have the file.
  - The Client chooses who he wants to download from and makes the direct request to the client who owns the file.
  - The file is downloaded in the shared folder.
  - Searches must be done using substrings of filenames or extensions.
  - A graphical interface is required to perform actions such as:
    - Configure the directory where files will be shared on clients.
    - Observe the files that are being downloaded.
    - Perform searches and view the responses to the requested content.
