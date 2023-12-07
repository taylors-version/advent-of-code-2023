package com.ben.aoc;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        CamelCards camelCards = new CamelCards();
        System.out.println("day7 puzzle1: " + camelCards.getWinAmount("input.txt"));
        System.out.println("day7 puzzle2: " + camelCards.getWinAmountJokers("input.txt"));
    }
}
