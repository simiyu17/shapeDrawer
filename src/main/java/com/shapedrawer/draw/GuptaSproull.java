/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shapedrawer.draw;

import java.awt.Color;
import java.awt.Graphics;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author simiyu
 */
public class GuptaSproull {
    
     private Point p1;
    private Point p2;
    private int pixels;
    private Color lineColor;
    private boolean isDda;

    public GuptaSproull() {
    }

    public GuptaSproull(Point p1, Point p2) {
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
        g.setColor(lineColor != null ? lineColor : Color.BLACK);
        if(isDda){
            DDALineAlgo(g, (this.pixels > 0 ? this.pixels : 1), p1.getX(), p1.getY(), p2.getX(), p2.getY());
        }else{
            drawLine(g, p1.getX(), p1.getY(), p2.getX(), p2.getY(), Color.BLACK, (this.pixels > 0 ? this.pixels : 1));
        }
        
    }
    
    // Gupta
    public static void drawLine(Graphics gc, int x1, int y1, int x2,
                                int y2, Color color, int width) {
        // If it is just a point
        if(x1 == x2 && y1 == y2) {
            //gc.putPixel(gc, x1, y1, color, width);
            gc.fillRect(x1,y1,1,1);
            return;
        }

        int dx = x2 - x1;
        int dy = y2 - y1;

        int du, dv, u, x, y, ix, iy;

        // By switching to (u,v), we combine all eight octants
        int adx = dx < 0 ? -dx : dx;
        int ady = dy < 0 ? -dy : dy;
        x = x1;
        y = y1;
        if (adx > ady) {
            du = adx;
            dv = ady;
            u = x2;
            ix = dx < 0 ? -1 : 1;
            iy = dy < 0 ? -1 : 1;
        } else {
            du = ady;
            dv = adx;
            u = y2;
            ix = dx < 0 ? -1 : 1;
            iy = dy < 0 ? -1 : 1;
        }

        int uEnd = u + du;
        int d = (2 * dv) - du; // Initial value as in Bresenham's
        int incrS = 2 * dv; // Δd for straight increments
        int incrD = 2 * (dv - du); // Δd for diagonal increments
        int twovdu = 0; // Numerator of distance
        double invD = 1.0 / (2.0 * Math.sqrt(du * du + dv * dv)); // Precomputed inverse denominator
        double invD2du = 2.0 * (du * invD); // Precomputed constant

        if (adx > ady) {
            do {
                intensifyPixel(gc, x, y, twovdu * invD, color);
                intensifyPixel(gc, x, y + iy, invD2du - twovdu * invD, color);
                intensifyPixel(gc, x, y - iy, invD2du + twovdu * invD, color);

                if (d < 0) {
                    // Choose straight
                    twovdu = d + du;
                    d += incrS;

                } else {
                    // Choose diagonal
                    twovdu = d - du;
                    d += incrD;
                    y += iy;
                }
                u++;
                x += ix;
            } while (u < uEnd);
        } else {
            do {
                intensifyPixel(gc, x, y, twovdu * invD, color);
                intensifyPixel(gc, x, y + iy, invD2du - twovdu * invD, color);
                intensifyPixel(gc, x, y - iy, invD2du + twovdu * invD, color);

                if (d < 0) {
                    // Choose straight
                    twovdu = d + du;
                    d += incrS;

                } else {
                    // Choose diagonal
                    twovdu = d - du;
                    d += incrD;
                    x += ix;
                }
                u++;
                y += iy;
            } while (u < uEnd);
        }
    }

    /**
     * Draw the pixel with an opacity determined by its distance.
     */
    private static void intensifyPixel(Graphics gc, int x, int y,
                                       double distance, Color c) {
        // Normalized inverse of distance squared
        double alpha = 1 - Math.pow((distance * 2 / 3), 2);
        Color color = new Color(c.getRed(), c.getGreen(), c.getBlue(), (float) alpha);
        //DrawingUtils.putPixel(gc, x, y, color, 1);
        gc.fillRect(x,y,1,1);
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

    /**
     * @return the lineColor
     */
    public Color getLineColor() {
        return lineColor;
    }

    /**
     * @param lineColor the lineColor to set
     */
    public void setLineColor(Color lineColor) {
        this.lineColor = lineColor;
    }
    
    public Map<String, String> getLineProperties() {
        return new HashMap<String, String>() {
            {
                put("Starting Point", "(X:" + getP1().getX() + ", Y=" + getP1().getY() + ")");
                put("End Point", "(X:" + getP2().getX() + ", Y=" + getP2().getY() + ")");
                put("Pixel/Thickiness", String.valueOf(pixels>0?pixels:1));
                //put("Color", getLineColor().toString());
            }
        };
    }

    /**
     * @return the isDda
     */
    public boolean isIsDda() {
        return isDda;
    }

    /**
     * @param isDda the isDda to set
     */
    public void setIsDda(boolean isDda) {
        this.isDda = isDda;
    }
    
}
