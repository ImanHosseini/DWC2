# DWC2: Dev Warz Client 
![alt text](https://github.com/ImanHosseini/DWS2/blob/master/logo.png)

This is course project for DB. It was to implement a database for a massively-multiplayer game, 
for which we also implemented the game itself. The server communicates with the clients via http protocol, and with postgres
via the java driver (JDBC). The server can handle multiple clients, as it makes a new thread for every TCP connection. <br />
It has been tried to do things the right way, from an engineering point of view, and the code is scalable as a result of following 
OOP concepts. For example, there is a class hierarchy between different entities. On top there is a Drawable class which is an 
abstract class, and forces the object to be something the GUI on the client side can draw. Then there is a Resource class
extending drawable, and finally any Resource, like Wood is a class extending that. <br />

![alt text](https://github.com/ImanHosseini/DWC2/blob/master/pro1.JPG)
![alt text](https://github.com/ImanHosseini/DWC2/blob/master/pro2.JPG)
The game graphics is via JavaFX and the GUI is made into FXML format via SceneBuilder. The Client repeatedly sends requests to Server 
to get updates of the map, credits and new chats. On faulty input the program informs the user of the problem, as can be seen here. The buttons for changing the viewpoint, trigger a new rendering of the
mapview canvas. <br />

As for security, no input from user directly transforms into SQL queries on Server side, and only limited methods implemented by the 
Server are callable, which do not involve direct unsanitized queries. For better performance, the whole messagebox doesn't get sent with
every update, and on first load, it gets sent. Then, only new messages get sent.
