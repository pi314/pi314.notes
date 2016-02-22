#!/usr/bin/env python
import socket
import sys
import os

if len(sys.argv) != 3:
    print('Usage:')
    print('  {} host port'.format(os.path.basename(sys.argv[0])))
    exit(64)

HOST_IP = sys.argv[1]
HOST_PORT = int(sys.argv[2])
s = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
s.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1)
s.setsockopt(socket.SOL_SOCKET, socket.SO_BROADCAST, 1)
s.sendto(bytes(input(), 'utf8'), (HOST_IP, HOST_PORT))
