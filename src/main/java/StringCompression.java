import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StringCompression {

    public static void main(String[] args) {
        System.out.print("\n\nInput string to be compressed: ");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.next();
        scanner.close();
        System.out.format("\nCompression output: %s\n\n", compress(input));
    }

    /**
     * Compresses the given string. For eg: aabcccccaaa would become a2b1c5a3
     *
     * @param input String value to be compressed
     * @return Returns compressed string if it is smaller than input string in length, otherwise return the
     * input string.
     */
    public static String compress(String input) {
        List<CompressionNode> compressionNodes = new ArrayList<CompressionNode>();

        // Add first character of the input string into the list.
        compressionNodes.add(new CompressionNode(input.charAt(0), 1));

        // Now iterate through all of the characters available, 
        // from index position 1, as we have already included the char at 0th position.
        for (int i = 1; i < input.length(); i++) {
            char currentCharacter = input.charAt(i);
            // Check if current character is equal to the character available in the 
            // last item of the compressionNodes.
            CompressionNode lastCompressionNode = compressionNodes.get(compressionNodes.size() - 1);
            if (currentCharacter == lastCompressionNode.getCharacter()) {
                // Increment the value of the its existing count.
                lastCompressionNode.increment();
            } else {
                // Add a new character to compressionNodes as it doesn't exists yet.
                compressionNodes.add(new CompressionNode(currentCharacter, 1));
            }
        }

        // Iterate back the compressionNodes and retrieve the compressedString.
        String compressedString = "";
        for (CompressionNode compressionNode : compressionNodes) {
            compressedString += String.format("%c%d", compressionNode.getCharacter(), compressionNode.getCount());
        }

        // Check if compressedString is smaller than the input string, if yes then
        // return it otherwise return the input string itself.
        if (compressedString.length() < input.length()) {
            return compressedString;
        } else {
            return input;
        }
    }

    private static class CompressionNode {
        private char character;
        private int count;

        public CompressionNode() {

        }

        public CompressionNode(char character, int count) {
            this.character = character;
            this.count = count;
        }

        public char getCharacter() {
            return character;
        }

        public void setCharacter(char character) {
            this.character = character;
        }

        public int getCount() {
            return count;
        }

        public void increment() {
            count = count + 1;
        }
    }
}