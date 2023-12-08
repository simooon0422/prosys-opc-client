import com.prosysopc.ua.DataTypeConversionException;
import com.prosysopc.ua.ServiceException;
import com.prosysopc.ua.StatusException;
import com.prosysopc.ua.client.UaClient;
import com.prosysopc.ua.stack.builtintypes.NodeId;
import com.prosysopc.ua.stack.builtintypes.UnsignedInteger;

public class SensorFloat extends Sensor{
	protected float value;
	protected float lowerBound;
	protected float upperBound;
	
	SensorFloat(UaClient client, NodeId node, String name, float lowerBound, float upperBound, int frequency) {
		super(client, node, name, frequency);
		this.lowerBound = lowerBound;
		this.upperBound = upperBound;	
	}
	
	@Override
	protected void generateValue()
	{
		if (this.malfunction == false)
		{
			this.value = rand.nextFloat(this.lowerBound, this.upperBound);
		} 
		else this.value = rand.nextFloat(this.lowerBound-50, this.upperBound+50);
	}
	
	@Override
	protected void sendValue()
	{
		try {
			client.writeAttribute(this.sensorNode, UnsignedInteger.valueOf(13), this.value, true);
		} catch (DataTypeConversionException | ServiceException | StatusException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public float getValue() 
	{
		return this.value;
	}
}
