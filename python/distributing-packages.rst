=====================
Distrubuting Packages
=====================
..  contents::


製作 PyPI Package
--------------------
PyPI Package 需符合一定的目錄結構 ::

  root-folder/
  ├── <package-name>/
  │   └── __init__.py
  ├── setup.py
  ├── README.rst
  └── LICENSE

以上是最基本的結構，隨著 Package 的用途，可能會有更多的目錄和檔案需要加入

以下是一個簡單的 ``setup.py`` 範例

..  code:: python

    from setuptools import find_packages, setup
    setup(
        name='<package-name>',
        packages=find_packages(exclude=['scripts']),
        version='0.1',
        description='<說明>',
        author='<作者>',
        author_email='<作者 email>',
        url='<Package URL>',
        classifiers=[],
    )


產生 Dist Files
----------------
1.  安裝 ``wheel`` Package
2.  產生 PKG-INFO 檔案 ::

      $ python setup.py egg_info

    + PKG-INFO 檔案是 PyPI Package 的 Metadata

3.  產生 Dist Files ::

      $ python setup.py sdist bdist_wheel

    + ``sdist`` 會把 Source code 包裝成壓縮檔
    + ``bdist_wheel`` 會把 Source code 編譯過再包裝成壓縮檔，這個方法的安裝過程會比較快 [1]_
    + ``bdist_wheel`` 選項需安裝 ``wheel`` Package 以後才能使用
    + 產生的壓縮檔會放在 ``dist/`` 目錄中

..  [1] https://packaging.python.org/en/latest/distributing/#wheels


上傳至 PyPI
------------

第一次上傳
````````````
1.  至 https://pypi.python.org/pypi 申請帳號
2.  點選 Package Submision 選項
3.  上傳 PKG-INFO 檔案
4.  安裝 ``twine`` Package
5.  透過 ``twine`` 上傳 ::

      $ twine upload dist/*


上傳新版本
````````````
::

  $ twine upload dist/*
