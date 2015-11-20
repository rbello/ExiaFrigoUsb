package abstractions;

import modele.Statement;

public interface IConnection {
	
	/**
	 * Initialise le syst�me.
	 * 
	 * @throws Exception Si le syst�me ne peut �tre intialis�.
	 */
	public void init() throws Throwable;
	
	/**
	 * D�marrer le syst�me.
	 */
	public void start();
	
	/**
	 * Ajouter un observateur.
	 */
	public void addListener(IConnectionListener obs);
	
	/**
	 * Retirer un observateur.
	 */
	public void removeListener(IConnectionListener obs);
	
	/**
	 * Notifier les observateurs qu'une nouvelle donn�e a �t� lue.
	 */
	public void notifyListeners(Statement data);
	
	/**
	 * Activer ou d�sactiver l'alimentation �lectrique du r�frig�rateur.
	 */
	public void setPowerEnabled(boolean value);
	
	/**
	 * Renvoie TRUE si l'alimentation �lectrique est activ�e.
	 */
	public boolean isPowerEnabled();

}
