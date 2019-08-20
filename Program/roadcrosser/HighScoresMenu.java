package roadcrosser;
// The "HighScoresMenu" class.
import java.awt.*;
import java.awt.event.*; // for listening to buttons
import javax.swing.*;
import java.text.*;
import java.io.*;

public class HighScoresMenu extends JFrame implements MouseListener, KeyListener
{
    //declare the container
    Container frame;

    Font font = new Font ("Arial", Font.BOLD, 20);

    int btnNameCounter = 1;

    int btnScoreCounter = 1;

    Color color = new Color (25, 25, 25);

    //declaring labels for graphics
    static JLabel btnScore, notFoundError, btnScoreHover, btnScorePressed, btnSearch, btnSearchHover, btnSearchPressed, btnName, btnNameHover, btnNamePressed, btnMenu, btnMenuPressed, btnMenuHover, btnClose, btnCloseHover, btnClosePressed, lblBackgroundImage, lblAscendingIcon, lblDescendingIcon, border;

    static JTextArea txtPlayerNames, txtPlayerScores;
    JTextField txtSearch;

    static String scoreList;

    static String nameList;

    static String playerNames[] = {};

    static int playerHighScores[] = {};

    int index;

    int location;

    public HighScoresMenu ()
    {
	super ("Road Crosser"); //set the frame's name
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
	setIconImage (new ImageIcon ("graphics/misc/logo.png").getImage ());

	setSize (620, 620);     // Set the frame's size

	lblBackgroundImage = new JLabel (new ImageIcon ("graphics/backgrounds/allScoresBackground.jpg"));
	border = new JLabel (new ImageIcon ("graphics/misc/border.png"));

	//button initialization
	btnScore = new JLabel (new ImageIcon ("graphics/buttons/highscores/score.png"));
	btnScoreHover = new JLabel (new ImageIcon ("graphics/buttons/highscores/scoreHover.png"));
	btnScorePressed = new JLabel (new ImageIcon ("graphics/buttons/highscores/scorePress.png"));

	btnClose = new JLabel (new ImageIcon ("graphics/buttons/closeButton.png"));
	btnCloseHover = new JLabel (new ImageIcon ("graphics/buttons/closeButtonHover.png"));
	btnClosePressed = new JLabel (new ImageIcon ("graphics/buttons/closeButtonPress.png"));

	btnMenu = new JLabel (new ImageIcon ("graphics/buttons/highscores/menuBtn.png"));
	btnMenuHover = new JLabel (new ImageIcon ("graphics/buttons/highscores/menuBtnHover.gif"));
	btnMenuPressed = new JLabel (new ImageIcon ("graphics/buttons/highscores/menuBtnPress.png"));

	btnName = new JLabel (new ImageIcon ("graphics/buttons/highscores/names.png"));
	btnNameHover = new JLabel (new ImageIcon ("graphics/buttons/highscores/namesHover.png"));
	btnNamePressed = new JLabel (new ImageIcon ("graphics/buttons/highscores/namesPress.png"));

	btnSearch = new JLabel (new ImageIcon ("graphics/buttons/highscores/search.png"));
	btnSearchHover = new JLabel (new ImageIcon ("graphics/buttons/highscores/searchHover.png"));
	btnSearchPressed = new JLabel (new ImageIcon ("graphics/buttons/highscores/searchPress.png"));

	lblAscendingIcon = new JLabel (new ImageIcon ("graphics/misc/listAscending.png"));
	lblDescendingIcon = new JLabel (new ImageIcon ("graphics/misc/listDescending.png"));

	notFoundError = new JLabel ("User not found.");

	Font errorFont = new Font ("Calibri", Font.BOLD, 12);
	Color errorColor = new Color (223, 58, 58);

	notFoundError.setFont (errorFont);
	notFoundError.setForeground (errorColor);

	txtPlayerNames = new JTextArea ();
	txtPlayerScores = new JTextArea ();
	txtSearch = new JTextField ();

	txtPlayerNames.setFont (font);
	txtPlayerScores.setFont (font);

	txtSearch.setFont (font);
	txtSearch.setOpaque (false);
	txtSearch.setBorder (null);

	txtPlayerNames.setForeground (Color.WHITE);
	txtPlayerScores.setForeground (Color.WHITE);
	txtSearch.setForeground (Color.WHITE);

	txtPlayerScores.setEditable (false);
	txtPlayerNames.setEditable (false);

	txtPlayerScores.setBackground (color);
	txtPlayerNames.setBackground (color);

	//positioning buttons
	btnName.setBounds (155, 180, 210, 26);
	btnScore.setBounds (368, 180, 90, 26);
	btnMenu.setBounds (205, 544, 209, 42);
	btnClose.setBounds (559, 24, 37, 37);
	txtSearch.setBounds (230, 135, 150, 26);
	btnSearch.setBounds (380, 136, 26, 26);
	border.setBounds (0, 0, 620, 620);
	lblBackgroundImage.setBounds (0, 0, 620, 620);
	txtPlayerNames.setBounds (156, 209, 208, 315);
	txtPlayerScores.setBounds (368, 209, 89, 315);
	lblDescendingIcon.setBounds (437, 188, 15, 12);

	//add elements to frame
	frame.add (border);
	frame.add (notFoundError);
	frame.add (lblAscendingIcon);
	frame.add (lblDescendingIcon);
	frame.add (btnClosePressed);
	frame.add (btnCloseHover);
	frame.add (btnClose);
	frame.add (btnMenuPressed);
	frame.add (btnMenuHover);
	frame.add (btnMenu);
	frame.add (btnNamePressed);
	frame.add (btnNameHover);
	frame.add (btnName);
	frame.add (btnScorePressed);
	frame.add (btnScoreHover);
	frame.add (btnScore);
	frame.add (txtPlayerScores);
	frame.add (txtPlayerNames);
	frame.add (txtSearch);
	frame.add (btnSearchPressed);
	frame.add (btnSearchHover);
	frame.add (btnSearch);
	frame.add (lblBackgroundImage);

	//adding mouse listeners to buttons
	btnName.addMouseListener (this);
	btnScore.addMouseListener (this);
	btnClose.addMouseListener (this);
	btnMenu.addMouseListener (this);
	btnSearch.addMouseListener (this);

	txtSearch.addKeyListener (this);

	//show the frame in center of screen.
	//source: http://stackoverflow.com/questions/2442599/how-to-set-jframe-to-appear-centered-regardless-of-monitor-resolution
	Dimension dim = Toolkit.getDefaultToolkit ().getScreenSize ();
	this.setLocation (dim.width / 2 - this.getSize ().width / 2, dim.height / 2 - this.getSize ().height / 2);
	setVisible (true);                // Show the frame

	try
	{
	    FileReader fileR = new FileReader ("data/highscores.dat");
	    BufferedReader input = new BufferedReader (fileR);

	    index = Integer.parseInt (input.readLine ());

	    playerNames = new String [index];

	    playerHighScores = new int [index];

	    for (int i = 0 ; i < playerNames.length ; i++)
	    {
		playerNames [i] = input.readLine ();
		playerHighScores [i] = Integer.parseInt (input.readLine ());
	    }

	    fileR.close (); //close the file
	}
	catch (Exception e)
	{
	}

	populateChart ();
    }


