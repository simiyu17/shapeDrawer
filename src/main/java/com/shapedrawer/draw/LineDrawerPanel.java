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
public class LineDrawerPanel extends JPanel implements MouseInputListener {
 
    private List<Line> list = new ArrayList<>();
    private Line lineObj;
    private int clicks = 0;
    private int width, height;
 
    public LineDrawerPanel(int width, int height) {
 
        setBorder(BorderFactory.createTitledBorder("Drawing Line"));
        addMouseListener(this);
        setPreferredSize(new Dimension(width, height));
        setMaximumSize(new Dimension(width, height));
        this.width = width;
        this.height = height-100;
    }
    
     public void editLine(float lineThickness) {
        if (list.size() > 0) {
            lineObj = new Line();
            lineObj.setLineThickness(lineThickness);
            lineObj.setP1(list.get(list.size() - 1).getP1());
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
        Line currentline;
        for (int i = 0; i < list.size(); i++) {
            currentline = (Line) (list.get(i));
            currentline.LineDraw(g2);
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
            lineObj = new Line();
            lineObj.setLineThickness(0.0f);
            lineObj.setP1(new Point(x, y));
            clicks++;
        } else {
            lineObj.setP2(new Point(x, y));
            list.add(lineObj);
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
