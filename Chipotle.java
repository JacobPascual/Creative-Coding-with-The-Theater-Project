import org.code.theater.*;
import org.code.media.*;
import java.util.ArrayList;
import java.util.Scanner;
public class Chipotle extends Scene{
  private static ArrayList<String> proteinList = new ArrayList<String>();
  private static ArrayList<Integer> proteinCals = new ArrayList<Integer>();
  private static ArrayList<Double> proteinPrice = new ArrayList<Double>();
  
  private static ArrayList<String> toppingList = new ArrayList<String>();
  private static ArrayList<Integer> toppingCals = new ArrayList<Integer>();
  
  /*
  * Creates a Chipotle object
  *
  * @param proteinList: Information for the proteins on the menu
  * @param toppingList: Information for the toppings on the menu
  */
  public Chipotle(String proteinList, String toppingList){
    ArrayList<String> proteins = FileReader.toStringList(proteinList);
    ArrayList<String> toppings = FileReader.toStringList(toppingList);
    
    for(int i = 0; i < proteins.size(); i++){
      this.proteinList.add(proteins.get(i).substring(0, proteins.get(i).indexOf("-")));
      proteinCals.add(Integer.parseInt(proteins.get(i).substring(proteins.get(i).indexOf("-") + 1, proteins.get(i).indexOf("/"))));
      proteinPrice.add(Double.parseDouble(proteins.get(i).substring(proteins.get(i).indexOf("/") + 1)));
    }
    
    for(int i = 0; i < toppings.size(); i++){
      this.toppingList.add(toppings.get(i).substring(0, toppings.get(i).indexOf("-")));
      toppingCals.add(Integer.parseInt(toppings.get(i).substring(toppings.get(i).indexOf("-") + 1)));
    }
  }
  
  //Accessor methods

  /*
 * Returns the list of available protein names.
 *
 * @param: none
 * @return: ArrayList of protein names
 */
  public static ArrayList<String> getProteinList(){
      return proteinList;
    }

  /*
 * Returns the list of protein calorie values.
 *
 * @param: none
 * @return: ArrayList of protein calorie integers
 */
  public static ArrayList<Integer> getProteinCals(){
      return proteinCals;
    }

  /*
 * Returns the list of protein prices.
 *
 * @param: none
 * @return: ArrayList of protein prices as doubles
 */
  public static ArrayList<Double> getProteinPrice(){
      return proteinPrice;
    }

  /*
 * Returns the list of available topping names.
 *
 * @param: none
 * @return: ArrayList of topping names
 */
  public static ArrayList<String> getToppingList(){
      return toppingList;
    }

  /*
 * Returns the list of topping calorie values.
 *
 * @param: none
 * @return: ArrayList of topping calorie integers
 */
  public static ArrayList<Integer> getToppingCals(){
      return toppingCals;
    }

  /*
   * Splits a sentence into individual words based on spaces.
   *
   * @param sentence: the input sentence to split
   * @return: ArrayList of words from the sentence
   */
  public ArrayList<String> getWordsFromSentence(String sentence){
    ArrayList<String> words = new ArrayList<String>();
    int s = sentence.indexOf(" ");
    while(s != -1){
      String curWrd = sentence.substring(0, s);
      words.add(curWrd); 
      sentence = sentence.substring(s + 1);
      s = sentence.indexOf(" ");
    }
    words.add(sentence.substring(0, sentence.length()));
    return words;
  }

  /*
   * Initiates the drawing of scenes by calling the order scene.
   *
   * @param: none
   * @return: none
  */
  public String grammar(String t){
    String[] symbols = {",", ".", "/", "!", "?"};
    for (String s : symbols) {
      t = t.replace(s, "");
    }
    return t;
  }
  
  //Scenes
  public void drawScenes(){
    drawBackground();
    pause(1);
    orderScene();
  }

