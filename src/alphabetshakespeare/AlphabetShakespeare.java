/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alphabetshakespeare;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author danie
 */
public class AlphabetShakespeare {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int a = 'z'-'a';
        System.out.println(a);
//        ShakespeareTrie st = new ShakespeareTrie();
        SoundexspeareTrie st = new SoundexspeareTrie();
        try {
            // TODO code application logic here
            String[] array = FileUtility.toStringArray("shakespeare-complete-works.txt", "[^A-Za-z']+");
            
            for (String string : array) {
                st.put(string, string);
            }
            
            st.print(System.out);
            st.printTotal(System.out);
            System.out.println(FileUtility.soundexefy("Pfister"));
            System.out.println(st.get("rage"));
        } catch (IOException ex) {
            Logger.getLogger(AlphabetShakespeare.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
}
