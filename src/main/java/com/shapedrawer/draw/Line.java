/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shapedrawer.draw;

import java.awt.BasicStroke;
import java.awt.Graphics2D;

/**
 *
 * @author simiyu
 */
public class Line {

    private Point p1;
    private Point p2;
    private float lineThickness;

    public Line() {
    }

    public Line(Point p1, Point p2) {
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


     void LineDraw(Graphics2D g) {
        Draw2Points(g, this.lineThickness, p1.getX(), p1.getY(), p2.getX(), p2.getY());
    }

    void Draw2Points(Graphics2D g, float lineThickiness, int x1, int y1, int x2, int y2) {
        if(lineThickiness > 0.0f){
            g.setStroke(new BasicStroke(lineThickiness));
        }
        g.drawLine(x1, y1, x2, y2);
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
