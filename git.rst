============
= Git 筆記 =
============

概念
----

-   特色

    -   分散式
        -   每個人都有完整的 repo
        -   開發時不一定要網路，同步時才要

    -   非線性開發
        -   不一定只能依靠上一個版本

-   repo 目錄結構

    -   working directory
        working directory 中的檔案在 add 後會進入 staging area

    -   staging area
        staging area 中的檔案在 commit 後會進入 repository

    -   repository
        repository 中的檔案在 checkout 後會進入 working directory

基本
----

-   初始化
    -   git init

-   設定自己的資料
    -   git config --global user.name "Cychih"
    -   git config --global user.email "michael66230@gmail.com"

-   把一個 repo 複製到 local 端
    -   git clone {URI}

-   查看 commit history
    -   git log

-   把所有檔案加入 commit
    -   git add -A

-   查看 commit repository 和 working directory 的差異
    -   git status
    -   git diff

-   追蹤檔案

    -   列出追蹤中的檔案
        -   git ls-files

    -   設定不要被追蹤的檔案
        -   永久性
            -   .gitignore
            -   有敏感資訊的檔案一定要加入 .gitignore

        -   一次性 (只有這次需要暫時 unstage)
            -   git reset {filename}

    -   刪除追蹤中的檔案 (會真的把檔案刪掉)
        -   git rm {filename}
        -   建議不要手動 rm，不然 tracking list 中仍然會存在該檔名，導致 repo dirty

    -   重新命名追蹤中的檔案
        -   git mv {name1} {name2}
        -   建議不要手動 mv，不然 tracking list 中仍然在存在該檔名，導致 repo dirty

-   回到最新的 commit 的狀態
    -   git reset --hard HEAD

進階
----

-   更動暫存系統 - stash

    -   stash 是一個 stack，可以把未 commit 的內容暫存起來，避免因正在修改某些檔案而導致無法做其他操作，如切換 branch 等等

    -   把未 commit 的更動暫存起來
        -   git stash
        -   同時會把檔案內容復原成 branch 的最新狀態

    -   把暫存的更動放回原檔
        -   git stash apply

    -   列出最新 stash 的改動
        -   git stash show

    -   放棄一個 stash
        -   git stash drop

-   submodule

    -   submodule 讓一個 repo 可以使用其他 repo，且每個 repo 可以是不同版本

    -   為 repo 加入一個 submodule
        -   git submodule add {URI}

    -   把一個含有 submodule 的 repo clone 下來
        1.  git clone {URI}
        2.  cd {repo}
        3.  git submodule init
        4.  git submodule update

    -   更新 repo 內的 submodule
        1.  cd {submodule}
        2.  git pull origin master
        3.  cd ..
        4.  git add {submodule}
        5.  git commit -m "Update submodule"
        6.  git push origin
        -   其他 main repo 也需要更新
            -   git pull origin
            -   git submodule update

Branch 相關
-----------

-   列出 branch
    -   git branch

-   創造一個 branch
    -   git branch {name}

-   跳到另一個 branch
    -   git checkout {name}

-   回到某個 commit 的狀態
    -   git reset {hash}

-   把一個 branch push 到 github 上
    -   git push origin {branch_name}
    -   git push origin master

-   把一個 remote branch pull 下來而不 merge
    -   git fetch origin {remote branch name}:{local branch name}
    -   有時會失敗的方法
        -   git checkout --track origin/{branch_name}

-   避免把 branch 上的所有  history 都 merge 進 master
    -   git checkout master
    -   git merge --edit --no-ff {branch_name}

-   刪除 remote branch
    -   git push origin --delete {branch_name}

-   更新 branch database
    -   git fetch -p

Github 相關
-----------

-   git pull

-   設定目的地
    -   git remote add origin {URI}
    -   git remote add origin http://github.com/pi314/{}.git
    -   有設定 ssh key 的話，可用 ssh
        -   git remote add origin git@github.com:pi314/{}.git
    -   add 可改成 set-url

-   在 github 上隱藏前一個 commit
    -   git push -f origin HEAD^:master

協作 - 在 github 上貢獻別人的 Project
-------------------------------------

假設該 project 名稱為 A

1.  在 github 上 fork A, A 會複製一份到自己的帳號底下 (稱為 B
2.  git clone B
3.  git remote add upstream A
4.  git fetch upstream
5.  修改 repo 內的東西
6.  git push origin master (或是其他想要 push 合併回原 repo 的 branch)
7.  在 github 上的 A 頁面發出 pull request