package com.battlejack;

public class Card {
    private final Suit suit;
    private final Rank rank;

    public Card(Suit suit, Rank rank){
        this.suit = suit;
        this.rank = rank;
    }

    public Suit getSuit(){
        return suit;
    }

    public Rank getRank(){
        return rank;
    }
    
    public int getValue(){
        switch(rank){
            case ACE:
                return 11;
            case TWO:
                return 2;
            case THREE:
                return 3;
            case FOUR:
                return 4;
            case FIVE:
                return 5;
            case SIX:
                return 6;
            case SEVEN:
                return 7;
            case EIGHT:
                return 8;
            case NINE:
                return 9;
            case TEN:
            case JACK:
            case QUEEN:
            case KING:
                return 10;
            default:
                throw new IllegalArgumentException("Invalid card rank");
        }
    }

    @Override
    public String toString(){
        return rank.toString() +  " OF " + suit.toString();
    }

    public String getFileName() {
        switch (rank) {
            case ACE:
                return "ace_of_" + suit.toString().toLowerCase() + ".png";
            case TWO:
                return "2_of_" + suit.toString().toLowerCase() + ".png";
            case THREE:
                return "3_of_" + suit.toString().toLowerCase() + ".png";
            case FOUR:
                return "4_of_" + suit.toString().toLowerCase() + ".png";
            case FIVE:
                return "5_of_" + suit.toString().toLowerCase() + ".png";
            case SIX:
                return "6_of_" + suit.toString().toLowerCase() + ".png";
            case SEVEN:
                return "7_of_" + suit.toString().toLowerCase() + ".png";
            case EIGHT:
                return "8_of_" + suit.toString().toLowerCase() + ".png";
            case NINE:
                return "9_of_" + suit.toString().toLowerCase() + ".png";
            case TEN:
                return "10_of_" + suit.toString().toLowerCase() + ".png";
            case JACK:
                return "jack_of_" + suit.toString().toLowerCase() + ".png";
            case QUEEN:
                return "queen_of_" + suit.toString().toLowerCase() + ".png";
            case KING:
                return "king_of_" + suit.toString().toLowerCase() + ".png";
            default:
                throw new IllegalArgumentException("Invalid rank: " + rank);
        }
    }
}
