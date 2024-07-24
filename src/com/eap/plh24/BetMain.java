package com.eap.plh24;

public class BetMain {

	public static void main(String[] args) {
		BetOrganization bo = BetOrganization.getInstance();
		//Κατά την προσομοίωση ΔΕ ζητούνται δεδομένα εισόδου στο πρόγραμμα
		//Όλα τα δεδομένα είναι hardcoded μέσα στον κώδικά σας

		//1.Δημιουργία διαθέσιμων στοιχημάτων για το ποδόσφαιρο
		Bet footballGame1 =  new FootballBet("ARIS-AEK", 2.1);
		Bet footballGame2 =  new FootballBet("PAOK-OFI", 3.1);

		//----- Προσθήκη στοιχημάτων  ------
		bo.addBet(footballGame1);
		bo.addBet(footballGame2);

		//2.Δημιουργία διαθέσιμων στοιχημάτων για το μπάσκετ
		Bet basketGame1 = new BasketballBet("IRAKLIS-OSFP",1.2);
		Bet basketGame2 = new BasketballBet("APOLLON-ARIS",1.3);

		bo.addBet(basketGame1);
		bo.addBet(basketGame2);


		//3.Δημιουργία παιχτών
		Customer c1 = new Customer("Tony Di Naples");
		Customer c2 = new GoldCustomer("Babis Sougias");
		Customer c3 = new PlatinumCustomer("Dimitris Gergefis");

		//Εναλλακτικά θα μπορούσα να φτιάξω πίνακα,
		// δεν επέλεξα να το κάνω με πίνακες επειδή καταχωρώ μόνο 3 παίκτες
//		Customer[] customer = new Customer[3];
//		customer[0] = new Customer("Tony Di Naples");
//		customer[1] = new GoldCustomer("Babis Sougias");
//		customer[2] = new PlatinumCustomer("Dimitris Gergefis");

		//4.Δημιουργία στοιχημάτων των παιχτών για το Ποδόσφαιρο
		CustomerBet customerBet1 = new CustomerBet("ARIS-AEK","Football", 100, "2");
		CustomerBet customerBet2 = new CustomerBet("PAOK-OFI","Football", 4, "X");


		//4.Δημιουργία στοιχημάτων των παιχτών για το Μπάσκετ
		CustomerBet customerBet3 = new CustomerBet("IRAKLIS-OSFP","Basketball", 1000, "1");
		CustomerBet customerBet4 = new CustomerBet("APOLLON-ARIS","Basketball", 2000, "2");

//		Αντιστοίχηση στοιχήματος σε παίκτες
		c1.addCustomerBet(customerBet1);
		c1.addCustomerBet(customerBet2);

		c2.addCustomerBet(customerBet3);
		c3.addCustomerBet(customerBet4);



		//5.Προσθήκη παιχτών στο σύστημα
		bo.addCustomer(c1);
		bo.addCustomer(c2);
		bo.addCustomer(c3);


		//6.Προσομοίωση αγώνων
		GameEmulator gm = GameEmulator.getInstance();
		gm.doGameEmulation();

		//7.Προβολή συνολικών αποτελεσμάτων παιχτών
		bo.showCustomersResults();

		//8.Και εγγραφή αυτών σε αρχείο κειμένου
		bo.printCustomersResultsToTextFile();

		//Τελικά Αποτελέσματα Αγώνων
		System.out.println("\nGames Results: "+gm.getEmulatedGamesResults());
	}
}
