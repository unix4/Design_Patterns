
/*
 * - Decouple an abstraction from its implementation so that the two can vary independently
 * - The Bridge Pattern is very poorly explained
 * - Everyone seems to explain it differently
 * - Progressively adding functionality while separating out major differences using abstract classes
 * 
 * - When to use the Bridge Design Pattern?
 * 		- When you want to be able to change both the abstractions (abstract classes) and concrete classes independently
 * 		- When you want the first abstract class to define rules (Abstract TV)
 * 		- The concrete class adds additional rules (Concrete TV)
 * 		- An abstract class has a reference to the device and it defines abstract methods that will be defined (Abstract Remote)
 * 		- The Concrete Remote defines the abstract methods required
 * 
 */


public class BridgeDesignPattern implements IDesignPattern {

	@Override
	public void run() {
		RemoteButton theTV = new TVRemoteMute(new TVDevice(1,200));
		RemoteButton theTV2 = new TVRemotePause(new TVDevice(1,200));
		
		System.out.println("Test TV with Mute ");
		
		theTV.buttonFivePressed();
		theTV.buttonSixPressed();
		theTV.buttonNinePressed();
		
		System.out.println("\nTest TV with Pause");
		
		theTV2.buttonFivePressed();
		theTV2.buttonSixPressed();
		theTV2.buttonNinePressed();
	}
	
	public class TVRemotePause extends RemoteButton {

		public TVRemotePause(BridgeDesignPattern.EntertainmentDevice newDevice) {
			super(newDevice);
		}

		@Override
		public void buttonNinePressed() {
			System.out.println("TV was Paused");
			
		}
	}
	
	public class TVRemoteMute extends RemoteButton {

		public TVRemoteMute(BridgeDesignPattern.EntertainmentDevice newDevice) {
			super(newDevice);
		}

		@Override
		public void buttonNinePressed() {
			System.out.println("TV was Muted");
			
		}
	}
	
	public abstract class RemoteButton {
		
		private EntertainmentDevice theDevice;
		
		public RemoteButton(EntertainmentDevice newDevice) {
			theDevice = newDevice;
		}
		
		public void buttonFivePressed() {
			theDevice.buttonFivePressed();
		}
		
		public void buttonSixPressed() {
			theDevice.buttonSixPressed();
		}
		
		public void deviceFeedback() {
			theDevice.deviceFeedback();
		}
		
		public abstract void buttonNinePressed();
	}
	
	public class TVDevice extends EntertainmentDevice {
		
		public TVDevice(int newDeviceState, int newMaxSetting) {
			deviceState = newDeviceState;
			maxSetting = newMaxSetting;
		}

		@Override
		public void buttonFivePressed() {
			System.out.println("Channel Down");
			deviceState--;
			
		}

		@Override
		public void buttonSixPressed() {
			System.out.println("Channel Up");
			deviceState++;
		}
		
	}
	
	public abstract class EntertainmentDevice {
		public int deviceState;
		public int maxSetting;
		public int volumeLevel = 0;
		public abstract void buttonFivePressed();
		public abstract void buttonSixPressed();
		
		public void deviceFeedback() {
			if(deviceState > maxSetting || deviceState < 0) {
				deviceState = 0;
			}
			
			System.out.println("On" + deviceState);
		}
		
		public void buttonSevenPressed() {
			volumeLevel ++;
			System.out.println("Volume at: " + volumeLevel);
		}
		
		public void buttonEightPressed() {
			volumeLevel --;
			System.out.println("Volume at: " + volumeLevel);
		}
	}
}
