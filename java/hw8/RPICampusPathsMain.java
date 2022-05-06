package hw8;

import java.awt.*;
import javax.swing.*;

public class RPICampusPathsMain {
    public static void main(String[] args)
    {
       EventQueue.invokeLater(new Runnable()
          {
             public void run()
             {
                JFrame frame = new RPIMapFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
             }
          });
    }
}