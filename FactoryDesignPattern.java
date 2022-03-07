
// Classes are chosen by runtime
// When you don't know ahead of time what class object you need

public class FactoryPattern implements IDesignPattern {

	@Override
	public void run() {
		EnemyShipFactory enemyShipFactory = new EnemyShipFactory();
		EnemyShip theEnemy = enemyShipFactory.createEnemy("B");
		
		theEnemy.displayEnemyShip();
		theEnemy.enemyShipShoots();
		theEnemy.followHeroShip();
	}
	
	class EnemyShipFactory {
		
		public EnemyShip createEnemy(String option) {
			
			switch (option) {
			case "U":
				return new UFOEnemyShip();
			case "R":
				return new RocketEnemyShip();
			case "B":
				return new BigUFOEnemyShip();
			}
			
			return null;
		}
		
	}

	public void doStuffEnemy(EnemyShip anEnemyShip) {
		anEnemyShip.displayEnemyShip();
		anEnemyShip.followHeroShip();
		anEnemyShip.enemyShipShoots();
	}

	abstract class EnemyShip {

		private String name;
		private double amtDamage;

		public void followHeroShip() {
			System.out.println(getName() + " is following the hero");
		}

		public void displayEnemyShip() {
			System.out.println(getName() + " is on the screen.");
		}

		public void enemyShipShoots() {
			System.out.println(getName() + " attacks and does " + getDamage());
		}

		public String getName() {
			return name;
		}

		public void setName(String newName) {
			name = newName;
		}

		public double getDamage() {
			return amtDamage;
		}

		public void setDamage(double newDamage) {
			this.amtDamage = newDamage;
		}
	}

	class UFOEnemyShip extends EnemyShip {

		public UFOEnemyShip() {
			setName("UFO Enemy Ship");
			setDamage(20.0);
		}
	}

	class RocketEnemyShip extends EnemyShip {

		public RocketEnemyShip() {
			setName("Rocket Enemy Ship");
			setDamage(10.0);
		}
	}
	
	class BigUFOEnemyShip extends UFOEnemyShip {
		
		public BigUFOEnemyShip() {
			setName("Big UFO Enemy Ship");
			setDamage(50.0);
		}
	}
}
