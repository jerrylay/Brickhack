package com.battlejack;

import java.io.*;

public class GameState {
    private static int level = 1;
    private static int startingMoney = 1000;
    private static int winningTarget = 10000;
    private static int totalWinnings = loadWinnings();
    private static int multiplier = loadMultiplier();

    public static void nextLevel() {
        level++;

        switch (level) {
            case 2:
                startingMoney = 2000;
                winningTarget = 25000;
                break;
            case 3:
                startingMoney = 5000;
                winningTarget = 50000;
                break;
            default:
                level = 1;
                startingMoney = 1000;
                winningTarget = 10000;
                break;
        }
    }

    public static int getLevel(){
        return level; 
    }

    public static int getStartingMoney(){ 
        return startingMoney; 
    }

    public static int getWinningTarget(){ 
        return winningTarget; 
    }

    public static int getTotalWinnings() {
        return totalWinnings;
    }

    public static void addWinnings(int amount) {
        totalWinnings += amount;
        saveWinnings();
    }

    private static void saveWinnings() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("winnings.txt"))) {
            writer.write(String.valueOf(totalWinnings));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int getMultiplier(){
        return multiplier;
    }

    private static int loadWinnings() {
        try (BufferedReader reader = new BufferedReader(new FileReader("winnings.txt"))) {
            return Integer.parseInt(reader.readLine());
        } catch (IOException | NumberFormatException e) {
            return 0;
        }
    }

    public static void addMultplier(int amount){
        multiplier += amount;
        saveMultiplier();
    }

    private static void saveMultiplier() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("multiplier.txt"))) {
            writer.write(String.valueOf(multiplier));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int loadMultiplier() {
        try (BufferedReader reader = new BufferedReader(new FileReader("multiplier.txt"))) {
            return Integer.parseInt(reader.readLine());
        } catch (IOException | NumberFormatException e) {
            return 0;
        }
    }
}
