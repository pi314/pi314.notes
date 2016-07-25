========
iptables
========
* 清除所有 Rules ::

    # iptables -F

* 新增 Rule : Allow Incomming TCP/SSH connection ::

    # iptables -A INPUT -p tcp --dport 22 -j ACCEPT


預設 Rules
-----------
* Fedora: ``/etc/sysconfig/iptables``
