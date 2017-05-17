/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alphabetshakespeare;

import java.io.PrintStream;

/**
 *
 * @author danie
 */
public class ShakespeareTrie {
    // +1 for array size, +1 for "'"
      public static int SIZE = indexOf('z') + 2;

  private TrieData data = null;
  private ShakespeareTrie[] tries = null; 
  
  private static int indexOf(char letter) {
      if (letter =='\'') return 26;
    return (letter - 'a');
    }
  
  public void put(String word, String data) {
    if (word.isEmpty()) {
      if (this.data==null)this.data = new TrieData(data);
      else {
          int count = this.data.getCount()+1;
          this.data.setCount(count);
      }
      }
    else {
      char letter = word.charAt(0);
      int index = indexOf(letter);
      if (tries == null) tries = new ShakespeareTrie[SIZE];
      if (tries[index] == null) tries[index] = new ShakespeareTrie();
      ShakespeareTrie trie = tries[index];
      trie.put(word.substring(1), data);
      }
    }
  
  public TrieData get(String word) {
    if (word.isEmpty()) return data;
    if (tries == null) return null;
    char letter = word.charAt(0);
    int index = indexOf(letter);
    ShakespeareTrie trie = tries[index];
    if (trie == null) return null;
    return trie.get(word.substring(1));
    }
  
  public void print(PrintStream out) {
      
    if (data != null){
        out.println(data.getData() + " count: "+data.getCount());
       
    }
    if (tries != null) {
      for (ShakespeareTrie trie : tries) {
        if (trie == null) continue;
        trie.print(out);
        }
      }
    }
  public void printTotal(PrintStream out){
      
      out.println("Total count = "+getTotal());
      
  }
  public int getTotal(){
   int totalCount = 0;
      if (data != null){
        totalCount += data.getCount();
    }
    if (tries != null) {
      for (ShakespeareTrie trie : tries) {
        if (trie == null) continue;
        totalCount+=trie.getTotal();
        }
      }
    return totalCount;
  }
}
