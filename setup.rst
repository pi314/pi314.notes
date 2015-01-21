==============
安裝設定懶人包
==============

* Jails

  - 見 ``freebsd-jails.rst``

* Django ::

    $ pyvenv-3.4 .venv
    $ source .venv/bin/activate
    $ pip install Django
    # portmaster -Bdw database/py-sqlite3
    $ django-admin startproject { project-name }
    $ cd { project-name }
    $ ./manage.py syncdb
    $ ./manage.py runserver
