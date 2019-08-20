package roadcrosser;
// The "AchievementsMenu" class.
import java.awt.*;
import java.awt.event.*; // for listening to buttons
import javax.swing.*;
import java.text.*;
import java.io.*;

public class AchievementsMenu extends JFrame implements MouseListener
{
    //declare the container
    Container frame;

    //declaring lables for graphics
    JLabel btnMenu, btnMenuPressed, btnMenuHover, btnClose, btnCloseHover, btnClosePressed, lblBackgroundImage;
    JLabel[] collectButtons = new JLabel [6];
    JLabel collectButtonsPress;
    JLabel[] collectButtonsGray = new JLabel [6];

    int[] buttonX = {134, 322, 510, 134, 322, 510};
    int[] buttonY = {288, 288, 288, 494, 494, 494};
    int[] rewards = {30, 60, 90, 120, 210, 300};
    int[] scoreReqs = {25, 50, 75, 100, 150, 200};

    static int[] achievementState = new int [6];
    static int amount;
    static String[] achievementData;

    public AchievementsMenu ()
    {
	super ("AchievementsMenu");     // Set the frame's name
	//program exits on close.
	setDefaultCloseOperation (EXIT_ON_CLOSE);

	//intializing the frame
	frame = getContentPane ();

	//removing default windows border around frame
	setUndecorated (true);

	//removing preset frame layout
	frame.setLayout (null);

	//frame cannot be resized.
	setResizable (false);

	setSize (620, 620);     // Set the frame's size


	lblBackgroundImage = new JLabel (new ImageIcon ("graphics/backgrounds/achievementsbackground.jpg"));

	btnClose = new JLabel (new ImageIcon ("graphics/buttons/closeButton.png"));
	btnCloseHover = new JLabel (new ImageIcon ("graphics/buttons/closeButtonHover.png"));
	btnClosePressed = new JLabel (new ImageIcon ("graphics/buttons/closeButtonPress.png"));

	btnMenu = new JLabel (new ImageIcon ("graphics/buttons/highscores/menuBtn.png"));
	btnMenuHover = new JLabel (new ImageIcon ("graphics/buttons/highscores/menuBtnHover.gif"));
	btnMenuPressed = new JLabel (new ImageIcon ("graphics/buttons/highscores/menuBtnPress.png"));

	collectButtonsPress = new JLabel (new ImageIcon ("graphics/buttons/achievements/collectPress.png"));

	//positioning buttons
	btnMenu.setBounds (206, 544, 209, 42);
	btnClose.setBounds (559, 24, 37, 37);
	lblBackgroundImage.setBounds (0, 0, 620, 620);


	frame.add (collectButtonsPress);

	for (int i = 0 ; i < collectButtons.length ; i++)
	{
	    collectButtons [i] = new JLabel (new ImageIcon ("graphics/buttons/achievements/collect.png"));
	    collectButtonsGray [i] = new JLabel (new ImageIcon ("graphics/buttons/achievements/collectGray.png"));

	    frame.add (collectButtons [i]);
	    frame.add (collectButtonsGray [i]);

	    collectButtons [i].addMouseListener (this);
	}

	frame.add (btnClosePressed);
	frame.add (btnCloseHover);
	frame.add (btnClose);
	frame.add (btnMenuPressed);
	frame.add (btnMenuHover);
	frame.add (btnMenu);
	frame.add (lblBackgroundImage);
	
	loadAchievementData ();
	achievementCheck();
	updateButtons ();

	//adding mouse listeners to buttons
	btnClose.addMouseListener (this);
	btnMenu.addMouseListener (this);

	//show the frame in center of screen.
	//source: http://stackoverflow.com/questions/2442599/how-to-set-jframe-to-appear-centered-regardless-of-monitor-resolution
	Dimension dim = Toolkit.getDefaultToolkit ().getScreenSize ();
	this.setLocation (dim.width / 2 - this.getSize ().width / 2, dim.height / 2 - this.getSize ().height / 2);

	setVisible (true);
    } // Constructor


    public static void loadAchievementData ()
    {
	try
	{
	    FileReader fileR = new FileReader ("data/achievements.dat");
	    BufferedReader input = new BufferedReader (fileR);

	    amount = Integer.parseInt (input.readLine ());

	    achievementData = new String [amount];

	    for (int i = 0 ; i < achievementData.length ; i++)
	    {
		achievementData [i] = input.readLine ();
	    }

	    input.close ();

	    String userData = achievementData [LoginMenu.userNumber - 1];

	    System.out.println (userData);

	    for (int i = 0 ; i < achievementState.length ; i++)
	    {
		achievementState [i] = Integer.parseInt (userData.substring (i, i + 1));
	    }
	}
	catch (Exception e)
	{
	}
    }


