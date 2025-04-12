import org.code.theater.*;
import org.code.media.*;
import java.util.ArrayList;
import java.util.Scanner;
public class Bowl extends Food{
  private double price;

  /*
 * Constructs a Bowl object with the given protein and toppings.
 * Adds an additional cost specific to a bowl.
 *
 * @param protein: the selected protein for the bowl
 * @param toppings: the list of toppings selected for the bowl
 * @return: none
 */
  public Bowl(String protein, ArrayList<String> toppings){
    super(protein, toppings);
    price = getPrice() + 1.50;
  }

  /*
 * Returns the image filename representing an empty bowl.
 *
 * @param: none
 * @return: filename of the bowl image
 */
  public static String getBowlImage(){
    return "emptyBowl.png";
  }












  
}