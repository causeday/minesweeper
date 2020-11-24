# 扫雷/Minesweeper
-----English Version Below-----

这是我软件开发课的期中作业，用纯Java实现，界面实现部分使用awt。整个项目大约消耗1-2周课余时间。

实现的游戏操作如下：

1. 基本的扫雷玩法，如左键翻开、右键标记有雷和取消有雷等。

2. 一旦翻开雷区，游戏立刻结束并弹出对话框显示游戏时长，所有雷区标红显示。当所有雷区被标记且所有非雷区被翻开时，游戏自动结束并弹出对话框显示游戏时长。

3. 翻开非雷区时，若附近9格无雷，则会自动将无雷区域全部翻开，直到触及雷区为止。

4. 无论何时，均可以通过点击最上面的“Start a New Game”开始新游戏。

5. 设置三种难度，雷数为10、40和99.

屏幕截图如下：

![image](https://github.com/causeday/minesweeper/blob/main/screenshots/main.png)

![image](https://github.com/causeday/minesweeper/blob/main/screenshots/low-level.png)

![image](https://github.com/causeday/minesweeper/blob/main/screenshots/game-fail.png)

![image](https://github.com/causeday/minesweeper/blob/main/screenshots/game-success.png)
