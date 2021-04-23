/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shapedrawer.draw;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;

/**
 *
 * @author simiyu
 */
public class CircleDrawerPanel extends JPanel implements MouseInputListener {

    //private Point p1 = new Point();
    //private Point p2 = new Point();
    private List<Circle> list = new ArrayList();
    private Circle circleObj;
    private int clicks = 0;
    private int width, height;

    public CircleDrawerPanel(int width, int height) {

        setBorder(BorderFactory.createTitledBorder("Drawing Circle"));
        addMouseListener(this);
        setPreferredSize(new Dimension(width, height));
        setMaximumSize(new Dimension(width, height));
        this.width = width;
        this.height = height - 100;

    }

    public void editCircle(float lineThickness) {
        if (list.size() > 0) {
            circleObj = new Circle();
            circleObj.setLineThickness(lineThickness);
            circleObj.setCenter(list.get(list.size() - 1).getCenter());
            list.remove(list.size() - 1);
            clicks = 1;
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.white);
        g2.fillRect(0, 20, width, height);
        g2.setColor(Color.BLACK);
        Circle currentCircle;
        for (int i = 0; i < list.size(); i++) {
            currentCircle = (Circle) (list.get(i));
            currentCircle.CircleMidPoint(g2);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();

        if (clicks == 0) {
            circleObj = new Circle();
            circleObj.setCenter(new Point(x, y));

            clicks++;
        } else {
            circleObj.setP2(new Point(x, y));
            circleObj.calRadius();
            list.add(circleObj);
            clicks = 0;
        }
        repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }
}
