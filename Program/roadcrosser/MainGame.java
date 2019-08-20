package roadcrosser;
// The "MainGame" class.
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.Timer;
import java.util.*;
import java.io.*;

public class MainGame extends JFrame implements KeyListener
{
    int[] seed = {1, 2, 3, 4, 5};

    JLabel benUp, benDown, benRight, benLeft, background, road1, road2, road3, road4, road5, scoreDisplay, gameoverGraphic, timeDisplay, border, coinDisplay;

    //declaring trucks
    JLabel truckOne, truckTwo, truckThree, truckFour, truckFive, truckSix, truckSeven, truckEight, truckNine, truckTen;

    static JLabel coinOne, coinTwo, coinThree;

    //truck X
    int truckOneX, truckTwoX, truckThreeX, truckFourX, truckFiveX, truckSixX, truckSevenX, truckEightX, truckNineX, truckTenX;

    int laneOneRow, laneTwoRow, laneThreeRow, laneFourRow, laneFiveRow;

    Container frame;
    int characterX, characterY, negSteps, moveTime = 15;
    boolean topRow, bottomRow, rightColumn, leftColumn, gameOver = false;

    public static String[] playerNames;
    public static int[] playerHighScores;

    int objectLocations[] [] = new int [7] [8];
    int benLocation[] [] = new int [7] [8];
    static int coinLocations[] [] = new int [7] [8];

    static int coinOneX, coinTwoX, coinThreeX, coinOneY, coinTwoY, coinThreeY;

    Timer timer = new Timer ();
    Timer laneOneTimer = new Timer ();
    Timer laneTwoTimer = new Timer ();
    Timer laneThreeTimer = new Timer ();
    Timer laneFourTimer = new Timer ();
    Timer laneFiveTimer = new Timer ();
    Timer timer2 = new Timer ();

    public static int score;

