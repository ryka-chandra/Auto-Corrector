// SpellChecker: Loads a dictionary into an array and
// outputs all the misspelled words of a file to the console window.

import java.io.*;
import java.util.*;

public class SpellChecker {

    public static PrintStream out = System.out;

    private String[] dictionary;

    // This will load all the words in the dictionary 
    // fle into an array that will be used as the dictionary
    // of correctly spelled words.
    //
    // Parameters:
    //  - dictionaryFilename : the  name of the file using a relative path.
    //
    // Returns/outputs: nothing
    //
    // Preconditions:
    //  - No word has a space within it.
    //  - You do not know the number of words in the file.
    //  - The words will be sorted.
    public SpellChecker(String dictionaryFilename) {
      loadDictionary(dictionaryFilename);
    }

    // This method counts the numebr of words in a file and returns that number
    //
    // Parameters:
    //  - filename : the name of the file that whose tokens are going to be counted
    //
    // Returns/outputs:
    //  - the number of words in the file that the user put in as a parameter
    //
    // Precondition:
    //  - No word has a space within it.
    private int countWords(String filename) {
      
      // becuase there can be a FileNotFoundException thrown here, it is best to try a block
      // of code and then catch the exception if it pops up
      try {
        
        // this creates a scanner that will parse through the file so that every token
        // in it can be counted becuase wihtout scanning tokens cannot be counted
        // (the fiel is also intialized in the same line of code)
        Scanner parser = new Scanner(new File(filename));
        
        // this "count" variable is intitilized and it is incremented every time a word 
        // has been scanned in the while loop (that will keep on going until there is no
        // more tokens to be scanned in the file)
        int count = 0;
        while (parser.hasNext()) {
          count++;
          
          // after the count has been incremented since another word has been recognized, 
          // the parser has to move to it's cursor to scan the next token, and as such
          // the parser is moved to the next position here
          parser.next();
        }
        
        // the number of words that has been counted in the file is returned after the file is parsed
        return count;
      } 
      
      // becuase in this case we are scanning a file, it is possible for the file to
      // not be found and that would throw a FileNotFoundException and thus it needs to be
      // caught (and it has an identifier of "e") so there is no error thrown and instead the
      // method will return a value of 0 because the file was not found and so there cannot
      // be any token in it that can be counted
      catch (Exception e) {
        return 0;
      }
    }

    // This method scans a file and parses through it and adds that to a string array called dictionary
    // so that the dictionary has everything in the file, but as tokens
    //
    // Parameters:
    //  - filename : the name of the file that is going to be scanned to create the dictionary
    //
    // Returns/outputs: nothing
    //
    // Pre-condition:
    //  - The String[] dictionary is already declared (but the size is not).
    private void loadDictionary(String filename) {
      
      // becuase there can be a FileNotFoundException thrown here, it is best to try a block
      // of code and then catch the exception if it pops up
      try {
        
        // this is counting all the words in the String parameter and making the
        // dictionary array equal to the number of all the words
        int size = countWords(filename);
        dictionary = new String[size];
        
        // the method is then scanning the file and uses a for loop 
        // to put what been scanned into the dictionary array
        Scanner parser = new Scanner(new File(filename));
        for (int i = 0; i < dictionary.length; i++) {
          dictionary[i] = parser.next();
        }
      } 
      
      // becuase in this case we are scanning a file, it is possible for the file to
      // not be found and that would throw a FileNotFoundException and thus it needs to be
      // caught (and it has an identifier of "ex") so there is no error thrown
      catch (FileNotFoundException ex) {}
    }

    // HELPER METHOD #1 -- This will check to see if the word is mispelled and if it is 
    // then it will return true 
    //
    // Parameters:
    //  - word : the word in the essay that is being checked for misspelling
    //
    // Returns/outputs:
    //  If a word cannot be found in the dictionary file, then it will be considered misspelled
    //  and the method will return true (becuase the word IS misspelled)
    //
    // Pre-condition:
    //  - No word has a space within it.
    //  - The word has no hyphens.
    public boolean isWordMisspelled(String word) {
      
      // this for loop checks the parameter against every word in the dictionary array
      // at an index of i (which is a value that is constantly incrementing until the end of
      // the array has been reached)
      for (int i = 0; i < dictionary.length; i++) {
        if (word.equals(dictionary[i])) {
          return false;
        }
      }
      return true;
    }


