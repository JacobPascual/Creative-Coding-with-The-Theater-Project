import org.code.theater.*;
import org.code.media.*;
import java.util.ArrayList;
import java.util.Scanner;
public class Burrito extends Food{
  private double price;

  /*
 * Constructs a Burrito object with the given protein and toppings.
 * Adds an additional cost specific to a burrito.
 *
 * @param protein: the selected protein for the burrito
 * @param toppings: the list of toppings selected for the burrito
 * @return: none
 */
  public Burrito(String protein, ArrayList<String> toppings){
    super(protein, toppings);
    price = getPrice() + 1.0;
  }

  /*
 * Returns the image filename representing an empty tortilla.
 *
 * @param: none
 * @return: filename of the tortilla image
 */
  public static String getTortillaImage(){
    return "emptyTortilla.png";
  }




}