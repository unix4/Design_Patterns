
public class VisitorPattern implements IDesignPattern{
	
	interface IVisitor{
		void behaviour();
	}

	abstract class Car{
		
		abstract void accept(IVisitor visitor);
	}
	
	class BMW extends Car {

		@Override
		void accept(IVisitor visitor) {
			visitor.behaviour();
		}
	}
	
	class Mercedes extends Car{

		@Override
		void accept(IVisitor visitor) {
			visitor.behaviour();
		}	
	}
	
	class BMWHandler implements IVisitor{

		@Override
		public void behaviour() {
			System.out.println("BMW-Behaviour");
		}
	}
	
	class MercedesHandler implements IVisitor{

		@Override
		public void behaviour() {
			System.out.println("Mercedes-Behaviour");
		}
	}
	
	@Override
	public void run() {
		BMW bmw = new BMW();
		BMWHandler bmwHandler = new BMWHandler();
		bmw.accept(bmwHandler);
		
		Mercedes mercedes = new Mercedes();
		MercedesHandler mercedesHandler = new MercedesHandler();
		mercedes.accept(mercedesHandler);	
	}
}
