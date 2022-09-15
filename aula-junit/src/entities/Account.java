package entities;

public class Account {
	
	public static double DEPOSIT_FEE_PERCENTE = 0.02;
	
	private Long id;
	private Double balance;
	
	public Account() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Account(Long id, Double balance) {
		super();
		this.id = id;
		this.balance = balance;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Double getBalance() {
		return balance;
	}
	
	
	public void deposit(double amount) {
		
		if (amount > 0) {
			amount = amount - amount * DEPOSIT_FEE_PERCENTE;
			balance += amount;
		}
		
	}
	
	public void witdraw(double amount) {
		if ( amount > balance) {
			throw new IllegalArgumentException();
		}
		balance -= amount;
	}
	
	public double fullWithdraw() {
		double aux = balance;
		balance = 0.0;
		return aux;
	}
}
