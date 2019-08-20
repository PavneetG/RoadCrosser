package roadcrosser;
// The "MainMenu" class.
import java.awt.*;
import java.awt.event.*; // for listening to buttons
import javax.swing.*;
import java.text.*;
public class MainMenu extends JFrame implements MouseListener
{
    //declare the container
    Container frame;
    //declaring labels for graphics
    JLabel btnPlay, btnLogout, btnLogoutPress, lblCurrentUser, btnPlayHover, btnPlayPressed, btnHighscores, btnHighscoresHover, btnHighscoresPressed, btnClose, btnCloseHover, btnClosePressed, btnHelp, btnHelpHover, btnHelpPressed, btnCharacters, btnCharactersPressed, btnCharactersHover, btnAchievements, btnAchievementsHover, btnAchievementsPressed, lblBackgroundImage;
    public MainMenu ()
    {
	super ("Road Crosser");     // Set the frame's name

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

	lblBackgroundImage = new JLabel (new ImageIcon ("graphics/backgrounds/menu.jpg"));

	//button initialization
	btnPlay = new JLabel (new ImageIcon ("graphics/buttons/main menu/playButton.gif"));
	btnPlayHover = new JLabel (new ImageIcon ("graphics/buttons/main menu/playButtonHover.gif"));
	btnPlayPressed = new JLabel (new ImageIcon ("graphics/buttons/main menu/playButtonPress.gif"));

	btnLogout = new JLabel (new ImageIcon ("graphics/buttons/main menu/logout.png"));
	btnLogoutPress = new JLabel (new ImageIcon ("graphics/buttons/main menu/logoutPress.png"));
	lblCurrentUser = new JLabel (LoginMenu.currentUser);

	btnClose = new JLabel (new ImageIcon ("graphics/buttons/closeButton.png"));
	btnCloseHover = new JLabel (new ImageIcon ("graphics/buttons/closeButtonHover.png"));
	btnClosePressed = new JLabel (new ImageIcon ("graphics/buttons/closeButtonPress.png"));

	btnAchievements = new JLabel (new ImageIcon ("graphics/buttons/main menu/achievementsButton.png"));
	btnAchievementsHover = new JLabel (new ImageIcon ("graphics/buttons/main menu/achievementsButtonHover.gif"));
	btnAchievementsPressed = new JLabel (new ImageIcon ("graphics/buttons/main menu/achievementsButtonPress.png"));

	btnCharacters = new JLabel (new ImageIcon ("graphics/buttons/main menu/characters.png"));
	btnCharactersHover = new JLabel (new ImageIcon ("graphics/buttons/main menu/charactersHover.gif"));
	btnCharactersPressed = new JLabel (new ImageIcon ("graphics/buttons/main menu/charactersPress.png"));

	btnHelp = new JLabel (new ImageIcon ("graphics/buttons/main menu/helpButton.gif"));
	btnHelpHover = new JLabel (new ImageIcon ("graphics/buttons/main menu/helpButtonHover.gif"));
	btnHelpPressed = new JLabel (new ImageIcon ("graphics/buttons/main menu/helpButtonPress.gif"));

	btnHighscores = new JLabel (new ImageIcon ("graphics/buttons/main menu/highscoresButton.png"));
	btnHighscoresHover = new JLabel (new ImageIcon ("graphics/buttons/main menu/highscoresButtonHover.gif"));
	btnHighscoresPressed = new JLabel (new ImageIcon ("graphics/buttons/main menu/highscoresButtonPress.png"));

	//positioning buttons
	btnHelp.setBounds (205, 465, 209, 42);
	btnHighscores.setBounds (205, 520, 209, 42);
	btnCharacters.setBounds (205, 355, 209, 42);
	btnPlay.setBounds (205, 300, 209, 42);
	btnClose.setBounds (559, 24, 37, 37);
	btnAchievements.setBounds (205, 410, 209, 42);
	lblBackgroundImage.setBounds (0, 0, 620, 620);
	btnLogout.setBounds (170, 22, 43, 43);

	lblCurrentUser.setFont (new Font ("Calibri", Font.BOLD, 19));
	lblCurrentUser.setForeground (new Color (250, 166, 54));
	lblCurrentUser.setBounds (32, 47, 120, 18);



	//add elements to frame
	frame.add (btnLogoutPress);
	frame.add (btnLogout);
	frame.add (lblCurrentUser);
	frame.add (btnHighscoresPressed);
	frame.add (btnHighscoresHover);
	frame.add (btnHighscores);
	frame.add (btnClosePressed);
	frame.add (btnCloseHover);
	frame.add (btnClose);
	frame.add (btnAchievementsPressed);
	frame.add (btnAchievementsHover);
	frame.add (btnAchievements);
	frame.add (btnPlayPressed);
	frame.add (btnPlayHover);
	frame.add (btnPlay);
	frame.add (btnHelpPressed);
	frame.add (btnHelpHover);
	frame.add (btnHelp);
	frame.add (btnCharactersPressed);
	frame.add (btnCharactersHover);
	frame.add (btnCharacters);
	frame.add (lblBackgroundImage);

	//adding mouse listeners to buttons
	btnCharacters.addMouseListener (this);
	btnHelp.addMouseListener (this);
	btnClose.addMouseListener (this);
	btnAchievements.addMouseListener (this);
	btnPlay.addMouseListener (this);
	btnHighscores.addMouseListener (this);
	btnLogout.addMouseListener(this);

	//show the frame in center of screen.
	//source: http://stackoverflow.com/questions/2442599/how-to-set-jframe-to-appear-centered-regardless-of-monitor-resolution
	Dimension dim = Toolkit.getDefaultToolkit ().getScreenSize ();
	this.setLocation (dim.width / 2 - this.getSize ().width / 2, dim.height / 2 - this.getSize ().height / 2);


	setVisible (true);                // Show the frame
    } // Constructor


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
	else if (me.getSource () == btnPlay)
	{
	    btnPlayPressed.setBounds (205, 300, 209, 42);
	}
	//if the user presses left mouse button on the clear button, new image is shown showing the button being pressed in.
	else if (me.getSource () == btnHelp)
	{
	    btnHelpPressed.setBounds (205, 465, 209, 42);
	}
	//if the user presses left mouse button on the clear button, new image is shown showing the button being pressed in.
	else if (me.getSource () == btnAchievements)
	{
	    btnAchievementsPressed.setBounds (205, 410, 209, 42);
	}
	//if the user presses left mouse button on the clear button, new image is shown showing the button being pressed in.
	else if (me.getSource () == btnCharacters)
	{
	    btnCharactersPressed.setBounds (205, 355, 209, 42);
	}
	else if (me.getSource () == btnHighscores)
	{
	    btnHighscoresPressed.setBounds (205, 520, 209, 42);
	}
	else if (me.getSource () == btnLogout)
	{
	    btnLogoutPress.setBounds (170, 22, 43, 43);
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
	else if (me.getSource () == btnPlay)
	{
	    new MainGame ();
	    dispose ();
	}
	//if the user releases left mouse button on the translate button, input is translated.
	else if (me.getSource () == btnHelp)
	{
	    new HelpMenu ();
	    dispose ();
	}
	else if (me.getSource () == btnAchievements)
	{
	    new AchievementsMenu();
	    dispose();
	}
	else if (me.getSource () == btnCharacters)
	{
	    new CharactersMenu();
	    dispose();
	}
	else if (me.getSource () == btnHighscores)
	{
	    new HighScoresMenu ();
	    dispose ();
	}
	else if (me.getSource () == btnLogout)
	{
	    EndScreen.userHighScore = 0;
	    new LoginMenu();
	    dispose();
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
	else if (me.getSource () == btnCharacters)
	{
	    btnCharactersHover.setBounds (205, 355, 209, 42);
	}
	//if the user hovers the cursor over the clear button, new image is shown showing the button being lit up
	else if (me.getSource () == btnPlay)
	{
	    btnPlayHover.setBounds (205, 300, 209, 42);
	}
	//if the user hovers the cursor over the clear button, new image is shown showing the button being lit up
	else if (me.getSource () == btnHelp)
	{
	    btnHelpHover.setBounds (205, 465, 209, 42);
	}
	//if the user hovers the cursor over the clear button, new image is shown showing the button being lit up
	else if (me.getSource () == btnAchievements)
	{
	    btnAchievementsHover.setBounds (205, 410, 209, 42);
	}
	else if (me.getSource () == btnHighscores)
	{
	    btnHighscoresHover.setBounds (205, 520, 209, 42);
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
	else if (me.getSource () == btnHelp)
	{
	    btnHelpHover.setBounds (0, 0, 0, 0);
	}
	//if the user stops hovering the cursor over the clear button, the lit up button image is removed.
	else if (me.getSource () == btnAchievements)
	{
	    btnAchievementsHover.setBounds (0, 0, 0, 0);
	}
	else if (me.getSource () == btnPlay)
	{
	    btnPlayHover.setBounds (0, 0, 0, 0);
	}
	else if (me.getSource () == btnCharacters)
	{
	    btnCharactersHover.setBounds (0, 0, 0, 0);
	}
	else if (me.getSource () == btnHighscores)
	{
	    btnHighscoresHover.setBounds (0, 0, 0, 0);
	}
    } // mouseExited method
} // MainMenu class
