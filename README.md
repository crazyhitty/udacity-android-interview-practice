# Android Interview Practice

### What’s your favorite tool or library for Android? Why is it so useful?

**[Retrofit](https://square.github.io/retrofit/)** : This library makes network requests quite simple and it is very easy to implement this library in an android project. The reason why this is my favorite library is that it reduces the overall time required to integrate web services into an android application and its API is very easy to understand. Apart from that it is completely open sourced and widely used by the android developer community. Therefore, it is heavily tested in various scenarios in numerous applications.

Some of my favorite features:

* While creating instance of Rest service we can add our own custom [OkHttpClient](https://square.github.io/okhttp/3.x/okhttp/okhttp3/OkHttpClient.html) through which we can further add custom interceptors. These interceptors can modify or read the contents of our requests or responses, also we can use 3rd party libraries like [Stetho](https://github.com/facebook/stetho) to debug the requests with their responses easily.
* We can add custom converter which can automatically parse and save the response into a POJO. Multiple converters are available for handling json and xml responses.
* Run any request in background thread using [enqueue()](https://square.github.io/retrofit/2.x/retrofit/retrofit2/Call.html#enqueue-retrofit2.Callback-) method.

----

### You want to open a map app from an app that you’re building. The address, city, state, and ZIP code are provided by the user. What steps are involved in sending that data to a map app?

First, I would look out if that particular map application has some documentation which provides some information on how that map application handles intents. If that application has an activity which handles implicit intents through which we can send data like address, city, state, and ZIP code then we can proceed and fire such intent in our own application. 

For example: Let's suppose we want to open Google Maps application with a custom address from our own application. As we can see from the documentation provided at [Google Maps Intents](https://developers.google.com/maps/documentation/android-api/intents), Google Maps application provides various types of intents which can be used externally by any application.

Here is the code snippet copied directly from their documentation on how to open a particular address:

```
Uri gmmIntentUri = Uri.parse("geo:0,0?q=1600 Amphitheatre Parkway, Mountain+View, California");
Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
mapIntent.setPackage("com.google.android.apps.maps");
startActivity(mapIntent);
```

The above code will start the Google Maps application and load the provided address and will show a marker on the loaded position.

----

### Implement a method to perform basic string compression using the counts of repeated characters. For example, the string aabcccccaaa would become a2b1c5a3. If the "compressed" string would not become smaller than the original string, your method should return the original string. The method signature is: “public static String compress(String input)” You must write all code in proper Java, and please include import statements for any libraries you use.

```
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
```

**NOTE**: You can also follow these steps in order to run the above program directly: 

1. Clone this repository.
2. In the root directory, run this command: `gradle --console plain executeProgram`, this will execute the java program automatically.

----

### List and explain the differences between four different options you have for saving data while making an Android app. Pick one, and explain (without code) how you would implement it.

1. **SharedPrefrences**: Stores data in an xml format, good for storing simple data types like int, boolean, String, etc.
2. **SQLite**: Database that stores data in tables and allows quering of data.
3. **ContentProvider**: Acts as an abstraction layer on top of your core database, good if you want your application database to be sharable with other applications.
4. **File System**: Storing data in normal files in device storage, good for storing media files and data with larger size.

**Implementing ContentProvider**: Inorder to implement ContentProvider properly, we must follow these steps:

* Create the table structure of the database backed by a unique URI, through which we can access that particular table.
* Create core database and maintain a helper class that will manage all of the database transactions.
* Add connectivity between ContentProvider and Database so that you can manage data using your ContentProvider.
* Access ContentResolver to query on data.

----

### What are your thoughts about Fragments? Do you like or hate them? Why?

Fragments are great for creating applications with modular UI, especially when your application supports mutiple devices with different screen sizes. As for liking them, its more like a love-hate relationship. Their complex lifecycle is one thing I hate, plus when I initially started using them, it was very difficult to manage savedInstanceState on orientation changes. Things I like is that we can just create them and then use the same fragment in different UIs, like in multi-pane layouts.

----

### If you were to start your Android position today, what would be your goals a year from now?

Mainly I would like to focus on testing, continuous integration, reactive programming and algorithmic skills as it would help me become a much better software engineer. Apart from that I would like to increase my team membership skills and actively contribue to the engineering team I work with.