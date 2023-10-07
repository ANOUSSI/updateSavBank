package vue;

import java.io.File;
import javax.swing.*;

import Global.GlobalVar;

import java.io.*;

public class MainApp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*if (fileExists(GlobalVar.filePathConfig)) {
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					new FrameConnexion().init();
				}
			});
		} else {
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					new FrameConfig().init();
				}
			});
		}*/
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new FrameConnexion().init();
			}
		});

	}

	public static boolean fileExists(String filePath) {
		File file = new File(filePath);
		return file.exists();
	}

}
