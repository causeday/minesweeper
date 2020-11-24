package com.yunchen.view;

import com.yunchen.application.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

class GameFrame extends JFrame {

    GameFrame() throws HeadlessException {

    }

    private Button makeLevelButton(String name){
        Button newButton1 = new Button(name);
        newButton1.setPreferredSize(new Dimension(100,60));
        return newButton1;
    }

    /*
    Use to make the layout of game.
     */
    private Box makeGameBox(JButton button, GamePanel gamePanel){
        Box box=Box.createVerticalBox();
        JPanel upperPanel = new JPanel();
        upperPanel.add(button);
        box.add(upperPanel);
        box.add(Box.createVerticalGlue());
        JPanel middlePanel = new JPanel();
        middlePanel.add(gamePanel.getRestMineCount());
        box.add(middlePanel);
        box.add(Box.createVerticalGlue());
        box.add(gamePanel);
        return box;
    }

    void launchFrame(){
        this.setTitle("MineSweeper by Yunchen");
        this.setSize(500, 400);
        this.setLocation(300, 150);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Box b1=Box.createVerticalBox();
        this.add(b1);
        b1.add(Box.createVerticalStrut(40));
        Button lowButton = this.makeLevelButton("Low Level");
        b1.add(lowButton);
        b1.add(Box.createVerticalStrut(40));
        b1.add(Box.createVerticalGlue());
        Button middleButton = this.makeLevelButton("Middle Level");
        b1.add(middleButton);
        b1.add(Box.createVerticalStrut(40));
        b1.add(Box.createVerticalGlue());
        Button highButton = this.makeLevelButton("High Level");
        b1.add(highButton);
        b1.add(Box.createVerticalStrut(40));
        JPanel initPanel = new JPanel();
        JPanel cards = new JPanel(new CardLayout());
        initPanel.add(b1);
        cards.add(initPanel, "initial");
        CardLayout cl = (CardLayout) (cards.getLayout());
        cl.show(cards, "initial");
        JButton button = new JButton("Start a New Game");
        button.addActionListener(e -> {
            cl.show(cards, "initial");
            this.setSize(500, 400);
        });
        lowButton.addActionListener(e -> {
            this.setSize(500, 500);
            GamePanel gamePanel = new GamePanel(Difficulty.LOW);
            gamePanel.setPreferredSize(new Dimension(500, 400));
            Box b2 = this.makeGameBox(button, gamePanel);
            cards.add(b2, "game");
            cl.show(cards, "game");
        });
        middleButton.addActionListener(e -> {
            this.setSize(800, 800);
            GamePanel gamePanel = new GamePanel(Difficulty.MEDIUM);
            gamePanel.setPreferredSize(new Dimension(800, 700));
            Box b2 = this.makeGameBox(button, gamePanel);
            cards.add(b2, "game");
            cl.show(cards, "game");
        });
        highButton.addActionListener(e -> {
            this.setSize(1500, 800);
            GamePanel gamePanel = new GamePanel(Difficulty.HIGH);
            gamePanel.setPreferredSize(new Dimension(1500, 700));
            Box b2 = this.makeGameBox(button, gamePanel);
            cards.add(b2, "game");
            cl.show(cards, "game");
        });
        this.add(cards);
        this.setVisible(true);
    }

}


class GamePanel extends JPanel{
    private Difficulty difficulty;
    private int rows;
    private int cols;
    private GameButton[][] gameButtons;
    private final int[][] neighbors =
            {{-1, -1}, {0, -1}, {1, -1}, {1, 0}, {1, 1}, {0, 1}, {-1, 1}, {-1, 0}};
    private int mineCount;
    private int allEleCount;
    private int openCount;
    private int allMineCount;
    private long start = System.currentTimeMillis();
    private JLabel mineLabel;

    GamePanel(Difficulty difficulty) {
        this.difficulty = difficulty;
        this.rows = difficulty.getRows();
        this.cols = difficulty.getCols();
        this.mineCount = 0;
        this.openCount = 0;
        this.allEleCount = difficulty.getEleCount();
        this.allMineCount = difficulty.getMineCount();
        this.setLayout(new GridLayout(rows, cols, 0, 0));
        this.generateButtons();
        this.mineLabel = new JLabel("Rest mines: "+allMineCount);
    }

    private boolean verify(int row, int col) {
        return row >= 0
                && row < this.rows
                && col >= 0
                && col < this.cols;
    }

    JLabel getRestMineCount(){
        return mineLabel;
    }

