import com.sun.org.apache.bcel.internal.generic.SWITCH;
import sun.awt.image.ToolkitImage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.math.*;
public class GamePanel extends JPanel {


    int isStart ;
    Random r = new Random();
    int[] x = new int[3000];
    int[] y = new int[3000];
    int[]condition = new int[3000];
    Timer t;
    int cnt;
    int TotalT;
    private void InitP()
    {
        for(int i = 0 ; i < 3000 ; i++) {
            Random a = new Random();
            x[i] = a.nextInt(1085) + 255;
            y[i] = a.nextInt(685) + 100;
            condition[i] = 0;
        }
    }
    public void ChangeP(int i)
    {
        if(isStart == 1)
        {
            int t = r.nextInt(4);
            switch (t){
                case 0:
                    x[i] = x[i] + r.nextInt(200);
                    y[i] = y[i] + r.nextInt(200);
                    break;
                case 1:
                    x[i] = x[i] + r.nextInt(200);
                    y[i] = y[i] - r.nextInt(200);
                    break;
                case 2:
                    x[i] = x[i] - r.nextInt(200);
                    y[i] = y[i] + r.nextInt(200);
                    break;
                case 3:
                    x[i] = x[i] - r.nextInt(200);
                    y[i] = y[i] - r.nextInt(200);
                    break;
            }

            if(x[i]<255)x[i] +=300;
            else if(x[i] > 1340)x[i]-=400;
            if (y[i] < 100)y[i] += 200;
            else if(y[i] > 785)y[i] -= 300;
        }
    }
    public void ChangeC(int i)
    {

        if(condition[i] == 1){
            cnt++;
            for(int j = 0 ; j < 3000 ; j++) {
                if (Math.abs(x[i] - x[j]) <= 2 && Math.abs(y[i] - y[j]) <= 2) {
                     if (r.nextInt(100) < 10 && i != j) {
                         condition[j] = 1;
                     }
                }
            }
        }
    }
    public void TestAll()
    {
        t.stop();
        for(int i = 0 ; i < 3000 ; i++) {
            if (condition[i] == 1) {
                int t = r.nextInt(100);
                if (t < 90)
                {
                    condition[i] = 0;
                    cnt--;
                }
            }
        }
        t.start();
    }
    public GamePanel()
    {
        init2();
    }
    public  void init1()
    {
        isStart = 1;
        cnt = 2;
        InitP();
        condition[0] = 1;
        condition[1] = 1;
        TotalT = 0;
        removeAll();
        setLayout(null);
        JLabel p1 = new JLabel();
        p1.setIcon(new ImageIcon("img//start.jpg"));
        add(p1,-1);
        p1.setLocation(200,10);
        JButton sbtn = new JButton("暂停");
        sbtn.setFont(new Font("宋体",Font.BOLD,20));
        sbtn.setBounds(0,0,100,50);
        JButton ebtn =new JButton("返回");
        ebtn.setFont(new Font("宋体",Font.BOLD,20));
        ebtn.setBounds(1500,0,100,50);
        JButton pbtn = new JButton("筛查");
        pbtn.setFont(new Font("宋体",Font.BOLD,20));
        pbtn.setBounds(1500,75,100,50);
        sbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(isStart == 0)isStart = 1;
                else  if(isStart == 1)isStart = 0;
            }
        });
        ebtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                init2();
            }
        });
        pbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TestAll();
            }
        });
        add(ebtn);
        add(sbtn);
        add(pbtn);
        setVisible(true);
        t = new Timer(200, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isStart == 1) {
                    cnt = 0;
                    for (int i = 0; i < 3000; i++) {
                        ChangeP(i);
                        ChangeC(i);
                    }
                    TotalT++;
                    if(cnt > 300 || TotalT > 24000)
                    {
                        isStart = 2;
                    }
                    else if(cnt == 0)
                    {
                        isStart = 3;
                    }

                    else
                    repaint();
                }
                else if(isStart == 2)
                {
                    JOptionPane.showMessageDialog(GamePanel.this,"防疫失败");
                    init2();
                }
                else if(isStart == 3)
                {
                    JOptionPane.showMessageDialog(GamePanel.this,"防疫成功");
                    init2();
                }
            }
        });
        t.start();
    }
    public void init2()
    {
        isStart = 0;
        removeAll();
        setLayout(null);
        JLabel p2 = new JLabel();
        p2.setIcon(new ImageIcon("img//start.jpg"));
        add(p2,-1);
        p2.setBounds(0, 0,1600 , 900);
        JButton btn1 = new JButton("开始游戏");
        btn1.setFont(new Font("宋体",Font.BOLD,20));
        add(btn1,0);
        btn1.setBounds(600,600,400,50);
        JButton btn2= new JButton("选项");
        btn2.setBounds(600,675,400,50);
        btn2.setFont(new Font("宋体",Font.BOLD,20));
        add(btn2,0);
        JButton btn3 = new JButton("退出游戏");
        btn3.setFont(new Font("宋体",Font.BOLD,20));
        add(btn3,0);
        btn3.setBounds(600,750,400,50);
        btn1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                init1();
            }
        });
        btn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              String in = JOptionPane.showInputDialog(GamePanel.this,"音量","选项",JOptionPane.PLAIN_MESSAGE);

            }
        });
        btn3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });


    }



    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Image image = Toolkit.getDefaultToolkit().getImage("img//map.jpg");
        g.drawImage(image,200,10,this);
      for(int i = 0 ; i < 3000 ; i++) {
            if(condition[i] == 0) g.setColor(new Color(0, 255, 0));
            else g.setColor(new Color(255,0,0));
            g.fillOval(x[i],y[i],3,3);
        }
      String str1 = new String();
      str1 = "总人数：3000";
      String str2 = new String();
      str2 = "感染数："+cnt;
      String str3 = new String();
      str3 = "时间：第"+TotalT/24+"天";
      g.setColor(new Color(0,0,0));
      g.setFont(new Font("宋体",Font.BOLD,20));
      g.drawString(str1,5,100);
      g.drawString(str2,5,130);
      g.drawString(str3,5,160);
    }
}

    /* btn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showCustomDialog( StartPanel.this,sp);

            }
        });*/
      /*  public static void showCustomDialog(Frame owner,Component parent){
        final JDialog dialog = new JDialog(owner, "选项", true);
        dialog.setSize(500, 350);
        dialog.setResizable(false);
        dialog.setLocationRelativeTo(parent);
        JLabel messageLabel = new JLabel("声音");
        int value = 50;
        JSlider a = new JSlider(0,100,value);
        JButton okBtn = new JButton("确定");
        JButton quitBtn = new JButton("取消");
        quitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                dialog.dispose();
            }
        });

        JPanel panel = new JPanel();
        panel.add(messageLabel);
        panel.add(a);
        panel.add(okBtn);
        panel.add(quitBtn);
        dialog.setContentPane(panel);
        dialog.setVisible(true);
    }

} */