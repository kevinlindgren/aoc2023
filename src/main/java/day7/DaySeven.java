package day7;

import java.util.ArrayList;
import java.util.List;

public class DaySeven {

    public long summ(List<String> rows) {
        List<HandAndStrength> handAndStrengths = new ArrayList<>();
        for (String row : rows) {
            String[] s = row.split(" ");
            String hand = s[0];
            Integer bet = Integer.parseInt(s[1]);

            int handStrength = getHandStrength(hand);
            handAndStrengths.add(new HandAndStrength(hand, handStrength, bet));
        }

        int sum = 0;
        List<HandAndStrength> sorted = handAndStrengths.stream().sorted().toList();
        for (int i = 0; i < sorted.size(); i++) {
            HandAndStrength handAndStrength = sorted.get(i);
            sum += handAndStrength.getBet() * (i + 1);
        }
        return sum;
    }

    private int getHandStrength(String hand) {
        String newHand = modifyJokersToHand(hand);
        if (isFiveOfAKind(hand)) {
            return 7;
        } else if (isFourOfAKind(hand)) {
            return 6;
        } else if (isFullHouse(hand)) {
            return 5;
        } else if (isThreeOfAKind(hand)) {
            return 4;
        } else if (isTwoPair(hand)) {
            return 3;
        } else if (isOnePair(hand)) {
            return 2;
        } else if (isHighCard(hand)) {
            return 1;
        }
        return 0;
    }

    private boolean isHighCard(String hand) {
        return hand.chars().distinct().count() == 5;
    }

    private boolean isOnePair(String hand) {
        String newHand = modifyJokersToHand(hand);
        return newHand.chars().distinct().count() == 4;
    }

    private boolean isTwoPair(String hand) {
        String newHand = modifyJokersToHand(hand);
        return newHand.chars().distinct().count() == 3;
    }

    private boolean isThreeOfAKind(String hand) {
        String newHand = modifyJokersToHand(hand);
        int[] dc = newHand.chars().distinct().toArray();
        return newHand.chars().distinct().count() == 3
                && (countOfChars(newHand, dc[0]) == 3 || countOfChars(newHand, dc[1]) == 3 || countOfChars(newHand, dc[2]) == 3);
    }

    private boolean isFullHouse(String hand) {
        String newHand = modifyJokersToHand(hand);
        int[] dc = newHand.chars().distinct().toArray();
        if (newHand.chars().distinct().count() == 2
                && ((countOfChars(newHand, dc[0]) == 3 || countOfChars(newHand, dc[1]) == 2)
                || countOfChars(newHand, dc[1]) == 3 || countOfChars(newHand, dc[0]) == 2)) {
            return true;
        }
        return false;
    }

    private boolean isFourOfAKind(String hand) {
        String newHand = modifyJokersToHand(hand);
        for (int i = 0; i < newHand.toCharArray().length; i++) {
            char ch =  newHand.charAt(i);
            if (countOfChars(newHand, ch) == 4) {
                return true;
            }
        }
        return false;
    }

    private boolean isFiveOfAKind(String hand) {
        String newHand = modifyJokersToHand(hand);
        for (int i = 0; i < newHand.toCharArray().length; i++) {
            char ch =  newHand.charAt(i);
            if (countOfChars(newHand, ch) == 5) {
                return true;
            }
        }
        return false;
    }
    
    private String modifyJokersToHand(String originalHand) {
        int[] dc = originalHand.chars().distinct().toArray();
        char maxChar = 'z';
        int maxCharCount = 0;
        for (int i = 0; i < dc.length; i++) {
            if ((char) dc[i] == 'J')
                continue;
            int count = countOfChars(originalHand, (char) dc[i]);
            if (count > maxCharCount) {
                maxCharCount = count;
                maxChar = (char) dc[i];
            }
        }
        
        return originalHand.replaceAll("J", Character.toString(maxChar));
    }

    private int countOfChars(String str, int ch) {
        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == ch) {
                count++;
            }
        }
        return count;
    }

    private static final class HandAndStrength implements Comparable<HandAndStrength> {
        private final String hand;
        private final int strength;
        private final Integer bet;

        private HandAndStrength(String hand, int strength, Integer bet) {
            this.hand = hand;
            this.strength = strength;
            this.bet = bet;
        }

        public String getHand() {
            return hand;
        }

        public int getStrength() {
            return strength;
        }

        public Integer getBet() {
            return bet;
        }

        @Override
        public int compareTo(HandAndStrength other) {
            if (getStrength() > other.getStrength()) {
                return 1;
            } else if (other.getStrength() > getStrength()) {
                return -1;
            } else {
                char[] thisHandChars = getHand().toCharArray();
                char[] otherHandChars = other.getHand().toCharArray();
                for (int i = 0; i < 5; i++) {
                    if (getCardStrength(thisHandChars[i]) > getCardStrength(otherHandChars[i])) {
                        return 1;
                    } else if (getCardStrength(thisHandChars[i]) < getCardStrength(otherHandChars[i])) {
                        return -1;
                    }
                }
            }
            return 0;
        }

        private int getCardStrength(char ch) {
            return switch (ch) {
                case 'A' -> 14;
                case 'K' -> 13;
                case 'Q' -> 12;
                case 'J' -> 1;
                case 'T' -> 10;
                default -> Character.getNumericValue(ch);
            };
        }
    }
}
