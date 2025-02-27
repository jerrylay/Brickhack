package com.battlejack;

public enum Rank {
    ACE,
    TWO,
    THREE,
    FOUR,
    FIVE,
    SIX,
    SEVEN,
    EIGHT,
    NINE,
    TEN,
    JACK,
    QUEEN,
    KING;

    // Convert enum to file name value
    public String toFileNameValue() {
        switch (this) {
            case ACE:
                return "ace";
            case TWO:
                return "2";
            case THREE:
                return "3";
            case FOUR:
                return "4";
            case FIVE:
                return "5";
            case SIX:
                return "6";
            case SEVEN:
                return "7";
            case EIGHT:
                return "8";
            case NINE:
                return "9";
            case TEN:
                return "10";
            case JACK:
                return "jack";
            case QUEEN:
                return "queen";
            case KING:
                return "king";
            default:
                throw new IllegalArgumentException("Invalid rank");
        }
    }
}
