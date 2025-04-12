import org.code.theater.*;
import org.code.media.*;
import java.util.ArrayList;
import java.util.Scanner;
public class Food {
  private String protein;
  private ArrayList<String> toppings;

  private double price = 0.0;

  /*
 * Represents a food item with a protein, list of toppings, calories, and price.
 *
 * @param protein: the chosen protein for the food item
 * @param toppings: the list of chosen toppings
 * @return: none
 */
  public Food(String protein, ArrayList<String> toppings){
    this.protein = protein;
    this.toppings = toppings;

    ArrayList<String> tempProteins = Chipotle.getProteinList();
    for(int i = 0; i < tempProteins.size(); i++){
      if(protein.toLowerCase().equals(tempProteins.get(i).toLowerCase())){
        setPrice(Chipotle.getProteinPrice().get(i));
      }
    }
    //System.out.println(temp);
  }

  /*
 * Gets the selected protein.
 *
 * @param: none
 * @return: the name of the protein
 */
  public String getProtein(){
    return protein;
  }

  /*
 * Gets the list of selected toppings.
 *
 * @param: none
 * @return: ArrayList of topping names
 */
  public ArrayList<String> getToppings(){
    return toppings;
  }

  /*
 * Gets the current price of the food item.
 *
 * @param: none
 * @return: price of the food item
 */
  public double getPrice(){
    return price;
  }

  /*
 * Sets the base price of the food item.
 *
 * @param p: the price to set
 * @return: none
 */
  public void setPrice(double p){
    price = p;
  }

  /*
 * Gets the price of the food item, with an optional cost for guacamole.
 *
 * @param hasGuac: whether guacamole was added
 * @return: total price including guac if applicable
 */
  public double getPrice(boolean hasGuac){
    if(hasGuac == true){
      return getPrice() + 4.25;
    }
    return getPrice();
  }

  /*
 * Calculates the total calorie count based on the selected protein and toppings.
 *
 * @param protein: the chosen protein
 * @param toppings: the list of chosen toppings
 * @return: total number of calories
 */
  public int getTotalCal(String protein, ArrayList<String> toppings){
    int cals = 0;
    for(int i = 0; i < Chipotle.getProteinList().size(); i++){
      if(protein.toLowerCase().equals(Chipotle.getProteinList().get(i).toLowerCase())){
        cals += Chipotle.getProteinCals().get(i);
      }
    }
    for(int i = 0; i < toppings.size(); i++){
      for(int g = 0; g < Chipotle.getToppingList().size(); g++){
        if(toppings.get(g).toLowerCase().equals(Chipotle.getToppingList().get(g).toLowerCase())){
          cals += Chipotle.getToppingCals().get(g);
        }
      }
    }
    return cals;
  }

/*
 * Returns the image filename associated with a given protein.
 *
 * @param protein: the name of the protein
 * @return: filename of the image representing the protein
 */
  public static String getImage(String protein){
    if(protein.toLowerCase().equals("chicken")){
      return "chicken.png";
    }else if(protein.toLowerCase().equals("steak")){
      return "steak.png";
    }else if(protein.toLowerCase().equals("carnitas")){
      return "carn.png";
    }else if(protein.toLowerCase().equals("barbacoa")){
      return "barbo.png";
    }else{
      return "sofr.png";
    }
  }

  /*
 * Returns the image filenames associated with a list of toppings.
 *
 * @param topps: list of selected toppings
 * @return: ArrayList of filenames representing each topping
 */
  public static ArrayList<String> getImage(ArrayList<String> topps){
    ArrayList<String> tImages = FileReader.toStringList("toppingImageNames.txt");
    
    ArrayList<String> temp = new ArrayList<String>();
    ArrayList<String> compare = Chipotle.getToppingList();
    for(int i = 0; i < compare.size(); i++){
      for(int g = 0; g < topps.size(); g++){
        if(topps.get(g).toLowerCase().equals(compare.get(i).toLowerCase())){
          temp.add(tImages.get(i));
        }
      }
    }
    
    return temp;
  }
  
}