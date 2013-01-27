package com.gmail.aellondir.dierollerdnd.gui;

import com.gmail.aellondir.dierollerdnd.enumerations.DiceDefinitions;
import com.gmail.aellondir.dierollerdnd.nethandler.NetHandler;
import com.gmail.aellondir.dierollerdnd.nethandler.NetHandlerMaster;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.*;
import java.util.ResourceBundle;
import javax.swing.*;
import javax.swing.border.TitledBorder;

/**
 *
 * @author James Hull
 * @serial JPGH.0001 class 1
 * @version 0.01
 */
public class RollerFrame extends JFrame {
    /*
     * Dynamicly cast to either slave or master when necessary,
     * simple overriden method call for figuring out which is which.
     */

    private NetHandler nH;
    private boolean isNonGMClient = true;
    private static RollerFrame rF;
    private ResourceBundle bundle;
    private JPanel[] jPanelArr;
    private JComboBox jCBDice;
    private JComboBox jCBPlayers;
    private JTextField jTF0;
    private JTextArea jTARes;
    private JTextArea jTAMsg;
    private JButton rollJB;
    private JButton msgJB;
    private JScrollPane jSP1;
    private JScrollPane jSP2;
    private JMenuBar jMB;
    private JMenu jMFile;
    private JMenu jMConn;
    private JMenu jMWindow;
    private JMenuItem[] jMIFileArr;
    private JMenuItem[] jMIConnArr;
    private JMenuItem[] jMIWindowArr;

