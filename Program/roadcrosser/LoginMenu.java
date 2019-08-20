package roadcrosser;
// The "LoginMenu" class.
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

public class LoginMenu extends JFrame implements MouseListener, KeyListener, FocusListener, ActionListener
{
    Container frame;
    JLabel background, border, confirmPassField, confirmPassText, textFieldSelected, textFieldError, textFieldError1, textFieldError2, lblInvalidLogin, btnLogin, btnLoginPress, btnLoginHover, btnCreate, btnCreateHover, btnCreatePress, btnCancel, btnCancelPress, btnCancelHover, lblPassMatchError, lblPassLengthError, lblUsernameError, btnClose, btnCloseHover, btnClosePressed;
    JTextField usernameIn;
    JPasswordField passwordIn, cPasswordIn;
    boolean confirmEnabled = false;
    boolean validName = false;
    boolean validPass = false;
    boolean passMatch = false;
    int arraySize;
    public static int userNumber;

    public static String[] usernames;
    public static String[] passwords;
    public static int coinCount;
    public static int[] userCoins;

    public static String currentUser;

    public LoginMenu ()
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
	setSize (620, 620);

	usernameIn = new JTextField ();
	passwordIn = new JPasswordField ();
	cPasswordIn = new JPasswordField ();
	cPasswordIn.enable (false);

	lblInvalidLogin = new JLabel ("Invalid Username & Password Combination", SwingConstants.CENTER);
	lblUsernameError = new JLabel ("*Username is taken.");
	lblPassMatchError = new JLabel ("*Passwords do not match!");
	lblPassLengthError = new JLabel ("*3 character minimum.");

	background = new JLabel (new ImageIcon ("graphics/backgrounds/loginScreenExample.jpg"));
	border = new JLabel (new ImageIcon ("graphics/misc/border.png"));

	confirmPassField = new JLabel (new ImageIcon ("graphics/text/loginTextField.png"));
	confirmPassText = new JLabel (new ImageIcon ("graphics/text/confirmPassText.png"));
	textFieldSelected = new JLabel (new ImageIcon ("graphics/text/loginTextFieldSelected.png"));
	textFieldError = new JLabel (new ImageIcon ("graphics/text/loginTextFieldError.png"));
	textFieldError1 = new JLabel (new ImageIcon ("graphics/text/loginTextFieldError.png"));
	textFieldError2 = new JLabel (new ImageIcon ("graphics/text/loginTextFieldError.png"));

	btnLogin = new JLabel (new ImageIcon ("graphics/buttons/login/btnLogin.png"));
	btnLoginPress = new JLabel (new ImageIcon ("graphics/buttons/login/btnLoginPress.png"));
	btnLoginHover = new JLabel (new ImageIcon ("graphics/buttons/login/btnLoginHover.gif"));

	btnCreate = new JLabel (new ImageIcon ("graphics/buttons/login/btnCreate.png"));
	btnCreatePress = new JLabel (new ImageIcon ("graphics/buttons/login/btnCreatePress.png"));
	btnCreateHover = new JLabel (new ImageIcon ("graphics/buttons/login/btnCreateHover.gif"));

	btnCancel = new JLabel (new ImageIcon ("graphics/buttons/login/btnCancel.png"));
	btnCancelPress = new JLabel (new ImageIcon ("graphics/buttons/login/btnCancelPress.png"));
	btnCancelHover = new JLabel (new ImageIcon ("graphics/buttons/login/btnCancelHover.gif"));

	btnClose = new JLabel (new ImageIcon ("graphics/buttons/closeButton.png"));
	btnCloseHover = new JLabel (new ImageIcon ("graphics/buttons/closeButtonHover.png"));
	btnClosePressed = new JLabel (new ImageIcon ("graphics/buttons/closeButtonPress.png"));

	background.setBounds (0, 0, 620, 620);
	border.setBounds (0, 0, 620, 620);

	textFieldSelected.setBounds (171, 305, 278, 48);
	cPasswordIn.setBounds (194, 490, 232, 34);

	btnClose.setBounds (559, 24, 37, 37);
	btnLogin.setBounds (201, 464, 209, 42);
	btnCreate.setBounds (201, 519, 209, 42);

	usernameIn.setBounds (194, 312, 232, 34);
	passwordIn.setBounds (194, 401, 232, 34);

	Font fieldFont = new Font ("Arial", Font.BOLD, 20);
	Color color = new Color (230, 230, 230);
	usernameIn.setOpaque (false);
	usernameIn.setBorder (null);
	usernameIn.setHorizontalAlignment (JTextField.CENTER);
	usernameIn.setForeground (color);
	usernameIn.setFont (fieldFont);

