import oracle.jvm.hotspot.jfr.JFR;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ThreadLocalRandom;

class ThreadPePart2 extends JFrame {
    private JPanel jp;
    private JButton but1;
    private JFrame progress;
    private JProgressBar pBar1;
    private JProgressBar pBar2;
    private Thread thr1;
    private Thread thr2;
    private boolean keepGoing;

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

        but1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                thr1 = new Thread(ip1);
                thr2 = new Thread(ip2);
                thr1.start();
                thr2.start();
            }
        });

        this.add(jp);
        jp.add(but1);
        jp.add(ip1);
        jp.add(ip2);

        //ThreadPePart2 thread1 = new ThreadPePart2();
        this.setVisible(true);
        this.setResizable(true);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(3);

    }

    protected class InnerProgress extends JPanel implements Runnable{
        private String threadName;
        private JProgressBar progressBarObject;


        public InnerProgress(String label, JProgressBar progressBar) {
            threadName = label;
            progressBarObject = progressBar;
            progressBarObject.setMinimum(0);
            progressBarObject.setMaximum(79);
            progressBarObject.setStringPainted(true);

            this.add(new JLabel(threadName));
            this.add(progressBarObject);

        }

        @Override
        public void run() {
            keepGoing = true;
            System.out.println("running thread: " + threadName);
            Thread.yield();

            for (int i = 0; i < 80 && keepGoing; i++){
                int randomNum = ThreadLocalRandom.current().nextInt(0, 1);
                try {
                    Thread.sleep((randomNum * 100));
                    progressBarObject.setValue(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            keepGoing = false;
            System.out.println("running thread: " + threadName + " " + System.currentTimeMillis());
        }
    }

    public static void main(String[] args){
        new ThreadPePart2();

    }

}
