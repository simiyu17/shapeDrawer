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

public class MidPointCircleAlgorithm {

    private Point center;
    private Point p2;
    private float radius;
    private int pixels;
    private Color circleColor;

    public MidPointCircleAlgorithm() {
    }

    public MidPointCircleAlgorithm(Point center, Point p2) {
        this.center = center;
        this.p2 = p2;
        radius = (float) Math.sqrt((double) ((center.getX()
                - p2.getX()) * (center.getX() - p2.getX())) + ((center.getY()
                - p2.getY()) * (center.getY() - p2.getY())));
    }

    public Point getCenter() {
        return center;
    }

    public Point getP2() {
        return p2;
    }

    public void setCenter(Point p1) {
        this.center = p1;
    }

    public void setP2(Point p2) {
        this.p2 = p2;
    }

    public void calRadius() {
        radius = (float) Math.round(Math.sqrt((double) ((center.getX()
                - p2.getX()) * (center.getX() - p2.getX())) + ((center.getY()
                - p2.getY()) * (center.getY() - p2.getY()))));
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }

    public float getRadius() {
        return radius;
    }

    //MidPoint MidPointCircleAlgorithm Algorithm
    void CircleMidPoint(Graphics g) {
        g.setColor(circleColor != null ? circleColor : Color.BLACK);
        int xc = center.getX();
        int yc = center.getY();
        int R = (int) radius;

        int x = 0, y = R;
        int d = 1 - R;
        int c1 = 3, c2 = 5 - 2 * R;
        int circlePixels = this.pixels > 0 ? this.pixels : 1;
        System.out.println("Starting At Centre ==(" + xc + "," + yc + "), Starting Point ==(" + x + "," + y + ") And Pixels =" + circlePixels);
        Draw8Points(g, circlePixels, xc, yc, x, y);
        while (x < y) {
            if (d < 0) {
                d += c1;
                c2 += 2;
            } else {
                d += c2;
                c2 += 4;
                y--;
            }
            c1 += 2;
            x++;
            System.out.println("Centre ==(" + xc + "," + yc + "), Drawing At  Point ==(" + x + "," + y + ") And Pixels =" + circlePixels);
            Draw8Points(g, circlePixels, xc, yc, x, y);
        }
    }

    void Draw8Points(Graphics g, int lineThickiness, int xc, int yc, int a, int b) {
        g.fillRect(xc + a, yc + b, lineThickiness, lineThickiness);
        g.fillRect(xc - a, yc + b, lineThickiness, lineThickiness);
        g.fillRect(xc - a, yc - b, lineThickiness, lineThickiness);
        g.fillRect(xc + a, yc - b, lineThickiness, lineThickiness);

        g.fillRect(xc + b, yc + a, lineThickiness, lineThickiness);
        g.fillRect(xc - b, yc + a, lineThickiness, lineThickiness);
        g.fillRect(xc - b, yc - a, lineThickiness, lineThickiness);
        g.fillRect(xc + b, yc - a, lineThickiness, lineThickiness);

    }

    /**
     * @return the pixels
     */
    public int getPixels() {
        return pixels;
    }

    /**
     * @param pixels the pixels to set
     */
    public void setPixels(int pixels) {
        this.pixels = pixels;
    }

    /**
     * @return the circleColor
     */
    public Color getCircleColor() {
        return circleColor;
    }

    /**
     * @param circleColor the circleColor to set
     */
    public void setCircleColor(Color circleColor) {
        this.circleColor = circleColor;
    }

    public Map<String, String> getCircleProperties() {
        Map<String, String> props = new HashMap<String, String>() {
            {
                put("Circle Center", "(X:" + center.getX() + ", Y=" + center.getY() + ")");
                put("Radius", String.valueOf(radius));
                put("Pixel/Thickiness", String.valueOf(pixels>0?pixels:1));
                put("Color", (getCircleColor() != null ? getCircleColor() : Color.BLACK).toString().replace("java.awt.Color", ""));
            }
        };
        
        return props;
    }
}
