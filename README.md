# Auto-Corrector
SpellChecker: A Mini Project

The `SpellChecker` program is a simple tool that loads a dictionary from a file and checks the spelling of words in a given text file. It identifies and outputs all misspelled words to the console.

## Features

- **Load Dictionary**: The program reads a list of correctly spelled words from a dictionary file and stores them in an array.
- **Spell Check**: It checks the spelling of each word in a specified text file against the loaded dictionary.
- **Misspelled Words Detection**: The program identifies and outputs misspelled words, along with their line number and word position in the text file.

## Usage

1. **Load the Dictionary**: 
   - The dictionary file should contain a list of correctly spelled words, one word per line. 
   - The words should be sorted and should not contain spaces within them.

2. **Run Spell Check**:
   - Provide the name of the text file you want to check.
   - The program will output any misspelled words to the console in the format: 
     ```
     Line <line number>,  Word <word number>, <misspelled word>
     ```
   - After processing, the total number of misspelled words and the total number of words in the file will be displayed.

## Example

Suppose you have a dictionary file named `dictionary.txt` and a text file named `document.txt`. You can run the spell checker as follows:

```java
SpellChecker spellChecker = new SpellChecker("dictionary.txt");
spellChecker.doSpellCheck("document.txt");
```

## Methods

### `SpellChecker(String dictionaryFilename)`
- **Description**: Loads the dictionary from the specified file into an array.
- **Parameters**: `dictionaryFilename` - The name of the dictionary file.

### `void doSpellCheck(String filename)`
- **Description**: Checks the spelling of each word in the specified file and outputs misspelled words to the console.
- **Parameters**: `filename` - The name of the text file to be spell-checked.

### `boolean isWordMisspelled(String word)`
- **Description**: Checks if a word is misspelled by comparing it against the dictionary.
- **Parameters**: `word` - The word to check.
- **Returns**: `true` if the word is misspelled; `false` otherwise.

### `String removePunctuation(String word)`
- **Description**: Removes common punctuation from a word.
- **Parameters**: `word` - The word from which to remove punctuation.
- **Returns**: The word without punctuation.

## Notes

- The program assumes that words in the dictionary file are lowercase and do not contain spaces or hyphens.
- The text file being checked can contain punctuation and mixed case words; these will be normalized during the spell check process.
