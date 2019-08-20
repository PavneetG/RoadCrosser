package roadcrosser;
// The "HelpMenu" class.
import java.awt.*;
import java.awt.event.*; // for listening to buttons
import javax.swing.*;
import java.text.*;
import java.io.*;

public class HelpMenu extends JFrame implements MouseListener
{
    //declare the container
    Container frame;

    //declaring lables for graphics
    JLabel btnMenu, btnMenuPressed, btnMenuHover, btnClose, btnCloseHover, btnClosePressed, lblBackgroundImage, btnUp, btnUpPress, btnDown, btnDownPress;
    JLabel[] slides = {new JLabel (new ImageIcon ("graphics/misc/help/objective.png")),
	new JLabel (new ImageIcon ("graphics/misc/help/controls.png")),
	new JLabel (new ImageIcon ("graphics/misc/help/coins.png")) };

    int index, lastIndex;

    public HelpMenu ()
    {
	super ("HelpMenu");     // Set the frame's name
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


	lblBackgroundImage = new JLabel (new ImageIcon ("graphics/backgrounds/help-background.jpg"));

	btnClose = new JLabel (new ImageIcon ("graphics/buttons/closeButton.png"));
	btnCloseHover = new JLabel (new ImageIcon ("graphics/buttons/closeButtonHover.png"));
	btnClosePressed = new JLabel (new ImageIcon ("graphics/buttons/closeButtonPress.png"));

	btnMenu = new JLabel (new ImageIcon ("graphics/buttons/highscores/menuBtn.png"));
	btnMenuHover = new JLabel (new ImageIcon ("graphics/buttons/highscores/menuBtnHover.gif"));
	btnMenuPressed = new JLabel (new ImageIcon ("graphics/buttons/highscores/menuBtnPress.png"));

	btnUp = new JLabel (new ImageIcon ("graphics/buttons/help/up.png"));
	btnUpPress = new JLabel (new ImageIcon ("graphics/buttons/help/upPress.png"));

	btnDown = new JLabel (new ImageIcon ("graphics/buttons/help/down.png"));
	btnDownPress = new JLabel (new ImageIcon ("graphics/buttons/help/downPress.png"));



	//positioning buttons
	btnMenu.setBounds (206, 542, 209, 42);
	btnClose.setBounds (559, 24, 37, 37);
	btnUp.setBounds (284, 140, 51, 43);
	btnDown.setBounds (284, 461, 51, 43);
	lblBackgroundImage.setBounds (0, 0, 620, 620);

	for (int i = 0 ; i < slides.length ; i++)
	{
	    frame.add (slides [i]);
	}

	//add elements to frame
	frame.add (btnDownPress);
	frame.add (btnUpPress);
	frame.add (btnDown);
	frame.add (btnUp);
	frame.add (btnClosePressed);
	frame.add (btnCloseHover);
	frame.add (btnClose);
	frame.add (btnMenuPressed);
	frame.add (btnMenuHover);
	frame.add (btnMenu);
	frame.add (lblBackgroundImage);

	updateSlide (index);

	//adding mouse listeners to buttons
	btnUp.addMouseListener (this);
	btnDown.addMouseListener (this);
	btnClose.addMouseListener (this);
	btnMenu.addMouseListener (this);

	//show the frame in center of screen.
	//source: http://stackoverflow.com/questions/2442599/how-to-set-jframe-to-appear-centered-regardless-of-monitor-resolution
	Dimension dim = Toolkit.getDefaultToolkit ().getScreenSize ();
	this.setLocation (dim.width / 2 - this.getSize ().width / 2, dim.height / 2 - this.getSize ().height / 2);

	setVisible (true);
    } // Constructor


    public void updateSlide (int index)
    {
	slides [lastIndex].setBounds (0, 0, 0, 0);

	lastIndex = index;

	if (index == 1)
	{
	    slides [index].setBounds (28, 203, 561, 236);
	}
	else
	{
	    slides [index].setBounds (28, 254, 561, 139);
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
	    btnMenuPressed.setBounds (206, 542, 209, 42);
	}
	else if (me.getSource () == btnUp)
	{
	    btnUpPress.setBounds (284, 140, 51, 43);
	}
	else if (me.getSource () == btnDown)
	{
	    btnDownPress.setBounds (284, 461, 51, 43);
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
	else if (me.getSource () == btnUp)
	{
	    btnUpPress.setBounds (0, 0, 0, 0);

	    if (index == 0)
	    {
		index = 2;
	    }
	    else
	    {
		index--;
	    }

	    updateSlide (index);
	}
	else if (me.getSource () == btnDown)
	{
	    btnDownPress.setBounds (0, 0, 0, 0);

	    if (index == 2)
	    {
		index = 0;
	    }
	    else
	    {
		index++;
	    }
	    updateSlide (index);
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
	    btnMenuHover.setBounds (206, 542, 209, 42);
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
} // HelpMenu class
