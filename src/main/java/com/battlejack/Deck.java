package com.battlejack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    private List<Card> cards;

    public Deck(int deckCount){
        cards = new ArrayList<>();
        initializeDeck(cards, deckCount);
        shuffle();
    }

    public void initializeDeck(List<Card> cards, int deckCount){
        for(int i = 0; i < deckCount; i++){
            for(Suit suit : Suit.values()){
                for(Rank rank : Rank.values()){
                    cards.add(new Card(suit, rank));
                }
            }
        }
    }
    
    public void print(){
        for(Card card : cards){
            System.out.println(card);
        }
    }

    public void shuffle(){
        Collections.shuffle(cards);
    }

    public Card deal(){
        return cards.isEmpty() ? null : cards.remove(0);
    }

    public int getRemainingCards() {
        return cards.size();
    }
}
