# ProjectTwo_Team05

Run the following commands to view the application:

1. First compile the code using the following command

javac -cp ".;./lib/jcommon-1.0.23.jar;./lib/jfreechart-1.0.19.jar" src/Client/*.java src/Server/*.java src/Shared/*.java

2. Then, run the ServerUIMain.java and ClientUIMain.java 

java -cp ".;./src/;./lib/jcommon-1.0.23.jar;./lib/jfreechart-1.0.19.jar" Client.ClientUIMain

java -cp ".;./src/;./lib/jcommon-1.0.23.jar;./lib/jfreechart-1.0.19.jar" Server.ServerUIMain

Note: For Mac/Linux use ':' instead of ';'

Follow the below mentioned steps to check the features:
Step 1] Run ClientMainUI.java
Step 2] Run ServerMainUI.java
Step 3] Enter the required inputs on Client GUI.
Step 4] Enter the required inputs on Server GUI (or else it will automaticaaly pickup values)
Step 5] Click Start/Stop button on Server GUI to start the server
Step 6] Click Start/Stop button on Client GUI to start the Client
Step 7] Click Start/Stop button on Client GUI to stp the client after sometime.
Step 9] Stop the server clicking the start/stop button on server GUI.