    public void saveAchievementData ()
    {
	try
	{
	    FileWriter fileW = new FileWriter ("data/achievements.dat");
	    PrintWriter output = new PrintWriter (fileW);

	    output.println (amount);

	    String newUserData = "";

	    for (int i = 0 ; i < achievementState.length ; i++)
	    {
		newUserData += achievementState [i];
	    }

	    achievementData [LoginMenu.userNumber - 1] = newUserData;

	    for (int i = 0 ; i < achievementData.length ; i++)
	    {
		output.println (achievementData [i]);
	    }

	    output.close ();
	}
	catch (Exception e)
	{
	}
    }


    public void achievementCheck ()
    {
	for (int i = 0 ; i < scoreReqs.length ; i++)
	{
	    if (EndScreen.userHighScore >= scoreReqs [i] && achievementState[i] == 0)
	    {
		achievementState[i] = 1;
		saveAchievementData();
	    }
	}
    }


    public void updateButtons ()
    {
	for (int i = 0 ; i < achievementState.length ; i++)
	{
	    if (achievementState [i] == 0)
	    {
		collectButtonsGray [i].setBounds (buttonX [i], buttonY [i], 59, 24);
	    }
	    else if (achievementState [i] == 1)
	    {
		collectButtons [i].setBounds (buttonX [i], buttonY [i], 59, 24);
	    }
	    else
	    {
		//no buttons.
	    }
	}
    }


    /*
    ------------------------------------------------------------------------------------------
				   MouseListener Methods
    ------------------------------------------------------------------------------------------
    */

    public void mouseClicked (MouseEvent me)
    {

    } // mouseClicked method


    public void mousePressed (MouseEvent me)
    {
	//if the user presses left mouse button on the close button, new image is shown showing the button being pressed in.
	if (me.getSource () == btnClose)
	{
	    btnClosePressed.setBounds (473, 21, 209, 42);
	}
	//if the user presses left mouse button on the clear button, new image is shown showing the button being pressed in.
	else if (me.getSource () == btnMenu)
	{
	    btnMenuPressed.setBounds (206, 544, 209, 42);
	}

	for (int i = 0 ; i < collectButtons.length ; i++)
	{
	    if (me.getSource () == collectButtons [i])
	    {
		collectButtonsPress.setBounds (buttonX [i], buttonY [i], 59, 24);
	    }
	}
    } // mousePressed method


    public void mouseReleased (MouseEvent me)
    {
	//if the user releases left mouse button on the close button, program closes.
	if (me.getSource () == btnClose)
	{
	    System.exit (0);
	}
	else if (me.getSource () == btnMenu)
	{
	    btnMenuPressed.setBounds (0, 0, 0, 0);
	    new MainMenu ();
	    dispose (); //get rid of window completely
	}

	for (int i = 0 ; i < collectButtons.length ; i++)
	{
	    if (me.getSource () == collectButtons [i])
	    {
		collectButtonsPress.setBounds (0, 0, 0, 0);
		collectButtons [i].setBounds (0, 0, 0, 0);
		achievementState [i] = 2;

		LoginMenu.coinCount += rewards [i];
		saveAchievementData ();
		EndScreen.updateUsers ();
	    }
	}
    } // mouseReleased method


    public void mouseEntered (MouseEvent me)
    {
	//if the user hovers the cursor over the close button, new image is shown showing the button being lit up
	if (me.getSource () == btnClose)
	{
	    btnCloseHover.setBounds (473, 21, 209, 42);
	}
	//if the user hovers the cursor over the clear button, new image is shown showing the button being lit up
	else if (me.getSource () == btnMenu)
	{
	    btnMenuHover.setBounds (206, 544, 209, 42);
	}
    } // mouseEntered method


    public void mouseExited (MouseEvent me)
    {
	//if the user stops hovering the cursor over the close button, the lit up button image is removed.
	if (me.getSource () == btnClose)
	{
	    btnCloseHover.setBounds (0, 0, 0, 0);
	}
	else if (me.getSource () == btnMenu)
	{
	    btnMenuHover.setBounds (0, 0, 0, 0);
	}
    } // mouseExited method
} // AchievementsMenu class
