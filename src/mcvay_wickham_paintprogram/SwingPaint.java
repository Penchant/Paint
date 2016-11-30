
package mcvay_wickham_paintprogram;

import java.awt.Container;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import static java.awt.Color.*;
import java.awt.image.BufferedImage;
import java.awt.image.MemoryImageSource;
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
            fillRectBtn, fillOvalBtn, rectBtn, ovalBtn, lineDrawingBtn, pencilBtn, addTextBtn;
    Dimension buttonSpace = new Dimension(0, 10);
    static JFrame frame;
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
            else if (e.getSource() ==  addTextBtn){
                DrawFrame.getInstance().setAction("Add Text");
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
        frame = new JFrame("Paint");
        JMenuBar bar = new JMenuBar();
        JMenu file = new JMenu("File");
        JMenu edit = new JMenu("Edit");
        JMenu functions = getFunctions();
        JMenu about = new JMenu("About");
        JMenuItem openItem, exitItem, resetItem;
        frame.addWindowListener(new WindowAdapter(){
            @Override
              public void windowClosing(WindowEvent ev){quit();}
            });
      openItem = new JMenuItem("Open");
      openItem.addActionListener(new ActionListener(){
            @Override
          public void actionPerformed(ActionEvent evt){ 
              //handleOpen();
          }
           });
      resetItem = new JMenuItem("Edit");
      resetItem.addActionListener(new ActionListener(){
            @Override
          public void actionPerformed(ActionEvent evt){ 
          //    reset();
          }
           });     
      exitItem = new JMenuItem("Exit");
      exitItem.addActionListener(new ActionListener(){
            @Override
          public void actionPerformed(ActionEvent evt){ quit(); }
           });
      file.add(openItem);
      file.add(resetItem);
      file.add(exitItem);
      
      JMenuItem SOL = new JMenuItem("SOL, nothing here!");
      edit.add(SOL);
      
      JMenuItem help = new JMenuItem("Help");
      help.addActionListener(new ActionListener(){
            @Override
          public void actionPerformed(ActionEvent evt){ 
              String clearH = "Clear: clear the page \n\nClick and drag to create shapes. \n";
              String fillRectH = "Filled Rectangle: set drawing action to a filled rectangle. \n";
              String fillOvalH = "Filled Oval: sets drawing action to a filled oval.\n";
              String rectH = "Rectangle Outline: sets drawing action to a rectangle outline.\n";
              String ovalH = "Oval Outline: sets drawing action to a oval outline. \n";
              String lineDrawingH = "Line Drawing: sets drawing action a line.\n";
              String pencilH = "Pencil: sets drawing action to draw wherever the mouse is dragged.\n";
              
              JOptionPane.showMessageDialog(frame,  clearH + fillRectH + fillOvalH + rectH +ovalH + lineDrawingH + pencilH, "Help", 1);
              
              
              /*JFrame helpFrame = new JFrame("Help");
              Container content = helpFrame.getContentPane();
              content.setLayout(new BorderLayout());

              //content.add(DrawFrame.getInstance(), BorderLayout.CENTER);
               helpFrame.setSize(1000, 1000);

               helpFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
               helpFrame.setVisible(true);*/
          }
           });
      about.add(help);
      
        bar.add(file);
        bar.add(edit);
        bar.add(functions);
        bar.add(about);
        
        frame.setJMenuBar(bar);
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
        
        addTextBtn = new JButton("Add Text");
        addTextBtn.addActionListener(actionListener);
        
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
        controls.add(Box.createRigidArea(buttonSpace));
        controls.add(addTextBtn);
        
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
      private JMenu getFunctions()
  {
     JMenu fun = new JMenu("Choices");
     JMenuItem fillRect = new JMenuItem("Filled Rectangle");
     JMenuItem fillOval = new JMenuItem("Filled Oval");
     JMenuItem oval = new JMenuItem("Oval Outline");
     JMenuItem rectangle = new JMenuItem("Rectangle Outline");
     JMenuItem lineDrawing = new JMenuItem("Line Drawing");
     JMenuItem pencil = new JMenuItem("Pencil");
     fillRect.addActionListener(new ActionListener(){
            @Override
          public void actionPerformed(ActionEvent evt){
              DrawFrame.getInstance().setAction("Fill Rectangle");
          }
           });
     fillOval.addActionListener(new ActionListener(){
            @Override
          public void actionPerformed(ActionEvent evt){
              DrawFrame.getInstance().setAction("Fill Oval");
          }
           });
     oval.addActionListener(new ActionListener(){
            @Override
          public void actionPerformed(ActionEvent evt){
              DrawFrame.getInstance().setAction("Oval");
          }
           });
     rectangle.addActionListener(new ActionListener(){
            @Override
          public void actionPerformed(ActionEvent evt){
              DrawFrame.getInstance().setAction("Rectangle");
          }
           });
     lineDrawing.addActionListener(new ActionListener(){
            @Override
          public void actionPerformed(ActionEvent evt){
              DrawFrame.getInstance().setAction("Line Drawing");
          }
           });
     pencil.addActionListener(new ActionListener(){
            @Override
          public void actionPerformed(ActionEvent evt){
              DrawFrame.getInstance().setAction("Pencil");
          }
           });
      fun.add(fillRect);
      fun.add(fillOval);
      fun.add(rectangle);
      fun.add(oval);
      fun.add(lineDrawing);
      fun.add(pencil);
      
      return fun;   

  }
    
     private void quit()
  {  
     System.exit(0);
  }

}
