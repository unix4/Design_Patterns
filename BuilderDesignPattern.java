
// Pattern used to create objects made from a bunch of other objects
// When you want to build an object up from other objects
// When you want the creation of these parts to be independent of the main object
// Hide the creation of the parts from the client so both aren't dependent
// The builder knows the specifics and nobody else does

public class BuilderDesignPattern implements IDesignPattern {

	@Override
	public void run() {
		RobotBuilder oldStyleRobot = new OldRobotBuilder();
		RobotEngineer robotEngineer = new RobotEngineer(oldStyleRobot);
		
		robotEngineer.makeRobot();
		Robot firstRobot = robotEngineer.getRobot();
		
		System.out.println("Robot Built");
		System.out.println("Robot Head Type: " + firstRobot.getRobotHead());
		System.out.println("Robot Torso Type: " + firstRobot.getRobotTorso());
		System.out.println("Robot Arm Type: " + firstRobot.getRobotArms());
		System.out.println("Robot Leg Type: " + firstRobot.getRobotLegs());

	}

	public interface RobotBuilder {
		
		public void buildRobotHead();

		public void buildRobotTorso();

		public void buildRobotArms();

		public void buildRobotLegs();
		
		public Robot getRobot();
	}

	public interface RobotPlan {

		public void setRobotHead(String head);

		public void setRobotTorso(String torso);

		public void setRobotArms(String arms);

		public void setRobotLegs(String legs);
	}
	
	class RobotEngineer {
		
		private RobotBuilder robotBuilder;
		
		public RobotEngineer(RobotBuilder robotBuilder) {
			this.robotBuilder = robotBuilder;
		}
		
		public Robot getRobot() {
			return this.robotBuilder.getRobot();
		}
		
		public void makeRobot() {
			this.robotBuilder.buildRobotHead();
			this.robotBuilder.buildRobotTorso();
			this.robotBuilder.buildRobotArms();
			this.robotBuilder.buildRobotLegs();
		}
	}
	
	class OldRobotBuilder implements RobotBuilder {
		
		private Robot robot;
		
		public OldRobotBuilder() {
			this.robot = new Robot();
		}

		@Override
		public void buildRobotHead() {
			robot.setRobotHead("Tin Head");
			
		}

		@Override
		public void buildRobotTorso() {
			robot.setRobotTorso("Tin Torso");
			
		}

		@Override
		public void buildRobotArms() {
			robot.setRobotArms("Blowtorch Arms");
			
		}

		@Override
		public void buildRobotLegs() {
			robot.setRobotLegs("Roller Skates");
			
		}

		@Override
		public BuilderDesignPattern.Robot getRobot() {
			return this.robot;
		}
		
	}

	class Robot implements RobotPlan {

		private String robotHead;
		private String robotTorso;
		private String robotArms;
		private String robotLegs;

		@Override
		public void setRobotHead(String head) {
			this.robotHead = head;
		}

		public String getRobotHead() {
			return robotHead;
		}

		@Override
		public void setRobotTorso(String torso) {
			this.robotTorso = torso;
		}

		public String getRobotTorso() {
			return robotTorso;
		}

		@Override
		public void setRobotArms(String arms) {
			this.robotArms = arms;
		}

		public String getRobotArms() {
			return robotArms;
		}

		@Override
		public void setRobotLegs(String legs) {
			this.robotLegs = legs;
		}

		public String getRobotLegs() {
			return robotLegs;
		}

	}

}
