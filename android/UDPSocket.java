String messageStr = "Hello Android!";
int server_port = 9876;
DatagramSocket s = new DatagramSocket();
InetAddress local = InetAddress.getByName("127.0.0.1");
int msg_length = messageStr.length();
byte[] message = messageStr.getBytes();
DatagramPacket p = new DatagramPacket(
    message, msg_length, local, server_port);
s.send(p);
