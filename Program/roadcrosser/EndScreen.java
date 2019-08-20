package roadcrosser;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.io.*;
// The "EndScreen" class.
public class EndScreen extends JFrame implements MouseListener
{
    JLabel score, background, lblPersonalBest, lblAllTimeBest, btnMenu, btnMenuPress, btnMenuHover, btnRestart, btnRestartPress, btnRestartHover, btnScores, btnScoresPress, btnScoresHover, btnClose, btnClosePressed, btnCloseHover, border;
    Container frame;
    public static int userHighScore;
    static int allTimeBest;

    public EndScreen ()
    {
	super ("Road Crosser");
	setUndecorated (true);
	setSize (620, 620);
	frame = getContentPane ();
	setDefaultCloseOperation (EXIT_ON_CLOSE);
	frame.setLayout (null);

	updateHighScores ();
	updateUsers();

	score = new JLabel (Integer.toString (MainGame.score), SwingConstants.CENTER);
	lblPersonalBest = new JLabel (Integer.toString (userHighScore), SwingConstants.CENTER);
	lblAllTimeBest = new JLabel (Integer.toString (allTimeBest), SwingConstants.CENTER);

	btnRestart = new JLabel (new ImageIcon ("graphics/buttons/endscreen/btnRestart.png"));
	btnRestartPress = new JLabel (new ImageIcon ("graphics/buttons/endscreen/btnRestartPress.png"));
	btnRestartHover = new JLabel (new ImageIcon ("graphics/buttons/endscreen/btnRestartHover.gif"));

	btnMenu = new JLabel (new ImageIcon ("graphics/buttons/endscreen/btnMenu.png"));
	btnMenuPress = new JLabel (new ImageIcon ("graphics/buttons/endscreen/btnMenuPress.png"));
	btnMenuHover = new JLabel (new ImageIcon ("graphics/buttons/endscreen/btnMenuHover.gif"));

	btnScores = new JLabel (new ImageIcon ("graphics/buttons/endscreen/btnScores.png"));
	btnScoresPress = new JLabel (new ImageIcon ("graphics/buttons/endscreen/btnScoresPress.png"));
	btnScoresHover = new JLabel (new ImageIcon ("graphics/buttons/endscreen/btnScoresHover.gif"));

	btnClose = new JLabel (new ImageIcon ("graphics/buttons/closeButton.png"));
	btnCloseHover = new JLabel (new ImageIcon ("graphics/buttons/closeButtonHover.png"));
	btnClosePressed = new JLabel (new ImageIcon ("graphics/buttons/closeButtonPress.png"));

	background = new JLabel (new ImageIcon ("graphics/backgrounds/endscreen.jpg"));
	border = new JLabel (new ImageIcon ("graphics/misc/border.png"));

	setIconImage (new ImageIcon ("graphics/misc/logo.png").getImage ());

	background.setBounds (0, 0, 620, 620);
	border.setBounds(0,0,620,620);
	btnRestart.setBounds (120, 448, 86, 87);
	btnMenu.setBounds (265, 448, 86, 87);
	btnScores.setBounds (410, 448, 86, 87);
	btnClose.setBounds (473, 21, 209, 42);

	score.setBounds (70, 225, 275, 145);

	lblPersonalBest.setBounds (370, 180, 176, 106);
	lblPersonalBest.setFont (new Font ("Calibri", Font.BOLD, 70));
	lblPersonalBest.setForeground (new Color (230, 230, 230));

	lblAllTimeBest.setBounds (370, 309, 176, 106);
	lblAllTimeBest.setFont (new Font ("Calibri", Font.BOLD, 70));
	lblAllTimeBest.setForeground (new Color (230, 230, 230));

	Font font = new Font ("Calibri", Font.BOLD, 110);
	score.setFont (font);
	score.setForeground (new Color (230, 230, 230));
	
	frame.add(border);
	frame.add (lblPersonalBest);
	frame.add (lblAllTimeBest);
	frame.add (btnClosePressed);
	frame.add (btnCloseHover);
	frame.add (btnClose);
	frame.add (btnScoresPress);
	frame.add (btnScoresHover);
	frame.add (btnScores);
	frame.add (btnRestartPress);
	frame.add (btnRestartHover);
	frame.add (btnRestart);
	frame.add (btnMenuPress);
	frame.add (btnMenuHover);
	frame.add (btnMenu);
	frame.add (btnScores);
	frame.add (score);
	frame.add (background);

	//show the frame in center of screen.
	//source: http://stackoverflow.com/questions/2442599/how-to-set-jframe-to-appear-centered-regardless-of-monitor-resolution
	Dimension dim = Toolkit.getDefaultToolkit ().getScreenSize ();
	this.setLocation (dim.width / 2 - this.getSize ().width / 2, dim.height / 2 - this.getSize ().height / 2);

	setVisible (true);

	btnRestart.addMouseListener (this);
	btnMenu.addMouseListener (this);
	btnScores.addMouseListener (this);
	btnClose.addMouseListener (this);
    }


