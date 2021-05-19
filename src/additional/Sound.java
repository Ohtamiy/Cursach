package additional;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.swing.JOptionPane;
import javax.swing.JSlider;

public class Sound {

	private Clip clip;
	private String musicLocation;
	private FloatControl volumeControl;
	private JSlider volume;
	
	/* конструктор класу, в якості параметрів приймає абсолютний 
	 * шлях до музичного файлу і повзунок регулятора гучності */
	public Sound(String musicLocation, JSlider volume) {
		this.musicLocation = musicLocation;
		this.volume = volume;
		try {
			// отримуємо посилання на музичний файл
			URL url = this.getClass().getResource(this.musicLocation);
			if (url != null) {
				// аудіопотік
				AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(url);
				// музичний кліп
				clip = AudioSystem.getClip();
				clip.open(audioInputStream);
				// регулятор гучності
				volumeControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			} else {
				JOptionPane.showMessageDialog(null, "Can't find music file", "ERROR WITH SONG", 
						JOptionPane.ERROR_MESSAGE);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void playMusic() {
		clip.setFramePosition(0);
		setVolume();
		clip.start();
	}
	
	public void setVolume() {
		volumeControl.setValue(volumeControl.getMinimum() - volume.getValue()*(-12));
	}
	
	public void loopMusic() {
		clip.loop(Clip.LOOP_CONTINUOUSLY);
	}

	public void stopMusic() {
		clip.stop();
		clip.close();
	}

}