    // HELPER METHOD #2 -- This will remove the punctuation (if present) from each word.
    //
    // Parameters:
    //  - word : the word in the essay that is being checked for misspelling
    //
    // Returns/outputs:
    //  If a word contains punctuation [ie (, ), ., ', /, \, ,] then it will
    //  return the word having no punctuation by replacing that part with an empty string
    //
    // Pre-condition:
    //  - No word has a space within it.
    //  - The word has all lowercase letters in it.
    //  - The word has no hyphens.
    private String removePunctuation(String word) {
      word = word.replace(",", "");
      word = word.replace(".", "");
      word = word.replace("/", "");
      word = word.replace("\\", "");
      word = word.replace("'", "");
      word = word.replace("\"", "");
      word = word.replace("(", "");
      word = word.replace(")", "");
      return word;
    }

    // This will load the file and check the spelling of each word.
    //
    // Parameters:
    //  - filename : the name of the file to be spell checked
    //
    // Returns/outputs:
    //  If a word is misspelled, it will output to the console
    //    Line   <#>,  Word   <#>, <misspelling>
    //  The format will be: "Line %3d,  Word %2d, %s\n"
    //
    // Pre-condition:
    //  - The file will contain punctuation.
    //  - The words may have upper and lowercase letters.
    public void doSpellCheck(String filename) {
      
      // a try/catch is being used here to catch any exceptions that may pop up
      try {
        
        // this variable will be incremented after each word the is misspelled to
        // be printed at the end of the method
        int totalWrong = 0;
        
        // this Scanner will parse through the entire file becuae we need to scan
        // every line in the essay to be able to scan every word (that is why we
        // need 2 scanners)
        Scanner parser = new Scanner(new File(filename));
        
        // this variable represents the line number and after very line is parsd, 
        // it will be incremented to keep track of the line that each word is 
        // being scanned on
        int line = 1;
        
        // while there is another line in the essay, this while loop will continue
        // becuase we want every single line in the essay to have its words scanned
        while (parser.hasNextLine()) {
          
          // after each word in the line has been scanned, the wordNum has to be
          // set back to 1 because it keeps track of the number of the word in the 
          // line that it is on, not in the whole essay
          int wordNum = 1;          
          
          // this scanner scans every word in each line and while there is 
          // another word in the essay, this while loop will continue becuase we want 
          // check every word in the essay 
          Scanner lineReader = new Scanner(parser.nextLine());
          while (lineReader.hasNext()) {
            String word = lineReader.next();
            
            // becuase 1 of the preconditions for the removePunctuation method is
            // that the String parameter has to be all lowercase letters, the word
            // being scanned is being changed to all lowercase
            word = word.toLowerCase();
            word = removePunctuation(word);
            
            // if the word contains a hyphen then it must be split into two tokens
            // that will be put into a string called words (with the part before the hyphen as 1
            // string and the part after the hyphen as another string in the array)
            if (word.contains("-")) {
              String[] words = word.split("-");
              
              // for every word in the words array, this will check to see if it
              // is misspelled and if it is then it will print the line, word number, 
              // and mispelled word and also increment the totalWrong so that it can be 
              // accurately printed at the end of the method
              for (int i = 0; i < words.length; i++) {
                if (isWordMisspelled(words[i])) {
                  out.printf("Line %3d,  Word %2d, %s\n", line, wordNum, removePunctuation(word));
                  totalWrong++;
                }
              }
            } 
            
            // if the word does not have a hyphen but is misspelled then it will also 
            // print the line, word number, and mispelled word and also increment 
            // the totalWrong so that it can be accurately printed at the end of the method
            else if (isWordMisspelled(word)) {
                out.printf("Line %3d,  Word %2d, %s\n", line, wordNum, removePunctuation(word));
                totalWrong++;
            }
            
            // becuase the scanner is moving on to the next word, it will increment the
            // count of words (wordNum) so it can accurately determine what number word this is
            // ON THE LINE IT IS ON -- not in the whole essay
            wordNum++;
          }

          // this wil increment the line, so that ater each line has been scanned
          // it keeps track of what line the word being scanned is in the WHOLE ESSAY
          line++;
        }

        // this prints the final print statments that tell us the number of misspelled words
        // in the essay as well as the total number of words in the essay a line after all the
        // misspelled words are printed
        SpellChecker.out.println("\nDocument contains " + totalWrong + " error(s).");
        SpellChecker.out.print("There are " + countWords(filename) + " words in the file.");
      } 
      
      // if there is an exception present, it needs to be caught and it has the identifier "e"
      // becuase in this case we are scanning a file, it is possible for the file to
      // not be found and that would throw a FileNotFoundException
      catch (Exception e) {}
    }
}