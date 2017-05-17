/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alphabetshakespeare;

import java.io.PrintStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author danie
 */
public class SoundexspeareTrie {
    // +1 for array size, +1 for "'"

    public static int SIZE = indexOf('z') + 8;

    //private TrieData data = null;
    private Map<String, TrieData> suggestions = null;
    private SoundexspeareTrie[] tries = null;

    private static int indexOf(char letter) {
        if (letter == '\'') {
            return 26;
        }
        if (letter == '1') {
            return 27;
        }
        if (letter == '2') {
            return 28;
        }
        if (letter == '3') {
            return 29;
        }
        if (letter == '4') {
            return 30;
        }
        if (letter == '5') {
            return 31;
        }
        if (letter == '6') {
            return 32;
        }

        return (letter - 'a');
    }
    
    public void put(String word){
        put(FileUtility.soundexefy(word),word);
    }

    public void put(String word, String data) {
        if (word.isEmpty()) {
            if (suggestions == null) {
                suggestions = new HashMap();
            }
            if (suggestions.containsKey(data)) {
                TrieData get = suggestions.get(data);
                get.setCount(get.getCount() + 1);
            } else {
                suggestions.put(data, new TrieData(data));
            }
//            if (this.data == null) {
//                this.data = new TrieData(data);
//            } else {
//                int count = this.data.getCount() + 1;
//                this.data.setCount(count);
//            }
        } else {
            char letter = word.charAt(0);
            int index = indexOf(letter);
            if (tries == null) {
                tries = new SoundexspeareTrie[SIZE];
            }
            if (tries[index] == null) {
                tries[index] = new SoundexspeareTrie();
            }
            SoundexspeareTrie trie = tries[index];
            trie.put(word.substring(1), data);
        }
    }

    public TrieData get(String word) {
        return get(word, FileUtility.soundexefy(word));
    }

    public TrieData get(String word, String soundex) {
        if (soundex.isEmpty()) {
            if (suggestions.containsKey(word)) {
                return suggestions.get(word);
            }
            getSuggestions(word);
        }
        if (tries == null) {
            return null;
        }
        char letter = soundex.charAt(0);
        int index = indexOf(letter);
        SoundexspeareTrie trie = tries[index];
        if (trie == null) {
            return null;
        }
        return trie.get(word, soundex.substring(1));
    }
    
    public Map getSuggestions(String word,String soundex) {
        if (soundex.isEmpty()) {
           
                return suggestions;
            
        }
        if (tries == null) {
            return null;
        }
        char letter = soundex.charAt(0);
        int index = indexOf(letter);
        SoundexspeareTrie trie = tries[index];
        if (trie == null) {
            return null;
        }
        return trie.getSuggestions(word, soundex.substring(1));
    }
    

    public void getSuggestions(String word) {
      String soundex = FileUtility.soundexefy(word);
        Map sug = getSuggestions(word,soundex);

        Collection<TrieData> values = sug.values();
        System.out.println("The word '"+word+"' was not found!");
        for (TrieData value : values) {
            System.out.println("Did you mean: "+value.getData()+ "?");

        }
    }

    public void print(PrintStream out) {

        if (suggestions != null) {
            Collection<TrieData> values = suggestions.values();
            for (TrieData value : values) {
                
            out.println(value.getData() + " count: " + value.getCount());
            }

        }
        if (tries != null) {
            for (SoundexspeareTrie trie : tries) {
                if (trie == null) {
                    continue;
                }
                trie.print(out);
            }
        }
    }

    public void printTotal(PrintStream out) {

        out.println("Total count = " + getTotal());

    }

    public int getTotal() {
        int totalCount = 0;
        if (suggestions != null) {
            Collection<TrieData> values = suggestions.values();
            for (TrieData value : values) {
                
            totalCount += value.getCount();
            }
        }
        if (tries != null) {
            for (SoundexspeareTrie trie : tries) {
                if (trie == null) {
                    continue;
                }
                totalCount += trie.getTotal();
            }
        }
        return totalCount;
    }
}
