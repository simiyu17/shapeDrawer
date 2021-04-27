/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shapedrawer;

import com.shapedrawer.draw.CircleDrawerPanel;
import com.shapedrawer.draw.LineDrawerPanel;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import net.miginfocom.swing.MigLayout;

public class Home extends JFrame {

    // Filter Menu Items
    private JLabel drawLine, editLine, clearLines;
    private JLabel drawCircle, editCircle, clearCircles;

    // Main Control Panels
    private JPanel mainPanel;
    private CircleDrawerPanel circleDrawingPanel;
    private LineDrawerPanel lineDrawingPanel;
    private LineDrawerPanel line2DrawingPanel;

    // Panels inside Controls panel
    private JPanel drawingControlsPanel;

    // Panel dimesions
    private int drawpanelWidth, drawpanelHeight;

    //Thickiness Control
    private JLabel lineThicknessLabel;
    private JTextField lineThicknesstext;

    public Home() throws HeadlessException {
        setTitle("SHAPE DRAWER");
        initLayout();
        setVisible(Boolean.TRUE);

    }

    private void initLayout() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize((screenSize.width - 10), (screenSize.height - 10));
        setLocationRelativeTo(null);
        getContentPane().add(getMainPanel(screenSize.width, screenSize.height));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private JPanel getMainPanel(int screenWidth, int screenHeight) {
        JPanel panel = new JPanel();
        panel.setLayout(new MigLayout());
        panel.add(getMenuPanel(screenWidth, screenHeight));
        panel.add(getMainContentPanel(screenWidth, screenHeight));
        panel.add(getControlContentPanel(screenWidth, screenHeight));
        panel.setBorder(BorderFactory.createEmptyBorder());

        return panel;
    }

    private JPanel getMainContentPanel(int screenWidth, int screenHeight) {
        //Panel dimensions
        int width = ((screenWidth * 7) / 8) - 340;
        int height = screenHeight;

        // Set Drawing Panel dimensions
        this.drawpanelWidth = width - 50;
        this.drawpanelHeight = height;

        mainPanel = new JPanel(new MigLayout());
        mainPanel.setBorder(BorderFactory.createTitledBorder(""));
        mainPanel.setPreferredSize(new Dimension(width, height));
        mainPanel.setMaximumSize(new Dimension(width, height));

        return mainPanel;
    }

    // Image Loader Panel
    private JPanel getControlContentPanel(int width, int height) {
        drawingControlsPanel = new JPanel(new MigLayout());
        drawingControlsPanel.setPreferredSize(new Dimension(width / 7, height));
        drawingControlsPanel.setMaximumSize(new Dimension(width / 7, height));
        drawingControlsPanel.setBorder(BorderFactory.createTitledBorder("Adjustment Controls"));

        //lineThicknessLabel = new JLabel("Enter Line Pixel \n(Enter Before Clicking Edit)");
        lineThicknessLabel = new JLabel("<html>Enter Drawing Line Pixel<br/>(Enter Before Clicking Edit)</html>", SwingConstants.CENTER);
        lineThicknesstext = new JTextField(18);
        drawingControlsPanel.add(lineThicknessLabel, "wrap");
        drawingControlsPanel.add(lineThicknesstext, "wrap");

        return drawingControlsPanel;
    }

