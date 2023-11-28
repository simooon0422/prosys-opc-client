
public class MyClient {

	public static void main(String[] args) throws InterruptedException{
		Sensor t_sens = new Sensor(10, 100);
		Thread t_thread = new Thread(t_sens);
		t_thread.setDaemon(true);
		
		t_thread.start();
		
		for(int i = 0; i < 10; i++)
		{
			System.out.println(t_sens.getValue());
			Thread.sleep(500);
		}
//		String address = "opc.tcp://LAPTOP-7499MVRF:53530/OPCUA/SimulationServer";
//		UaClient client = new UaClient();
//		NodeId myNode = new NodeId(3, 1001);
//		
//		client.setAddress(address);
//		client.setSecurityMode(SecurityMode.NONE);
//		client.connect();
//		System.out.println("Connected");
//		for(int i = 0; i < 10; i++)
//		{
//			client.writeAttribute(myNode, UnsignedInteger.valueOf(13), i, true);
//			Thread.sleep(1000);
//		}
////		DataValue value;
////		for(int i = 0; i < 5; i++)
////		{
////			value = client.readValue(myNode);
////	        System.out.println(value.getValue()); 
////	        Thread.sleep(1000);
////		}
//		
//		client.disconnect();
//		System.out.println("Disconnected");
	}

}
