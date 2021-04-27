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
import java.awt.Graphics;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;
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
    private JButton chooseColorButton;
    private JLabel colorLabel;

    public Home() throws HeadlessException {
        setTitle("SHAPE DRAWER");
        initLayout();
        setVisible(Boolean.TRUE);

    }

    private void initLayout() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize((screenSize.width - 10), (screenSize.height - 10));
        setLocationRelativeTo(null);
        setJMenuBar(getMenu());
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
        chooseColorButton = new JButton("<html>Select a Color For Drawing<br/>(Select Before Clicking Edit)</html>");
        colorLabel = new JLabel("SELETED COLOR");
        chooseColorButton.addActionListener(a -> {
            Color c = JColorChooser.showDialog(null, "Choose a Color For Drawing", colorLabel.getForeground());
            if (c != null) {
                colorLabel.setForeground(c);
            }
        });

        drawingControlsPanel.add(lineThicknessLabel, "wrap");
        drawingControlsPanel.add(lineThicknesstext, "wrap");

        drawingControlsPanel.add(chooseColorButton, "wrap");
        drawingControlsPanel.add(colorLabel, "wrap");

        return drawingControlsPanel;
    }

    private JMenuBar getMenu() {
        JMenuBar b = new JMenuBar();
        JMenu menu = new JMenu("File");

        JMenuItem saveImage = new JMenuItem("Save Image");
        saveImage.addActionListener((ActionEvent arg0) -> {
            if (line2DrawingPanel != null || circleDrawingPanel != null) {

                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileFilter(new FileNameExtensionFilter("*.png", "png"));
                if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                    File file = fileChooser.getSelectedFile();

                    try {
                        if (!file.getName().endsWith(".png")) {
                            JOptionPane.showMessageDialog(null, "Enter File Extension As .png", "Error", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                        if (line2DrawingPanel != null) {
                            savePanelAsImage(line2DrawingPanel, file);
                            writeToAtextFile("Line Properties", file, line2DrawingPanel.getList().get(line2DrawingPanel.getList().size() - 1).getLineProperties());
                        } else {
                            savePanelAsImage(circleDrawingPanel, file);
                            writeToAtextFile("Circle Properties", file, circleDrawingPanel.getList().get(circleDrawingPanel.getList().size() - 1).getCircleProperties());
                        }

                        JOptionPane.showMessageDialog(null, "Image Saved At " + new File(file.getAbsolutePath()).getAbsolutePath(), "Done", JOptionPane.INFORMATION_MESSAGE);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(null, "Failed To Save Shape!!", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "You Have No Shape To Save !!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "You Have No Shape To Save !!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        menu.add(saveImage);
        b.add(menu);
        return b;
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
                    line2DrawingPanel.editLine(linewidth, colorLabel.getForeground());
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
                    circleDrawingPanel.editCircle(linewidth, colorLabel.getForeground());
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

    public void savePanelAsImage(JPanel panel, File file) {
        BufferedImage image = new BufferedImage(panel.getWidth(), panel.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics g = image.getGraphics();
        panel.paint(g);
        try {
            ImageIO.write((BufferedImage) image, "png", new File(file.getAbsolutePath()));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void writeToAtextFile(String title, File file, Map<String, String> map) throws IOException {
        try ( BufferedWriter writer = new BufferedWriter(new FileWriter(file.getAbsolutePath().replaceAll(".png", ".txt"), true));) {
            writer.append(' ');
            writer.append(title + "\n\n");
            for (Map.Entry<String, String> entry : map.entrySet()) {
                writer.append(entry.getKey() + " : " + entry.getValue() + "\n");
            }
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
