import com.prosysopc.ua.client.UaClient;
import com.prosysopc.ua.stack.builtintypes.NodeId;

public class SensorFloat extends Sensor{

	SensorFloat(UaClient client, NodeId node, String name, float lowerBound, float upperBound, int frequency) {
		super(client, node, name, lowerBound, upperBound, frequency);
		// TODO Auto-generated constructor stub
		
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
	
}
