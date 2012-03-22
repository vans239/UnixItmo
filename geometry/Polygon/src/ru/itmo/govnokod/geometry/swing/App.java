package ru.itmo.govnokod.geometry.swing;

import ru.itmo.govnokod.geometry.algorithm.Hull;
import ru.itmo.govnokod.geometry.model.Polygon;
import ru.itmo.govnokod.geometry.algorithm.QuickHull;
import ru.itmo.govnokod.geometry.io.polygon.InputStreamPolygon;
import ru.itmo.govnokod.geometry.model.Point;
import ru.itmo.govnokod.geometry.swing.model.PointsBuilder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class App extends JPanel {
    private static final long serialVersionUID = -7968534402264114962L;
    private static final String title = "App";

    private final PolygonArea polygonArea;
    private final Controller controller = new Controller();
    private final JFileChooser fileChooser;
    private final JTextArea log;

    public App() {
        super(new GridLayout(0, 1));

        final JPanel panel = new JPanel(new BorderLayout());
        {
            final JPanel container = new JPanel(new GridLayout(0 , 1));
            {
                final JButton buttonStart = new JButton("Start");
                container.add(buttonStart);
                buttonStart.addActionListener(new StartActionListener());
            }
            {
                final JButton buttonDrop = new JButton("Drop");
                container.add(buttonDrop);
                buttonDrop.addActionListener(new DropActionListener());
            }
            {
                final JButton buttonLoad = new JButton("Load");
                container.add(buttonLoad);
                buttonLoad.addActionListener(new LoadActionListener());
            }
            panel.add(container, BorderLayout.LINE_START);
        }
        {
            polygonArea = new PolygonArea(controller.getDataBuilder());
            panel.add(polygonArea, BorderLayout.LINE_END);
        }
        add(panel);
        {
            log = new JTextArea(5, 20);
            log.setMargin(new Insets(5, 5, 5, 5));
            log.setEditable(false);
            final JScrollPane logScrollPane = new JScrollPane(log);
            add(logScrollPane, BorderLayout.CENTER);
        }
        fileChooser = new JFileChooser();
//        setPreferredSize(new Dimension(450, 450));
//        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
    }

    private void append(final String string) {
        log.append(string);
        log.setCaretPosition(log.getDocument().getLength());
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        } catch (UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        } catch (IllegalAccessException ex) {
            ex.printStackTrace();
        } catch (InstantiationException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }

    private static void createAndShowGUI() {
        final JFrame frame = new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        final JComponent newContentPane = new App();
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);

        frame.pack();
        frame.setVisible(true);
    }

    private class StartActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            controller.run();
            App.this.polygonArea.repaint();
        }
    }

    private class DropActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            controller.drop();
            App.this.polygonArea.repaint();
        }
    }

    private class LoadActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            final int returnVal = fileChooser.showOpenDialog(App.this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                final File file = fileChooser.getSelectedFile();
                controller.load(file);
            }
            App.this.polygonArea.repaint();
        }
    }

    private class Controller {
        private final PointsBuilder pointsBuilder;

        public Controller() {
            this.pointsBuilder = new PointsBuilder();
        }

        public DataBuilder getDataBuilder() {
            return pointsBuilder;
        }

        public void drop() {
            polygonArea.getDrawer().dropToDraw();
            polygonArea.getBuilder().dropBuilder();
        }

        public void run() {
            final Hull hull = new QuickHull();
            try {
                final Polygon polygon = hull.hull(pointsBuilder.getPoints());
                polygonArea.getDrawer().dropToDraw();
                polygonArea.getDrawer().addPolygon(polygon);
                append("Polygon: " + polygon.getPoints().toString());
                append("\n");
            } catch (Throwable throwable) {
                System.err.println("Error: " + pointsBuilder.getPoints());
                append("Error: " + pointsBuilder.getPoints());
            }
        }

        public void load(final File file) {
            try {
                final ru.itmo.govnokod.geometry.model.Polygon polygon = new InputStreamPolygon(new FileInputStream(file));
                drop();
                for (final Point point : polygon.getPoints()) {
                    pointsBuilder.addPoint(point);
                }
            } catch (FileNotFoundException e) {
                System.err.println(e.getMessage());
                append("Error: " + e.getMessage());
            }
        }
    }
}