    public static void updateUsers ()
    {
	try
	{
	    FileReader fileR = new FileReader ("data/users.dat");
	    BufferedReader input = new BufferedReader (fileR);

	    int index = Integer.parseInt (input.readLine ());

	    String[] usernames = new String [index];

	    String[] passwords = new String [index];

	    int[] coins = new int [index];

	    for (int i = 0 ; i < usernames.length ; i++)
	    {
		usernames [i] = input.readLine ();
		passwords [i] = input.readLine ();
		coins [i] = Integer.parseInt (input.readLine ());
	    }

	    fileR.close (); //close the file

	    for (int i = 0 ; i < usernames.length ; i++)
	    {
		if (usernames [i].equals (LoginMenu.currentUser))
		{
		    coins [i] = LoginMenu.coinCount;
		}
	    }

	    FileWriter fileW = new FileWriter ("data/users.dat");
	    PrintWriter output = new PrintWriter (fileW);
	    output.println (index);

	    for (int i = 0 ; i < usernames.length ; i++)
	    {
		output.println (usernames [i]);
		output.println (passwords [i]);
		output.println (coins [i]);
	    }
	    output.close ();

	}
	catch (Exception e)
	{
	}
    }


    public static void updateHighScores ()
    {
	try
	{
	    FileReader fileR = new FileReader ("data/highscores.dat");
	    BufferedReader input = new BufferedReader (fileR);

	    int index = Integer.parseInt (input.readLine ());
	    index += 1;

	    String[] playerNames = new String [index];

	    int[] playerHighScores = new int [index];

	    for (int i = 0 ; i < playerNames.length - 1 ; i++)
	    {
		playerNames [i] = input.readLine ();
		playerHighScores [i] = Integer.parseInt (input.readLine ());
	    }

	    fileR.close (); //close the file

	    boolean returnUser = true;

	    for (int i = 0 ; i < playerNames.length - 1 ; i++)
	    {
		if (playerNames [i].equals (LoginMenu.currentUser))
		{
		    if (MainGame.score > playerHighScores [i])
		    {
			playerHighScores [i] = MainGame.score;
			userHighScore = MainGame.score;
		    }

		    userHighScore = playerHighScores [i];
		    returnUser = true;
		    index--;
		    break;

		}
		else
		{
		    returnUser = false;
		}
	    }

	    if (returnUser == false)
	    {
		playerHighScores [playerHighScores.length - 1] = MainGame.score;
		playerNames [playerNames.length - 1] = LoginMenu.currentUser;
		userHighScore = MainGame.score;
	    }

	    FileWriter fileW = new FileWriter ("data/highscores.dat");
	    PrintWriter output = new PrintWriter (fileW);
	    output.println (index);

	    for (int i = 0 ; i < index ; i++)
	    {
		output.println (playerNames [i]);
		output.println (playerHighScores [i]);
	    }

	    output.close ();

	    HighScoresMenu.scoreDescendingOrder (playerHighScores, playerNames);
	    allTimeBest = playerHighScores [0];
	}
	catch (Exception e)
	{
	}
    }


    public void mouseClicked (MouseEvent e)
    {
    }


    public void mouseEntered (MouseEvent e)
    {
	if (e.getSource () == btnRestart)
	{
	    btnRestartHover.setBounds (120, 448, 86, 87);
	}
	else if (e.getSource () == btnMenu)
	{
	    btnMenuHover.setBounds (265, 448, 86, 87);
	}
	else if (e.getSource () == btnScores)
	{
	    btnScoresHover.setBounds (410, 448, 86, 87);
	}
	else if (e.getSource () == btnClose)
	{
	    btnCloseHover.setBounds (473, 21, 209, 42);
	}
    }


    public void mouseExited (MouseEvent e)
    {
	if (e.getSource () == btnRestart)
	{
	    btnRestartHover.setBounds (0, 0, 0, 0);
	}
	else if (e.getSource () == btnMenu)
	{
	    btnMenuHover.setBounds (0, 0, 0, 0);
	}
	else if (e.getSource () == btnScores)
	{
	    btnScoresHover.setBounds (0, 0, 0, 0);
	}
	else if (e.getSource () == btnClose)
	{
	    btnCloseHover.setBounds (0, 0, 0, 0);
	}
    }


    public void mousePressed (MouseEvent e)
    {
	if (e.getSource () == btnRestart)
	{
	    btnRestartPress.setBounds (120, 448, 86, 87);
	}
	else if (e.getSource () == btnMenu)
	{
	    btnMenuPress.setBounds (265, 448, 86, 87);
	}
	else if (e.getSource () == btnScores)
	{
	    btnScoresPress.setBounds (410, 448, 86, 87);
	}
	else if (e.getSource () == btnClose)
	{
	    btnClosePressed.setBounds (473, 21, 209, 42);
	}
    }


    public void mouseReleased (MouseEvent e)
    {
	if (e.getSource () == btnRestart)
	{
	    MainGame.score = 0;
	    new MainGame ();
	    dispose ();
	}
	else if (e.getSource () == btnMenu)
	{
	    MainGame.score = 0;
	    new MainMenu ();
	    dispose ();
	}
	else if (e.getSource () == btnScores)
	{
	    MainGame.score = 0;
	    new HighScoresMenu ();
	    dispose ();
	}
	else if (e.getSource () == btnClose)
	{
	    System.exit (0);
	}
    }
} // EndScreen class
