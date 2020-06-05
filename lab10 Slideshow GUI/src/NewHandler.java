import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class NewHandler implements ActionListener {

	private SlideShowFrame frame;
	
	public NewHandler(SlideShowFrame frame) {
		this.frame = frame;
	}
	public void actionPerformed(ActionEvent e) {
		frame.setTitle("Bla bla");
		new SlideShowFrame().setVisible(true);
	}
}