package com.eap.plh24;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BetOrganization{

	//Λίστα με τους διαθέσιμους παίχτες
	private final List<Customer> cList = new ArrayList<>();

	// Εγγραφή αρχείου "bet-results.txt" σε File
	File f = new File("bet-results.txt");

	//Λίστα με τα διαθέσιμα στοιχήματα για αγώνες ποδοσφαίρου και μπάσκετ
	private final List<Bet> betList = new ArrayList<>();

	// Πεδίο δήλωσης Singleton instance
	private static BetOrganization instance;

	// BEGIN // private constructor να αποτρέψουμε "διπλότυπα"
	private BetOrganization() {

	}
	// END  private constructor να αποτρέψουμε "διπλότυπα"

	public boolean checkCustomerBetInList(CustomerBet customerBet){
		for (Bet bet : betList){
			if (bet.getGame().equals(customerBet.getBetName()))
				return true;
		}
		return false;
	}
	public List<Bet> getBetList() {
		return betList;
	}
	public void addCustomer(Customer customer) {
		cList.add(customer);
	}
	public void addBet(Bet bet) {
		betList.add(bet);
	}

	//Η μέθοδος υπολογίζει τα κέρδη του παίχτη που δίδεται ως παράμετρός της.
	//Πιο συγκεκριμένα, η παράμετρος αφορά στην λίστα στοιχημάτων του εκάστοτε παίχτη
	private double calculateGainsPerCustomer(IGiveBetList customer) {

		double gains = 0.0;

		//Για κάθε ένα στοίχημα που έχει κάνει ο παίχτης
		//Ψάχνουμε να το αντιστοιχήσουμε με τη λίστα στοιχημάτων του BetOrganization

		// Διατρέχω κάθε ένα στοίχημα της λίστας του παίχτη
		for (CustomerBet cb : customer.getCustomerBetList()) {
			// Διατρέχω κάθε ένα στοίχημα της λίστας των αγώνων
			for (Bet bet : betList) {

			// Αναζήτηση του σχετικού στοιχήματος στη λίστα των στοιχημάτων της BetOrganization
				if(bet.getGame().contains(cb.getBetName())
						&& cb.getAvailableBetTypes().contains(cb.betType)){


					//Στη συνέχεια, εφόσον το βρούμε, κοιτάζουμε αν έχει κερδίσει η επιλογή του παίχτη
					//και αν ναι, προσθέτουμε το ποσό στα κέρδη (επιστρεφόμενη τιμή της μεθόδου)
					if (cb.getChoice()
							.contains(GameEmulator.getInstance()
							.getEmulatedGamesResults()
									.get(bet.getGame()))) {

						// Προσθήκη του κέρδους στα κέρδη
						gains += cb.getStake() * bet.getOdd();
					}
				}
			}
		}
		// Επιστροφή των κερδών
		return gains;
	}

	public void showCustomersResults(){
		System.out.println("------------------Results-------------------");
		for (Customer customer : cList){
			System.out.println("####################################");
			System.out.println("Customer name: "+customer.getName());
			System.out.println("Customer money paid: "+customer.getMoneyPlayed());

			//---- String.valueOf() το περιείχε η εκφώνηση θα μπορούσαμε να το αφαιρέσουμε, δεν προσφέρει κάτι ---
			System.out.println("Customer gains: "+ String.valueOf(calculateGainsPerCustomer(customer)));
		}
		System.out.println("--------------End-of-Results----------------");
	}
	//Εγγραφή των αποτελεσμάτων των παιχτών στο αρχείο κειμένου "bet-results.txt"
	//Το αρχείο αυτό θα αντικαθίσταται από νέο αρχείο,
	// κάθε φορά που εκτελείται το πρόγραμμα (δεν κρατάμε τα προηγούμενα δεδομένα)
	//Το format του αρχείου να είναι ίδιο με την εκτύπωση των αποτελεσμάτων (showCustomersResults)
	public void printCustomersResultsToTextFile() {
		// Δημιουργία FileWriter για εγγραφή σε αρχείο bet-results.txt
		// και περιτυλίσσω σε BufferedWriter για μεγαλύτερη χωρητικότητα
		//ροή εξόδου για εγγραφή χαρακτήρων σε προσωρινή μνήμη.
		// Χειρισμός λαθών (exception)
		BufferedWriter bWriter = null;
		try {
				bWriter= new BufferedWriter(new FileWriter(f));

				bWriter.write("------------------Results-------------------\n");
			for (Customer customer : cList){
				bWriter.write("####################################\n" +
						"Customer name: "+customer.getName() +"\n" +
						"Customer money paid: "+customer.getMoneyPlayed() +"\n" +
						"Customer gains: "+ calculateGainsPerCustomer(customer) + "\n");
			}
			bWriter.write("--------------End-of-Results----------------");

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (bWriter != null) {
				try {
					bWriter.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	// BEGIN Δήλωση μεθόδου για Singleton Design Pattern
	public static BetOrganization getInstance() {
		if (instance == null) {
			instance = new BetOrganization();
		}
		return instance;
	}
	// END Δήλωση μεθόδου για Singleton Design Pattern
}
