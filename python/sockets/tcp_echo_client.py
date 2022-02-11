import socket


HOST = 'localhost'
PORT = 1989


def main():
    with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as s:
        s.connect((HOST, PORT))
        print(s.recv(1024).decode('utf8'))

        for i in range(3):
            data = 'msg' + str(i)
            print('[client]', data)
            s.sendall(data.encode('utf8'))

            print('[server]', s.recv(1024).decode('utf8'))

        print('[client] bye')
        s.sendall('bye'.encode('utf8'))

        print('[server]', s.recv(1024).decode('utf8'))


if __name__ == '__main__':
    main()
