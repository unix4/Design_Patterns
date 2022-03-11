
/*
 * Provide a class which will limit access to another class
 * 
 * You may do this for security reasons, because an Object is intensive to create, or is accessed from a remote location
 */

public class ProxyDesignPattern implements IDesignPattern {

	@Override
	public void run() {
		ATMMachine atmMachine = new ATMMachine();
		
		atmMachine.insertCard();
		atmMachine.ejectCard();
		atmMachine.insertCard();
		atmMachine.insertPin(1234);
		atmMachine.requestCash(2000);
		atmMachine.insertCard();
		atmMachine.insertPin(1234);
		atmMachine.requestCash(2000);
		
		GetATMData realATMMachine = new ATMMachine();
		GetATMData atmProxy = new ATMProxy();
		
		System.out.println("\nCurrent ATM State " + atmProxy.getATMData());
		System.out.println("\nCash in ATM Machine " + atmProxy.getCashInMachine());
		
	}
	
	interface ATMState {

		void insertCard();

		void ejectCard();

		void insertPin(int pinEntered);

		void requestCash(int cashToWithdraw);
	}
	
	interface GetATMData {
		
		public ATMState getATMData();
		public int getCashInMachine();
	}
	
	class ATMProxy implements GetATMData {

		@Override
		public ProxyDesignPattern.ATMState getATMData() {
			ATMMachine realATMMachine = new ATMMachine();
			
			return realATMMachine.getATMData();
		}

		@Override
		public int getCashInMachine() {
			ATMMachine realATMMachine = new ATMMachine();
			
			return realATMMachine.getCashInMachine();
		}
		
	}

	class ATMMachine implements GetATMData {
		ATMState hasCard;
		ATMState noCard;
		ATMState hasCorrectPin;
		ATMState atmOutOfMoney;

		ATMState atmState;

		int cashInMachine = 2000;
		boolean correctPinEntered = false;

		public ATMMachine() {
			hasCard = new HasCard(this);
			noCard = new NoCard(this);
			hasCorrectPin = new HasPin(this);
			atmOutOfMoney = new NoCash(this);

			atmState = noCard;

			if (cashInMachine < 0) {
				atmState = atmOutOfMoney;
			}
		}

		void setATMState(ATMState newATMState) {
			atmState = newATMState;
		}

		public void setCashInMachine(int newCashInMachine) {
			cashInMachine = newCashInMachine;

		}

		public void insertCard() {
			atmState.insertCard();
		}

		public void ejectCard() {
			atmState.ejectCard();
		}

		public void requestCash(int cashToWithdraw) {
			atmState.requestCash(cashToWithdraw);
		}

		public void insertPin(int pinEntered) {
			atmState.insertPin(pinEntered);
		}

		public ATMState getYesCardState() {
			return hasCard;
		}

		public ATMState getNoCardState() {
			return noCard;
		}

		public ATMState getHasPin() {
			return hasCorrectPin;
		}

		public ATMState getNoCashState() {
			return atmOutOfMoney;
		}

		@Override
		public ProxyDesignPattern.ATMState getATMData() {
			return atmState;
		}

		@Override
		public int getCashInMachine() {
			return cashInMachine;
		}
	}

	class HasCard implements ATMState {
		ATMMachine atmMachine;

		public HasCard(ATMMachine newATMMachine) {
			atmMachine = newATMMachine;
		}

		@Override
		public void insertCard() {
			System.out.println("You can't enter more than one card ");

		}

		@Override
		public void ejectCard() {
			System.out.println("Card Ejected ");
			atmMachine.setATMState(atmMachine.getNoCardState());

		}

		@Override
		public void insertPin(int pinEntered) {

			if (pinEntered == 1234) {
				System.out.println("Correct PIN");
				atmMachine.correctPinEntered = true;
				atmMachine.setATMState(atmMachine.getHasPin());
			} else {
				System.out.println("Wrong PIN");
				atmMachine.correctPinEntered = false;
				System.out.println("Card Ejected ");
				atmMachine.setATMState(atmMachine.getNoCardState());
			}

		}

		@Override
		public void requestCash(int cashToWithdraw) {
			System.out.println("Enter PIN First");

		}
	}

	class NoCard implements ATMState {

		ATMMachine atmMachine;

		public NoCard(ATMMachine newATMMachine) {
			atmMachine = newATMMachine;
		}

		@Override
		public void insertCard() {
			System.out.println("Please Enter a PIN ");
			atmMachine.setATMState(atmMachine.getYesCardState());
		}

		@Override
		public void ejectCard() {
			System.out.println("Enter a card first ");
		}

		@Override
		public void insertPin(int pinEntered) {
			System.out.println("Enter a card first");
		}

		@Override
		public void requestCash(int cashToWithdraw) {
			System.out.println("Enter a card first");
		}

	}
	
	class HasPin implements ATMState {
		ATMMachine atmMachine;
		
		public HasPin(ATMMachine newATMMachine) {
			atmMachine = newATMMachine;
		}

		@Override
		public void insertCard() {
			System.out.println("You can't enter more than one card");
		}

		@Override
		public void ejectCard() {
			System.out.println("Card Ejected");
			atmMachine.setATMState(atmMachine.getNoCardState());
			
		}

		@Override
		public void insertPin(int pinEntered) {
			System.out.println("Already entered");
			
		}

		@Override
		public void requestCash(int cashToWithdraw) {
			if(cashToWithdraw > atmMachine.cashInMachine) {
				System.out.println("Don't have enough cash");
			} else {
				System.out.println(cashToWithdraw + " is provided by the machine");
				atmMachine.setCashInMachine(atmMachine.cashInMachine - cashToWithdraw);
				
				if(atmMachine.cashInMachine <= 0) {
					atmMachine.setATMState(atmMachine.getNoCashState());
				}
			}
			
			System.out.println("Card Ejected");
			atmMachine.setATMState(atmMachine.getNoCardState());
		}
	}
	
	class NoCash implements ATMState{
		
		ATMMachine atmMachine;
		
		public NoCash(ATMMachine newATMMachine) {
			atmMachine = newATMMachine;
		}

		@Override
		public void insertCard() {
			System.out.println("Out of order (No money left) ");
			
		}

		@Override
		public void ejectCard() {
			System.out.println("Out of order (No money left) ");
			
		}

		@Override
		public void insertPin(int pinEntered) {
			System.out.println("Out of order (No money left) ");
			
		}

		@Override
		public void requestCash(int cashToWithdraw) {
			System.out.println("Out of order (No money left) ");
			
		}
		
	}

	class ATMSate {

	}

}