	passwordIn.setOpaque (false);
	passwordIn.setBorder (null);
	passwordIn.setHorizontalAlignment (JTextField.CENTER);
	passwordIn.setForeground (color);
	passwordIn.setFont (fieldFont);

	cPasswordIn.setOpaque (false);
	cPasswordIn.setBorder (null);
	cPasswordIn.setHorizontalAlignment (JTextField.CENTER);
	cPasswordIn.setForeground (color);
	cPasswordIn.setFont (fieldFont);

	Font errorFont = new Font ("Calibri", Font.BOLD, 12);
	Color errorColor = new Color (223, 58, 58);

	lblInvalidLogin.setFont (errorFont);
	lblInvalidLogin.setForeground (errorColor);

	lblPassLengthError.setFont (errorFont);
	lblPassLengthError.setForeground (errorColor);

	lblPassMatchError.setFont (errorFont);
	lblPassMatchError.setForeground (errorColor);

	lblUsernameError.setFont (errorFont);
	lblUsernameError.setForeground (errorColor);

	frame.add (confirmPassText);
	frame.add (lblPassMatchError);
	frame.add (lblPassLengthError);
	frame.add (lblUsernameError);
	frame.add (lblInvalidLogin);
	frame.add (usernameIn);
	frame.add (cPasswordIn);
	frame.add (passwordIn);
	frame.add (textFieldError);
	frame.add (textFieldError1);
	frame.add (textFieldError2);
	frame.add (textFieldSelected);
	frame.add (confirmPassField);
	frame.add (btnCancelPress);
	frame.add (btnCancelHover);
	frame.add (btnCancel);
	frame.add (btnCreatePress);
	frame.add (btnCreateHover);
	frame.add (btnCreate);
	frame.add (btnLoginPress);
	frame.add (btnLoginHover);
	frame.add (btnLogin);
	frame.add (btnClosePressed);
	frame.add (btnCloseHover);
	frame.add (btnClose);
	frame.add (background);
	frame.add (border);

	//show the frame in center of screen.
	//source: http://stackoverflow.com/questions/2442599/how-to-set-jframe-to-appear-centered-regardless-of-monitor-resolution
	Dimension dim = Toolkit.getDefaultToolkit ().getScreenSize ();
	this.setLocation (dim.width / 2 - this.getSize ().width / 2, dim.height / 2 - this.getSize ().height / 2);

	usernameIn.addFocusListener (this);
	passwordIn.addFocusListener (this);
	cPasswordIn.addFocusListener (this);

	usernameIn.addKeyListener (this);
	passwordIn.addKeyListener (this);
	cPasswordIn.addKeyListener (this);

	usernameIn.addActionListener (this);
	passwordIn.addActionListener (this);
	cPasswordIn.addActionListener (this);

	btnLogin.addMouseListener (this);
	btnCreate.addMouseListener (this);
	btnCancel.addMouseListener (this);
	btnClose.addMouseListener (this);

	setVisible (true);

