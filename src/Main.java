import java.awt.Color;
import java.awt.Font;

/**
 * Choose-Your-Own-Adventure Lab
 * Read instructions for more details.
 * See the (#) indicating which Milestone each part of code needs to be completed by.
 * @author _____________________ (2)
 * @version ____________________ (2)
 */

public class Main {

	//declare a new Adventure
	public static Adventure a;


	public static void main(String[] args) {
		/*------------------------------------------------- */
		/*---- ADVENTURE SETUP SECTION -------------------- */
    /*------------------------------------------------- */
		System.out.println("Starting Choose-Your-Own-Adventure Lab!");

		//Construct a new Adventure object
		a = new Adventure();
		//a = new Adventure(800, 600, 4);	//Use if you want 4 buttons
		
		// (5) Make changes to the colors & shapes in this method at the bottom
		moreAdventureSetup();
		
		// (2) Change the Title Bar to include the name of your adventure and your name as well 
		a.setTitleText("<Insert Adventure Name here>" + "     by <Insert Your Name Here>");

		// (2) Set the first background image
		a.setBackground("images/forest.jpg");

		// (2) Write your first greeting Description for the Adventure
		a.printDescription("You're lost in the woods!");

		// (5) Setup at least 1 Adventure status variable
		int h = 0;

		// Ask and record the name of the Adventurer (What if they skip this?)
		String name = a.popupQuestion("What is your name?");

		// (2) Add a popupMessage that includes the Adventurers name
		

		// (5) Setup the String structure for your Status bar
		updateStatusBar(name, h);

	
    /*------------------------------------------------- */
		/*---- ADVENTURE CHOICES SECTION ----------------- */
    /*------------------------------------------------- */
    //Initialize a variable to identify which moment (ie. Choice, Endscreen, etc.) is supposed to be happening.
    String moment = "A";

    // (5) Make the Adventure end if the moment is a certain letter
		while(2+2==4){
      
      //Print statement for programmer to check which moment should be running
      System.out.println("Looking for moment: " + moment);
      
      // Setup a new decision for the Adventurer.  
			//MOMENT 1: STARTING MOMENT			
			if(moment.equals("A")){
        //	(3) Description of the Moment


        // (3) What is the rightButton choice?


        // (3) What is the leftButton choice?


        // (3) Set a new background image for this moment


        // (3) Wait for the Adventurer to make a choice
				a.waitForButtonPush();

        // (3) What should happen if they choose rightButton?


          // --> (3.5) Update the next "moment" for the Adventure if they choose the rightButton


          // --> (5) Update any Status variables based on choosing the rightButton


          // --> (5) Display a popup message for any urgent info if rightButton chosen


        // (3) What should happen if they choose leftButton?


				  // --> (3.5) Update the next "moment" for the Adventure if they choose the leftButton


          // --> (5) Update any Status variables based on choosing the leftButton


          // --> (5) Display a popup message for any urgent info if leftButton chosen


        // (5) Update the Status Bar


			}

			// (4) MOMENT B: 
			

			// (4) MOMENT C: 


			// (4) MOMENT D: 


			// (4) MOMENT E: POSITIVE ENDING 


			// (4) MOMENT F: NEGATIVE ENDING


    } //while loop close


    //System.out.println("End of Adventure");
    //(5) Popup message that Lab has ended

		//(5) Call the closeAdventure method to end the game

  }	//closes the main() method



    /*------------------------------------------------- */
    /*--------HELPER METHODS--------------------------- */
    /*------------------------------------------------- */        

  // (5) Adjust these method calls to customize your Adventure
  public static void moreAdventureSetup(){

	//Change your Description Color, Text Size, Text Color, and Font
    a.setDescriptionColor(new Color(0,150,220));	//Set color with Red, Gree, Blue ints
    //a.setDescriptionColor(Color.BLUE);					//Set color with Color class String
    a.setDescriptionTextSize(25);
    //a.setDescriptionTextFont("serif");	//options of "sans-serif", "serif", "courier"
		a.setDescriptionTextColor(Color.MAGENTA);

		//Change the colors of the buttons
    a.setLeftButtonColor(Color.GREEN);
    a.setRightButtonColor(Color.GREEN);
		a.setLeftButtonColor2(Color.GREEN);
    a.setRightButtonColor2(Color.GREEN);

		//Change the shapes of the buttons: 1 = Circle, 2 = UpTriangle, 3 = RightTriangle, 4 = LeftTriangle, 5 = Rectangle 
    //a.setLeftButtonShape(1);	
		a.setRightButtonShape(3);
		// a.setLeftButtonShape2(3);
		// a.setRightButtonShape2(4);

		//Change the sizes of the buttons
    a.setLeftButtonSize(100,300);
    a.setRightButtonSize(100,300);
		a.setLeftButtonSize2(100,300);
    a.setRightButtonSize2(100,300);

		//change button text
		a.setLeftButtonFont( new Font("Arial", Font.PLAIN, 40));


    System.out.println("moreAdventureSetup() complete");
  } //closes the moreAdventure() method

  // (5) Method to update the Adventure Status Bar
  public static void updateStatusBar(String name, int h){

    //Create a easily readable String to display as the Status bar
    String statusString = name + " " + h;

    a.setStatusBar(statusString);

  } //closes the updateStatusBar() method

}