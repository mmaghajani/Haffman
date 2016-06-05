package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

/**
 * Created by mma on 6/4/2016.
 */
public class Frame extends JFrame {
    private int heightScreen = (int) getToolkit().getScreenSize().getHeight();
    private int widthScreen = (int) getToolkit().getScreenSize().getWidth();
    private int width;
    private int height;
    private JButton decode;
    private JButton encode;
    private MyLabel title;
    private MyButton exit;
    private JToolBar exitBar;

    public Frame() {
        super();

        initialize();

        setTitle();

        setDecodeButton();

        setEncodeButton();

        setExitButton() ;

        AddComponentsToFrame() ;

        setVisible(true);
    }

    private void initialize(){
        this.setUndecorated(true);
        //this.setShape(new RoundRectangle2D.Double(0,0, widthScreen/4, heightScreen/2, 40, 40));
        this.setSize(widthScreen / 4, heightScreen / 3);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocation(widthScreen / 2 - widthScreen / 8, heightScreen / 2 - heightScreen / 4);
        this.setResizable(false);
        this.setLayout(null);
        width = widthScreen / 4;
        height = heightScreen / 3;
        try {
            UIManager
                    .setLookAndFeel("com.jtattoo.plaf.aluminium.AluminiumLookAndFeel");
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InstantiationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private void AddComponentsToFrame() {
        getContentPane().add(title);
        getContentPane().add(decode);
        getContentPane().add(encode);
        getContentPane().add(exitBar);

    }

    private void setExitButton() {
        exit = new MyButton("/images/exit.jpg", 1);
        exit.setLocation(width / 70, height * 11 / 12);
        exit.setSize(28, 28);
        exit.setIcon(new ImageIcon(getClass().getResource("/images/exit.png")));
        exit.setBorder(BorderFactory.createEmptyBorder());
        exit.setBackground(Color.decode("#01aaeb"));
        exit.setToolTipText("Exit");
        exit.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                // TODO Auto-generated method stub
                dispose();
            }
        });

        exitBar = new JToolBar();
        exitBar.setLocation(0, height * 6 / 7);
        exitBar.setSize(width, 28);
        exitBar.setBorder(BorderFactory.createEmptyBorder());
        exitBar.add(exit);
    }

    private void setEncodeButton() {
        encode = new MyButton("Encode");
        encode.setLocation(width / 4, height * 9 / 13);
        encode.setSize(width / 2, height / 6);
        encode.setBackground(Color.MAGENTA);
        encode.setFont(new Font("/fonts/SegeoPrint", Font.BOLD, 18));
        encode.setForeground(Color.WHITE);
        encode.addActionListener(new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent arg0) {
            // TODO Auto-generated method stub
            try {
                //	JOptionPane.showMessageDialog(null, "Entering...") ;
                Thread.sleep(800);
                Frame.this.setVisible(false);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            try {
                UIManager
                        .setLookAndFeel("com.jtattoo.plaf.aluminium.AluminiumLookAndFeel");
            } catch (ClassNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (InstantiationException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (UnsupportedLookAndFeelException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            EncodePanel mp = new EncodePanel(Frame.this);
            Runnable good = () -> {
                while (true) {
                    if (mp == null)
                        setVisible(true);
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            };

            Thread th = new Thread(good);
            th.setPriority(10);
            th.start();
        }
    });

    }

    private void setDecodeButton() {
        decode = new MyButton("Decode");
        decode.setLocation(width / 4, height * 3 / 8);
        decode.setSize(width / 2, height / 6);
        decode.setBackground(Color.BLUE);
        decode.setFont(new Font("SegeoPrint", Font.BOLD, 18));
        decode.setForeground(Color.WHITE);
        decode.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                // TODO Auto-generated method stub
                try {
                    //	JOptionPane.showMessageDialog(null, "Entering...") ;
                    Thread.sleep(800);
                    Frame.this.setVisible(false);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                try {
                    UIManager
                            .setLookAndFeel("com.jtattoo.plaf.aluminium.AluminiumLookAndFeel");
                } catch (ClassNotFoundException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (UnsupportedLookAndFeelException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                DecodePanel mp = new DecodePanel(Frame.this);
                Runnable good = () -> {
                    while (true) {
                        if (mp == null)
                            setVisible(true);
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                };

                Thread th = new Thread(good);
                th.setPriority(10);
                th.start();
            }
        });
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        Image image = new ImageIcon(getClass().getResource("/images/images.jpg")).getImage();
        g.drawImage(image, 0, 0, width, height, null);

        g.setFont(new Font("Segoe Print", Font.PLAIN, 13));
        g.setColor(Color.WHITE);

        title.repaint();
        decode.repaint();
        encode.repaint();
        exit.repaint();
    }

    private void setTitle() {
        title = new MyLabel("Haffman", JLabel.CENTER);
        title.setLocation(0, 0);
        title.setSize(width, height / 6);
        title.setForeground(Color.WHITE);
        title.setFont(new Font("FantasticFont", Font.BOLD, 40));
    }
}