    public static void populateChart ()
    {
	scoreDescendingOrder (playerHighScores, playerNames);

	nameList = playerlist (playerNames);

	scoreList = playerScoreList (playerHighScores);

	txtPlayerNames.setText (nameList);

	txtPlayerScores.setText (scoreList);
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
	//if the user presses left mouse button on the translate button, new image is shown showing the button being pressed in.
	else if (me.getSource () == btnName)
	{
	    btnNamePressed.setBounds (155, 180, 210, 26);

	}
	//if the user presses left mouse button on the clear button, new image is shown showing the button being pressed in.
	else if (me.getSource () == btnScore)
	{
	    btnScorePressed.setBounds (368, 180, 90, 26);

	}
	//if the user presses left mouse button on the clear button, new image is shown showing the button being pressed in.
	else if (me.getSource () == btnMenu)
	{
	    btnMenuPressed.setBounds (205, 544, 209, 42);
	}
	else if (me.getSource () == btnSearch)
	{
	    btnSearchPressed.setBounds (380, 136, 26, 26);
	}
    } // mousePressed method


    public void mouseReleased (MouseEvent me)
    {
	//if the user releases left mouse button on the close button, program closes.
	if (me.getSource () == btnClose)
	{
	    System.exit (0);
	}
	//if the user releases left mouse button on the clear button, textfields are cleared, and button pressed image is removed.
	else if (me.getSource () == btnScore)
	{
	    btnScorePressed.setBounds (0, 0, 0, 0);
	    btnScoreCounter++;
	    btnNameCounter = 0;
	    if (btnScoreCounter % 2 == 0) //if the button counter number is even
	    {
		lblAscendingIcon.setBounds (437, 188, 15, 12);
		lblDescendingIcon.setBounds (0, 0, 0, 0);

		scoreAscendingOrder (playerHighScores, playerNames);

		nameList = playerlist (playerNames);

		scoreList = playerScoreList (playerHighScores);

		txtPlayerNames.setText (nameList);

		txtPlayerScores.setText (scoreList);
	    }
	    else //otherwise when the button counter is odd
	    {
		lblDescendingIcon.setBounds (437, 188, 15, 12);
		lblAscendingIcon.setBounds (0, 0, 0, 0);

		scoreDescendingOrder (playerHighScores, playerNames);

		nameList = playerlist (playerNames);

		scoreList = playerScoreList (playerHighScores);

		txtPlayerNames.setText (nameList);

		txtPlayerScores.setText (scoreList);
	    }
	}
	//if the user releases left mouse button on the translate button, input is translated.
	else if (me.getSource () == btnName)
	{
	    btnNamePressed.setBounds (0, 0, 0, 0);

	    btnNameCounter++; //add one to the button counter
	    btnScoreCounter = 0;

	    if (btnNameCounter % 2 == 0) //if the button counter number is even
	    {
		lblAscendingIcon.setBounds (344, 188, 15, 12);
		lblDescendingIcon.setBounds (0, 0, 0, 0);

		namesAscendingOrder (playerHighScores, playerNames);

		nameList = playerlist (playerNames);

		scoreList = playerScoreList (playerHighScores);

		txtPlayerNames.setText (nameList);

		txtPlayerScores.setText (scoreList);
	    }
	    else //otherwise when the button counter is odd
	    {
		lblDescendingIcon.setBounds (344, 188, 15, 12);
		lblAscendingIcon.setBounds (0, 0, 0, 0);
		namesDescendingOrder (playerHighScores, playerNames);

		nameList = playerlist (playerNames);

		scoreList = playerScoreList (playerHighScores);

		txtPlayerNames.setText (nameList);

		txtPlayerScores.setText (scoreList);
	    }
	}
	else if (me.getSource () == btnMenu)
	{
	    btnMenuPressed.setBounds (0, 0, 0, 0);
	    new MainMenu ();
	    dispose (); //get rid of window completely
	}
	else if (me.getSource () == btnSearch)
	{
	    btnSearchPressed.setBounds (0, 0, 0, 0);

	    findName (txtSearch.getText (), playerNames); //call search method
	}
    } // mouseReleased method


