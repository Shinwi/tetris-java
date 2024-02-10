package org.example;

import java.awt.Rectangle;
import java.awt.Color;
import java.awt.Graphics2D;

public class Block extends Rectangle {
    public int x,y;
    public static final int SIZE = 30; // block will be 30x30 pixels
    public Color c;

    public Block(Color c) {
        this.c = c;
    }

    public void draw(Graphics2D g2) {
        int margin = 1;
        g2.setColor(c);
        g2.fillRect(x + margin,y + margin,SIZE - (margin*2),SIZE - (margin*2));
    }


}
