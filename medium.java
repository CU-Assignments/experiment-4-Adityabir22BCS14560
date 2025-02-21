import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

class Card {
    private String symbol;
    private String value;

    public Card(String symbol, String value) {
        this.symbol = symbol;
        this.value = value;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value + " of " + symbol;
    }
}

public class Medium {
    private Collection<Card> cards;

    public Medium() {
        cards = new ArrayList<>();
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public Collection<Card> findCardsBySymbol(String symbol) {
        Collection<Card> result = new ArrayList<>();
        for (Card card : cards) {
            if (card.getSymbol().equalsIgnoreCase(symbol)) {
                result.add(card);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Medium cardCollection = new Medium();
        Scanner scanner = new Scanner(System.in);

        // Adding some cards to the collection
        cardCollection.addCard(new Card("Hearts", "Ace"));
        cardCollection.addCard(new Card("Spades", "King"));
        cardCollection.addCard(new Card("Hearts", "Queen"));
        cardCollection.addCard(new Card("Diamonds", "Jack"));
        cardCollection.addCard(new Card("Clubs", "Ten"));

        System.out.println("Enter the symbol of the cards you want to find (e.g., Hearts, Spades): ");
        String symbol = scanner.nextLine();

        Collection<Card> foundCards = cardCollection.findCardsBySymbol(symbol);

        if (foundCards.isEmpty()) {
            System.out.println("No cards found with the symbol: " + symbol);
        } else {
            System.out.println("Cards found with the symbol " + symbol + ":");
            for (Card card : foundCards) {
                System.out.println(card);
            }
        }

        scanner.close();
    }
}
