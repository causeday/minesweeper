# 扫雷/Minesweeper
【English Version Below】

这是我软件开发课的期中作业，用纯Java实现，界面实现部分使用awt。整个项目大约消耗1-2周课余时间（2020.3），由我一人独立完成。

实现的游戏操作如下：

1. 基本的扫雷玩法，如左键翻开、右键标记有雷和取消有雷等。

2. 一旦翻开雷区，游戏立刻结束并弹出对话框显示游戏时长，所有雷区标红显示。当所有雷区被标记且所有非雷区被翻开时，游戏自动结束并弹出对话框显示游戏时长。

3. 翻开非雷区时，若附近9格无雷，则会自动将无雷区域全部翻开，直到触及雷区为止。

4. 无论何时，均可以通过点击最上面的“Start a New Game”开始新游戏。

5. 设置三种难度，雷数为10、40和99.

游戏截图请拉到最下方。




【Chinese Version Above】

This is my middle-term assignment of the course Software Development. The whole project is based on pure Java, and the UI part is based on awt. 

The whole project took me around 1-2 weeks in March, 2020 and all work is done by myself. 

Game functions implemented show below:

1. Basic minesweeper rules, like open a block by left mouse button and flag a block by right mouse button. 

2. Once the user opens a mined block, the game will stop immediately and a dialog pops up to show the game duration, and all mined blocks will be marked as red. The same thing happens when the user passes the game successfully.

3. If an unmined block is opened and there is no mined block around it(3x3), all unmined blocks will be opened until a mined block is reached.

4. The user can start a new game anytime by pushing the button "Start a New Game".

5. Three game levels: 10 mines, 40 mines and 99 mines.

Screeenshots show below. 




【游戏截图/Screenshots】

![image](https://github.com/causeday/minesweeper/blob/main/screenshots/main.png)

![image](https://github.com/causeday/minesweeper/blob/main/screenshots/low-level.png)

![image](https://github.com/causeday/minesweeper/blob/main/screenshots/game-fail.png)

![image](https://github.com/causeday/minesweeper/blob/main/screenshots/game-success.png)

