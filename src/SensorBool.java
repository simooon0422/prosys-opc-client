import com.prosysopc.ua.DataTypeConversionException;
import com.prosysopc.ua.ServiceException;
import com.prosysopc.ua.StatusException;
import com.prosysopc.ua.client.UaClient;
import com.prosysopc.ua.stack.builtintypes.NodeId;
import com.prosysopc.ua.stack.builtintypes.UnsignedInteger;

public class SensorBool extends Sensor{
	protected boolean value;
	protected boolean initState;
	protected float threshold;
	
	SensorBool(UaClient client, NodeId node, String name, float freq, boolean initState, float thresh) {
		super(client, node, name, freq);
		this.initState = initState;
		this.threshold = thresh;
	}

	@Override
	protected void generateValue() 
	{
		if (this.malfunction == false)
		{
			float val = rand.nextFloat(0, 1);
			if (val >= this.threshold)
			{
				this.value = !this.initState;
			}
			else
			{
				this.value = this.initState;
			}
		}
		else this.value = !this.initState;;
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
	public Object getValue() {
		return this.value;
	}

}
