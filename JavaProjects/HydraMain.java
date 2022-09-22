import javax.swing.*;
public class HydraMain{
  public static void main(String[] args){
    int a = JOptionPane.showConfirmDialog(null, "Do you know what a hydra is?", "Hydra", JOptionPane.YES_NO_OPTION);
    if(a == JOptionPane.YES_OPTION) {
      JOptionPane.showMessageDialog(null, "Alrighty good luck then");
    }
    else if(a == JOptionPane.NO_OPTION) {
      JOptionPane.showMessageDialog(null, "Oooooh well your about too, gl");
    }
    else{
      JOptionPane.showMessageDialog(null, "I'ma take that as a noooooo gl");
    }
    Hydra hydra = new Hydra(1);
  }
}
