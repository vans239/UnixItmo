package ru.itmo.govnokod.geometry.swing;

/*
* PolygonArea.java is used by:
*    App.java.
*    MouseMotionEventDemo.java
*/

import ru.itmo.govnokod.geometry.swing.model.Drawer;

import javax.swing.*;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class PolygonArea extends JLabel implements MouseListener {
    private static final long serialVersionUID = -9037578862570848080L;
    private final Dimension minSize = new Dimension(500, 300);

    private final DataBuilder dataBuilder;
    private final Drawer drawer = new Drawer();

    public PolygonArea(final DataBuilder dataBuilder) {
        setBackground(Color.WHITE);
        setOpaque(true);
        setBorder(BorderFactory.createLineBorder(Color.black));
        this.addMouseListener(this);
        this.dataBuilder = dataBuilder;
    }

    public Dimension getMinimumSize() {
        return minSize;
    }

    public Dimension getPreferredSize() {
        return minSize;
    }

    public void paintComponent(Graphics g) {
        clear(g);
        Graphics2D g2d = (Graphics2D)g;
        dataBuilder.paint(g2d);
    }

    public void paint(Graphics g) {
        clear(g);
        final Graphics2D g2d = (Graphics2D)g;
        g2d.setColor(Color.BLACK);
        dataBuilder.paint(g2d);

        g2d.setColor(Color.RED);
        drawer.paint(g2d);
    }

    private void clear(Graphics g) {
        super.paintComponent(g);
    }
    
    public void addPoint(final ru.itmo.govnokod.geometry.model.Point point){
        dataBuilder.addPoint(point);
    }

    public void mousePressed(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mouseClicked(final MouseEvent e) {
        addPoint(new ru.itmo.govnokod.geometry.model.Point(e.getX(), e.getY()));
        //i don't know java swing))
        this.repaint();
    }

    public DataBuilder getBuilder(){
        return dataBuilder;
    }

    public Drawer getDrawer(){
        return drawer;
    }
}