	loadUsers ();
    } // Constructor


    public void loadUsers ()
    {
	try
	{
	    FileReader fileR = new FileReader ("data/users.dat");
	    BufferedReader input = new BufferedReader (fileR);

	    arraySize = Integer.parseInt (input.readLine ());
	    arraySize++;

	    usernames = new String [arraySize];
	    passwords = new String [arraySize];
	    userCoins = new int [arraySize];

	    for (int i = 0 ; i < usernames.length - 1 ; i++)
	    {
		usernames [i] = input.readLine ();
		passwords [i] = input.readLine ();
		userCoins [i] = Integer.parseInt (input.readLine ());
		System.out.println (usernames [i]);
	    }

	    input.close ();
	}
	catch (Exception e)
	{
	}
    }


    public void actionPerformed (ActionEvent ae)
    {
	if (ae.getSource () == usernameIn || ae.getSource () == passwordIn && confirmEnabled == false)
	{
	    try
	    {
		login (usernameIn.getText (), passwordIn.getText ());
	    }
	    catch (Exception e)
	    {
	    }
	}
	else
	{
	    try
	    {
		createAccount (usernameIn.getText (), passwordIn.getText ());
	    }
	    catch (Exception e)
	    {
	    }
	}
    }


    public void login (String username, String password)
    {
	for (int i = 0 ; i < usernames.length ; i++)
	{
	    if (usernames [i].equalsIgnoreCase (username) && passwords [i].equals (password))
	    {
		currentUser = usernames [i];
		coinCount = userCoins [i];
		userNumber = i + 1;
		CharactersMenu.loadCharacterData ();

		System.out.println (coinCount);
		new MainMenu ();
		dispose ();
	    }
	    else
	    {
		lblInvalidLogin.setBounds (190, 261, 250, 10);
	    }
	}
    }


    public void createAccount (String username, String password)
    {
	if (validName == true && validPass == true && passMatch == true)
	{
	    usernames [usernames.length - 1] = usernameIn.getText ();
	    passwords [passwords.length - 1] = passwordIn.getText ();
	    userCoins [userCoins.length - 1] = 0;
	    try
	    {
		FileWriter fileW = new FileWriter ("data/users.dat");
		PrintWriter output = new PrintWriter (fileW);

		output.println (arraySize);

		for (int i = 0 ; i < usernames.length ; i++)
		{
		    output.println (usernames [i]);
		    output.println (passwords [i]);
		    output.println (userCoins [i]);
		}

		output.close ();
		
		createNewCharacterData();
		createNewAchievementData();
		
		try
		{
		    login (usernameIn.getText (), passwordIn.getText ());
		}
		catch (Exception e)
		{
		}

	    }
	    catch (Exception e)
	    {
	    }
	}
    }


    public void createNewCharacterData ()
    {
	try
	{
	    //new character data
	    FileReader fileR = new FileReader ("data/characters.dat");
	    BufferedReader input = new BufferedReader (fileR);

	    int amount = Integer.parseInt (input.readLine ());

	    String[] characterData = new String [amount + 1];

	    for (int i = 0 ; i < characterData.length - 1 ; i++)
	    {
		characterData [i] = input.readLine ();
	    }

	    input.close ();

	    FileWriter fileW = new FileWriter ("data/characters.dat");
	    PrintWriter output = new PrintWriter (fileW);

	    characterData [amount] = "2000000000000000000";

	    amount++;
	    output.println (amount);
	    for (int i = 0 ; i < characterData.length ; i++)
	    {
		output.println (characterData [i]);
	    }

	    output.close ();
	}
	catch (Exception e)
	{
	}
    }


    public void createNewAchievementData ()
    {
	try
	{
	    //new achievement data
	    FileReader fileR = new FileReader ("data/achievements.dat");
	    BufferedReader input = new BufferedReader (fileR);

	    int amount = Integer.parseInt (input.readLine ());

	    String[] achievementData = new String [amount + 1];

	    for (int i = 0 ; i < achievementData.length - 1 ; i++)
	    {
		achievementData [i] = input.readLine ();
	    }

	    input.close ();

	    FileWriter fileW = new FileWriter ("data/achievements.dat");
	    PrintWriter output = new PrintWriter (fileW);

	    achievementData [amount] = "000000";

	    amount++;
	    output.println (amount);
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


    public void checkInfo (String username, String password, String cPassword)
    {
	for (int i = 0 ; i < usernames.length ; i++)
	{
	    if (usernames [i].equalsIgnoreCase (username))
	    {
		lblUsernameError.setBounds (368, 276, 200, 20);
		textFieldError.setBounds (171, 305, 278, 48);
		validName = false;
	    }
	    else
	    {
		lblUsernameError.setBounds (0, 0, 0, 0);
		textFieldError.setBounds (0, 0, 0, 0);
		validName = true;
	    }

	    if (!cPassword.equals (password))
	    {
		lblPassMatchError.setBounds (413, 453, 200, 20);
		textFieldError2.setBounds (171, 483, 278, 48);
		passMatch = false;
	    }
	    else
	    {
		lblPassMatchError.setBounds (0, 0, 0, 0);
		textFieldError2.setBounds (0, 0, 0, 0);
		passMatch = true;
	    }

	    if (password.length () < 3)
	    {
		lblPassLengthError.setBounds (368, 364, 200, 20);
		textFieldError1.setBounds (171, 394, 278, 48);
		validPass = false;
	    }
	    else
	    {
		lblPassLengthError.setBounds (0, 0, 0, 0);
		textFieldError1.setBounds (0, 0, 0, 0);
		validPass = true;
	    }
	}
    }


    public void mouseClicked (MouseEvent me)
    {

    } // mouseClicked method


    public void mousePressed (MouseEvent me)
    {
	if (me.getSource () == btnLogin)
	{
	    btnLoginPress.setBounds (201, 464, 209, 42);
	}
	else if (me.getSource () == btnCreate && confirmEnabled == false)
	{
	    btnCreatePress.setBounds (201, 519, 209, 42);
	}
	else if (me.getSource () == btnCreate)
	{
	    btnCreatePress.setBounds (80, 549, 209, 42);
	}
	else if (me.getSource () == btnCancel)
	{
	    btnCancelPress.setBounds (329, 549, 209, 42);
	}
	else if (me.getSource () == btnClose)
	{
	    btnClosePressed.setBounds (473, 21, 209, 42);
	}
    } // mousePressed method


    public void mouseReleased (MouseEvent me)
    {
	if (me.getSource () == btnLogin)
	{
	    btnLoginPress.setBounds (0, 0, 0, 0);
	    try
	    {
		login (usernameIn.getText (), passwordIn.getText ());
	    }
	    catch (Exception e)
	    {
	    }
	}
	else if (me.getSource () == btnCreate && confirmEnabled == false)
	{
	    confirmEnabled = true;
	    btnCreatePress.setBounds (0, 0, 0, 0);
	    lblInvalidLogin.setBounds (0, 0, 0, 0);

	    confirmPassField.setBounds (170, 483, 278, 48);
	    btnLogin.setBounds (0, 0, 0, 0);

	    btnCreate.setBounds (80, 549, 209, 42);
	    btnCancel.setBounds (329, 549, 209, 42);
	    confirmPassText.setBounds (214, 453, 192, 20);

	    cPasswordIn.enable (true);
	}
	else if (me.getSource () == btnCreate)
	{
	    btnCreatePress.setBounds (0, 0, 0, 0);
	    try
	    {
		createAccount (usernameIn.getText (), passwordIn.getText ());
	    }
	    catch (Exception e)
	    {
	    }
	}
	else if (me.getSource () == btnCancel)
	{
	    btnCancel.setBounds (0, 0, 0, 0);
	    btnCancelPress.setBounds (0, 0, 0, 0);

	    btnCreate.setBounds (201, 519, 209, 42);
	    btnLogin.setBounds (201, 464, 209, 42);

	    confirmPassText.setBounds (0, 0, 0, 0);
	    confirmPassField.setBounds (0, 0, 0, 0);

	    textFieldError.setBounds (0, 0, 0, 0);
	    textFieldError1.setBounds (0, 0, 0, 0);
	    textFieldError2.setBounds (0, 0, 0, 0);
	    lblUsernameError.setBounds (0, 0, 0, 0);
	    lblPassMatchError.setBounds (0, 0, 0, 0);
	    lblPassLengthError.setBounds (0, 0, 0, 0);

	    cPasswordIn.setText ("");
	    cPasswordIn.enable (false);
	    confirmEnabled = false;
	}
	else if (me.getSource () == btnClose)
	{
	    System.exit (0);
	}
    } // mouseReleased method


    public void mouseEntered (MouseEvent me)
    {
	if (me.getSource () == btnLogin)
	{
	    btnLoginHover.setBounds (201, 464, 209, 42);
	}
	else if (me.getSource () == btnCreate && confirmEnabled == false)
	{
	    btnCreateHover.setBounds (201, 519, 209, 42);
	}
	else if (me.getSource () == btnCreate)
	{
	    btnCreateHover.setBounds (80, 549, 209, 42);
	}
	else if (me.getSource () == btnCancel)
	{
	    btnCancelHover.setBounds (329, 549, 209, 42);
	}
	else if (me.getSource () == btnClose)
	{
	    btnCloseHover.setBounds (473, 21, 209, 42);
	}
    } // mouseEntered method


    public void mouseExited (MouseEvent me)
    {
	if (me.getSource () == btnLogin)
	{
	    btnLoginHover.setBounds (0, 0, 0, 0);
	}
	else if (me.getSource () == btnCreate)
	{
	    btnCreateHover.setBounds (0, 0, 0, 0);
	}
	else if (me.getSource () == btnCancel)
	{
	    btnCancelHover.setBounds (0, 0, 0, 0);
	}
	else if (me.getSource () == btnClose)
	{
	    btnCloseHover.setBounds (0, 0, 0, 0);
	}
    } // mouseExited method


    public void keyPressed (KeyEvent ke)
    {
	lblInvalidLogin.setBounds (0, 0, 0, 0);
    }


    public void keyReleased (KeyEvent ke)
    {
	if (confirmEnabled == true)
	{
	    try
	    {
		checkInfo (usernameIn.getText (), passwordIn.getText (), cPasswordIn.getText ());
	    }
	    catch (Exception e)
	    {
	    }
	}
    }


    public void keyTyped (KeyEvent ke)
    {

    }


    public void focusGained (FocusEvent fe)
    {
	if (fe.getSource () == usernameIn)
	{
	    textFieldSelected.setBounds (171, 305, 278, 48);
	}
	if (fe.getSource () == passwordIn)
	{
	    textFieldSelected.setBounds (171, 394, 278, 48);
	}
	if (fe.getSource () == cPasswordIn && confirmEnabled == true)
	{
	    textFieldSelected.setBounds (170, 483, 278, 48);
	}

    }


    public void focusLost (FocusEvent fe)
    {
    }
} // LoginMenu class