    private void generateButtons(){
        Block[][] blocks = new BlocksGenerator(difficulty).getBlocks();
        gameButtons = new GameButton[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                GameButton blockButton = new GameButton(blocks[i][j]);
                gameButtons[i][j]=blockButton;
                int i1 = i;
                int j1 = j;
                blockButton.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        blockButton.requestFocus();
                        switch (e.getButton()) {
                            case MouseEvent.BUTTON1:
                                if (openCount == 0) {
                                    /*
                                    If the first click hits the mine, the mine block will exchange its position with
                                    another non-mined block.
                                    And related number blocks will change their number of mines in the neighborhood.
                                     */
                                    if (blockButton.getBlock() instanceof MinedBlock) {
                                        OUT:
                                        for (int m = 0; m < rows; m++) {
                                            for (int n = 0; n < cols; n++) {
                                                if (!(blocks[m][n] instanceof MinedBlock)) {
                                                    blockButton.setBlock(blocks[m][n]);
                                                    gameButtons[m][n].setBlock(blocks[i1][j1]);
                                                    for(int[] neighbor:neighbors){
                                                        int newRow1 = i1 + neighbor[0];
                                                        int newCol1 = j1 + neighbor[1];
                                                        int newRow2 = m + neighbor[0];
                                                        int newCol2 = n + neighbor[1];
                                                        if (verify(newRow1, newCol1)) {
                                                            if(blocks[newRow1][newCol1] instanceof NumberBlock){
                                                                NumberBlock block = (NumberBlock) blocks[newRow1][newCol1];
                                                                int oldMine = block.getMineCount();
                                                                block.setMineCount(oldMine-1);
                                                            }
                                                        }
                                                        if (verify(newRow2, newCol2)) {
                                                            if(blocks[newRow2][newCol2] instanceof NumberBlock){
                                                                NumberBlock block = (NumberBlock) blocks[newRow2][newCol2];
                                                                int oldMine = block.getMineCount();
                                                                block.setMineCount(oldMine+1);
                                                            }
                                                        }
                                                    }
                                                    break OUT;
                                                }
                                            }
                                        }
                                    }
                                }
                                open(blockButton, i1, j1);
                                break;
                            case MouseEvent.BUTTON3:
                                blockButton.setFlagStatus();
                                if(blocks[i1][j1].isFlag()){
                                    mineCount++;
                                }else if(!blocks[i1][j1].isOpen()){
                                    mineCount--;
                                }
                                mineLabel.setText("Rest mines: "+(allMineCount-mineCount));

                        }
                        if((mineCount>=allMineCount)&&((openCount+mineCount)>=allEleCount)){
                            gameOver();
                        }
                    }
                });
                this.add(blockButton);
            }
        }
    }

    private void open(GameButton gameButton,int row, int col) {
        if(!gameButton.getBlock().isOpen()) {
            openCount++;
            gameButton.setOpenStatus();
            if(gameButton.getBlock() instanceof MinedBlock) {
                gameOver();
            }else{
                NumberBlock block = (NumberBlock)gameButton.getBlock();
                /*
                If you hit an empty block, the neighboring empty block will also open.
                 */
                if(block.getMineCount()==0) {
                    for (int[] neighbor : neighbors) {
                        int newRow = row + neighbor[0];
                        int newCol = col + neighbor[1];
                        if (verify(newRow, newCol)) {
                            GameButton sButton = gameButtons[newRow][newCol];
                            if (!(sButton.getBlock().isOpen()) && !(sButton.getBlock() instanceof MinedBlock)) {
                                open(sButton, newRow, newCol);
                            }
                        }
                    }
                }
            }
        }
    }

    private void gameOver(){
            for (GameButton[] buttons : gameButtons) {
                for (GameButton b : buttons) {
                    b.setOpenStatus();
                }
            }
            long end = System.currentTimeMillis();
            JFrame overFrame = new JFrame();
            overFrame.setTitle("Game Over!");
            overFrame.setSize(300,200);
            overFrame.setLocation(900, 350);
            overFrame.setLayout(new BorderLayout());
            JLabel jLabel = new JLabel("Your game lifetime is: "+(end-start)/1000+"s!", JLabel.CENTER);
            overFrame.add(jLabel, BorderLayout.CENTER);
            overFrame.setVisible(true);
    }
}

class GameButton extends JButton{
    private Block block;

    GameButton(Block block) throws HeadlessException {
        this.block = block;
        this.setBackground(Color.LIGHT_GRAY);
    }

    Block getBlock() {
        return block;
    }

    void setBlock(Block block) {
        this.block = block;
    }

    void setOpenStatus(){
        if(!block.isFlag()) {
            if (block instanceof MinedBlock) {
                this.setBackground(Color.RED);
            } else {
                this.setBackground(Color.WHITE);
            }
            this.setText(block.open());
        }
    }

    void setFlagStatus(){
        if(!block.isOpen()) {
            if (!block.isFlag()) {
                this.setBackground(Color.BLACK);
            } else {
                this.setBackground(Color.LIGHT_GRAY);
            }
            block.toggleFlag();
        }
    }
}