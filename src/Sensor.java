import java.util.Random;

public class Sensor{
	private float value;
	private float lowerBound;
	private float upperBound;
	private String name;
	
	Random rand = new Random();
	
	Sensor(String name, float lowerBound, float upperBound)
	{
		this.lowerBound = lowerBound;
		this.upperBound = upperBound;
		this.name = name;
	};
	
	public void generateValue()
	{
		this.value = rand.nextFloat(this.lowerBound, this.upperBound);
	}
	
	public float getValue() 
	{
		return this.value;
	}
	
	public String getName()
	{
		return this.name;
	}

}
