#!/usr/bin/env python
import socket
import sys
import os

if len(sys.argv) != 2:
    print('Usage:')
    print('  {} port'.format(os.path.basename(sys.argv[0])))
    exit(64)

UDP_IP = ''
UDP_PORT = int(sys.argv[1])
s = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
s.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1)
s.bind((UDP_IP, UDP_PORT))
data, addr = s.recvfrom(1024)
print('[{addr[0]}:{addr[1]}] {data}'.format(addr=addr, data=data))
