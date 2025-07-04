package practice.Leetcode;
/*
 Alice and Bob are playing a game. Initially, Alice has a string word = "a".

You are given a positive integer k.

Now Bob will ask Alice to perform the following operation forever:

Generate a new string by changing each character in word to its next character in the English alphabet, and append it to the original word.
For example, performing the operation on "c" generates "cd" and performing the operation on "zb" generates "zbac".

Return the value of the kth character in word, after enough operations have been done for word to have at least k characters.

Note that the character 'z' can be changed to 'a' in the operation.
 */
//***** BRUTE FORCE APPROACH *****
class Solution {
    static char kthCharacter(int k) {
        String word = "a";

        while (word.length() < k) {
            StringBuilder shift = new StringBuilder();
            //this should be inside while-loop to remove appended string

            for (int i=0; i<word.length(); i++) {
                char c = word.charAt(i);

                char shiftChar = (char) ((c - 'a' + 1)%26 + 'a');
                //A wrapper for shifting characters within the alphabet.
                /*
                 * ex: finding the next (shifted) char for 'c'
                 * ascii of c = 99, ascii of a = 97
                 * so ('c (99)' - 'a (97)' + 1) = 3
                 * 3%26 (because we have 26 letters) = 3
                 * 3 + 'a (97)' = 100
                 * 100 = 'd' in ascii
                 */

                shift.append(shiftChar);
            }
            word += shift.toString();
        }

        return word.charAt(k-1);
    }

    public static void main(String[] args) {
        int k = 5;
        System.out.println(kthCharacter(k));
    }
}