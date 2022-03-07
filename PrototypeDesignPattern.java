
// Create new objects (instances) by cloning (copying) other objects

public class PrototypePattern implements IDesignPattern {

	@Override
	public void run() {
		Sheep sally = new Sheep();
		Sheep clonedSheep = (Sheep) getClone(sally);
		
		System.out.println(sally);
		System.out.println(clonedSheep);
		System.out.println("Sally Hashcode: " + System.identityHashCode(sally));
		System.out.println("Clone Hashcode: " + System.identityHashCode(clonedSheep));

	}
	
	public Animal getClone(Animal animalSample) {
		return animalSample.makeCopy();
	}
	
	interface Animal extends Cloneable {
		public Animal makeCopy();
	}
	
	class Sheep implements Animal {
		
		public Sheep() {
			System.out.println("Sheep is made ");
		}
		
		public Animal makeCopy() {
			System.out.println("Sheep is being made ");
			Sheep sheepObject = null;
			
			try {
				sheepObject = (Sheep) super.clone();
			} catch (CloneNotSupportedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return sheepObject;
		}
		
		public String toString() {
			return "Dolly is my Hero, Baaaa";
		}
	}	
}
