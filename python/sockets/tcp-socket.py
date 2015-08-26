s = socket.socket()
s.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1)
s.bind(('', self.port))
s.listen(5)

client, addr = s.accept()

while True:
    data = client.recv(1024)
    if len(data) <= 0:
        break

    client.send(data)

client.shutdown(socket.SHUT_RDWR)
client.close()

s.shutdown(socket.SHUT_RDWR)
s.close()
