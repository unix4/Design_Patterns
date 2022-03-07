
/*
 When you crate a simplified interface that performs many other actions behind the scenes
 Example:
 	- Can I Withdrawl $50 From the Bank?
 	- Check if the checking account is valid
 	- Check if the security code is valid
 	- Check if funds are available
 	- Make changes accordingly
 */

public class FacadeDesignPattern implements IDesignPattern {

	@Override
	public void run() {

		BankAccountFacade accessingBank = new BankAccountFacade(1234567, 1234);

		accessingBank.widthdrawCash(50.00);
		accessingBank.widthdrawCash(900.00);
		accessingBank.depositCash(200.00);

	}

	class BankAccountFacade {

		private int accountNumber;

		private int securityCode;

		AccountNumberCheck acctChecker;
		SecurityCodeCheck codeChecker;
		FundsCheck fundChecker;

		WelcomeToBank bankWelcome;

		public BankAccountFacade(int newAcctNum, int newSecCode) {
			accountNumber = newAcctNum;
			securityCode = newSecCode;

			bankWelcome = new WelcomeToBank();
			acctChecker = new AccountNumberCheck();
			codeChecker = new SecurityCodeCheck();
			fundChecker = new FundsCheck();
		}

		public int getAccountNumber() {
			return accountNumber;
		}

		public int getSecurityCode() {
			return securityCode;
		}

		public void widthdrawCash(double cashToGet) {

			if (acctChecker.accountActive(getAccountNumber()) && codeChecker.isCodeCorrect(securityCode)
					&& fundChecker.haveEnoughMoney(cashToGet)) {
				System.out.println("Transaction Complete \n");
			} else {
				System.out.println("Transaction Failed \n");
			}
		}

		public void depositCash(double cashToDeposit) {
			if (acctChecker.accountActive(getAccountNumber()) && codeChecker.isCodeCorrect(securityCode)) {
				fundChecker.makeDeposit(cashToDeposit);
				System.out.println("Transaction Complete \n");
			} else {
				System.out.println("Transaction Failed \n");
			}

		}
	}

	class WelcomeToBank {

		public WelcomeToBank() {

			System.out.println("Welcome to ABC Bank");
			System.out.println("We are happy to give you your money if we can find it");

		}
	}

	class AccountNumberCheck {

		private int accountNumber = 1234567;

		public int getAccountNumber() {
			return accountNumber;
		}

		public boolean accountActive(int accNumToCheck) {
			return accNumToCheck == getAccountNumber();
		}
	}

	class SecurityCodeCheck {

		private int securityCode = 1234;

		public int getSecurityCode() {
			return securityCode;
		}

		public boolean isCodeCorrect(int secCodeToCheck) {
			return secCodeToCheck == getSecurityCode();
		}

	}

	class FundsCheck {

		private double cashInAccount = 1000.00;

		public double getCashInAccount() {
			return cashInAccount;
		}

		public void decreaseCashInAccount(double cashWithdrawn) {
			cashInAccount -= cashWithdrawn;
		}

		public void increaseCashInAccount(double cashDeposited) {
			cashInAccount += cashDeposited;
		}

		public boolean haveEnoughMoney(double cashToWithdrawal) {

			if (cashToWithdrawal > getCashInAccount()) {
				System.out.println("Error: You don't have enough money ");
				System.out.println("Current Balance: " + getCashInAccount());

				return false;
			} else {
				decreaseCashInAccount(cashToWithdrawal);
				System.out.println("Withdrawal Complete: Current Balance " + getCashInAccount());

				return true;
			}
		}

		public void makeDeposit(double cashToDeposit) {
			increaseCashInAccount(cashToDeposit);
			System.out.println("Deposit Complete: current Balance is " + getCashInAccount());
		}
	}

}
