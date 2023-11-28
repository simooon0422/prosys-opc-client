import java.util.Random;

public class Sensor implements Runnable{
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
		return this.value;
	}

	@Override
	public void run() 
	{
		while(true)
		{
			this.generateValue();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}

}
