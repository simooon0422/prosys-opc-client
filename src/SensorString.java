import com.prosysopc.ua.DataTypeConversionException;
import com.prosysopc.ua.ServiceException;
import com.prosysopc.ua.StatusException;
import com.prosysopc.ua.client.UaClient;
import com.prosysopc.ua.stack.builtintypes.NodeId;
import com.prosysopc.ua.stack.builtintypes.UnsignedInteger;

public class SensorString extends Sensor{
	protected String value;
	protected String[] valueArray;
	protected String[] malValueArray;

	SensorString(UaClient client, NodeId node, String name, float freq, String[] arr, String[] malArr) {
		super(client, node, name, freq);
		this.valueArray = arr;
		this.malValueArray = malArr;
	}

	@Override
	protected void generateValue() 
	{
		
		if (this.malfunction == false)
		{
			int index = rand.nextInt(0, this.valueArray.length);
			this.value = this.valueArray[index];
		}
		else 
		{
			int index = rand.nextInt(0, this.malValueArray.length);
			this.value = this.malValueArray[index];
		}
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
