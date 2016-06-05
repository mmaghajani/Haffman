package view;

import core.HaffmanHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by mma on 6/5/2016.
 */
public class DecodePanel extends JFrame {
    private int heightScreen = (int) getToolkit().getScreenSize().getHeight();
    private int widthScreen = (int) getToolkit().getScreenSize().getWidth();
    private int width;
    private int height;
    private JButton brows;
    private JButton browsTree;
    private JButton decode;
    private JLabel title;
    private JTextField filePath ;
    private JTextField filePathTree ;
    private JLabel description ;
    private JLabel descriptionTree ;
    private Frame parent ;
    private File selectedFile ;
    private File selectedTreeFile ;

    public DecodePanel(Frame parent) {
        super();

        this.parent = parent ;

        initialize();

        setTitle();

        setEncodedPanel();

        setTreePanel();

//        setExitButton() ;

        AddComponentsToFrame() ;

        setVisible(true);
    }

    private void setTreePanel() {
        browsTree = new JButton("Brows");
        browsTree.setLocation(width *5 / 7, height * 6 / 13);
        browsTree.setSize(width / 4, height / 8);
        browsTree.setBackground(Color.LIGHT_GRAY);
        browsTree.setFont(new Font("/fonts/SegeoPrint", Font.BOLD, 12));
        browsTree.setForeground(Color.WHITE);

        filePathTree = new JTextField() ;
        filePathTree.setLocation(width / 20 , height * 6 / 13);
        filePathTree.setSize(width * 3 / 5 , height / 8);

        descriptionTree = new JLabel("Please select a text file for tree of file") ;
        descriptionTree.setLocation(width  / 20, height * 5 / 14);
        descriptionTree.setSize(width *3 / 4, height / 8);
        descriptionTree.setFont(new Font("/fonts/SegeoPrint", Font.BOLD, 10));
        descriptionTree.setForeground(Color.BLACK);

        browsTree.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser() ;
                int returnValue = fileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    selectedTreeFile = fileChooser.getSelectedFile();
                    filePathTree.setText(selectedTreeFile.getPath());
                }
            }
        });

        decode = new JButton("Decode!") ;
        decode.setLocation(width *5 / 7, height * 9 / 13);
        decode.setSize(width / 4, height / 8);
        decode.setBackground(Color.LIGHT_GRAY);
        decode.setFont(new Font("/fonts/SegeoPrint", Font.BOLD, 12));
        decode.setForeground(Color.WHITE);

        decode.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                File decodedFile = null  ;
                HaffmanHandler handler = new HaffmanHandler(selectedFile , selectedTreeFile ) ;
                try {
                    decodedFile = handler.decode() ;
                    String s = "" ;
                    JFileChooser chooser = new JFileChooser() ;
                    chooser.setDialogTitle("Save as ...");
                    chooser.setFileSelectionMode(JFileChooser.SAVE_DIALOG);
                    int returnValue = chooser.showSaveDialog(null);
                    if (returnValue == JFileChooser.APPROVE_OPTION) {
                        s = chooser.getSelectedFile().getPath() ;
                    }

                    s += "/" + decodedFile.getName() ;
                    File temp = new File(s) ;
                    try {
                        FileWriter writer = new FileWriter(temp) ;
                        for( String line : Files.readAllLines( Paths.get(decodedFile.getPath()) ) ){
                            writer.write(line);
                        }
                        writer.close();

                        parent.setVisible(true);
                        dispose();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                }
            }
        });
    }

    private void setEncodedPanel() {
        brows = new JButton("Brows");
        brows.setLocation(width *5 / 7, height * 3 / 13);
        brows.setSize(width / 4, height / 8);
        brows.setBackground(Color.LIGHT_GRAY);
        brows.setFont(new Font("/fonts/SegeoPrint", Font.BOLD, 12));
        brows.setForeground(Color.WHITE);

        filePath = new JTextField() ;
        filePath.setLocation(width / 20 , height * 3 / 13);
        filePath.setSize(width * 3 / 5 , height / 8);

        description = new JLabel("Please select a text file for decoding") ;
        description.setLocation(width  / 20, height * 2 / 14);
        description.setSize(width *3 / 4, height / 8);
        description.setFont(new Font("/fonts/SegeoPrint", Font.BOLD, 10));
        description.setForeground(Color.BLACK);

        brows.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser() ;
                int returnValue = fileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    selectedFile = fileChooser.getSelectedFile();
                    filePath.setText(selectedFile.getPath());
                }
            }
        });
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
        getContentPane().add(brows);
        getContentPane().add(filePath) ;
        getContentPane().add(description) ;
        getContentPane().add(browsTree);
        getContentPane().add(filePathTree) ;
        getContentPane().add(descriptionTree) ;
        getContentPane().add(decode) ;

    }

