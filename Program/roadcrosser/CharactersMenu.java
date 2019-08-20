package roadcrosser;
// The "CharactersMenu" class.
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

public class CharactersMenu extends JFrame implements MouseListener
{
    Container frame;
    JLabel background, btnMenu, btnMenuPressed, btnMenuHover, coinDisplay, btnSelected, btnBuy, btnBuyPress, btnBuyHover, btnSelect, btnSelectHover, btnSelectPress, btnRight, btnRightPress, btnLeftPress, btnLeft, btnClose, btnCloseHover, btnClosePressed;
    int index = 0, lastIndex, lastLeftIndex, lastRightIndex;

    JLabel[] bigCharacters = {new JLabel (new ImageIcon ("graphics/characters/_withName/ninjaTurtle.png")),
	new JLabel (new ImageIcon ("graphics/characters/_withName/neilArmstrong.png")),
	new JLabel (new ImageIcon ("graphics/characters/_withName/batman.png")),
	new JLabel (new ImageIcon ("graphics/characters/_withName/captainAmerica.png")),
	new JLabel (new ImageIcon ("graphics/characters/_withName/darthVader.png")),
	new JLabel (new ImageIcon ("graphics/characters/_withName/deadpool.png")),
	new JLabel (new ImageIcon ("graphics/characters/_withName/emoji.png")),
	new JLabel (new ImageIcon ("graphics/characters/_withName/flash.png")),
	new JLabel (new ImageIcon ("graphics/characters/_withName/homer.png")),
	new JLabel (new ImageIcon ("graphics/characters/_withName/hulk.png")),
	new JLabel (new ImageIcon ("graphics/characters/_withName/ironMan.png")),
	new JLabel (new ImageIcon ("graphics/characters/_withName/joker.png")),
	new JLabel (new ImageIcon ("graphics/characters/_withName/luigi.png")),
	new JLabel (new ImageIcon ("graphics/characters/_withName/marge.png")),
	new JLabel (new ImageIcon ("graphics/characters/_withName/mario.png")),
	new JLabel (new ImageIcon ("graphics/characters/_withName/mickeyMouse.png")),
	new JLabel (new ImageIcon ("graphics/characters/_withName/spiderman.png")),
	new JLabel (new ImageIcon ("graphics/characters/_withName/spongebob.png")),
	new JLabel (new ImageIcon ("graphics/characters/_withName/superman.png")) };

    JLabel[] smallCharacters = {new JLabel (new ImageIcon ("graphics/characters/ninjaturtle/up.png")),
	new JLabel (new ImageIcon ("graphics/characters/astronaut/up.png")),
	new JLabel (new ImageIcon ("graphics/characters/batman/up.png")),
	new JLabel (new ImageIcon ("graphics/characters/captainamerica/up.png")),
	new JLabel (new ImageIcon ("graphics/characters/darthvader/up.png")),
	new JLabel (new ImageIcon ("graphics/characters/deadpool/up.png")),
	new JLabel (new ImageIcon ("graphics/characters/emoji/up.png")),
	new JLabel (new ImageIcon ("graphics/characters/flash/up.png")),
	new JLabel (new ImageIcon ("graphics/characters/homer/up.png")),
	new JLabel (new ImageIcon ("graphics/characters/hulk/up.png")),
	new JLabel (new ImageIcon ("graphics/characters/ironman/up.png")),
	new JLabel (new ImageIcon ("graphics/characters/joker/up.png")),
	new JLabel (new ImageIcon ("graphics/characters/luigi/up.png")),
	new JLabel (new ImageIcon ("graphics/characters/marge/up.png")),
	new JLabel (new ImageIcon ("graphics/characters/mario/up.png")),
	new JLabel (new ImageIcon ("graphics/characters/mickey/up.png")),
	new JLabel (new ImageIcon ("graphics/characters/spiderman/up.png")),
	new JLabel (new ImageIcon ("graphics/characters/spongebob/up.png")),
	new JLabel (new ImageIcon ("graphics/characters/superman/up.png")) };

