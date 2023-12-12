import com.prosysopc.ua.DataTypeConversionException;
import com.prosysopc.ua.ServiceException;
import com.prosysopc.ua.StatusException;
import com.prosysopc.ua.client.UaClient;
import com.prosysopc.ua.stack.builtintypes.NodeId;
import com.prosysopc.ua.stack.builtintypes.UnsignedInteger;

public class SensorFloat extends Sensor{
	protected float value;
	protected float lastValue;
	protected float lowerBound;
	protected float upperBound;
	protected float malLowerBound;
	protected float malUpperBound;
	protected float fluctuation;
	
	SensorFloat(UaClient client, 
				NodeId node,
				String name,
				float freq,
				float lB,
				float uB,
				float mlB,
				float muB,
				float fluct) 
	{
		super(client, node, name, freq);
		this.lowerBound = lB;
		this.upperBound = uB;
		this.malLowerBound = mlB;
		this.malUpperBound = muB;
		this.fluctuation = fluct/100;
		
		this.lastValue = rand.nextFloat(this.lowerBound, this.upperBound);
	}
	
	@Override
	protected void generateValue()
	{
		if (this.malfunction == false)
		{
			this.value = rand.nextFloat(this.lastValue*(1-this.fluctuation),
										this.lastValue*(1+this.fluctuation));
			if (this.value < this.lowerBound)
			{
				this.value = this.lowerBound;
			}
			else if (this.value > this.upperBound)
			{
				this.value = this.upperBound;
			}
		} 
		else this.value = rand.nextFloat(this.malLowerBound, this.malUpperBound);
	}
	
	@Override
	protected void sendValue()
	{
		if(client.getEndpoint() != null)
		{
			try {
				client.writeAttribute(this.sensorNode, UnsignedInteger.valueOf(13), this.value, true);
			} catch (DataTypeConversionException | ServiceException | StatusException e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public Object getValue() 
	{
		return this.value;
	}
}
