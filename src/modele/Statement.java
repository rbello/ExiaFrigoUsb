package modele;

public class Statement {
	
	private double humidity;
	private double tempOut;
	private double tempIn;

	/**
	 * @param humidity En pourcentage d'humidité.
	 * @param tempOut  Le température extérieure.
	 * @param tempIn   La température intérieure.
	 */
	public Statement(double humidity, double tempOut, double tempIn) {
		this.humidity = humidity;
		this.tempOut = tempOut;
		this.tempIn = tempIn;
	}
	
	/**
	 * @return the h
	 */
	public double getHumidityRate() {
		return humidity;
	}

	/**
	 * @return the ext
	 */
	public double getExteriorTemperature() {
		return tempOut;
	}

	/**
	 * @return the inte
	 */
	public double getInteriorTemperature() {
		return tempIn;
	}

}
