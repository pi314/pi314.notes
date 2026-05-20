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
