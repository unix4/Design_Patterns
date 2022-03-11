import java.util.ArrayList;
import java.util.List;

/*
 *  - The command pattern is a behavioural design pattern in which an object is used to represent and encapsulate all the information needed to call a method at 
 *    a later time.
 *    
 *  - This information includes the method name, the object that owns the method and values for the method parameters.
 *  
 *  - Allows you to store lists of code that is executed at a later time or many times.
 *  - Client say I want a specific Command to run when execute() is called on 1 of these encapsulated (hidden) Objects
 *  - An Object called the Invoker transfers this Command to another Object called a Receiver to execute the right code
 *  - TurnTVOn - DeviceButton - TurnTVOn - Television.TurnTVOn()
 *  
 *  Benefits:
 *  	- Allows you to set aside a list of commands for later use
 *  	- A class is a great place to store procedures you want to be executed
 *  	- You can store multiple commands in a class to use over and over
 *  	- You can implement undo procedures for past commands
 *  	- Negative: You create many small classes that store lists of commands
 *  
 */
public class CommandDesignPattern implements IDesignPattern {

	@Override
	public void run() {
		ElectronicDevice newDevice = TVRemote.getDevice();

		TurnTVOn onCommand = new TurnTVOn(newDevice);
		DeviceButton onPressed = new DeviceButton(onCommand);

		onPressed.press();
		// -----------------
		TurnTVOff offCommand = new TurnTVOff(newDevice);
		DeviceButton offPressed = new DeviceButton(offCommand);

		offPressed.press();

		// -----------------
		TurnTVUp volUpCommand = new TurnTVUp(newDevice);
		onPressed = new DeviceButton(volUpCommand);

		onPressed.press();
		onPressed.press();
		onPressed.press();
		
		// -----------------
		
		Television theTV = new Television();
		Radio theRadio = new Radio();
		
		List<ElectronicDevice> allDevices = new ArrayList<>();
		
		allDevices.add(theTV);
		allDevices.add(theRadio);
		
		TurnItAllOff turnDevicesOff = new TurnItAllOff(allDevices);
		
		DeviceButton turnAllOffButton = new DeviceButton(turnDevicesOff);
		turnAllOffButton.press();
		

	}

	public interface ElectronicDevice {

		public void on();

		public void off();

		public void volumeUp();

		public void volumeDown();
	}

	public class Television implements ElectronicDevice {

		public Television() {

		}

		private int volume = 0;

		@Override
		public void on() {
			System.out.println("TV is ON");

		}

		@Override
		public void off() {
			System.out.println("TV is OFF");

		}

		@Override
		public void volumeUp() {
			volume++;
			System.out.println("TV Volume is at " + volume);
		}

		@Override
		public void volumeDown() {
			volume--;
			System.out.println("TV Volume is at " + volume);
		}
	}

	public class Radio implements ElectronicDevice {

		public Radio() {

		}

		private int volume = 0;

		@Override
		public void on() {
			System.out.println("Radio is ON");

		}

		@Override
		public void off() {
			System.out.println("Radio is OFF");

		}

		@Override
		public void volumeUp() {
			volume++;
			System.out.println("Radio Volume is at " + volume);
		}

		@Override
		public void volumeDown() {
			volume--;
			System.out.println("Radio Volume is at " + volume);
		}

	}

	public interface Command {
		public void execute();
		public void undo();
	}

	public class TurnTVOn implements Command {

		ElectronicDevice theDevice;

		public TurnTVOn(ElectronicDevice newDevice) {
			theDevice = newDevice;
		}

		@Override
		public void execute() {
			theDevice.on();
		}

		@Override
		public void undo() {
			theDevice.off();
		}

	}

	public class TurnTVOff implements Command {

		ElectronicDevice theDevice;

		public TurnTVOff(ElectronicDevice newDevice) {
			theDevice = newDevice;
		}

		@Override
		public void execute() {
			theDevice.off();
		}

		@Override
		public void undo() {
			theDevice.on();
			
		}

	}

	public class TurnTVUp implements Command {

		ElectronicDevice theDevice;

		public TurnTVUp(ElectronicDevice newDevice) {
			theDevice = newDevice;
		}

		@Override
		public void execute() {
			theDevice.volumeUp();
		}

		@Override
		public void undo() {
			theDevice.volumeDown();
		}

	}

	public class DeviceButton {

		Command theCommand;

		public DeviceButton(Command newCommand) {
			theCommand = newCommand;
		}

		public void press() {
			theCommand.execute();
		}
	}

	public class TVRemote {

		public static ElectronicDevice getDevice() {
			CommandDesignPattern cmdp = new CommandDesignPattern();
			return cmdp.new Television();
		}
	}

	public class TurnItAllOff implements Command {

		List<ElectronicDevice> theDevices;

		public TurnItAllOff(List<ElectronicDevice> newDevices) {
			theDevices = newDevices;
		}

		@Override
		public void execute() {
			theDevices.forEach(device -> device.off());
		}

		@Override
		public void undo() {
			theDevices.forEach(device -> device.on());
		}

	}

}
