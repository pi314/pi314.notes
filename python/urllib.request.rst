===============================================================================
urllib.request
===============================================================================
::

    headers = {
        'Content-Type': 'application/json; charset=utf-8',
    }

    message = {
        'chat_id': chat_id,
        'text': text,
    }

    req = urllib.request.Request(
        url,
        headers=headers,
        data=json.dumps(message).encode('utf-8'),
    )

    res = urllib.request.urlopen(req)
    print(res.read().decode('utf-8'))
