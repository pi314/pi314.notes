import tornado.ioloop
import tornado.websocket
import tornado.web

import os
ROOT_DIR = os.path.dirname(os.path.abspath(__file__))

STATIC_PATH = os.path.join(ROOT_DIR, 'static')

class IndexPageHandler(tornado.web.RequestHandler):
    def set_extra_headers(self, path):
        self.set_header("Cache-control", "no-cache")

    def get(self):
        self.render('templates/chat.html')

class MyWebSocketHandler(tornado.websocket.WebSocketHandler):
    websockets = set()

    @classmethod
    def broadcast(cls, msg):
        for ws in cls.websockets:
            ws.write_message(msg)

    def open(self):
        print('WebSocket opened')
        self.websockets.add(self)
        MyWebSocketHandler.broadcast('one user online from {}'.format(self.request.remote_ip))

    def on_message(self, message):
        MyWebSocketHandler.broadcast('{}: {}'.format(self.request.remote_ip, message))

    def on_close(self):
        print('WebSocket closed')
        self.websockets.remove(self)
        MyWebSocketHandler.broadcast('one user offline from {}'.format(self.request.remote_ip))

    def ping(self, data):
        """Send ping frame to the remote end."""
        if self.ws_connection is None:
            raise WebSocketClosedError()
        self.ws_connection.write_ping(data)

    def on_pong(self, data):
        """Invoked when the response to a ping frame is received."""
        pass

application = tornado.web.Application([
    tornado.web.url(r'/', IndexPageHandler),
    tornado.web.url(r'/websocket', MyWebSocketHandler),
    tornado.web.url(r'/static/(.*)', tornado.web.StaticFileHandler, {'path': STATIC_PATH})
])

if __name__ == '__main__':
    application.listen(8888)
    tornado.ioloop.IOLoop.current().start()

