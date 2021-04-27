/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shapedrawer.draw;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;

public class CircleDrawerPanel extends JPanel implements MouseInputListener {

    private List<MidPointCircleAlgorithm> list = new ArrayList();
    private MidPointCircleAlgorithm circleObj;
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

    public void editCircle(int lineThickness, Color lineColor) {
        if (getList().size() > 0) {
            circleObj = new MidPointCircleAlgorithm();
            circleObj.setPixels(lineThickness);
            circleObj.setCircleColor(lineColor);
            circleObj.setCenter(getList().get(getList().size() - 1).getCenter());
            getList().remove(getList().size() - 1);
            clicks = 1;
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.white);
        g.fillRect(0, 20, width, height);
       // g.setColor(this.circleColor);
        MidPointCircleAlgorithm currentCircle;
        for (int i = 0; i < getList().size(); i++) {
            currentCircle = (MidPointCircleAlgorithm) (getList().get(i));
            currentCircle.CircleMidPoint(g);
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
            circleObj = new MidPointCircleAlgorithm();
            circleObj.setCenter(new Point(x, y));

            clicks++;
        } else {
            circleObj.setP2(new Point(x, y));
            circleObj.calRadius();
            getList().add(circleObj);
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

    /**
     * @return the list
     */
    public List<MidPointCircleAlgorithm> getList() {
        return list;
    }

    /**
     * @param list the list to set
     */
    public void setList(List<MidPointCircleAlgorithm> list) {
        this.list = list;
    }
}
