package services;

import controller.ConsoleLog;

public class SyncIsDoneRunnable implements Runnable {

	@Override
	public void run() {
		ConsoleLog.setText("All tables are up-to-date");
	}

}
