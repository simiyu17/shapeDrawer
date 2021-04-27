/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shapedrawer.draw;

import java.awt.Graphics;


public class DDALineAlgorithm {

    private Point p1;
    private Point p2;
    private int pixels;

    public DDALineAlgorithm() {
    }

    public DDALineAlgorithm(Point p1, Point p2) {
        this.p1 = p1;
        this.p2 = p2;
    }

    public Point getP2() {
        return p2;
    }

    public void setP1(Point p1) {
        this.p1 = p1;
    }

    public Point getP1() {
        return p1;
    }

    public void setP2(Point p2) {
        this.p2 = p2;
    }

    void LineDraw(Graphics g) {
        DDALineAlgo(g, (this.pixels > 0 ? this.pixels : 1), p1.getX(), p1.getY(), p2.getX(), p2.getY());
    }


    //DDA LINE ALGORITHM 
    void DDALineAlgo(Graphics g, int pixels, int X1, int Y1, int X2, int Y2) {
        Point p1 = new Point(X1, Y1);
        Point p2 = new Point(X2, Y2);

        double dx, dy, steps, x, y, k;
        double xc, yc;
       
        dx = p2.getX() - p1.getX();
        dy = p2.getY() - p1.getY();
        if (Math.abs(dx) > Math.abs(dy)) {
            steps = Math.abs(dx);
        } else {
            steps = Math.abs(dy);
        }
        xc = (dx / steps);
        yc = (dy / steps);
        x = p1.getX();
        y = p1.getY();
        System.out.println("Drawing at x="+(int)x+", y="+(int)y+" and Pixel="+pixels);
        g.fillRect((int)x, (int)y, pixels, pixels);
        for (k = 1; k <= steps; k++) {
            x = x + xc;
            y = y + yc;
            System.out.println("Drawing at x="+(int)x+", y="+(int)y+" and Pixel="+pixels);
            g.fillRect((int) x, (int) y, pixels, pixels);
        }

    }

    /**
     * @return the pixels
     */
    public int getPixels() {
        return pixels == 0 ? 1 : pixels;
    }

    /**
     * @param pixels the pixels to set
     */
    public void setPixels(int pixels) {
        this.pixels = pixels;
    }
}
