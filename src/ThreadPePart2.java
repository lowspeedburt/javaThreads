import oracle.jvm.hotspot.jfr.JFR;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintStream;
import java.util.*;

class ThreadPePart2 extends JFrame implements ActionListener{
    private JPanel jp;
    private JButton but1;
    private JFrame progress;
    private JProgressBar pBar1;
    private JProgressBar pBar2;
    private Thread thr1;
    private Thread thr2;



    ThreadPePart2(){
        progress = new JFrame("lab 2");
        jp = new JPanel();
        but1 = new JButton("Let's start this show");
        pBar1= new JProgressBar();
        pBar2= new JProgressBar();

        GridLayout gl = new GridLayout();
        gl.layoutContainer(progress);
        InnerProgress ip1 = new InnerProgress("Progress 1",pBar1);
        InnerProgress ip2 = new InnerProgress("Progress 2",pBar2);
        ip1.setVisible(true);
        ip2.setVisible(true);

        thr1 = new Thread(ip1);
        thr2 = new Thread(ip2);

        this.add(jp);
        jp.add(but1);
        jp.add(ip1);
        jp.add(ip2);

        but1.addActionListener(this);
        but1.setActionCommand("start");


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand() == "start") {
            System.out.println("Action performed");
            thr1.start();
            thr2.start();

        }

    }

    void runProgram(){
        ThreadPePart2 thread1 = new ThreadPePart2();
        thread1.setVisible(true);
        thread1.setResizable(true);
        thread1.pack();
        thread1.setLocationRelativeTo(null);
        thread1.setDefaultCloseOperation(3);

    }

    void startProgress() {

    }

    public static void main(String[] args){
        new ThreadPePart2().runProgram();
    }

    protected class InnerProgress extends JPanel implements Runnable{
        private String msg1;


        public InnerProgress(String msg, JProgressBar progressBar) {
            msg1 = msg;
            this.add(new JLabel(msg1));
            this.add(progressBar);
        }




        @Override
        public void run() {

            System.out.println("running thread: " + msg1);
            Thread.yield();
        }
    }





}
