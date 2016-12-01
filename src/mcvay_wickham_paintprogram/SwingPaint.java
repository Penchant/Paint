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
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * Daniel McVay Dylan Wickham November 10th, 2016 CSCI 132 Hunter Lloyd
 *
 * Contains instance of frame, all buttons, etc.
 */
public class SwingPaint implements ChangeListener{

    JButton clearBtn, fillRectBtn, fillOvalBtn, rectBtn, ovalBtn, lineDrawingBtn, pencilBtn, colorBtn, addTextBtn, colorOutlineBtn;
    Dimension buttonSpace = new Dimension(0, 10);
    static JFrame frame;
    Color color;
    ActionListener actionListener = new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == clearBtn) {
                DrawFrame.getInstance().clear();
            } else if (e.getSource() == fillRectBtn) {
                DrawFrame.getInstance().setAction("Fill Rectangle");
            } else if (e.getSource() == fillOvalBtn) {
                DrawFrame.getInstance().setAction("Fill Oval");
            } else if (e.getSource() == ovalBtn) {
                DrawFrame.getInstance().setAction("Oval");
            } else if (e.getSource() == rectBtn) {
                DrawFrame.getInstance().setAction("Rectangle");
            } else if (e.getSource() == lineDrawingBtn) {
                DrawFrame.getInstance().setAction("Line Drawing");
            } else if (e.getSource() == pencilBtn) {
                DrawFrame.getInstance().setAction("Pencil");
            } else if (e.getSource() == colorBtn) {
                color = JColorChooser.showDialog(DrawFrame.getInstance(), "Select a Color", black);
                DrawFrame.getInstance().setColor(color);
                 colorBtn.setBackground(color);
            }
            else if(e.getSource() == colorOutlineBtn){
                color = JColorChooser.showDialog(DrawFrame.getInstance(), "Select a Color", black);
                DrawFrame.getInstance().setColorOutline(color);
                 colorOutlineBtn.setBackground(color);
            }
            else if (e.getSource() ==  addTextBtn){
                DrawFrame.getInstance().setAction("Add Text");
            }
        }
    };
   
    @Override
    public void stateChanged(ChangeEvent e)
    {
        JSlider source = (JSlider)e.getSource();
        if(!source.getValueIsAdjusting())
        {
            DrawFrame.getInstance().setStroke((int)source.getValue());
        }
    }

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
              
              Icon icon = new ImageIcon(getClass().getClassLoader().getResource("#SuperProfessional.gif"));
              JOptionPane.showMessageDialog(frame,  clearH + fillRectH + fillOvalH + rectH +ovalH + lineDrawingH + pencilH, "Help", JOptionPane.INFORMATION_MESSAGE, icon);
              
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
        JPanel funStuff = new JPanel();

        colorBtn = new JButton();
        colorBtn.addActionListener(actionListener);
        colorBtn.setBackground(black);
        colorBtn.setBorderPainted(false);
        
        colorOutlineBtn = new JButton();
        colorOutlineBtn.addActionListener(actionListener);
        colorOutlineBtn.setBackground(black);
        colorOutlineBtn.setBorderPainted(false);

        clearBtn = new JButton(new ImageIcon(getClass().getClassLoader().getResource("Clear1.gif")));
        clearBtn.addActionListener(actionListener);
        clearBtn.setContentAreaFilled(false);
        clearBtn.setBorderPainted(false);

        fillRectBtn = new JButton(new ImageIcon(getClass().getClassLoader().getResource("FilledRect.gif")));
        fillRectBtn.addActionListener(actionListener);
        fillRectBtn.setContentAreaFilled(false);
        fillRectBtn.setBorderPainted(false);

        fillOvalBtn = new JButton(new ImageIcon(getClass().getClassLoader().getResource("FilledOval.gif")));
        fillOvalBtn.addActionListener(actionListener);
        fillOvalBtn.setContentAreaFilled(false);
        fillOvalBtn.setBorderPainted(false);

        rectBtn = new JButton(new ImageIcon(getClass().getClassLoader().getResource("OpenRect.gif")));
        rectBtn.addActionListener(actionListener);
        rectBtn.setContentAreaFilled(false);
        rectBtn.setBorderPainted(false);

        ovalBtn = new JButton(new ImageIcon(getClass().getClassLoader().getResource("OpenOval.gif")));
        ovalBtn.addActionListener(actionListener);
        ovalBtn.setContentAreaFilled(false);
        ovalBtn.setBorderPainted(false);

        lineDrawingBtn = new JButton(new ImageIcon(getClass().getClassLoader().getResource("blackLine.gif")));
        lineDrawingBtn.addActionListener(actionListener);
        lineDrawingBtn.setContentAreaFilled(false);
        lineDrawingBtn.setBorderPainted(false);

        pencilBtn = new JButton(new ImageIcon(getClass().getClassLoader().getResource("LineDraw.gif")));
        pencilBtn.addActionListener(actionListener);
        pencilBtn.setContentAreaFilled(false);
        pencilBtn.setBorderPainted(false);
        
        addTextBtn = new JButton(new ImageIcon(getClass().getClassLoader().getResource("TextBox.gif")));
        addTextBtn.addActionListener(actionListener);
        addTextBtn.setContentAreaFilled(false);
        addTextBtn.setBorderPainted(false);
        
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
       
        colors.add(colorBtn);
        colors.add(Box.createRigidArea(buttonSpace));
        colors.add(colorOutlineBtn);
        
        JLabel sliderLabel = new JLabel("Stroke Size", JLabel.CENTER);
        sliderLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        JSlider slider = new JSlider(JSlider.HORIZONTAL, 0 , 30 , 1);
       
        slider.addChangeListener(this);
        
        
        slider.setMinorTickSpacing(2);
        slider.setMajorTickSpacing(10);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        
        slider.setLabelTable(slider.createStandardLabels(5));
        
        funStuff.add(slider);
        funStuff.add(sliderLabel);
        
        

        controls.setLayout(new BoxLayout(controls, BoxLayout.PAGE_AXIS));
        colors.setLayout(new BoxLayout(colors, BoxLayout.Y_AXIS));

        content.add(controls, BorderLayout.WEST);
        content.add(colors, BorderLayout.EAST);
        content.add(funStuff, BorderLayout.SOUTH);
        

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
