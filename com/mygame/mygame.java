package com.mygame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.ImageObserver;
import java.util.Date;


public class mygame  extends JFrame implements ActionListener {
    JLabel heading, clockLabel;
    Font font = new Font("",Font.BOLD,40);
    JPanel mainPanel;

    JButton []btns= new JButton[9];

    int gameChance[]= {2,2,2,2,2,2,2,2,2};
    int activePlayer= 0;
    int wps[][]={
            {0,1,2},
            {3,4,5},
            {6,7,8},
            {0,3,6},
            {1,4,7},
            {2,5,8},
            {0,4,8},
            {2,4,6}
    };
    int winner= 2;
    boolean gameOver = false;
    mygame() {
        System.out.println("create instance of game");
        setTitle(" mt tic tac toe game..");
        setSize(850,850);
        ImageIcon icon = new ImageIcon("scr/img/x.jpeg");
        setIconImage(icon.getImage());

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        createGUI();
        setVisible(rootPaneCheckingEnabled);
    }
    private void createGUI(){
        this.getContentPane().setBackground(Color.decode("#2196f3"));
        this.setLayout(new BorderLayout());
        //north hadding
        heading = new JLabel("Tic Tac Toe");
        heading.setIcon(new ImageIcon(" " + "src/img/icon.jpeg"));
        heading.setFont(font);
        heading.setHorizontalAlignment(SwingConstants.CENTER );
        heading.setForeground(Color.white);
        heading.setHorizontalTextPosition(SwingConstants.CENTER );
        heading.setVerticalTextPosition(SwingConstants.BOTTOM);
        this.add(heading,BorderLayout.NORTH);

        clockLabel = new JLabel("Clock");
        clockLabel.setForeground(Color.white);
        clockLabel.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(clockLabel,BorderLayout.SOUTH);
        Thread t =  new Thread(){
            public void run(){
                try{
                    while(true){
                        String datetime = new Date().toLocaleString();
                        clockLabel.setText(datetime);
                        Thread.sleep(1000);
                    }
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        };
        t.start();

        //PANEL
        mainPanel = new JPanel();

        mainPanel.setLayout(new GridLayout(3,3));
        for (int i= 1;i<=9;i++){
            JButton btn = new JButton();
            //  btn.setIcon(new ImageIcon(""));
            btn.setBackground(Color.decode("#90caf9"));
            btn.setFont(font);
            mainPanel.add(btn);
            btns[i - 1]= btn;
            btn.addActionListener(this);
            btn.setName(String.valueOf(i-1));
        }
        this.add(mainPanel,BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton currentButton= (JButton)e.getSource();

        String nameStr = currentButton.getName();
        int name =Integer.parseInt(nameStr.trim());

        if(gameOver)
        {
            JOptionPane.showMessageDialog( this,"game aldready over...");
            return;
        }

        if(gameChance[name]==2)
        {
            if(activePlayer==1)
            {
                currentButton.setIcon( new ImageIcon("src/img/x.jpeg"));
                gameChance[name]= activePlayer;
                activePlayer=0;
            } else
            {
                currentButton.setIcon(new ImageIcon("src/img/o.jpeg"));
                gameChance[name]= activePlayer;
                activePlayer=1;
            }
            //find the error
            for(int[]temp:wps){

                if((gameChance[temp[0]] == gameChance[temp[1]]) && (gameChance[temp[1]] == gameChance[temp[2]])&&gameChance[temp[2]]!=2){

                    winner = gameChance[temp[0]];
                    gameOver= true;

                    JOptionPane.showMessageDialog(null,"Player"+winner+"has won to the game..");
                    int i= JOptionPane.showConfirmDialog(this,"do you want to play more ??");
                    if(i==0)
                    {
                        this.setVisible(false);
                        new mygame();
                    }else if(i==1)
                    {
                        System.exit(3433);
                    }else
                    {

                    }
                    System.out.println(i);
                    break;
                }
            }

            //drow logic
            int c = 0;
            for (int x: gameChance)
            {
                if(x==2)
                {
                    c++;
                    break;
                }
            }
            if(c ==0 && gameOver==false)
            {
                JOptionPane.showMessageDialog(null,"its drow..");
                int i=  JOptionPane.showConfirmDialog(this, "play more//");
                if(i==0)
                {

                    this.setVisible(false);
                    new  mygame();

                }else if(i==1)
                {
                    System.exit(1212);
                }else {

                }
                gameOver= true;

            }
        }
        else
        {
            JOptionPane.showMessageDialog(this,"Position already occupied...");
        }
    }
}