    public MainGame ()
    {
	super ("Road Crosser");
	setSize (620, 620);
	setDefaultCloseOperation (EXIT_ON_CLOSE);
	frame = getContentPane ();
	setUndecorated (true);
	frame.setLayout (null);
	setResizable (false);
	setIconImage (new ImageIcon ("graphics/misc/logo.png").getImage ());

	benUp = new JLabel (new ImageIcon ("graphics/characters/" + CharactersMenu.selectedCharacter + "/up.png"));
	benDown = new JLabel (new ImageIcon ("graphics/characters/" + CharactersMenu.selectedCharacter + "/down.png"));
	benRight = new JLabel (new ImageIcon ("graphics/characters/" + CharactersMenu.selectedCharacter + "/right.png"));
	benLeft = new JLabel (new ImageIcon ("graphics/characters/" + CharactersMenu.selectedCharacter + "/left.png"));

	background = new JLabel (new ImageIcon ("graphics/backgrounds/playMap.png"));
	border = new JLabel (new ImageIcon ("graphics/misc/border.png"));

	road1 = new JLabel (new ImageIcon ("graphics/trucks/road.gif"));
	road2 = new JLabel (new ImageIcon ("graphics/trucks/road.gif"));
	road3 = new JLabel (new ImageIcon ("graphics/trucks/road.gif"));
	road4 = new JLabel (new ImageIcon ("graphics/trucks/road.gif"));
	road5 = new JLabel (new ImageIcon ("graphics/trucks/road.gif"));

	truckOne = new JLabel (new ImageIcon ("graphics/trucks/truckOne.png"));
	truckTwo = new JLabel (new ImageIcon ("graphics/trucks/truckTwo.png"));
	truckThree = new JLabel (new ImageIcon ("graphics/trucks/truckThree.png"));
	truckFour = new JLabel (new ImageIcon ("graphics/trucks/truckFour.png"));
	truckFive = new JLabel (new ImageIcon ("graphics/trucks/truckFive.png"));
	truckSix = new JLabel (new ImageIcon ("graphics/trucks/truckSix.png"));
	truckSeven = new JLabel (new ImageIcon ("graphics/trucks/truckSeven.png"));
	truckEight = new JLabel (new ImageIcon ("graphics/trucks/truckEight.png"));
	truckNine = new JLabel (new ImageIcon ("graphics/trucks/truckNine.png"));
	truckTen = new JLabel (new ImageIcon ("graphics/trucks/truckTen.png"));

	coinOne = new JLabel (new ImageIcon ("graphics/misc/coin.png"));
	coinTwo = new JLabel (new ImageIcon ("graphics/misc/coin.png"));
	coinThree = new JLabel (new ImageIcon ("graphics/misc/coin.png"));

	scoreDisplay = new JLabel ("0", SwingConstants.CENTER);
	timeDisplay = new JLabel ("15", SwingConstants.CENTER);
	coinDisplay = new JLabel (Integer.toString (LoginMenu.coinCount), SwingConstants.CENTER);

	gameoverGraphic = new JLabel (new ImageIcon ("graphics/misc/gameOver.png"));

	characterX = 310;
	characterY = 556;

	Font font = new Font ("Calibri", Font.BOLD, 45);
	Color scoreColor = new Color (70, 126, 199);
	Color timeColor = new Color (199, 70, 70);
	Color coinColor = new Color (254, 209, 55);

	benUp.setBounds (characterX, characterY, 75, 34);
	background.setBounds (0, 0, 620, 620);
	border.setBounds (0, 0, 620, 620);

	scoreDisplay.setBounds (98, 30, 60, 38);
	scoreDisplay.setFont (font);
	scoreDisplay.setForeground (scoreColor);

	timeDisplay.setBounds (262, 30, 60, 38);
	timeDisplay.setFont (font);
	timeDisplay.setForeground (timeColor);

	coinDisplay.setBounds (416, 30, 75, 38);
	coinDisplay.setFont (font);
	coinDisplay.setForeground (coinColor);

	generateMap (randomizeSeed (seed));

	frame.add (border);
	frame.add (coinDisplay);
	frame.add (gameoverGraphic);
	frame.add (scoreDisplay);
	frame.add (timeDisplay);
	frame.add (benUp);
	frame.add (benDown);
	frame.add (benLeft);
	frame.add (benRight);
	frame.add (truckOne);
	frame.add (truckTwo);
	frame.add (truckThree);
	frame.add (truckFour);
	frame.add (truckFive);
	frame.add (truckSix);
	frame.add (truckSeven);
	frame.add (truckEight);
	frame.add (truckNine);
	frame.add (truckTen);
	frame.add (coinOne);
	frame.add (coinTwo);
	frame.add (coinThree);
	frame.add (road5);
	frame.add (road4);
	frame.add (road1);
	frame.add (road2);
	frame.add (road3);
	frame.add (background);
	benLocation [6] [4] = 1;

	addKeyListener (this);

	//show the frame in center of screen.
	//source: http://stackoverflow.com/questions/2442599/how-to-set-jframe-to-appear-centered-regardless-of-monitor-resolution
	Dimension dim = Toolkit.getDefaultToolkit ().getScreenSize ();
	this.setLocation (dim.width / 2 - this.getSize ().width / 2, dim.height / 2 - this.getSize ().height / 2);

	startMoveTimer ();

	setVisible (true);
    } // Constructor


    public void startMoveTimer ()
    {
	timer.scheduleAtFixedRate (new TimerTask ()
	{
	    public void run ()
	    {
		moveTime--;
		timeDisplay.setText (Integer.toString (moveTime));
		System.out.println (moveTime);
		if (moveTime == 0)
		{
		    endGame ();
		}
	    }
	}
	, 1000, 1000);
    }


    //------------------------------------------------------------------------------------------------
    public static int[] randomizeSeed (int[] seed)
    {
	for (int i = 0 ; i < seed.length ; i++)
	{
	    //source: https://www.youtube.com/watch?v=8I37elnmZ2I
	    int newPos = i + (int) (Math.random () * (seed.length - i));
	    int temp = seed [newPos];
	    seed [newPos] = seed [i];
	    seed [i] = temp;

	    System.out.println (seed [i]);
	}

	return seed;
    }


