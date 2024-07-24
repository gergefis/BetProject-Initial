package com.eap.plh24;

import java.util.ArrayList;
import java.util.List;

//Υπάρχουν 3 τύποι πελατών. Οι κανονικοί παίχτες (τρέχουσα κλάση), καθώς και οι παίχτες Gold και Platinum.
//Οι κανονικοί παίχτες μπορούν να ποντάρουν έως 100 ανά στοίχημα.
//Οι παίχτες Gold αντιστοιχούν στην κλάση GoldCustomer, υποκλάση της Customer, με μέγιστο επιτρεπτό όριο πονταρίσματος 1000.
//Οι παίχτες Platinum αντιστοιχούν στην κλάση PlatinumCustomer, υποκλάση της Customer, με μέγιστο επιτρεπτό όριο πονταρίσματος 2000.
//Η συγκεκριμένη κλάση θα πρέπει να υλοποιήσει τη διεπαφή IGiveBetList, η οποία επιστρέφει τη λίστα των στοιχημάτων του κάθε παίχτη.
public class Customer implements IGiveBetList{
	private final String name;
	private final List<CustomerBet> customerBetList = new ArrayList<>();

	//Constructor
	public Customer(String n) {
		name = n;
	}

	public String getName() {
		return name;
	}

	//Για να εισάγουμε ένα νέο στοίχημα στη λίστα στοιχημάτων του πελάτη,
	//πρέπει να ελέγξουμε πρώτα αν το στοίχημα έχει ποσό εντός του επιτρεπτού ορίου
	//του κάθε πελάτη (μέθοδος getMaxStake), καθώς και αν το εν λόγω στοίχημα υπάρχει
	//στη λίστα στοιχημάτων του BetOrganization
	public void addCustomerBet(CustomerBet customerBet) {
		if (customerBet.getStake()<=getMaxStake()){
			BetOrganization betOrganization = BetOrganization.getInstance();
			if (betOrganization.checkCustomerBetInList(customerBet)) {
				customerBetList.add(customerBet);
				// Εμφάνιση μηνύματος αποτυχίας εύρεση στοιχήματος στην BetList
			} else
				throw new IllegalArgumentException("\nThe Game " +
						customerBet.getBetName() + " doesn't exist.\n");
		}
		// Προσθέτω χειρισμό εξαιρέσεων σε περίπτωση
		// που ένας χρήστης ποντάρει περισσότερα χρήματα
		else
			throw new IllegalArgumentException("\nInvalid Stake: " + customerBet.getStake() + "€\n");
	}
	public int getMoneyPlayed(){
		int sum = 0;
		for (CustomerBet customerBet : customerBetList){
			sum+=customerBet.getStake();
		}
		return sum;
	}

// BEGIN Έλεγχος αν ο παίχτης είναι Platinum ή Gold, αλλιώς απλός παίχτης
	public int getMaxStake(){
			return 100;
	}
	// END Έλεγχος αν ο παίχτης είναι Platinum ή Gold, αλλιώς απλός παίχτης

	//BEGIN Δημιουργία κλάσης getCustomerBetList implementation απο iGiveBetList Interface
	@Override
	public List<CustomerBet> getCustomerBetList(){
		return customerBetList;
	}
	//END Δημιουργία κλάσης getCustomerBetList
}
