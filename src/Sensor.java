import java.util.Random;

import com.prosysopc.ua.DataTypeConversionException;
import com.prosysopc.ua.ServiceException;
import com.prosysopc.ua.StatusException;
import com.prosysopc.ua.client.UaClient;
import com.prosysopc.ua.stack.builtintypes.NodeId;
import com.prosysopc.ua.stack.builtintypes.UnsignedInteger;

public class Sensor extends Thread{
	protected float value;
	protected float lowerBound;
	protected float upperBound;
	protected String name;
	protected boolean malfunction = false;
	protected int frequency;
	protected UaClient client;
	protected NodeId sensorNode;
	
	Random rand = new Random();
	
	Sensor(UaClient client, NodeId node, String name, float lowerBound, float upperBound, int frequency)
	{
		this.lowerBound = lowerBound;
		this.upperBound = upperBound;
		this.name = name;
		this.client = client;
		this.sensorNode = node;
		this.frequency = frequency;
	};
	
	protected void generateValue()
	{
		if (this.malfunction == false)
		{
			this.value = rand.nextFloat(this.lowerBound, this.upperBound);
		} 
		else this.value = rand.nextFloat(this.lowerBound-50, this.upperBound+50);
		
	}
	
	protected void sendValue()
	{
		try {
			client.writeAttribute(this.sensorNode, UnsignedInteger.valueOf(13), this.value, true);
		} catch (DataTypeConversionException | ServiceException | StatusException e) {
			e.printStackTrace();
		}
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
	
	public String getSensorName()
	{
		return this.name;
	}

	public void run() 
	{
		while(true)
		{
			this.generateValue();
			this.sendValue();
			try {
				Thread.sleep(1000/this.frequency);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