    public void generateMap (int[] seed)
    {
	for (int i = 0 ; i < seed.length ; i++)
	{
	    if (seed [i] == 1)
	    {
		laneOne (i + 1);
	    }
	    else if (seed [i] == 2)
	    {
		laneTwo (i + 1);
	    }
	    else if (seed [i] == 3)
	    {
		laneThree (i + 1);
	    }
	    else if (seed [i] == 4)
	    {
		laneFour (i + 1);
	    }
	    else if (seed [i] == 5)
	    {
		laneFive (i + 1);
	    }
	}
	clearCoins ();
	placeCoins (seed);
    }


    public static void placeCoins (int[] seed)
    {
	int coinOneLane = seed [0];
	int coinTwoLane = seed [1];
	int coinThreeLane = seed [2];

	int coinOneColumn = (int) (Math.random () * 8);
	int coinTwoColumn = (int) (Math.random () * 8);
	int coinThreeColumn = (int) (Math.random () * 8);

	coinLocations [coinOneLane] [coinOneColumn] = 1;
	coinLocations [coinTwoLane] [coinTwoColumn] = 1;
	coinLocations [coinThreeLane] [coinThreeColumn] = 1;

	System.out.println ("COIN: " + coinOneLane + " " + coinOneColumn);
	System.out.println ("COIN: " + coinTwoLane + " " + coinTwoColumn);
	System.out.println ("COIN: " + coinThreeLane + " " + coinThreeColumn);

	coinOneX = coinX (coinOneColumn);
	coinTwoX = coinX (coinTwoColumn);
	coinThreeX = coinX (coinThreeColumn);

	coinOneY = getTruckY (coinOneLane) + 21;
	coinTwoY = getTruckY (coinTwoLane) + 21;
	coinThreeY = getTruckY (coinThreeLane) + 21;

	coinOne.setBounds (coinOneX, coinOneY, 33, 33);
	coinTwo.setBounds (coinTwoX, coinTwoY, 33, 33);
	coinThree.setBounds (coinThreeX, coinThreeY, 33, 33);
    }


    public static int coinX (int column)
    {
	int x = 75 * column + 31;
	return x;
    }


    public void updateObjects (int x, int row)
    {
	if (x >= -112 && x <= 57)
	{
	    objectLocations [row] [0] = 1;
	}
	else
	{
	    objectLocations [row] [0] = 0;
	}

	if (x >= -37 && x <= 132)
	{
	    objectLocations [row] [1] = 1;
	}
	else
	{
	    objectLocations [row] [1] = 0;
	}

	if (x >= 37 && x <= 207)
	{
	    objectLocations [row] [2] = 1;
	}
	else
	{
	    objectLocations [row] [2] = 0;
	}

	if (x >= 112 && x <= 282)
	{
	    objectLocations [row] [3] = 1;
	}
	else
	{
	    objectLocations [row] [3] = 0;
	}

	if (x >= 187 && x <= 357)
	{
	    objectLocations [row] [4] = 1;
	}
	else
	{
	    objectLocations [row] [4] = 0;
	}

	if (x >= 262 && x <= 432)
	{
	    objectLocations [row] [5] = 1;
	}
	else
	{
	    objectLocations [row] [5] = 0;
	}

	if (x >= 337 && x <= 507)
	{
	    objectLocations [row] [6] = 1;
	}
	else
	{
	    objectLocations [row] [6] = 0;
	}

	if (x >= 412 && x <= 582)
	{
	    objectLocations [row] [7] = 1;
	}
	else
	{
	    objectLocations [row] [7] = 0;
	}

	collisionCheck ();
    }


    public static int getTruckY (int row)
    {
	int y = 75 * row + 85;
	return y;
    }


    public static int fixTruck (int x, int lane)
    {
	if (x >= 610 && lane != 1 && lane != 5)
	{
	    x = -140;
	}
	else if (x < -140)
	{
	    x = 760;
	}
	return x;
    }


