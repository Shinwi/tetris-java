package org.example.mino;

import org.example.Block;
import org.example.PlayManager;

import java.awt.*;

public class TetriMino {
    public Block b[] = new Block[4];
    public Block tempB[] = new Block[4];
    int autoDropCounter = 0;
    int direction = 1; // 1: up, 2:rotate to the right, 3:downwards, 4:to the left
    boolean leftCollision, rightCollision, bottomCollision;
    public boolean active = true;

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

    public void updateXY(int direction) {
        checkRotationCollision();
        if (!leftCollision && !rightCollision && !bottomCollision) {
            this.direction = direction;
            b[0].x = tempB[0].x;
            b[0].y = tempB[0].y;
            b[1].x = tempB[1].x;
            b[1].y = tempB[1].y;
            b[2].x = tempB[2].x;
            b[2].y = tempB[2].y;
            b[3].x = tempB[3].x;
            b[3].y = tempB[3].y;
        }
    }

    public void checkMovementCollision() {
        leftCollision = false;
        rightCollision = false;
        bottomCollision = false;

        // Check collision with other blocks
        checkStaticBlockCollision();

        for (int i = 0; i < b.length ; i++) {
            // left wall
            if (b[i].x == PlayManager.left_x) leftCollision = true;
            // right wall
            if (b[i].x + Block.SIZE == PlayManager.right_x) rightCollision = true;
            // bottom wall
            if (b[i].y + Block.SIZE == PlayManager.bottom_y) bottomCollision = true;
        }
    }

    public void checkRotationCollision() {
        leftCollision = false;
        rightCollision = false;
        bottomCollision = false;

        // Check collision with other blocks
        checkStaticBlockCollision();

        for (int i = 0; i < b.length ; i++) {
            if (tempB[i].x < PlayManager.left_x) leftCollision = true;
            if (tempB[i].x + Block.SIZE > PlayManager.right_x) rightCollision = true;
            if (tempB[i].y + Block.SIZE > PlayManager.bottom_y) bottomCollision = true;
        }
    }

    public void checkStaticBlockCollision() {
        for (int i = 0; i < PlayManager.staticBlocks.size(); i++) {
            int targetX = PlayManager.staticBlocks.get(i).x;
            int targetY = PlayManager.staticBlocks.get(i).y;

            for (int j = 0; j < b.length ; j++) {
                // bottom collision
                if (b[j].y + Block.SIZE ==  targetY && b[j].x == targetX) bottomCollision = true;
                // left collision
                if (b[j].y == targetY && b[j].x - Block.SIZE == targetX) leftCollision = true;
                // right collision
                if (b[j].y == targetY && b[j].x + Block.SIZE == targetX) rightCollision = true;
            }
        }
    }

    public void getDirection1() {}

    public void getDirection2() {}

    public void getDirection3() {}

    public void getDirection4() {}

    public void update() {
        checkMovementCollision();
        if (bottomCollision) {
            active = false;
        } else {
            moveMino();
            autoDropCounter++;
            if (autoDropCounter == PlayManager.dropInterval) {
                // move downwards by one block
                b[0].y += Block.SIZE;
                b[1].y += Block.SIZE;
                b[2].y += Block.SIZE;
                b[3].y += Block.SIZE;

                autoDropCounter = 0;
            }
        }
    }


    public void moveMino() {
        if (KeyHandler.downPressed) {
            if (!bottomCollision) moveDown();
            KeyHandler.downPressed = false;
        } else if (KeyHandler.upPressed) {
            changeMinoDirection();
            KeyHandler.upPressed = false;
        } else if (KeyHandler.leftPressed) {
            if (!leftCollision) moveLeft();
            KeyHandler.leftPressed = false;
        } else if (KeyHandler.rightPressed) {
            if (!rightCollision) moveRight();
            KeyHandler.rightPressed = false;
        }
    }

    public void changeMinoDirection() {
        switch (direction) {
            case 1: getDirection2();break;
            case 2: getDirection3();break;
            case 3: getDirection4();break;
            case 4: getDirection1();break;
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
