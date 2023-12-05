import java.util.Random;

public class Sensor{
	private float value;
	private float lowerBound;
	private float upperBound;
	private String name;
	private boolean malfunction = false;
	
	Random rand = new Random();
	
	Sensor(String name, float lowerBound, float upperBound)
	{
		this.lowerBound = lowerBound;
		this.upperBound = upperBound;
		this.name = name;
	};
	
	public void generateValue()
	{
		if (this.malfunction == false)
		{
			this.value = rand.nextFloat(this.lowerBound, this.upperBound);
		} 
		else this.value = rand.nextFloat(this.lowerBound-50, this.upperBound+50);
		
	}
	
	public void setMalfunction()
	{
		this.malfunction = !this.malfunction;
	}
	
	public boolean getMalfunction()
	{
		return this.malfunction;
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