    public static int randomSpeed ()
    {
	int speed = (int) ((Math.random () * 10) + 1);

	if (speed < 3)
	{
	    speed = 3;
	}

	System.out.println ("Speed: " + speed);
	return speed;
    }


    //---------------------------------------[ LANES ]-------------------------------------------

    public void laneOne (int row)
    {
	laneOneRow = row;
	truckOneX = 310;
	truckTwoX = 0;
	road1.setBounds (10, getTruckY (row), 600, 75);
	laneOneTimer.cancel ();
	laneOneTimer = new Timer ();
	laneOneTimer.scheduleAtFixedRate (new TimerTask ()
	{
	    public void run ()
	    {
		truckOneX--;
		truckTwoX--;

		truckOneX = fixTruck (truckOneX, 1);
		truckTwoX = fixTruck (truckTwoX, 1);

		truckOne.setBounds (truckOneX, getTruckY (laneOneRow), 150, 75);
		truckTwo.setBounds (truckTwoX, getTruckY (laneOneRow), 150, 75);

		updateObjects (truckOneX, laneOneRow);
		updateObjects (truckTwoX, laneOneRow);
	    }
	}
	, 0, randomSpeed ());

    }


    public void laneTwo (int row)
    {
	laneTwoRow = row;
	truckThreeX = 460;
	truckFourX = 160;
	truckFiveX = 10;
	road2.setBounds (10, getTruckY (row), 600, 75);
	laneTwoTimer.cancel ();
	laneTwoTimer = new Timer ();
	laneTwoTimer.scheduleAtFixedRate (new TimerTask ()
	{
	    public void run ()
	    {
		truckThreeX++;
		truckFourX++;
		truckFiveX++;

		truckThreeX = fixTruck (truckThreeX, 2);
		truckFourX = fixTruck (truckFourX, 2);
		truckFiveX = fixTruck (truckFiveX, 2);

		truckThree.setBounds (truckThreeX, getTruckY (laneTwoRow), 150, 75);
		truckFour.setBounds (truckFourX, getTruckY (laneTwoRow), 150, 75);
		truckFive.setBounds (truckFiveX, getTruckY (laneTwoRow), 150, 75);

		updateObjects (truckThreeX, laneTwoRow);
		updateObjects (truckFourX, laneTwoRow);
		updateObjects (truckFiveX, laneTwoRow);
	    }
	}
	, 0, randomSpeed ());

    }


    public void laneThree (int row)
    {
	laneThreeRow = row;
	truckSixX = 85;
	road3.setBounds (10, getTruckY (row), 600, 75);
	laneThreeTimer.cancel ();
	laneThreeTimer = new Timer ();
	laneThreeTimer.scheduleAtFixedRate (new TimerTask ()
	{
	    public void run ()
	    {
		truckSixX++;

		truckSixX = fixTruck (truckSixX, 3);

		truckSix.setBounds (truckSixX, getTruckY (laneThreeRow), 150, 75);

		updateObjects (truckSixX, laneThreeRow);
	    }
	}
	, 0, randomSpeed ());

    }


    public void laneFour (int row)
    {
	laneFourRow = row;
	truckSevenX = 10;
	truckEightX = 385;
	road4.setBounds (10, getTruckY (row), 600, 75);
	laneFourTimer.cancel ();
	laneFourTimer = new Timer ();

	laneFourTimer.scheduleAtFixedRate (new TimerTask ()
	{
	    public void run ()
	    {
		truckSevenX++;
		truckEightX++;

		truckSevenX = fixTruck (truckSevenX, 4);
		truckEightX = fixTruck (truckEightX, 4);

		truckSeven.setBounds (truckSevenX, getTruckY (laneFourRow), 150, 75);
		truckEight.setBounds (truckEightX, getTruckY (laneFourRow), 150, 75);

		updateObjects (truckSevenX, laneFourRow);
		updateObjects (truckEightX, laneFourRow);
	    }
	}
	, 0, randomSpeed ());

    }