    static String[] characterNames = {"ninjaturtle",
	"astronaut",
	"batman",
	"captainamerica",
	"darthvader",
	"deadpool",
	"emoji",
	"flash",
	"homer",
	"hulk",
	"ironman",
	"joker",
	"luigi",
	"marge",
	"mario",
	"mickey",
	"spiderman",
	"spongebob",
	"superman"};

    static int[] characterState = new int [19];
    static String[] characterData;
    public static String selectedCharacter;
    static int amount;
    static int selectedIndex;

    public CharactersMenu ()
    {
	super ("Road Crosser");     // Set the frame's name
	frame = getContentPane ();
	setUndecorated (true);
	frame.setLayout (null);
	setIconImage (new ImageIcon ("graphics/misc/logo.png").getImage ());
	setSize (620, 620);

	background = new JLabel (new ImageIcon ("graphics/backgrounds/characters.jpg"));

	btnClose = new JLabel (new ImageIcon ("graphics/buttons/closeButton.png"));
	btnCloseHover = new JLabel (new ImageIcon ("graphics/buttons/closeButtonHover.png"));
	btnClosePressed = new JLabel (new ImageIcon ("graphics/buttons/closeButtonPress.png"));

	btnMenu = new JLabel (new ImageIcon ("graphics/buttons/highscores/menuBtn.png"));
	btnMenuHover = new JLabel (new ImageIcon ("graphics/buttons/highscores/menuBtnHover.gif"));
	btnMenuPressed = new JLabel (new ImageIcon ("graphics/buttons/highscores/menuBtnPress.png"));

	btnLeft = new JLabel (new ImageIcon ("graphics/buttons/characters/left.png"));
	btnLeftPress = new JLabel (new ImageIcon ("graphics/buttons/characters/leftPress.png"));

	btnRight = new JLabel (new ImageIcon ("graphics/buttons/characters/right.png"));
	btnRightPress = new JLabel (new ImageIcon ("graphics/buttons/characters/rightPress.png"));

	btnSelected = new JLabel (new ImageIcon ("graphics/buttons/characters/selected.png"));

	btnSelect = new JLabel (new ImageIcon ("graphics/buttons/characters/select.png"));
	btnSelectPress = new JLabel (new ImageIcon ("graphics/buttons/characters/selectPress.png"));
	btnSelectHover = new JLabel (new ImageIcon ("graphics/buttons/characters/selectHover.gif"));

	btnBuy = new JLabel (new ImageIcon ("graphics/buttons/characters/buy.png"));
	btnBuyPress = new JLabel (new ImageIcon ("graphics/buttons/characters/buyPress.png"));
	btnBuyHover = new JLabel (new ImageIcon ("graphics/buttons/characters/buyHover.gif"));

	coinDisplay = new JLabel (Integer.toString (LoginMenu.coinCount), SwingConstants.CENTER);

	Font font = new Font ("Calibri", Font.BOLD, 45);
	Color coinColor = new Color (254, 209, 55);

	coinDisplay.setBounds (517, 158, 75, 38);
	coinDisplay.setFont (font);
	coinDisplay.setForeground (coinColor);

	background.setBounds (0, 0, 620, 620);

	btnClose.setBounds (559, 24, 37, 37);
	btnMenu.setBounds (205, 533, 209, 42);
	btnLeft.setBounds (29, 309, 43, 51);
	btnRight.setBounds (546, 309, 43, 51);

	for (int i = 0 ; i < smallCharacters.length ; i++)
	{
	    frame.add (smallCharacters [i]);
	}

	for (int i = 0 ; i < bigCharacters.length ; i++)
	{
	    frame.add (bigCharacters [i]);
	}

	frame.add (coinDisplay);
	frame.add (btnSelected);
	frame.add (btnSelectPress);
	frame.add (btnSelectHover);
	frame.add (btnSelect);
	frame.add (btnBuyPress);
	frame.add (btnBuyHover);
	frame.add (btnBuy);
	frame.add (btnRightPress);
	frame.add (btnRight);
	frame.add (btnLeftPress);
	frame.add (btnLeft);
	frame.add (btnMenuPressed);
	frame.add (btnMenuHover);
	frame.add (btnMenu);
	frame.add (btnClosePressed);
	frame.add (btnCloseHover);
	frame.add (btnClose);
	frame.add (background);

	btnClose.addMouseListener (this);
	btnMenu.addMouseListener (this);
	btnLeft.addMouseListener (this);
	btnRight.addMouseListener (this);
	btnBuy.addMouseListener (this);
	btnSelect.addMouseListener (this);

	loadCharacterData ();
	updateCharacterGraphics (index);

	//show the frame in center of screen.
	//source: http://stackoverflow.com/questions/2442599/how-to-set-jframe-to-appear-centered-regardless-of-monitor-resolution
	Dimension dim = Toolkit.getDefaultToolkit ().getScreenSize ();
	this.setLocation (dim.width / 2 - this.getSize ().width / 2, dim.height / 2 - this.getSize ().height / 2);

	setVisible (true);
    } // Constructor


