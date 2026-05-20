# homebrew

## 基本

```console
sh$ brew list [FORMULA]
sh$ brew list --installed-on-request
sh$ brew search TEXT
sh$ brew info FORMULA
sh$ brew install FORMULA [--cask]
sh$ brew update
sh$ brew upgrade FORMULA [...]
```

## 列出 formula 安裝的檔案
```console
sh$ brew ls coreutils
/opt/homebrew/Cellar/coreutils/9.10/bin/b2sum
/opt/homebrew/Cellar/coreutils/9.10/bin/base32
/opt/homebrew/Cellar/coreutils/9.10/bin/basenc
/opt/homebrew/Cellar/coreutils/9.10/bin/factor
/opt/homebrew/Cellar/coreutils/9.10/bin/g[
...
```

## 反查指令所屬的 formula

```console
sh$ brew which-formula tac
coreutils

sh$ brew which-formula ag
the_silver_searcher

sh$ brew which-formula ffmpeg
ffmpeg
ffmpeg-full
ffmpeg@2.8
ffmpeg@4
ffmpeg@5
ffmpeg@6
ffmpeg@7
```
