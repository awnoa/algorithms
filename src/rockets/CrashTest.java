package rockets;

import javax.swing.JFrame;

import app.DrawingCanvas;

public class CrashTest extends JFrame{

	public CrashTest() {
		setTitle("Crash Test");
		setSize(480,640);
		DrawingCanvas dc = new DrawingCanvas();
		setContentPane(dc);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		dc.gameUpdate();
	}
}
