package org.example.mino;

import org.example.Block;
import org.example.PlayManager;

import javax.swing.plaf.basic.BasicTreeUI;
import java.awt.*;
import java.security.Key;

public class TetriMino {
    public Block b[] = new Block[4];
    public Block tempB[] = new Block[4];
    int autoDropCounter = 0;

    public void create(Color c) {
        b[0] = new Block(c);
        b[1] = new Block(c);
        b[2] = new Block(c);
        b[3] = new Block(c);

        tempB[0] = new Block(c);
        tempB[1] = new Block(c);
        tempB[2] = new Block(c);
        tempB[3] = new Block(c);
    }

    public void setXY(int x, int y) {}
    public void updateXY(int direction){}
    public void update() {
        // Move the mino
        moveMino();

        autoDropCounter++; // increases every frame
        if (autoDropCounter == PlayManager.dropInterval) {
            // move downwards by one block
            b[0].y += Block.SIZE;
            b[1].y += Block.SIZE;
            b[2].y += Block.SIZE;
            b[3].y += Block.SIZE;

            autoDropCounter = 0;
        }
    }

    public void moveMino() {
        if (KeyHandler.downPressed) {
            moveDown();
            autoDropCounter = 0;
            KeyHandler.downPressed = false;
        } else if (KeyHandler.upPressed) {
            moveUp();
            autoDropCounter = 0;
            KeyHandler.upPressed = false;
        } else if (KeyHandler.leftPressed) {
            moveLeft();
            autoDropCounter = 0;
            KeyHandler.leftPressed = false;
        } else if (KeyHandler.rightPressed) {
            moveRight();
            autoDropCounter = 0;
            KeyHandler.rightPressed = false;
        }
    }

    public void moveDown() {
        b[0].y += Block.SIZE;
        b[1].y += Block.SIZE;
        b[2].y += Block.SIZE;
        b[3].y += Block.SIZE;
    }
    public void moveUp() {
        b[0].y -= Block.SIZE;
        b[1].y -= Block.SIZE;
        b[2].y -= Block.SIZE;
        b[3].y -= Block.SIZE;
    }
    public void moveRight() {
        b[0].x += Block.SIZE;
        b[1].x += Block.SIZE;
        b[2].x += Block.SIZE;
        b[3].x += Block.SIZE;
    }
    public void moveLeft() {
        b[0].x -= Block.SIZE;
        b[1].x -= Block.SIZE;
        b[2].x -= Block.SIZE;
        b[3].x -= Block.SIZE;
    }

    public void draw(Graphics2D g2) {
        int margin = 1;
        g2.setColor(b[0].c);
        g2.fillRect(b[0].x + margin, b[0].y + margin, Block.SIZE - (margin*2), Block.SIZE - (margin*2));
        g2.fillRect(b[1].x + margin, b[1].y + margin, Block.SIZE - (margin*2), Block.SIZE - (margin*2));
        g2.fillRect(b[2].x + margin, b[2].y + margin, Block.SIZE - (margin*2), Block.SIZE - (margin*2));
        g2.fillRect(b[3].x + margin, b[3].y + margin, Block.SIZE - (margin*2), Block.SIZE - (margin*2));
    }
}
