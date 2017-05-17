/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alphabetshakespeare;

/**
 *
 * @author danie
 */
public class TrieData {
    private int count;
    private String data;

    
    public TrieData(String data){
        this.data=data;
        count=1;
    }
    

    
    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
    
    
    
}
