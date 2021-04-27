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

   /* 
    // MidPoint Algorithm
    void Draw2Points(Graphics g, int pixels, int X1, int Y1, int X2, int Y2) {
        // calculate dx & dy
        int dx = X2 - X1;
        int dy = Y2 - Y1;

        // initial value of decision
        // parameter d
        int d = dy - (dx / 2);
        int x = X1, y = Y1;

        // Plot initial given point
        // putpixel(x,y) can be used to
        // print pixel of line in graphics
        System.out.print(x + "," + y + "\n");
        g.fillRect(x + 7, y + 7, 8, 8);

        // iterate through value of X
        while (x < X2) {
            x++;

            // E or East is chosen
            if (d < 0) {
                d = d + dy;
            } // NE or North East is chosen
            else {
                d += (dy - dx);
                y++;
            }

            // Plot intermediate points
            // putpixel(x,y) is used to print
            // pixel of line in graphics
            System.out.print(x + "," + y + "\n");
            g.fillRect(x + 7, y + 7, 8, 8);
        }
    }

    // MidPoint Algorithm
    void Draw2Points2(Graphics g, int pixels, int X1, int Y1, int X2, int Y2) {
        Point p1 = new Point(X1, Y1);
        Point p2 = new Point(X2, Y2);

        int x, y, xEnd;
        int a, b, p;

        if (p1.getX() > p2.getX()) {
            x = p2.getX();
            y = p2.getY();
            xEnd = p1.getX();
        } else {
            x = p1.getX();
            y = p1.getY();
            xEnd = p2.getX();
        }
        a = p1.getY() - p2.getY();
        b = p2.getX() - p1.getX();
        p = 2 * a + b;

        //SetPixel(hdc, x,y,color);
        g.fillRect(x + 7, y + 7, 8, 8);

        while (x < xEnd) {
            x++;
            if (p < 0) {
                y++;
                p += 2 * (a + b);
            } else {
                p += 2 * a;
            }
            //SetPixel(hdc, x,y,color);
            g.fillRect(x + 7, y + 7, 8, 8);
        }

    }
    */

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