//    private void setExitButton() {
//        exit = new MyButton("/images/exit.jpg", 1);
//        exit.setLocation(width / 70, height * 11 / 12);
//        exit.setSize(28, 28);
//        exit.setIcon(new ImageIcon(getClass().getResource("/images/exit.png")));
//        exit.setBorder(BorderFactory.createEmptyBorder());
//        exit.setBackground(Color.decode("#01aaeb"));
//        exit.setToolTipText("Exit");
//        exit.addActionListener(new ActionListener() {
//
//            @Override
//            public void actionPerformed(ActionEvent arg0) {
//                // TODO Auto-generated method stub
//                dispose();
//            }
//        });
//
//        exitBar = new JToolBar();
//        exitBar.setLocation(0, height * 6 / 7);
//        exitBar.setSize(width, 28);
//        exitBar.setBorder(BorderFactory.createEmptyBorder());
//        exitBar.add(exit);
//    }

//    private void setDecodeButton() {
//        decode = new MyButton("Decode");
//        decode.setLocation(width / 4, height * 3 / 8);
//        decode.setSize(width / 2, height / 6);
//        decode.setBackground(Color.BLUE);
//        decode.setFont(new Font("SegeoPrint", Font.BOLD, 18));
//        decode.setForeground(Color.WHITE);
//        decode.addActionListener(new ActionListener() {
//
//            @Override
//            public void actionPerformed(ActionEvent arg0) {
//                // TODO Auto-generated method stub
//                try {
//                    //	JOptionPane.showMessageDialog(null, "Entering...") ;
//                    Thread.sleep(800);
//                    Frame.this.setVisible(false);
//                } catch (InterruptedException e) {
//                    // TODO Auto-generated catch block
//                    e.printStackTrace();
//                }
//
//                try {
//                    UIManager
//                            .setLookAndFeel("com.jtattoo.plaf.aluminium.AluminiumLookAndFeel");
//                } catch (ClassNotFoundException e) {
//                    // TODO Auto-generated catch block
//                    e.printStackTrace();
//                } catch (InstantiationException e) {
//                    // TODO Auto-generated catch block
//                    e.printStackTrace();
//                } catch (IllegalAccessException e) {
//                    // TODO Auto-generated catch block
//                    e.printStackTrace();
//                } catch (UnsupportedLookAndFeelException e) {
//                    // TODO Auto-generated catch block
//                    e.printStackTrace();
//                }
//
//                DecodePanel mp = new DecodePanel(Frame.this);
//                Runnable good = () -> {
//                    while (true) {
//                        if (mp == null)
//                            setVisible(true);
//                        try {
//                            Thread.sleep(50);
//                        } catch (InterruptedException e) {
//                            // TODO Auto-generated catch block
//                            e.printStackTrace();
//                        }
//                    }
//                };
//
//                Thread th = new Thread(good);
//                th.setPriority(10);
//                th.start();
//            }
//        });
//    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        Image image = new ImageIcon(getClass().getResource("/images/images.jpg")).getImage();
        g.drawImage(image, 0, 0, width, height, null);

        g.setFont(new Font("Segoe Print", Font.PLAIN, 13));
        g.setColor(Color.WHITE);

        title.repaint();
        brows.repaint();
//        encode.repaint();

    }

    private void setTitle() {
        title = new JLabel("Haffman Decoding" , JLabel.CENTER);
        title.setLocation(0, 0);
        title.setSize(width, height / 6);
        title.setForeground(Color.BLACK);
        title.setFont(new Font("FantasticFont", Font.BOLD, 30));
    }
}
