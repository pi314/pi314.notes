==========
``dialog``
==========
紀錄一些 ``dialog`` programming snippet

* 普通的 message box，長寬為 (20, 50)

  .. code:: sh

    dialog --title 'Title' --msgbox 'Hello world' 20 50

* 產生 menu 並取得使用者選擇的選項

  .. code:: sh

    items="option1 option2 option3"

    items=$(echo "$items" | tr ' ' '\n')
    b=$(echo "$items" | nl)
    selection=$(dialog --menu "Description" 20 50 50 $b 3>&1 1>&2 2>&3)
    if [ $? -eq 0 ]; then
        selection=$(echo "$items" | head -n ${selection} | tail -n 1)
        echo ${selection}
    fi

* 產生 inputbox 並取得使用者輸入的字串

  .. code:: sh

    user_input=$(dialog --inputbox "Description" 20 50 "initial text" 3>&1 1>&2 2>&3)
    if [ $? -eq 0 ]; then
        echo ${user_input}
    fi
