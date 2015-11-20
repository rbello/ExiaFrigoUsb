package modele;

import java.util.ArrayList;

import abstractions.IConnection;
import abstractions.IConnectionListener;

public class FakeDataInjector extends Thread implements IConnection {

	private ArrayList<IConnectionListener> listeners;
	private boolean enabled;
	
	public FakeDataInjector() {
		listeners = new ArrayList<IConnectionListener>();
	}

	@Override
	public void init() throws Throwable {
	}
	
	@Override
	public void run() {
		
		while (!Thread.interrupted()) {
			
			// On g�n�re de fausses donn�es
			notifyListeners(new Statement(
					Math.random() * 100,
					Math.random() * 30,
					Math.random() * 30
			));
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				return;
			}
			
		}
		
	}
	
	@Override
	public void addListener(IConnectionListener obs) {
		listeners.add(obs);
	}

	@Override
	public void removeListener(IConnectionListener obs) {
		listeners.remove(obs);
	}

	@Override
	public void notifyListeners(Statement data) {
		listeners.forEach(observer -> observer.onNewStatementRead(data));
	}

	@Override
	public void setPowerEnabled(boolean value) {
		if (this.enabled != value) {
			this.enabled = value;
		}
	}

	@Override
	public boolean isPowerEnabled() {
		return enabled;
	}

}
