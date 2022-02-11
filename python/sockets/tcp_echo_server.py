import socket
import threading


HOST = 'localhost'
PORT = 1989


def main():
    server_socket = socket.socket()
    server_socket.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1)
    server_socket.bind((HOST, PORT))
    server_socket.listen(5)

    print('[server] Listening on {}:{}'.format(HOST, PORT))

    while True:
        client_socket, addr = server_socket.accept()
        t = threading.Thread(target=client_handler, args=(client_socket, addr))
        t.daemon = True
        t.start()

    try:
        server_socket.shutdown(socket.SHUT_RDWR)
    except OSError:
        pass
    server_socket.close()


def client_handler(client_socket, addr):
    def send(s):
        client_socket.send(s.encode('utf8'))

    print('[server] hello')
    send('hello')

    while True:
        msg = client_socket.recv(1024).decode('utf8').strip()
        if not msg:
            break

        print('[server]', 'echo ' + msg)
        send('echo ' + msg)

    try:
        client_socket.shutdown(socket.SHUT_RDWR)
    except OSError:
        pass

    client_socket.close()


if __name__ == '__main__':
    try:
        main()
    except KeyboardInterrupt:
        pass
