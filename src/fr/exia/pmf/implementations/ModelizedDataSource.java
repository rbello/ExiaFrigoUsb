package fr.exia.pmf.implementations;

import fr.exia.pmf.model.Statement;

public class ModelizedDataSource extends RandomDataSource {
	
	private double H;
	private double Tin;
	private double Tout;
	
	// L'�quivalent de froid accumul� par le g�n�rateur
	private double variationFroid = 0d;
	
	// Variation accumul�e avec la mise en marche du frigo
	private double pasVariationFroid = -0.1d;
	
	// Variation accumul�e lors de l'arr�t du frigo
	private double pasVariationChaud = 0.2d;
	
	// Puissance maximale du frigo
	private double variationFroidMax = 0.8d;
	
	// Cible pour modifier la temperature ext�rieure
	private double cibleTempExt;
	
	@Override
	public void init() throws Throwable {
		
		// G�n�ration d'une temp�rature ext�rieure.
		// 22�C +/- 4�C
		Tout = 22.0d + (Math.random() * 8.0d) - 4.0d;
		
		// Initialement la T� int�rieure est la m�me qu'� l'ext�rieur.
		Tin = Tout;
		
		// Le taux d'humidit� est compl�tement random
		H = Math.random() * 100.0d;
		
		// D�s le d�but on fait grimper la temp�rature d'un degr�s
		cibleTempExt = Tout + 1;
		
	}
	
	@Override
	public void run() {
		while (!Thread.interrupted()) {
			
			// Naturellement la T� ext�rieure converge vers la T� int�rieure.
			Tin -= (Tin-Tout)/10d;

			// Si le frigo est allum�, la g�n�ration de froid monte en puissance
			if (isPowerEnabled() && variationFroid < variationFroidMax) {
				variationFroid -= pasVariationFroid;
			}
			
			// Quand le frido est coup� la g�n�ration de froid baisse rapidement
			else if (!isPowerEnabled()) {
				variationFroid -= pasVariationChaud;
			}
			
			// On �vite la chauffe du r�frig�rateur
			if (variationFroid < 0) variationFroid = 0;
			
			// On fait varier la T� int�rieure avec la g�n�ration de froid
			Tin -= variationFroid;
			
			// On fait varier la temp�rature ext�rieure
			if (Math.abs(cibleTempExt - Tout) > 0.01) {
				Tout -= (Tout-cibleTempExt)/20d;
			}
			else {
				// On g�n�re une nouvelle temp�rature � atteindre
				cibleTempExt += Math.random() * 2 - 1;
				if (cibleTempExt > 27) cibleTempExt -= 3;
				if (cibleTempExt < 6) cibleTempExt += 3;
			}
			
			// On �vite cette incoh�rence
			if (Tin > Tout) Tin = Tout;
			
			// On notifie les listeners qu'une nouvelle donn�es est disponible
			notifyListeners(new Statement(H, Tout, Tin));
			sleep();
		}
	}
	
}