    public static void main(String args[]) {
        if (System.getProperty("os.name").equals("Mac OS X")) {
            try {
                System.setProperty("apple.laf.useScreenMenuBar", "true");
                System.setProperty("com.apple.mrj.application.apple.menu.about.name", "Die Roller");
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (ClassNotFoundException | IllegalAccessException |
                    InstantiationException | UnsupportedLookAndFeelException e) {
                System.out.println(e.getMessage());
            }
        }

        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new RollerFrame().setVisible(true);
            }
        });
    }

    public RollerFrame() {
        try {
            this.initComponents();
        } catch (Exception e) {
            this.errorScreen(e);
        }
    }

    private void initComponents() {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        bundle = ResourceBundle.getBundle("com.gmail.aellondir.dierollerdnd.gui.Bundle");

        this.setTitle(bundle.getString("RollerFrame.frameTitle"));

        jCBDice = new JComboBox(DiceDefinitions.values());
        jCBPlayers = new JComboBox();
        jTF0 = new JTextField("", 5);
        jTARes = new JTextArea(6, 15);
        jTAMsg = new JTextArea(3, 15);
        rollJB = new JButton(bundle.getString("RollerFrame.rollButton"));
        msgJB = new JButton(bundle.getString("RollerFrame.messageButton"));
        jSP1 = new JScrollPane(jTARes, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        jSP2 = new JScrollPane(jTAMsg, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        jPanelArr = new JPanel[7];
        jPanelArr[0] = new JPanel();
        jPanelArr[1] = new JPanel();
        jPanelArr[2] = new JPanel();
        jPanelArr[3] = new JPanel();
        jPanelArr[4] = new JPanel();
        jPanelArr[5] = new JPanel();
        jPanelArr[6] = new JPanel();

        jPanelArr[0].setBackground(new java.awt.Color(204, 255, 255));
        jPanelArr[0].setLayout(new GridLayout(2, 2));

        jPanelArr[1].add(jTF0); //num dice
        jPanelArr[2].add(jCBDice); //Type of die
        jPanelArr[3].add(msgJB); //send message
        jPanelArr[4].add(rollJB); //roll them bones

        for (int i = 1; i < jPanelArr.length - 2; i++) {
            String bundleCall;

            switch (i) {
                case 1:
                    bundleCall = bundle.getString("RollerFrame.jP1");
                    break;
                case 2:
                    bundleCall = bundle.getString("RollerFrame.jP2");
                    break;
                case 3:
                    bundleCall = bundle.getString("RollerFrame.jP3");
                    break;
                case 4:
                    bundleCall = bundle.getString("RollerFrame.jP4");
                    break;
                default:
                    bundleCall = "nil";
            }

            if (!bundleCall.equals("nil")) {
                jPanelArr[i].setBorder(
                        BorderFactory.createTitledBorder(
                        BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(51, 0, 51)),
                        bundleCall, TitledBorder.RIGHT, TitledBorder.TRAILING));
            } else {
                throw new UnsupportedOperationException("No Idea how this happened what did we do?");
            }

            jPanelArr[i].setBackground(jPanelArr[0].getBackground());

            jPanelArr[0].add(jPanelArr[i]);
        }

        jTARes.setEditable(false);
        jTARes.setLineWrap(true);
        jTARes.setWrapStyleWord(true);
        jSP1.setBackground(jPanelArr[0].getBackground());
        jSP1.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(51, 0, 51)),
                bundle.getString("RollerFrame.jSP1"), TitledBorder.RIGHT, TitledBorder.TRAILING));

        jPanelArr[6].setLayout(new GridLayout(2, 1));
        jPanelArr[6].add(jCBPlayers);
        jPanelArr[6].add(jSP2);
        jPanelArr[6].setBackground(jPanelArr[0].getBackground());
        jPanelArr[6].setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(51, 0, 51)),
                bundle.getString("RollerFrame.jP6"), TitledBorder.RIGHT, TitledBorder.TRAILING));

        jTAMsg.setEditable(true);
        jTAMsg.setLineWrap(true);
        jTAMsg.setWrapStyleWord(true);
        jSP2.setBackground(jPanelArr[0].getBackground());

        jPanelArr[5].setBackground(jPanelArr[0].getBackground());
        jPanelArr[5].setLayout(new GridBagLayout());
        jPanelArr[5].add(jPanelArr[6]);
        jPanelArr[5].add(jPanelArr[0]);
        jPanelArr[5].add(jSP1);

        this.getContentPane().add(jPanelArr[5]);

        jCBPlayers.addItem("--");
        jCBPlayers.setEnabled(false);
        msgJB.setEnabled(false);
        jTAMsg.setEnabled(false);

        this.setJMenuBar(this.createMenuBar());

        this.addListeners();

        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.pack();
    }

    private JMenuBar createMenuBar() {
        jMB = new JMenuBar();

        jMFile = new JMenu(bundle.getString("RollerFrame.JMF"));
        jMConn = new JMenu(bundle.getString("RollerFrame.JMC"));
        jMWindow = new JMenu(bundle.getString("RollerFrame.JMW"));

        jMIFileArr = new JMenuItem[2];

        jMIFileArr[0] = new JCheckBoxMenuItem(bundle.getString("RollerFrame.JMIF1"), false);
        jMIFileArr[1] = new JMenuItem(bundle.getString("RollerFrame.JMIF2"));

        jMFile.add(jMIFileArr[0]);
        jMFile.add(new JSeparator());
        jMFile.add(jMIFileArr[1]);

        jMB.add(jMFile);

        jMIConnArr = new JMenuItem[3];

        jMIConnArr[0] = new JMenuItem(bundle.getString("RollerFrame.JMIC1"));
        jMIConnArr[1] = new JMenuItem(bundle.getString("RollerFrame.JMIC2"));
        jMIConnArr[2] = new JMenuItem(bundle.getString("RollerFrame.JMIC3"));

        jMConn.add(jMIConnArr[0]);
        jMConn.add(jMIConnArr[1]);
        jMConn.add(jMIConnArr[2]);

        jMB.add(jMConn);

        jMIWindowArr = new JMenuItem[1];

        jMIWindowArr[0] = new JMenuItem(bundle.getString("RollerFrame.JMIW1"));

        jMWindow.add(jMIWindowArr[0]);

        return jMB;
    }

    private void addListeners() {
    }

    public void updateJCB() {
        jCBPlayers.removeAllItems();

        jCBPlayers.addItem("--");

        for (String str : ((NetHandlerMaster) nH).getUsernames()) {
            jCBPlayers.addItem(str);
        }
    }

    public final void errorScreen(Exception e) {
        this.setVisible(false);
        this.removeAll();
        this.validate();

        int columns = e.toString().length(), rows = 1;
        this.setLayout(new GridBagLayout());

        JTextArea jTAErr = new JTextArea(e.toString() + "\n\n");
        jTAErr.setLineWrap(true);
        jTAErr.setWrapStyleWord(true);
        jTAErr.setEditable(false);

        for (StackTraceElement sTE : e.getStackTrace()) {
            columns = sTE.toString().length() > columns ? sTE.toString().length() : columns;

            jTARes.append(sTE.toString() + '\n');

            rows++;
        }

        jTARes.setColumns(columns);
        jTARes.setRows(rows);
        jTARes.validate();

        this.getContentPane().add(jTARes);
        this.setLocationRelativeTo(null);
        this.pack();
    }

    @Override
    protected void processWindowEvent(WindowEvent e) {
        super.processWindowEvent(e);

        if (e.getNewState() == WindowEvent.WINDOW_CLOSED) {
            //call a shutdown method for all threads, as well as make good faith efforts to disconnect and close all sockets and, free up all system resources.
            //This will be called by an abstract method from NetHandler.java, which in turn will be overriden in each of the other subclasses to shut down
            //threads etc.
        }
    }



    public static RollerFrame getFrame() {
        return rF;
    }

    private class RFMouseListener implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
        }

        @Override
        public void mousePressed(MouseEvent e) {
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }

        @Override
        public void mouseEntered(MouseEvent e) {
        }

        @Override
        public void mouseExited(MouseEvent e) {
        }
    }

    private class RFMouseWheelListener implements MouseWheelListener {

        @Override
        public void mouseWheelMoved(MouseWheelEvent e) {
            if (e.getWheelRotation() > 0) {
                if (jCBDice.getSelectedIndex() + 1 <= jCBDice.getItemCount()) {
                    jCBDice.setSelectedIndex(jCBDice.getSelectedIndex() + 1);
                }
            } else {
                if (jCBDice.getSelectedIndex() - 1 >= 0) {
                    jCBDice.setSelectedIndex(jCBDice.getSelectedIndex() - 1);
                }
            }
        }
    }

    private class RFKeyListener implements KeyListener {

        @Override
        public void keyTyped(KeyEvent e) {
        }

        @Override
        public void keyPressed(KeyEvent e) {
        }

        @Override
        public void keyReleased(KeyEvent e) {
        }
    }

    private class RFActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
        }
    }
}
