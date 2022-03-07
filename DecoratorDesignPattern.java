
/*
 * Decorator is a structural design pattern that lets you attach new behaviors to objects by placing these objects
 * inside special wrapper objects that contain the behaviors.
 * 
 * - The Decorator allows you to modify an object dynamically
 * - You would use it when you want the capabilities of inheritance with subclasses, but you need to add functionality at run time.
 * - It is more flexible than inheritance
 * - Simplifies code because you add functionality using many simple classes
 * - Rather than rewrite old code you can extend with new code
 */

public class DecoratorDesignPattern implements IDesignPattern {

	@Override
	public void run() {
		
		Pizza basicPizza = new TomatoSauce(new Mozzarella(new PlainPizza()));
		System.out.println("Ingredients: " + basicPizza.getDescription());
		System.out.println("Price: " + basicPizza.getCost());

	}
	

	public interface Pizza {

		public String getDescription();

		public double getCost();

	}

	class PlainPizza implements Pizza {

		@Override
		public String getDescription() {
			return "Thin Dough";
		}

		@Override
		public double getCost() {
			return 4.00;
		}

	}

	abstract class ToppingDecoratorJava implements Pizza {
		protected Pizza tempPizza;

		public ToppingDecoratorJava(Pizza newPizza) {
			tempPizza = newPizza;
		}

		public String getDescription() {
			return tempPizza.getDescription();
		}

		public double getCost() {
			return tempPizza.getCost();
		}
	}

	class Mozzarella extends ToppingDecoratorJava {

		public Mozzarella(Pizza newPizza) {
			super(newPizza);
			System.out.println("Adding Dough");

			System.out.println("Adding  Moz");
		}

		public String getDescription() {
			return tempPizza.getDescription() + ", Mozzarella";
		}

		public double getCost() {
			return tempPizza.getCost() + .50;
		}
	}

	class TomatoSauce extends ToppingDecoratorJava {

		public TomatoSauce(Pizza newPizza) {
			super(newPizza);
			System.out.println("Adding Sauce");
		}

		public String getDescription() {
			return tempPizza.getDescription() + ", Tomato Sauce";
		}

		public double getCost() {
			return tempPizza.getCost() + .35;
		}
	}

}
