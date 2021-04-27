/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shapedrawer.draw;

import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;
import com.shapedrawer.draw.Point;
import java.awt.Color;
import java.awt.Graphics;

public class LineDrawerPanel extends JPanel implements MouseInputListener {
    
    private List<DDALineAlgorithm> list = new ArrayList<>();
    private DDALineAlgorithm lineObj;
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
    
    
    public void editLine(int pixels) {
        if (list.size() > 0) {
            lineObj = new DDALineAlgorithm();
            lineObj.setPixels(pixels);
            lineObj.setP1(list.get(list.size() - 1).getP1());
            list.remove(list.size() - 1);
            clicks = 1;
        }
    }
    
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.white);
        g.fillRect(0, 20, width, height);
        g.setColor(Color.BLACK);
        DDALineAlgorithm currentline;
        for (int i = 0; i < list.size(); i++) {
            currentline = (DDALineAlgorithm) (list.get(i));
            currentline.LineDraw(g);
        }
    }

// midPoint function for line generation
    static void midPoint(int X1, int Y1,
            int X2, int Y2) {
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
        }
    }

// Driver code
    public static void main(String[] args) {
        int X1 = 2, Y1 = 2, X2 = 8, Y2 = 5;
        midPoint(X1, Y1, X2, Y2);
    }

    @Override
    public void mouseClicked(MouseEvent arg0) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
 
        if (clicks == 0) {
            lineObj = new DDALineAlgorithm();
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
    public void mouseReleased(MouseEvent arg0) {
    }

    @Override
    public void mouseEntered(MouseEvent arg0) {
    }

    @Override
    public void mouseExited(MouseEvent arg0) {
    }

    @Override
    public void mouseDragged(MouseEvent arg0) {
    }

    @Override
    public void mouseMoved(MouseEvent arg0) {
    }
}
