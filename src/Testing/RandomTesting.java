/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Testing;
import java.util.Random;
import java.math.BigDecimal;
/**
 *
 * @author Mark
 */
public class RandomTesting {

  public static final void main(String... aArgs){
      int i = 2;
      double t = 2.0;
      BigDecimal d = new BigDecimal(t);
      
      d = d.setScale(0, BigDecimal.ROUND_HALF_UP);
      i = d.intValue();
      
      System.out.println("i=" + i);
  } 
}

