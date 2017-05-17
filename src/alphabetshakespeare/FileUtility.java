
    package alphabetshakespeare;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class FileUtility {

  public static String[] toStringArray(
      String path,
      String delimiterPattern
      ) throws IOException {
    return Files.lines(Paths.get(path))
        .flatMap(line -> Stream.of(line.split(delimiterPattern)))
        .filter(word -> !word.isEmpty())
        .map(word -> word.toLowerCase())
        .toArray(String[]::new);
    }
public static String soundexefy(String word){
    String soundex = word;
    char first = word.charAt(0);
    soundex = drophw(soundex);
    soundex = consonantsToDigits(soundex);
    soundex = removeDuplicate(soundex,word);
    soundex = soundex.charAt(0)+dropaeiouy(soundex.substring(1));
    soundex = firstLetter(soundex,first);
    soundex = trim(soundex);
    return soundex;
}
private static String trim (String s){
    
    while (s.length()<4){
        s+="0";
    }
    if (s.length()>4){
        return s.substring(0, 4);
    }
    return s;
    
}
private static String firstLetter(String s, char first){
    if (s.charAt(0)=='1' || s.charAt(0)=='2' ||s.charAt(0)=='3' ||s.charAt(0)=='4' 
            ||s.charAt(0)=='5' ||s.charAt(0)=='6') return first+s.substring(1);
    return s;
}
private static String removeDuplicate(String s, String original){
    if (s.length()==1) return s;
    if (s.charAt(0)=='1' || s.charAt(0)=='2' ||s.charAt(0)=='3' ||s.charAt(0)=='4' 
            ||s.charAt(0)=='5' ||s.charAt(0)=='6'){
        if (s.charAt(0)==s.charAt(1)) return removeDuplicate(s.substring(1),original);
    }
    return s.charAt(0)+removeDuplicate(s.substring(1),original);
}
private static String drophw(String s){
    if (s.isEmpty()) return "";
    if (s.charAt(0) == 'h'||s.charAt(0) == 'w') return drophw(s.substring(1));
    else return s.charAt(0)+drophw(s.substring(1));
}
private static String dropaeiouy(String s){
    if (s.isEmpty()) return "";
    
    if (s.charAt(0) == 'a' || s.charAt(0) == 'e' || s.charAt(0) == 'i'||s.charAt(0) == 'o'||s.charAt(0) == 'u'
            ||s.charAt(0) == 'y'){
        return dropaeiouy(s.substring(1));
    }
    else return s.charAt(0)+dropaeiouy(s.substring(1));
    
}
private static String consonantsToDigits(String s){
    String toReturn;
    s=s.toLowerCase();
    if (s.isEmpty()) return "";
      toReturn = s.replaceAll("b", "1");
    toReturn = toReturn.replaceAll("f", "1");
    toReturn = toReturn.replaceAll("p", "1");
    toReturn = toReturn.replaceAll("v", "1");
    toReturn = toReturn.replaceAll("c", "2");
    toReturn = toReturn.replaceAll("g", "2");
    toReturn = toReturn.replaceAll("j", "2");
    toReturn = toReturn.replaceAll("k", "2");
    toReturn = toReturn.replaceAll("q", "2");
    toReturn = toReturn.replaceAll("s", "2");
    toReturn = toReturn.replaceAll("x", "2");
    toReturn = toReturn.replaceAll("z", "2");
    toReturn = toReturn.replaceAll("d", "3");
    toReturn = toReturn.replaceAll("t", "3");
    toReturn = toReturn.replaceAll("l", "4");
    toReturn = toReturn.replaceAll("m", "5");
    toReturn = toReturn.replaceAll("n", "5");
    toReturn = toReturn.replaceAll("r", "6");
    
    return toReturn;
}

  }

  // usage:
  // ------------------------
  //   String[] words = 
  //       FileUtility.toStringArray("PathToTheFile.txt", "[^A-Za-z]");