  /*
 * Handles the order scene logic including user input for item, protein, and toppings.
 *
 * @param: none
 * @return: none
 */
  public void orderScene(){
    Scanner input = new Scanner(System.in);
    System.out.println("Welcome to Chipotle, \nWould you like a bowl or a burrito?");
    String ans = input.nextLine();

    String item = ans.toLowerCase();
    
    System.out.println("Pick a protein:\n-Chicken\n-Steak\n-Carnitas\n-Barbacoa\n-Sofritas");
    String protein = input.nextLine().toLowerCase();
    protein = protein.replace(" ", "");
    protein = grammar(protein);    
    

    System.out.println("Pick your toppings:\n-Rice\n-Guacamole\n-Cream\n-Lettuce\n-Pico\n-Salsa\n-Fajitas\n-Chips\n-Cheese\n-Beans\n-Corn");
    String line = input.nextLine().toLowerCase();
    
    line = grammar(line);
    
    ArrayList<String> tempTopp = getWordsFromSentence(line);
    System.out.println(tempTopp);

    if(item.equals("bowl")){
      Bowl nathan = new Bowl(protein, tempTopp);
      pause(1);
      drawBowl();
      pause(3);
      drawImage(nathan.getImage(protein), 145, 140, 110);
      ArrayList<String> images = nathan.getImage(tempTopp);
      drawToppings(images);

      pause(3);
      drawReceipt(nathan);
    }else if(item.equals("burrito")){
      Burrito nathan = new Burrito(protein, tempTopp);
      pause(1);
      drawBurrito();
      pause(3);
      drawImage(nathan.getImage(protein), 145, 140, 110);
      ArrayList<String> images = nathan.getImage(tempTopp);
      drawToppings(images);

      pause(3);
      drawReceipt(nathan);
    }
  }

  /*
 * Draws topping images at random positions on the screen.
 *
 * @param toppings: a list of image filenames to draw
 * @return: none
 */
  public void drawToppings(ArrayList<String> toppings){
    for(int i = 0; i < toppings.size(); i++){
      int ranX = (int) (Math.random() * 200 + 90);
      int ranY = (int) (Math.random() * 200 + 90);
      pause(2);
      drawImage(toppings.get(i), ranX, ranY, 80);
    }
  }

  /*
 * Draws the background of the scene.
 *
 * @param: none
 * @return: none
 */
  public void drawBackground(){
    clear("white");
    drawImage("counterPic.png", 0, 0, 400);
  }

  /*
 * Draws the image of a bowl on the screen.
 *
 * @param: none
 * @return: none
 */
  public void drawBowl(){
    drawImage(Bowl.getBowlImage(), 40, 40, 325);
  }

  /*
 * Draws the image of a burrito on the screen.
 *
 * @param: none
 * @return: none
 */
  public void drawBurrito(){
    drawImage(Burrito.getTortillaImage(), 40, 40, 325);
  }

   /*
   * Draws the receipt for a burrito order.
   *
   * Displays the selected protein and toppings on a receipt background.
   * Checks if guacamole is included as a topping for pricing purposes.
   *
   * @param: object - the Burrito object containing the selected protein and toppings
   * @return: none
   */
  public void drawReceipt(Burrito object){
    setTextHeight(15);
    clear("white");
    drawImage("image_2025-04-06_182019836.png", 0, 0, 400);
    drawText("Protein: " + object.getProtein(), 80, 183);
    setTextHeight(10);
    drawText("Toppings: " + object.getToppings(), 80, 215);
    
    boolean guac = false;
    for(int i = 0; i < object.getToppings().size(); i++){
      if(object.getToppings().get(i).toLowerCase().equals("guacamole")){
       guac = true; 
      }
    }

    setTextHeight(15);
    drawText("Total: " + object.getPrice(guac) + "$", 80, 270);
  }

   /*
   * Draws the receipt for a bowl order.
   *
   * Displays the selected protein and toppings on a receipt background.
   * Checks if guacamole is included to adjust the final price.
   * Shows the total price based on the selected items.
   *
   * @param: object - the Bowl object containing the selected protein and toppings
   * @return: none
   */
  public void drawReceipt(Bowl object){
    setTextHeight(15);
    clear("white");
    drawImage("image_2025-04-06_182019836.png", 0, 0, 400);
    drawText("Protein: " + object.getProtein(), 80, 183);
    setTextHeight(10);
    drawText("Toppings: " + object.getToppings(), 80, 215);
    
    boolean guac = false;
    for(int i = 0; i < object.getToppings().size(); i++){
      if(object.getToppings().get(i).toLowerCase().equals("guacamole")){
       guac = true; 
      }
    }

    setTextHeight(15);
    drawText("Total: " + object.getPrice(guac) + "$", 80, 270);
  }


}