    public void mouseEntered (MouseEvent me)
    {
	//if the user hovers the cursor over the close button, new image is shown showing the button being lit up
	if (me.getSource () == btnClose)
	{
	    btnCloseHover.setBounds (473, 21, 209, 42);
	}
	//if the user hovers the cursor over the translate button, new image is shown showing the button being lit up
	else if (me.getSource () == btnName)
	{
	    btnNameHover.setBounds (155, 180, 210, 26);
	}
	//if the user hovers the cursor over the clear button, new image is shown showing the button being lit up
	else if (me.getSource () == btnScore)
	{
	    btnScoreHover.setBounds (368, 180, 90, 26);
	}
	//if the user hovers the cursor over the clear button, new image is shown showing the button being lit up
	else if (me.getSource () == btnMenu)
	{
	    btnMenuHover.setBounds (205, 544, 209, 42);
	}
	else if (me.getSource () == btnSearch)
	{
	    btnSearchHover.setBounds (380, 136, 26, 26);
	}
    } // mouseEntered method


    public void mouseExited (MouseEvent me)
    {
	//if the user stops hovering the cursor over the close button, the lit up button image is removed.
	if (me.getSource () == btnClose)
	{
	    btnCloseHover.setBounds (0, 0, 0, 0);
	}
	//if the user stops hovering the cursor over the translate button, the lit up button image is removed.
	else if (me.getSource () == btnName)
	{
	    btnNameHover.setBounds (0, 0, 0, 0);
	}
	//if the user stops hovering the cursor over the clear button, the lit up button image is removed.
	else if (me.getSource () == btnScore)
	{
	    btnScoreHover.setBounds (0, 0, 0, 0);
	}
	else if (me.getSource () == btnMenu)
	{
	    btnMenuHover.setBounds (0, 0, 0, 0);
	}
	else if (me.getSource () == btnSearch)
	{
	    btnSearchHover.setBounds (0, 0, 0, 0);
	}
    } // mouseExited method

