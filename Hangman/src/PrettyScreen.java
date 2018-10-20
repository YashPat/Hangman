import javax.sound.sampled.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;

import static java.lang.System.*;

class PrettyScreen extends Frame implements KeyListener
{
    private boolean keyPressed = false;
    private HangWord useMethod = new HangWord();

    PrettyScreen(String w)
    {

        setSize(1400, 950);
        addWindowListener(new WindowAdapter() //adds the window listener
        {
            public void windowClosing(WindowEvent e)
            {
                System.exit(0); //if they click the x button on the window the program will end
            }
        });
        setVisible(true);
        addKeyListener(this);
        useMethod.setWord(w); //sets the word
        useMethod.setBlank(); //creates the blank
        gameLoop();
    }

    private static void pause(long r)
    {
        try
        {
            Thread.sleep(r);
        } catch (Exception e)
        {
            out.println(" sleep error " + e);
        }
    }

    static void playSound2() //***WIN**
    {
        try
        {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("BLOOP.wav").getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception ex)
        {
            System.out.println("Error with playing sound.");
            ex.printStackTrace();
        }
    }

    static void playSound3()
    {
        try
        {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("EXPLODE.wav").getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception ex)
        {
            out.println("Error with playing sound.");
            ex.printStackTrace();
        }
    }

    protected void gameLoop() //throws InterruptedException
    {
        while (useMethod.getNumTries() > 0 && useMethod.getLose())
        {
            if (keyPressed)
            {
                this.repaint();
                keyPressed = false;
            }
        }
        this.repaint();
        pause(5000);
        exit(0);
    }

    private void drawHangMan(Graphics g)
    {
        g.setColor(Color.blue);
        g.fillRect(290, 760, 300, 25);
        g.fillRect(490, 160, 25, 625);
        g.fillRect(200, 160, 300, 25);
        if (useMethod.getNumTries() == 6)
        {
            g.fillRect(200, 160, 5, 170);
        }
        if (useMethod.getNumTries() == 5)
        {
            g.fillRect(200, 160, 5, 170);

            g.setColor(Color.black);
            g.fillOval(178, 320, 50, 50);
        }
        if (useMethod.getNumTries() == 4)
        {
            g.setColor(Color.blue);
            g.fillRect(200, 160, 5, 170);
            g.setColor(Color.black);
            g.fillOval(178, 320, 50, 50);

            g.fillRect(199, 350, 6, 140);
        }
        if (useMethod.getNumTries() == 3)
        {
            g.setColor(Color.blue);
            g.fillRect(200, 160, 5, 170);
            g.setColor(Color.black);
            g.fillOval(178, 320, 50, 50);
            g.fillRect(199, 350, 6, 140);

            g.drawLine(199, 370, 250, 440);
            g.drawLine(199, 371, 250, 441);
            g.drawLine(199, 369, 250, 439);
        }
        if (useMethod.getNumTries() == 2)
        {
            g.setColor(Color.blue);
            g.fillRect(200, 160, 5, 170);
            g.setColor(Color.black);
            g.fillOval(178, 320, 50, 50);
            g.fillRect(199, 350, 6, 140);
            g.drawLine(199, 370, 250, 440);
            g.drawLine(199, 371, 250, 441);
            g.drawLine(199, 369, 250, 439);

            g.drawLine(199, 380, 148, 440);
            g.drawLine(199, 381, 148, 441);
            g.drawLine(199, 379, 148, 439);
        }
        if (useMethod.getNumTries() == 1)
        {
            g.setColor(Color.blue);
            g.fillRect(200, 160, 5, 170);
            g.setColor(Color.black);
            g.fillOval(178, 320, 50, 50);
            g.fillRect(199, 350, 6, 140);
            g.drawLine(199, 370, 250, 440);
            g.drawLine(199, 371, 250, 441);
            g.drawLine(199, 369, 250, 439);
            g.drawLine(199, 380, 148, 440);
            g.drawLine(199, 381, 148, 441);
            g.drawLine(199, 379, 148, 439);

            g.drawLine(203, 490, 250, 600);
            g.drawLine(202, 490, 249, 600);
            g.drawLine(204, 490, 251, 600);
        }
        if (useMethod.getNumTries() == 0)
        {
            g.setColor(Color.blue);
            g.fillRect(200, 160, 5, 170);
            g.setColor(Color.white);
            g.fillOval(178, 320, 50, 50);
            g.fillRect(199, 350, 6, 140);
            g.drawLine(199, 370, 250, 440);
            g.drawLine(199, 371, 250, 441);
            g.drawLine(199, 369, 250, 439);
            g.drawLine(199, 380, 148, 440);
            g.drawLine(199, 381, 148, 441);
            g.drawLine(199, 379, 148, 439);
            g.drawLine(203, 490, 250, 600);
            g.drawLine(202, 490, 249, 600);
            g.drawLine(204, 490, 251, 600);

            g.drawLine(200, 490, 148, 600);
            g.drawLine(201, 490, 149, 600);
            g.drawLine(199, 490, 147, 600);
        }
    }

    public void paint(Graphics pen)
    {
        drawHangMan(pen);
        pen.setColor(Color.black);
        if (useMethod.getLose() && useMethod.getNumTries() > 0)
        {
            pen.setFont(new Font("Cooper Black", Font.ITALIC, 48));
            pen.drawString("MP1 Hangman COL Warr", 150, 80);
            pen.setFont(new Font("Comic Sans MS", Font.BOLD, 48));
            pen.drawString(useMethod.getBlank(), 750, 650);
            pen.drawString("Tries Remaining: " + useMethod.getNumTries() + "", 750, 550);
            pen.drawString("Letters Used: " + useMethod.getLettersTried(), 750, 350);
        } else if (!useMethod.getLose())
        {
            pen.setFont(new Font("Copperplate Gothic Bold", Font.BOLD, 48));
            pen.drawString(useMethod.getCongratulations(), 150, 80);
            pen.setFont(new Font("Comic Sans MS", Font.BOLD, 48));
            pen.drawString(useMethod.getBlank(), 650, 650);
            pen.drawString("Tries Remaining: " + useMethod.getNumTries() + "", 650, 550);
            playSound();
        }
        if (useMethod.getNumTries() <= 0)
        {
            pen.setColor(Color.red);
            setBackground(Color.black);
            pen.setFont(new Font("Copperplate Gothic Bold", Font.BOLD, 48));
            pen.drawString(useMethod.getGameOver(), 650, 150);
            pen.drawString("You are a horrible", 650, 450);
            pen.drawString("person!", 650, 500);

            pen.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
            pen.drawString("\"What the heck bro?!\"", 220, 320);
        }
    }

    public void keyPressed(KeyEvent e)
    {
        out.println("hit + " + KeyEvent.getKeyText(e.getKeyCode()));
        useMethod.checkLetter(KeyEvent.getKeyText(e.getKeyCode()));
        useMethod.checkWin();
        keyPressed = true;
    }

    public void keyReleased(KeyEvent e)// not used but abstract methods must be overridden
    {
    }

    public void keyTyped(KeyEvent e)
    {
    }

    private void playSound() //***WIN**
    {
        try
        {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("CLAPPING.wav").getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception ex)
        {
            System.out.println("Error with playing sound.");
            ex.printStackTrace();
        }
    }
}
