
public class StrategyPattern implements IDesignPattern {

	// 1 Example
	interface ClassSelector {
		public void printOS();
	}

	public ClassSelector getInstance() {

		final boolean isWin = System.getProperty("os.name").startsWith("Windows");
		final boolean isMac = System.getProperty("os.name").startsWith("Mac");
		final boolean isLinux = System.getProperty("os.name").startsWith("Linux");

		if (isWin) {
			return new WinImpl();
		} else if (isMac) {
			return new MacImpl();
		} else if (isLinux) {
			return new LinuxImpl();
		} else {
			System.out.println("Unknown");
		}
		return null;
	}

	class WinImpl implements ClassSelector {

		@Override
		public void printOS() {
			System.out.println("Windows");

		}
	}

	class MacImpl implements ClassSelector {

		@Override
		public void printOS() {
			System.out.println("Mac");

		}
	}

	class LinuxImpl implements ClassSelector {

		@Override
		public void printOS() {
			System.out.println("Linux");

		}
	}

	// 2 Example
	interface IStrategy {
		int execute(int a, int b);
	}

	class StrategyAddition implements IStrategy {

		@Override
		public int execute(int a, int b) {
			return a + b;
		}
	}

	class StrategySubtraction implements IStrategy {

		@Override
		public int execute(int a, int b) {
			return a - b;
		}
	}

	class StrategyMultiplication implements IStrategy {

		@Override
		public int execute(int a, int b) {
			return a * b;
		}
	}

	class StrategyDivision implements IStrategy {

		@Override
		public int execute(int a, int b) {
			if (b == 0)
				throw new RuntimeException("Division by zero");
			return a / b;
		}
	}

	class Context {

		int a;
		int b;

		public Context(int a, int b) {
			this.a = a;
			this.b = b;
		}

		IStrategy operation;

		void setStrategy(IStrategy strategy) {
			this.operation = strategy;
		}

		int executeStrategy() {
			if (operation != null) {
				return operation.execute(a, b);
			}
			throw new RuntimeException("No Strategy specified");
		}
	}

	@Override
	public void run() {
		getInstance().printOS();
		Context context = new Context(10, 20);
		context.setStrategy(new StrategySubtraction());
		int result = context.executeStrategy();
		System.out.println("Result is: " + result);
	}
}
