from flask import Flask, make_response, render_template, redirect, url_for

app = Flask(__name__)

@app.route('/')
def index():
    return make_response(redirect('/index'))

@app.route('/<username>')
def hello(username):
    ''' ./templates/index.html
        <html>
            <head>
                <link rel=stylesheet type=text/css href="{{ url_for('static', filename='style.css') }}">
            </head>
            <body>
                <h1>Hello, {{ name }}!</h1>
            </body>
        </html>
    '''
    return make_response(render_template('index.html', name=username))

@app.route('/hello/<username>')
def dummy_hello(username):
    return make_response(redirect(url_for('hello', username=username)))

app.run('0.0.0.0', debug=True)