    public static void loadCharacterData ()
    {
	try
	{
	    String userData = "";

	    FileReader fileR = new FileReader ("data/characters.dat");
	    BufferedReader input = new BufferedReader (fileR);

	    amount = Integer.parseInt (input.readLine ());

	    characterData = new String [amount];

	    for (int i = 0 ; i < characterData.length ; i++)
	    {
		characterData [i] = input.readLine ();
	    }

	    input.close ();

	    userData = characterData [LoginMenu.userNumber - 1];

	    System.out.println (userData);

	    for (int i = 0 ; i < characterState.length ; i++)
	    {
		characterState [i] = Integer.parseInt (userData.substring (i, i + 1));

		if (characterState [i] == 2)
		{
		    selectedCharacter = characterNames [i];
		    selectedIndex = i;
		}
	    }
	}
	catch (Exception e)
	{
	}

    }


    public void saveCharacterData ()
    {
	try
	{
	    String userData = "";

	    FileWriter fileW = new FileWriter ("data/characters.dat");
	    PrintWriter output = new PrintWriter (fileW);

	    output.println (amount);

	    String newUserData = "";

	    for (int i = 0 ; i < characterState.length ; i++)
	    {
		newUserData += characterState [i];
	    }

	    characterData [LoginMenu.userNumber - 1] = newUserData;

	    for (int i = 0 ; i < characterData.length ; i++)
	    {
		output.println (characterData [i]);
	    }

	    output.close ();
	}
	catch (Exception e)
	{
	    System.out.println ("error");
	}

    }


    public void updateCharacterGraphics (int i)
    {
	bigCharacters [lastIndex].setBounds (0, 0, 0, 0);
	smallCharacters [lastLeftIndex].setBounds (0, 0, 0, 0);
	smallCharacters [lastRightIndex].setBounds (0, 0, 0, 0);

	lastIndex = i;

	bigCharacters [i].setBounds (182, 239, 256, 133);

	int leftIndex, rightIndex;

	if (i == 0)
	{
	    leftIndex = 18;
	}
	else
	{
	    leftIndex = i - 1;
	}

	lastLeftIndex = leftIndex;
	smallCharacters [leftIndex].setBounds (148, 318, 33, 33);

	if (i == 18)
	{
	    rightIndex = 0;
	}
	else
	{
	    rightIndex = i + 1;
	}

	lastRightIndex = rightIndex;
	smallCharacters [rightIndex].setBounds (438, 318, 33, 33);

	if (characterState [i] == 2)
	{
	    btnSelect.setBounds (0, 0, 0, 0);
	    btnBuy.setBounds (0, 0, 0, 0);
	    btnSelected.setBounds (205, 423, 209, 42);
	}
	else if (characterState [i] == 1)
	{
	    btnSelected.setBounds (0, 0, 0, 0);
	    btnBuy.setBounds (0, 0, 0, 0);
	    btnSelect.setBounds (205, 423, 209, 42);
	}
	else
	{
	    btnSelected.setBounds (0, 0, 0, 0);
	    btnBuy.setBounds (205, 423, 209, 42);
	    btnSelect.setBounds (0, 0, 0, 0);
	}
    }