    public void laneFive (int row)
    {
	laneFiveRow = row;
	truckTenX = 160;
	truckNineX = 10;
	road5.setBounds (10, getTruckY (row), 600, 75);

	laneFiveTimer.cancel ();
	laneFiveTimer = new Timer ();
	laneFiveTimer.scheduleAtFixedRate (new TimerTask ()
	{
	    public void run ()
	    {
		truckNineX--;
		truckTenX--;

		truckNineX = fixTruck (truckNineX, 5);
		truckTenX = fixTruck (truckTenX, 5);

		truckNine.setBounds (truckNineX, getTruckY (laneFiveRow), 150, 75);
		truckTen.setBounds (truckTenX, getTruckY (laneFiveRow), 150, 75);

		updateObjects (truckNineX, laneFiveRow);
		updateObjects (truckTenX, laneFiveRow);
	    }
	}
	, 0, randomSpeed ());

    }



    //------------------------------------------------------------------------------------------------
    public void keyPressed (KeyEvent e)
    {
	benPosition ();
	//w + up
	if ((e.getKeyChar () == 119 || e.getKeyChar () == 87 || e.getKeyCode () == KeyEvent.VK_UP) && topRow != true && gameOver != true)
	{
	    characterY -= 75;
	    clearBen (1);
	    benUp.setBounds (characterX, characterY, 75, 34);
	    benMove (1);
	    if (negSteps > 0)
	    {
		negSteps--;
	    }
	    else
	    {
		score++;
		scoreDisplay.setText (Integer.toString (score));
	    }

	    nextSet ();
	}
	//a + left
	else if ((e.getKeyChar () == 65 || e.getKeyChar () == 97 || e.getKeyCode () == KeyEvent.VK_LEFT) && leftColumn != true && gameOver != true)
	{
	    characterX -= 75;
	    clearBen (3);
	    benLeft.setBounds (characterX + 21, characterY - 21, 34, 75);
	    benMove (3);
	}
	//s + down
	else if ((e.getKeyChar () == 83 || e.getKeyChar () == 115 || e.getKeyCode () == KeyEvent.VK_DOWN) && bottomRow != true && gameOver != true)
	{
	    characterY += 75;
	    clearBen (2);
	    benDown.setBounds (characterX, characterY, 75, 34);
	    benMove (2);
	    negSteps++;
	}
	//d + right
	else if ((e.getKeyChar () == 68 || e.getKeyChar () == 100 || e.getKeyCode () == KeyEvent.VK_RIGHT) && rightColumn != true && gameOver != true)
	{
	    characterX += 75;
	    clearBen (4);
	    benRight.setBounds (characterX + 21, characterY - 21, 34, 75);
	    benMove (4);
	}

	collisionCheck ();
	debugBen ();
    }


    public void keyReleased (KeyEvent e)
    {

    }


    public void keyTyped (KeyEvent e)
    {

    }


    public void nextSet ()
    {
	for (int i = 0 ; i < benLocation [0].length ; i++)
	{
	    if (benLocation [0] [i] == 1)
	    {
		moveTime = 16;
		characterY += 450;
		generateMap (randomizeSeed (seed));
		benUp.setBounds (characterX, characterY, 75, 34);
		benLocation [0] [i] = 0;
		benLocation [6] [i] = 1;
		clearObjects ();
	    }
	}

    }


    public void clearObjects ()
    {
	for (int i = 0 ; i < objectLocations.length ; i++)
	{
	    for (int j = 0 ; j < objectLocations [0].length ; j++)
	    {
		objectLocations [i] [j] = 0;
	    }
	}
    }


    public void clearCoins ()
    {
	for (int i = 0 ; i < objectLocations.length ; i++)
	{
	    for (int j = 0 ; j < objectLocations [0].length ; j++)
	    {
		coinLocations [i] [j] = 0;
	    }
	}
    }


