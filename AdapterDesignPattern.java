import java.util.Random;

/*
 * - Allows 2 incompatible interfaces to work together
 * - Used when the client expects a (target) interface
 * - the Adapter class allows the use of the available interface and the TargetInterface
 * - Any class can work together as long as the Adapter solves the issue that all classes must implement
 *   every method defined by the shared interface
 */

public class AdapterDesignPattern implements IDesignPattern{

	@Override
	public void run() {
		EnemyTank rx7Tank = new EnemyTank();
		EnemyRobot fredTheRobot = new EnemyRobot();
		EnemyAttacker robotAdapter = new EnemyRobotAdapter(fredTheRobot);
		
		System.out.println("The Robot");
		fredTheRobot.reactToHuman("Paul");
		fredTheRobot.walkForward();
		fredTheRobot.smashWithHands();
		
		System.out.println("The Enemy Tank");
		rx7Tank.assignDriver("Frank");
		rx7Tank.driveForward();
		rx7Tank.fireWeapon();
		
		System.out.println("The Robot with Adapter");
		robotAdapter.assignDriver("Mark");
		robotAdapter.driveForward();
		robotAdapter.fireWeapon();
		
	}
	
	public interface EnemyAttacker {
		
		public void fireWeapon();
		public void driveForward();
		public void assignDriver(String driverName);
	}
	
	public class EnemyRobotAdapter  implements EnemyAttacker {
		
		EnemyRobot enemyRobot;
		
		public EnemyRobotAdapter(EnemyRobot enemyRobot) {
			this.enemyRobot = enemyRobot;
		}
		

		@Override
		public void fireWeapon() {
			enemyRobot.smashWithHands();
		}

		@Override
		public void driveForward() {
			enemyRobot.walkForward();
		}

		@Override
		public void assignDriver(String driverName) {
			enemyRobot.reactToHuman(driverName);
		}
	}
	
	public class EnemyRobot {
		Random generator = new Random();
		
		public void smashWithHands() {
			int attackDamage = generator.nextInt(10) + 1;
			System.out.println("Enemy Robot Caused " + attackDamage +  " Damage with Its Hands");
		}
		
		public void walkForward() {
			int movement = generator.nextInt(5) + 1;
			System.out.println("Enemy Robot Walks Forward " +  movement + " Spaces");
		}
		
		public void reactToHuman(String driverName) {
			System.out.println("Enemy Robot Tramps on " + driverName);
		}
	}
	
	public class EnemyTank implements EnemyAttacker {
		
		Random generator = new Random();

		@Override
		public void fireWeapon() {
			int attackDamage = generator.nextInt(10) + 1;
			System.out.println("Enemy Tank Does " + attackDamage + " Damage");
		}

		@Override
		public void driveForward() {
			int movement = generator.nextInt(5) + 1;
			System.out.println("Enemy Tank moves " + movement + " spaces");
			
		}

		@Override
		public void assignDriver(String driverName) {
			System.out.println(driverName + " is driving the tank");
			
		}
		
	}
}
