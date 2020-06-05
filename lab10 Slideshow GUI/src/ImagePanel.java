import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class ImagePanel extends JPanel {

	private BufferedImage image = null;
	private String imageName = null;

	private boolean fitToScreen = false;
	private boolean aspectRatio = true;
	private double scaleValue = 1.0;

	public ImagePanel() {
		image = null;
		imageName = null;
		fitToScreen = false;
		aspectRatio = true;
		scaleValue = 1.0;
	}

	public BufferedImage getImage() {
		return image;
	}

	public void setImage(BufferedImage image) {
		this.image = image;
		repaint();
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
		repaint();
	}

	public boolean isFitToScreen() {
		return fitToScreen;
	}

	public void setFitToScreen(boolean fitToScreen) {
		this.fitToScreen = fitToScreen;
	}

	public boolean isAspectRatio() {
		return aspectRatio;
	}

	public void setAspectRatio(boolean aspectRatio) {
		this.aspectRatio = aspectRatio;
	}

	public double getScaleValue() {
		return scaleValue;
	}

	public void setScaleValue(double scaleValue) {
		this.scaleValue = scaleValue;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		if (image == null)
			return;

		if (fitToScreen) {
			if (aspectRatio) {

				double vScale = 1.0 * getWidth() / image.getWidth();
				double hScale = 1.0 * getHeight() / image.getHeight();
				scaleValue = Math.min(vScale, hScale);

				int width = (int) (scaleValue * image.getWidth());
				int hight = (int) (scaleValue * image.getHeight());

				g.drawImage(image, 0, 0, width, hight, null);
			} else {

				g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
			}

		} else {
				g.drawImage(image, image.getWidth(), image.getHeight(), null);
			}

		if (imageName != null) {
			g.setColor(Color.MAGENTA);
			g.drawString(imageName, 10, (int) (scaleValue * image.getHeight()) - 10);
		}
	}

	public void loadImage(String imageFile) {

		try {
			image = ImageIO.read(new File(imageFile));
			repaint();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public Dimension getPreferredSize() {
		if (image == null)
			return new Dimension(200, 200);
		if (fitToScreen)
			return new Dimension(0, 0);
		int width = (int) (scaleValue * image.getWidth());
		int height = (int) (scaleValue * image.getHeight());
		return new Dimension(width, height);
	}

	@Override
	public Dimension getMinimumSize() {
		return new Dimension(200, 200);
	}

	@Override
	public Dimension getMaximumSize() {
		return (image != null) ? new Dimension((int)

		(scaleValue * image.getWidth()), (int) (scaleValue * image.getHeight())) : new Dimension(200, 200);
	}
}