    // Menu Panel
    private JPanel getMenuPanel(int screenWidth, int screenHeight) {
        // Panel Dimensions
        int width = screenWidth / 8;
        int height = screenHeight;

        JPanel panel = new JPanel(new MigLayout());

        //Lines Menu Panel
        JPanel linesMenuPanel = new JPanel(new MigLayout());
        linesMenuPanel.setPreferredSize(new Dimension(width, height / 5));
        linesMenuPanel.setMaximumSize(new Dimension(width, height / 5));
        linesMenuPanel.setBorder(BorderFactory.createTitledBorder("Line Drawing"));

        drawLine = new JLabel("Draw Line");
        drawLine.setForeground(Color.BLUE.darker());
        drawLine.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        drawLine.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {

                // lineDrawingPanel = new LineDrawerPanel(drawpanelWidth, drawpanelHeight);
                line2DrawingPanel = new LineDrawerPanel(drawpanelWidth, drawpanelHeight);
                mainPanel.removeAll();
                mainPanel.add(line2DrawingPanel);
                mainPanel.revalidate();
                mainPanel.repaint();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                // the mouse has entered the label
            }

            @Override
            public void mouseExited(MouseEvent e) {
                // the mouse has exited the label
            }
        });
        editLine = new JLabel("Edit Line");
        editLine.setForeground(Color.BLUE.darker());
        editLine.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        editLine.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                int linewidth = 1;
                try {
                    linewidth = Integer.parseInt(lineThicknesstext.getText());
                } catch (Exception ex) {
                }
                if (line2DrawingPanel != null) {
                    line2DrawingPanel.editLine(linewidth);
                } else {
                    JOptionPane.showMessageDialog(null, "There\'s no line to edit !!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                // the mouse has entered the label
            }

            @Override
            public void mouseExited(MouseEvent e) {
                // the mouse has exited the label
            }
        });

        clearLines = new JLabel("Delete Lines");
        clearLines.setForeground(Color.BLUE.darker());
        clearLines.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        clearLines.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {

                lineDrawingPanel = new LineDrawerPanel(drawpanelWidth, drawpanelHeight);
                mainPanel.removeAll();
                mainPanel.add(lineDrawingPanel);
                mainPanel.revalidate();
                mainPanel.repaint();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                // the mouse has entered the label
            }

            @Override
            public void mouseExited(MouseEvent e) {
                // the mouse has exited the label
            }
        });

        linesMenuPanel.add(drawLine, "wrap");
        linesMenuPanel.add(editLine, "wrap");
        linesMenuPanel.add(clearLines, "wrap");

        //Circle Menu Panel
        JPanel circleMenuPanel = new JPanel(new MigLayout());
        circleMenuPanel.setPreferredSize(new Dimension(width, height / 5));
        circleMenuPanel.setMaximumSize(new Dimension(width, height / 5));
        circleMenuPanel.setBorder(BorderFactory.createTitledBorder("Circle Drawing"));

        drawCircle = new JLabel("Draw Circle");
        drawCircle.setForeground(Color.BLUE.darker());
        drawCircle.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        drawCircle.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                circleDrawingPanel = new CircleDrawerPanel(drawpanelWidth, drawpanelHeight);
                mainPanel.removeAll();
                mainPanel.add(circleDrawingPanel);
                mainPanel.revalidate();
                mainPanel.repaint();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                // the mouse has entered the label
            }

            @Override
            public void mouseExited(MouseEvent e) {
                // the mouse has exited the label
            }
        });
        editCircle = new JLabel("Edit Circle");
        editCircle.setForeground(Color.BLUE.darker());
        editCircle.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        editCircle.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                int linewidth = 0;
                try {
                    linewidth = Integer.parseInt(lineThicknesstext.getText());
                } catch (Exception ex) {
                }
                if (circleDrawingPanel != null) {
                    circleDrawingPanel.editCircle(linewidth);
                } else {
                    JOptionPane.showMessageDialog(null, "There\'s no cirle to edit !!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                // the mouse has entered the label
            }

            @Override
            public void mouseExited(MouseEvent e) {
                // the mouse has exited the label
            }
        });

        clearCircles = new JLabel("Delete Circles");
        clearCircles.setForeground(Color.BLUE.darker());
        clearCircles.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        clearCircles.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                circleDrawingPanel = new CircleDrawerPanel(drawpanelWidth, drawpanelHeight);
                mainPanel.removeAll();
                mainPanel.add(circleDrawingPanel);
                mainPanel.revalidate();
                mainPanel.repaint();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                // the mouse has entered the label
            }

            @Override
            public void mouseExited(MouseEvent e) {
                // the mouse has exited the label
            }
        });

        circleMenuPanel.add(drawCircle, "wrap");
        circleMenuPanel.add(editCircle, "wrap");
        circleMenuPanel.add(clearCircles, "wrap");

        panel.setPreferredSize(new Dimension(width, height));
        panel.setMaximumSize(new Dimension(width, height));
        panel.setBorder(BorderFactory.createTitledBorder("Menu"));

        panel.add(linesMenuPanel, "wrap");
        panel.add(circleMenuPanel, "wrap");

        return panel;
    }

}
