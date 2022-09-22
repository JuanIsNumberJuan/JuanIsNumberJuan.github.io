import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;

public class Hydra implements ActionListener{
  JFrame frame;// = new JFrame("Hydra"+counter);
  JPanel panel = new JPanel();
  JButton lButton = new JButton("Left");
  JButton rButton = new JButton("Right");
  Random random = new Random();


  public int counter;
  int x = random.nextInt(700);
  int y = random.nextInt(700);
  public Hydra(int counter){
    setCounter(counter);

    lButton.setFocusable(false);
    lButton.setBounds(450,100,200, 100);
    lButton.addActionListener(this);

    rButton.setFocusable(false);
    rButton.setBounds(650,100,120,80);
    rButton.addActionListener(this);

    panel.add(lButton);
    panel.add(rButton);

    if(counter>1){
      frame = new JFrame("Hydra: Cut one head off two will take its place");
    }
    else{
      frame = new JFrame("Hydra");
    }
    frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    //frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_MINIMIZE);
    frame.add(panel);
    frame.setLocation(x,y);
    frame.setSize(400,100);
    frame.setVisible(true);
  }
  public int getCounter(){
    return counter;
  }

  public void setCounter(int a){
    counter = a;
  }
  public void incrementCounter(){
    counter++;
  }

  @Override
  public void actionPerformed(ActionEvent a){
    if(getCounter() >= 20){
      JOptionPane.showMessageDialog(null, "Okay look sometimes when you got a problem it may be best to ask for help you shouldn't always want to go through things alone.");
      JOptionPane.showMessageDialog(null, "I'll poison the hydra for you ;)");
      //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      System.exit(0);
    }
    if(a.getSource() == lButton || a.getSource() == rButton){
      incrementCounter();
      Hydra hydra = new Hydra(counter);
      incrementCounter();
      Hydra hydra1 = new Hydra(counter);
      frame.dispose();
    }
  }
}
