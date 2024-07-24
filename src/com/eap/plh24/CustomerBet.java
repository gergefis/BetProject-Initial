package com.eap.plh24;

import java.util.List;

public class CustomerBet {
    //Λίστα που περιέχει τις δύο διαθέσιμες επιλογές αγώνα, ποδοσφαίρου και μπάσκετ αντίστοιχα
    private final List<String> availableBetTypes = List.of("Football", "Basketball");

    //Το μοναδικό όνομα του αγώνα
    //Κατά τη δημιουργία αντικειμένων της εν λόγω κλάσης, δεν ελέγχουμε αν το όνομα αγώνα που έδωσε το παίχτης είναι σωστό.
    //Αυτό θα ελεγχθεί αργότερα από την κλάση BetOrganization
    private String betName;

    //Το ποσό του πονταρίσματος σε ευρώ (χωρίς δεκαδικά)
    private int stake;

    //Η επιλογή πονταρίσματος του παίχτη. Όπως αναφέρθηκε και παραπάνω πρέπει να ελεγχθεί, ανάλογα με τον τύπο του αγώνα.
    //Οι διαθέσιμες εκβάσεις ενός αγώνα μπάσκετ είναι, 1:Νικήτρια η πρώτη ομάδα, 2:Νικήτρια η δεύτερη ομάδα
    //Οι διαθέσιμες εκβάσεις ενός αγώνα ποδοσφαίρου είναι, 1:Νικήτρια η πρώτη ομάδα, Χ:Ισοπαλία, 2:Νικήτρια η δεύτερη ομάδα
    private String choice;


    //Η παράμετρος "betType" είναι ο τύπος του αγώνα. Μπορεί να πάρει μόνο μια εκ των 2 τιμών: "Football" ή "Basketball"
    //Η δοθείσα τιμή του String betType που δίνεται κατά την προσομοίωση θα ελέγχεται στον constructor της κλάσης CustomerBet
    //και παράλληλα θα ελέγχεται αν η επιλογή, "choice", αφορά στις διαθέσιμες επιλογές του εκάστοτε τύπου αγώνα.
    public String betType;
    public CustomerBet(String betName, String betType, int stake, String choice) {
        this.betName = betName;
        this.stake = stake;

        //BEGIN Έλεγχος αν ο χρήστης έχει συμπληρώσει σωστό betType και επιλογή αποτελέσματος αγώνα (choice)
        if (availableBetTypes.contains(betType) ) {
            this.betType = betType;

            if (betType.contains("Football") && FootballBet.getChoices().contains(choice))
                this.choice = choice;

                else if (betType.contains("Basketball") && BasketballBet.getChoices().contains(choice))
                    this.choice = choice;
            else
            throw new IllegalArgumentException("Invalid choice: " + choice);

        } else
                throw new IllegalArgumentException("Invalid bet Type: " + betType);
        //END Έλεγχος αν ο χρήστης έχει συμπληρώσει σωστό betType και επιλογή αποτελέσματος αγώνα (choice)
    }


    //Προσθήκη getters για έλεγχο Διαθέσιμων Bet Types
    public List<String> getAvailableBetTypes() {
        return availableBetTypes;
    }

    public String getBetName() {
        return betName;
    }
    public int getStake() {
        return stake;
    }
    public String getChoice() {
        return choice;
    }
}