    public void mousePressed (MouseEvent me)
    {
	if (me.getSource () == btnClose)
	{
	    btnClosePressed.setBounds (473, 21, 209, 42);
	}
	else if (me.getSource () == btnMenu)
	{
	    btnMenuPressed.setBounds (205, 533, 209, 42);
	}
	else if (me.getSource () == btnLeft)
	{
	    btnLeftPress.setBounds (29, 309, 43, 51);
	}
	else if (me.getSource () == btnRight)
	{
	    btnRightPress.setBounds (546, 309, 43, 51);
	}
	else if (me.getSource () == btnSelect)
	{
	    btnSelectPress.setBounds (205, 423, 209, 42);
	}
	else if (me.getSource () == btnBuy)
	{
	    btnBuyPress.setBounds (205, 423, 209, 42);
	}
    }


    public void mouseReleased (MouseEvent me)
    {
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
	else if (me.getSource () == btnLeft)
	{
	    btnLeftPress.setBounds (0, 0, 0, 0);

	    if (index == 0)
	    {
		index = 18;
	    }
	    else
	    {
		index--;
	    }

	    updateCharacterGraphics (index);
	}
	else if (me.getSource () == btnRight)
	{
	    btnRightPress.setBounds (0, 0, 0, 0);

	    if (index == 18)
	    {
		index = 0;
	    }
	    else
	    {
		index++;
	    }

	    updateCharacterGraphics (index);
	}
	else if (me.getSource () == btnSelect)
	{
	    btnSelectPress.setBounds (0, 0, 0, 0);

	    characterState [selectedIndex] = 1;
	    characterState [index] = 2;
	    selectedIndex = index;
	    selectedCharacter = characterNames [index];

	    saveCharacterData ();
	    updateCharacterGraphics (index);
	}
	else if (me.getSource () == btnBuy)
	{
	    btnBuyPress.setBounds (0, 0, 0, 0);

	    if (LoginMenu.coinCount >= 100)
	    {
		LoginMenu.coinCount -= 100;
		characterState [index] = 1;
		coinDisplay.setText(Integer.toString (LoginMenu.coinCount));
		EndScreen.updateUsers();
	    }
	    else
	    {

	    }

	    saveCharacterData ();
	    updateCharacterGraphics (index);
	}
    }


    public void mouseEntered (MouseEvent me)
    {
	if (me.getSource () == btnClose)
	{
	    btnCloseHover.setBounds (473, 21, 209, 42);
	}
	else if (me.getSource () == btnMenu)
	{
	    btnMenuHover.setBounds (205, 533, 209, 42);
	}
	else if (me.getSource () == btnSelect)
	{
	    btnSelectHover.setBounds (205, 423, 209, 42);
	}
	else if (me.getSource () == btnBuy)
	{
	    btnBuyHover.setBounds (205, 423, 209, 42);
	}
    }


    public void mouseExited (MouseEvent me)
    {
	if (me.getSource () == btnClose)
	{
	    btnCloseHover.setBounds (0, 0, 0, 0);
	}
	else if (me.getSource () == btnMenu)
	{
	    btnMenuHover.setBounds (0, 0, 0, 0);
	}
	else if (me.getSource () == btnSelect)
	{
	    btnSelectHover.setBounds (0, 0, 0, 0);
	}
	else if (me.getSource () == btnBuy)
	{
	    btnBuyHover.setBounds (0, 0, 0, 0);
	}
    }


    public void mouseClicked (MouseEvent me)
    {

    }
} // CharactersMenu class