    public void benPosition ()
    {
	if (characterY == 106)
	{
	    topRow = true;
	}
	else
	{
	    topRow = false;
	}

	if (characterY == 556)
	{
	    bottomRow = true;
	}
	else
	{
	    bottomRow = false;
	}

	if (characterX == 535)
	{
	    rightColumn = true;
	}
	else
	{
	    rightColumn = false;
	}

	if (characterX == 10)
	{
	    leftColumn = true;
	}
	else
	{
	    leftColumn = false;
	}
    }


    public void benMove (int direction)
    {
	for (int i = 0 ; i < benLocation.length ; i++)
	{
	    for (int j = 0 ; j < benLocation [0].length ; j++)
	    {
		if (benLocation [i] [j] == 1)
		{
		    benLocation [i] [j] = 0;

		    if (direction == 1)
		    {
			benLocation [i - 1] [j] = 1;
			return;
		    }
		    else if (direction == 2)
		    {
			benLocation [i + 1] [j] = 1;
			return;
		    }
		    else if (direction == 3)
		    {
			benLocation [i] [j - 1] = 1;
			return;
		    }
		    else if (direction == 4)
		    {
			benLocation [i] [j + 1] = 1;
			return;
		    }
		}
	    }
	}

    }


    public void collisionCheck ()
    {
	for (int i = 0 ; i < benLocation.length ; i++)
	{
	    for (int j = 0 ; j < benLocation [i].length ; j++)
	    {
		if (benLocation [i] [j] == 1 && objectLocations [i] [j] == 1)
		{
		    benLocation [i] [j] = 0;
		    endGame ();
		}
	    }
	}

	for (int i = 0 ; i < benLocation.length ; i++)
	{
	    for (int j = 0 ; j < benLocation [i].length ; j++)
	    {
		if (benLocation [i] [j] == 1 && coinLocations [i] [j] == 1)
		{
		    coinLocations [i] [j] = 0;

		    if (coinOneX == characterX + 21 && coinOneY == characterY)
		    {
			coinOne.setBounds (0, 0, 0, 0);
		    }
		    else if (coinTwoX == characterX + 21 && coinTwoY == characterY)
		    {
			coinTwo.setBounds (0, 0, 0, 0);
		    }
		    else if (coinThreeX == characterX + 21 && coinThreeY == characterY)
		    {
			coinThree.setBounds (0, 0, 0, 0);
		    }

		    LoginMenu.coinCount++;
		    coinDisplay.setText (Integer.toString (LoginMenu.coinCount));
		    System.out.println ("Coin Collected");
		}
	    }
	}
    }


    public void endGame ()
    {


	timer.scheduleAtFixedRate (new TimerTask ()
	{
	    public void run ()
	    {
		timer.cancel ();
		laneOneTimer.cancel ();
		laneTwoTimer.cancel ();
		laneThreeTimer.cancel ();
		laneFourTimer.cancel ();
		laneFiveTimer.cancel ();
		gameoverGraphic.setBounds (160, 100, 300, 54);
		gameOver = true;
	    }
	}
	, 0, 1);

	timer2.scheduleAtFixedRate (new TimerTask ()
	{
	    public void run ()
	    {

		new EndScreen ();
		dispose ();
		timer2.cancel ();
	    }
	}
	, 1000, 1);
    }


    public void debugBen ()
    {
	for (int i = 0 ; i < benLocation.length ; i++)
	{
	    for (int j = 0 ; j < benLocation [i].length ; j++)
	    {
		if (benLocation [i] [j] == 1)
		{
		    System.out.println ("[" + i + "]" + "[" + j + "]");
		}
	    }
	}
    }


    public void clearBen (int direction)
    {
	//1 = up, 2 = down, 3 = left, 4 = right
	if (direction != 1)
	{
	    benUp.setBounds (0, 0, 0, 0);
	}

	if (direction != 2)
	{
	    benDown.setBounds (0, 0, 0, 0);
	}

	if (direction != 3)
	{
	    benLeft.setBounds (0, 0, 0, 0);
	}

	if (direction != 4)
	{
	    benRight.setBounds (0, 0, 0, 0);
	}
    }
} // MainGame class



