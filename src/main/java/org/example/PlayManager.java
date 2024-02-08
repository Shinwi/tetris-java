package org.example;

import org.example.mino.*;

import java.awt.*;
import java.util.Random;

public class PlayManager {
    // Main play area
    final int WIDTH = 360;
    final int HEIGHT = 600;
    public static int left_x;
    public static int right_x;
    public static int top_y;
    public static int bottom_y;

    // Mino
    TetriMino currentMino;
    final int MINO_START_X;
    final int MINO_START_Y;

    public static int dropInterval = 60; // tetris mino drops every 60 frames = 1 second

    public Random random = new Random();

    public PlayManager() {
        left_x = 100; // (GamePanel.WIDTH/2) - (WIDTH/2);
        right_x = left_x + WIDTH;
        top_y = 50;
        bottom_y = top_y + HEIGHT;

        MINO_START_X = left_x + (WIDTH/2) - Block.SIZE;
        MINO_START_Y = top_y + Block.SIZE;

        // TODO: randomize the choice of the mino
        currentMino = generateRandomMino();
        currentMino.setXY(MINO_START_X, MINO_START_Y);
    }

    public TetriMino generateRandomMino() {
        int choice = new Random().nextInt(4);
        TetriMino mino = null;
        System.out.println("choice : " + choice);
        // TODO: avoid generating the same mino 3 times in a row
        switch (choice) {
            case 0: mino = new Mino_L1();break; //  blue
            case 1: mino = new Mino_L2();break; // yello
            case 2: mino = new Mino_T();break;
            case 3: mino = new Mino_cube();break;
        }
        return mino;
    }

    public void update() {
        currentMino.update();
    }
    public void draw(Graphics2D g2) {
        g2.setColor(Color.white);
        g2.setStroke(new BasicStroke(4f));
        g2.drawRect(left_x - 4, top_y - 4, WIDTH + 8, HEIGHT + 8 );

        // Draw waiting room
        int x = right_x + 100;
        int y = bottom_y - 200;
        g2.drawRect(x,y,200,200);
        g2.setFont(new Font("Arial", Font.PLAIN, 30));
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_DEFAULT);
        g2.drawString("Next", x+60, y+60);

        // Draw current mino
        if (currentMino != null) {
            currentMino.draw(g2);
        }

        // Draw pause
        g2.setColor(Color.red);
        g2.setFont(g2.getFont().deriveFont(50f));
        if (KeyHandler.pausePressed) {
            x = left_x + 70;
            y = top_y + 100;
            g2.drawString("Paused", x, y);
        }
    }
}
