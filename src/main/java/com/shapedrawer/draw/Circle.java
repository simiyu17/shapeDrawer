/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shapedrawer.draw;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;

/**
 *
 * @author simiyu
 */
public class Circle {

    private Point center;
    private Point p2;
    private float radius;
    private float lineThickness;

    public Circle() {
    }

    public Circle(Point center, Point p2) {
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

    void CircleMidPoint(Graphics2D g) {
        int xc = center.getX();
        int yc = center.getY();
        int R = (int) radius;

        int x = 0, y = R;
        int d = 1 - R;
        int c1 = 3, c2 = 5 - 2 * R;
        Draw8Points(g, this.lineThickness, xc, yc, x, y);
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
            Draw8Points(g, this.lineThickness, xc, yc, x, y);
        }
    }

    void Draw8Points(Graphics2D g, float lineThickiness, int xc, int yc, int a, int b) {
        if(lineThickiness > 0.0f){
            g.setStroke(new BasicStroke(lineThickiness));
        }
        g.drawOval(xc + a, yc + b, 1, 1);
        g.drawOval(xc - a, yc + b, 1, 1);
        g.drawOval(xc - a, yc - b, 1, 1);
        g.drawOval(xc + a, yc - b, 1, 1);

        g.drawOval(xc + b, yc + a, 1, 1);
        g.drawOval(xc - b, yc + a, 1, 1);
        g.drawOval(xc - b, yc - a, 1, 1);
        g.drawOval(xc + b, yc - a, 1, 1);

    }

    /**
     * @return the lineThickness
     */
    public float getLineThickness() {
        return lineThickness;
    }

    /**
     * @param lineThickness the lineThickness to set
     */
    public void setLineThickness(float lineThickness) {
        this.lineThickness = lineThickness;
    }
}
