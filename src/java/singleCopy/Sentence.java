package singleCopy;

public class Sentence {
    
    public static String conv(String name){
        
        byte nm[]=name.getBytes();
        
        for (int i = 1; i < nm.length; i++) {
            nm[i]=(byte)(nm[i]+32);
        }
        name="";
        for (byte b : nm) {
            name=name.concat(String.valueOf((char)(b)));
        }
        return name;
    }    
}
