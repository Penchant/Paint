package mcvay_wickham_paintprogram;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.JComponent;
import static java.awt.Color.*;

/**
 * Daniel McVay 
 * Dylan Wickham
 * November 10th, 2016
 * CSCI 132
 * Hunter Lloyd
 * 
 * Component that acts as the canvas and has all the drawing done on it.
 */

public class DrawFrame extends JComponent {

    private Image image;
    private Graphics2D g2;
    private int currentX, currentY, oldX, oldY;
    private static DrawFrame instance;
    String action = "";
    public Color col = black, colOutline;
    public int stroke;
    Stroke strokeSize = new BasicStroke(stroke);
    

    public DrawFrame() {
      
        setDoubleBuffered(false);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                oldX = e.getX();
                oldY = e.getY();
                g2.setStroke( new BasicStroke(stroke));
                if( action.equals("Add Text")){
                    Icon icon = new ImageIcon(getClass().getClassLoader().getResource("#SuperProfessional.gif")); 
                    Object[] possibilities = null;
                            String text = (String)JOptionPane.showInputDialog(
                                    SwingPaint.frame,
                                    "What is the text you would like to add?",
                                    "Add Text", 1,
                                    icon, possibilities, "text");
                            g2.setColor(col);
                            g2.setFont(new Font("Arial Black", Font.PLAIN, 20));
                            g2.drawString(text, oldX, oldY);
                            repaint();
                }
            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                

                currentX = e.getX();
                currentY = e.getY();

                if (g2 != null) {
                    g2.setColor(col);
                   
                    if(action.equals("Pencil")){
                       
                       g2.drawLine(oldX, oldY, currentX, currentY);
                       
                       oldX = currentX;
                       oldY = currentY;
                   }
                }
                repaint();
            }
        }
        );

        addMouseListener(
                new MouseAdapter() {

            @Override
            public void mouseReleased(MouseEvent e
            ) {
                currentX = e.getX();
                currentY = e.getY();

                if (g2 != null) {
                    
                    g2.setColor(col);
                    
                    
                    drawShape(action,0);
                    
                    
                    g2.setColor(col);
                    repaint();
                }
            }
        }
        );
    }

    @Override
    protected void paintComponent(Graphics g) {
        if (image == null) {
            image = createImage(getSize().width, getSize().height);
            g2 = (Graphics2D) image.getGraphics();

            clear();
        }

        g.drawImage(image, 0, 0, null);
    }
    /**
     * Access DrawFrame through singleton method
     * @return Returns instance of DrawFrame 
     */
    public static DrawFrame getInstance(){
        if(instance == null){
            instance = new DrawFrame();
        }
        return instance;
    }
    private void drawShape(String actionD, int mode){
        //Determining width and height of object to be drawn
                    int w = Math.abs(currentX - oldX);
                    int h = Math.abs(currentY - oldY);
                    
                    int startX, startY;
                    
                    //Determine starting coordinates for drawing
                    if (currentX > oldX && currentY > oldY) {
                        startX = oldX;
                        startY = oldY;
                    }
                     else if (currentX > oldX && currentY < oldY) {
                        startX = oldX;
                        startY = currentY;
                     }
                     else if (currentX < oldX && currentY > oldY) {
                        startX = currentX;
                        startY = oldY;
                     }       
                     else {
                        startX = currentX;
                        startY = currentY;
                     }
    //Executes whatever action fits, unless the action is pencil being done when the mouse is dragged
                    switch(actionD){
                        case "Line Drawing":
                            
                            g2.drawLine(oldX, oldY, currentX, currentY);
                            break;
                            
                        case "Fill Rectangle":
                            
                                g2.fillRect(startX, startY, w, h);
                                g2.setColor(colOutline);
                                g2.drawRect(startX, startY, w, h);
                                
                            break;
                            
                        case "Fill Oval":
                            
                                g2.fillOval(startX, startY, w, h);
                                g2.setColor(colOutline);
                                g2.drawOval(startX, startY, w, h);
                            
                            break;
                            
                        case "Oval": 
                                g2.setColor(colOutline);
                                g2.drawOval(startX, startY, w, h);
                            break;
                        case "Rectangle":
                                g2.setColor(colOutline);
                                g2.drawRect(startX, startY, w, h);
                            break;
                    }
    }
    /**
     * 
     * Immediately clears canvas
    */
    public void clear() {
        action = "Clear";
        g2.setPaint(Color.white);
        g2.fillRect(0, 0, getSize().width, getSize().height);
        repaint();
    }
    /**
     * Sets the new action for the program to do
     * @param newAction is the newAction to be done
     */
    public void setAction(String newAction){
        action = newAction;
    }
    /**
     * Sets the color
     * @param color color to update col to
     */
    public void setColor(Color color){
        col = color;
    }
    public void setColorOutline(Color color){
        colOutline = color;
    }
    public void setStroke(int str)
    {
        stroke = str;
    }
}
