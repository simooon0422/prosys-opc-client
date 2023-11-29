import java.util.Random;

public class Sensor{
	private float value;
	private float lowerBound;
	private float upperBound;
	
	Random rand = new Random();
	
	Sensor(float lowerBound, float upperBound)
	{
		this.lowerBound = lowerBound;
		this.upperBound = upperBound;
	};
	
	private void generateValue()
	{
		this.value = rand.nextFloat(this.lowerBound, this.upperBound);
	}
	
	public float getValue() 
	{
		this.generateValue();
		return this.value;
	}

}