    /*
    ------------------------------------------------------------------------------------------
				    KeyListener Methods
    ------------------------------------------------------------------------------------------
    */
    
    public void keyPressed (KeyEvent ke)
    {
    }
    
    public void keyTyped (KeyEvent ke)
    {
    }
    
    public void keyReleased (KeyEvent ke)
    {
	findName (txtSearch.getText (), playerNames); 
    }
    
    /*
    --------------------------------------------------------------
    Sorting Methods
    --------------------------------------------------------------
    */

    public static String playerlist (String playerNames[])
    {
	String playerList = "";
	for (int i = 0 ; i < playerNames.length ; i++)
	{
	    playerList += " " + playerNames [i] + "\n";
	}
	return playerList;
    }


    public static String playerScoreList (int playerScores[])
    {
	String playerScoresList = "";
	for (int i = 0 ; i < playerScores.length ; i++)
	{
	    playerScoresList += " " + playerScores [i] + "\n";
	}
	return playerScoresList;
    }


    public static void scoreAscendingOrder (int playerScores[], String playerNames[])
    {
	for (int i = 0 ; i < playerNames.length ; i++)
	{
	    for (int j = 0 ; j < playerNames.length - 1 ; j++)
	    {
		if (playerScores [j] > playerScores [j + 1])
		{
		    int tempPlayerScores = playerScores [j];
		    playerScores [j] = playerScores [j + 1];
		    playerScores [j + 1] = tempPlayerScores;

		    String tempPlayerNames = playerNames [j];
		    playerNames [j] = playerNames [j + 1];
		    playerNames [j + 1] = tempPlayerNames;
		}
	    }
	}
    }


    public static void scoreDescendingOrder (int playerScores[], String playerNames[])
    {
	for (int i = 0 ; i < playerNames.length ; i++)
	{
	    for (int j = 0 ; j < playerNames.length - 1 ; j++)
	    {
		if (playerScores [j] < playerScores [j + 1])
		{
		    int tempPlayerScores = playerScores [j];
		    playerScores [j] = playerScores [j + 1];
		    playerScores [j + 1] = tempPlayerScores;

		    String tempPlayerNames = playerNames [j];
		    playerNames [j] = playerNames [j + 1];
		    playerNames [j + 1] = tempPlayerNames;
		}
	    }
	}
    }


    public static void namesDescendingOrder (int playerScores[], String playerNames[])
    {
	for (int i = 0 ; i < playerNames.length ; i++)
	{
	    for (int j = 0 ; j < playerNames.length - 1 ; j++)
	    {
		if (playerNames [j + 1].compareTo (playerNames [j]) < 0)
		{
		    String tempPlayerNames = playerNames [j];
		    playerNames [j] = playerNames [j + 1];
		    playerNames [j + 1] = tempPlayerNames;

		    int tempPlayerScores = playerScores [j];
		    playerScores [j] = playerScores [j + 1];
		    playerScores [j + 1] = tempPlayerScores;
		}
	    }
	}
    }


    public static void namesAscendingOrder (int playerScores[], String playerNames[])
    {
	for (int i = 0 ; i < playerNames.length ; i++)
	{
	    for (int j = 0 ; j < playerNames.length - 1 ; j++)
	    {
		if (playerNames [j + 1].compareTo (playerNames [j]) > 0)
		{
		    String tempPlayerNames = playerNames [j];
		    playerNames [j] = playerNames [j + 1];
		    playerNames [j + 1] = tempPlayerNames;

		    int tempPlayerScores = playerScores [j];
		    playerScores [j] = playerScores [j + 1];
		    playerScores [j + 1] = tempPlayerScores;
		}
	    }
	}
    }


    public static void findName (String name, String array[])
    {
	boolean found = false;
	//looping through array
	for (int i = 0 ; i < array.length ; i++)
	{
	    //checking if name is in the array
	    if (name.equalsIgnoreCase (array [i]) == true)
	    {
		txtPlayerNames.setText ("");
		txtPlayerScores.setText ("");
		txtPlayerNames.setText (" " + playerNames [i]);
		txtPlayerScores.setText (" " + playerHighScores [i]);
		found = true;
	    }
	}

	if (found == false && !name.equals (""))
	{
	    notFoundError.setBounds (424, 139, 100, 25);
	    populateChart ();
	}
	else
	{
	    notFoundError.setBounds (0, 0, 0, 0);
	}
    }
} // HighScoresMenu class
