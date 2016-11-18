
package mcvay_wickham_paintprogram;

import java.awt.Container;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import static java.awt.Color.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * Daniel McVay 
 * Dylan Wickham
 * November 10th, 2016
 * CSCI 132
 * Hunter Lloyd
 * 
 * Contains instance of frame, all buttons, etc.
 */

public class SwingPaint {

    JButton clearBtn, blackBtn, blueBtn, redBtn, greenBtn, yellowBtn, whiteBtn,
            fillRectBtn, fillOvalBtn, rectBtn, ovalBtn, lineDrawingBtn, pencilBtn;
    Dimension buttonSpace = new Dimension(0, 10);
    ActionListener actionListener = new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == clearBtn) {
                DrawFrame.getInstance().clear();
            } 
            else if (e.getSource() == fillRectBtn) {
                DrawFrame.getInstance().setAction("Fill Rectangle");
            }
            else if (e.getSource() == fillOvalBtn) {
                DrawFrame.getInstance().setAction("Fill Oval");
            }
            else if (e.getSource() == ovalBtn) {
                DrawFrame.getInstance().setAction("Oval");
            }
            else if (e.getSource() == rectBtn) {
                DrawFrame.getInstance().setAction("Rectangle");
            }
            else if (e.getSource() == lineDrawingBtn) {
                DrawFrame.getInstance().setAction("Line Drawing");
            }
            else if (e.getSource() == pencilBtn){
                DrawFrame.getInstance().setAction("Pencil");
            }
            else if (e.getSource() == blackBtn) {
                DrawFrame.getInstance().setColor(black);
            }
            else if (e.getSource() == blueBtn) {
                DrawFrame.getInstance().setColor(blue);
            }
            else if (e.getSource() == redBtn) {
                DrawFrame.getInstance().setColor(red);
            }
            else if (e.getSource() == greenBtn) {
                DrawFrame.getInstance().setColor(green);
            }
            else if (e.getSource() == yellowBtn) {
               DrawFrame.getInstance().setColor(yellow);
            }
            else if (e.getSource() == whiteBtn) {
               DrawFrame.getInstance().setColor(white);
            } 
        }
    };
    
    /**
     * Everything involving the initial display of the program
     */
    public void show() {
        JFrame frame = new JFrame("Paint");
        Container content = frame.getContentPane();
        content.setLayout(new BorderLayout());

        content.add(DrawFrame.getInstance(), BorderLayout.CENTER);
        JPanel controls = new JPanel();
        JPanel colors = new JPanel();

        blackBtn = new JButton();
        blackBtn.addActionListener(actionListener);
        blackBtn.setBackground(Color.black);

        blueBtn = new JButton();
        blueBtn.addActionListener(actionListener);
        blueBtn.setBackground(Color.blue);

        redBtn = new JButton();
        redBtn.addActionListener(actionListener);
        redBtn.setBackground(Color.red);

        greenBtn = new JButton();
        greenBtn.addActionListener(actionListener);
        greenBtn.setBackground(Color.green);

        yellowBtn = new JButton();
        yellowBtn.addActionListener(actionListener);
        yellowBtn.setBackground(Color.yellow);

        whiteBtn = new JButton();
        whiteBtn.addActionListener(actionListener);
        whiteBtn.setBackground(Color.white);

        Icon clearIcon = new ImageIcon("Clear.PNG");
        clearBtn = new JButton("Clear");
        clearBtn.addActionListener(actionListener);

        Icon fillRectIcon = new ImageIcon("FilledRect.PNG");
        fillRectBtn = new JButton("Filled Rectangle");
        fillRectBtn.addActionListener(actionListener);

        Icon fillOvalIcon = new ImageIcon("FilledOval.PNG");
        fillOvalBtn = new JButton("Fill Oval");
        fillOvalBtn.addActionListener(actionListener);

        Icon emptyRectIcon = new ImageIcon("OpenRect.PNG");
        rectBtn = new JButton("Empty Rectangle");
        rectBtn.addActionListener(actionListener);

        Icon emptyOvalIcon = new ImageIcon("OpenOval.PNG");
        ovalBtn = new JButton("Empty Oval");
        ovalBtn.addActionListener(actionListener);

        Icon lineDrawIcon = new ImageIcon("LineDraw.PNG");
        lineDrawingBtn = new JButton("Line Drawing");
        lineDrawingBtn.addActionListener(actionListener);
        
        pencilBtn = new JButton("Pencil");
        pencilBtn.addActionListener(actionListener);

        controls.add(clearBtn);
        controls.add(Box.createRigidArea(buttonSpace));
        controls.add(fillRectBtn);
        controls.add(Box.createRigidArea(buttonSpace));
        controls.add(fillOvalBtn);
        controls.add(Box.createRigidArea(buttonSpace));
        controls.add(rectBtn);
        controls.add(Box.createRigidArea(buttonSpace));
        controls.add(ovalBtn);
        controls.add(Box.createRigidArea(buttonSpace));
        controls.add(lineDrawingBtn);
        controls.add(Box.createRigidArea(buttonSpace));
        controls.add(pencilBtn);

        colors.add(blackBtn);
        colors.add(Box.createRigidArea(buttonSpace));
        colors.add(blueBtn);
        colors.add(Box.createRigidArea(buttonSpace));
        colors.add(redBtn);
        colors.add(Box.createRigidArea(buttonSpace));
        colors.add(greenBtn);
        colors.add(Box.createRigidArea(buttonSpace));
        colors.add(yellowBtn);
        colors.add(Box.createRigidArea(buttonSpace));
        colors.add(whiteBtn);

        controls.setLayout(new BoxLayout(controls, BoxLayout.PAGE_AXIS));
        colors.setLayout(new BoxLayout(colors, BoxLayout.Y_AXIS));

        content.add(controls, BorderLayout.WEST);
        content.add(colors, BorderLayout.EAST);

        frame.setSize(1000, 1000);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }

}
