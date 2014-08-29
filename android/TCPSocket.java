/* Client Socket */
socket = new Socket(connect_ip, connect_port);
writer = new PrintWriter(
        new OutputStreamWriter(socket.getOutputStream()));
reader = new BufferedReader(
        new InputStreamReader(socket.getInputStream()));

writer.write("data");
writer.flush();

String input_data;
while ((input_data = reader.readLine()) != null) {
    notify_message("interface", input_data);
}

/* *************** */
/* *************** */

/* Server Socket */
serverSocket = new ServerSocket(connect_port);

Socket clientSocket = serverSocket.accept();
writer = new PrintWriter(
        new OutputStreamWriter(clientSocket.getOutputStream()));
reader = new BufferedReader(
        new InputStreamReader(clientSocket.getInputStream()));

String input_data;
while ((input_data = reader.readLine()) != null) {
    notify_message("interface", input_data);
}
