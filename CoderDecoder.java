/**
*@author José Rui Nogueira de Sá - fc58200
*/

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.LineNumberInputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class CoderDecoder{

	/**
	*Reads a given file, which will then be codified using the alphabetical key, numerical key, number of lines of text and text to codify, written in that order on the file, all on different lines
	*
	*@param inFile file given to read
	*@param outFile file given to write
	*@throws FileNotFoundException
	*@throws IOException
	*/
	public static void codify(String inFile, String outFile)throws FileNotFoundException, IOException{
		
		Scanner s = new Scanner(new File(inFile));
		PrintWriter pw = new PrintWriter(new File(outFile));
		String alphabeticKey = s.nextLine();
		String[] alphabeticKeyBreakdown = alphabeticKey.split("");
		int numberKey = s.nextInt();
		s.nextLine();
		int lineNumber = s.nextInt();
		s.nextLine();
		int counterLine = 1;
		
		pw.println(alphabeticKey);
		pw.println(numberKey);
		pw.println(lineNumber);
		
		while(counterLine <= lineNumber){
			
			String line = s.nextLine();
			String[] lineBreakdown = line.split("");
			int m = 1;
			
			for(int i = 0; i < lineBreakdown.length; i++){
					
				if(lineBreakdown[i].equals(" ")){
					
					pw.print(" ");
				}
					
				else if(belongsTo(lineBreakdown[i],alphabeticKeyBreakdown)){
					
					if(m <= (alphabeticKeyBreakdown.length-1)){
						pw.print(alphabeticKeyBreakdown[m-1]);
						pw.print(cod(lineBreakdown[i],numberKey));
						pw.print(alphabeticKeyBreakdown[m]);
						m++;
					}
					
					else{
						pw.print(alphabeticKeyBreakdown[m-1]);
						m = 0;
						pw.print(cod(lineBreakdown[i],numberKey));
						pw.print(alphabeticKeyBreakdown[m]);
						m++;
					}
				}
					
				else if(!belongsTo(lineBreakdown[i],alphabeticKeyBreakdown)){
						
					pw.print(cod(lineBreakdown[i],numberKey));
				}
			}	
			pw.println();
			counterLine++;
		}		
		
		s.close();
		pw.close();
	}
	
	/**
	*Translates the given letter into key positions ahead in the alphabet, considering the alphabet to be circular, key being a given integer value
	*
	*@param letter the letter to translate
	*@param key the integer value to translate the letter by
	*@return the letter given translated by key in the 26 letter English alphabet
	*/
	public static String cod(String letter, int key){
		
		int index = getIndexLetter(letter);
		String finalLetter = "";
		
		if((index + key) > 25){
			
			int remaining = (index + key) - 25;
			finalLetter = getLetterIndex(remaining - 1);
		}
		
		else if((index + key) <= 25){
			
			finalLetter = getLetterIndex(index + key);
		}
		
		return finalLetter;
	}
	
	/**
	*Gets the position from 0 to 25 of the given letter on the English 26 letter alphabet
	*
	*@requires {@code letter != null}
	*@param letter the string with the letter to check
	*@return the index of the letter's position on the 26 letter alphabet
	*/
	public static int getIndexLetter(String letter){
		
		String[] alphabet = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
		int index = 0;
		
		for(int i = 0; i < alphabet.length; i++){
			
			if(letter.equals(alphabet[i])){
				
				index = i;
			}
		}
		
		return index;
	}
	
	/**
	*Gets the letter on the index's position on the 26 letter English alphabet
	*
	*@requires {@code 0 <= index <= 25}
	*@param index the index to check on the alphabet
	*@return the letter on the index's position on the 26 letter alphabet
	*/
	public static String getLetterIndex(int index){
		
		String[] alphabet = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
		int[] indexes = {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25};
		String letter = "";
	
		for(int i = 0; i < indexes.length; i++){
			
			if(index == indexes[i]){
				
				letter = alphabet[i];
			}
		}
		
		return letter;
	}
	
	/**
	*Checks if given letter exists in the given array
	* 
	*@param letter the letter to check on the array
	*@param key the alphabetical key 
	*@return boolean value of letter existing in key
	*/
	public static boolean belongsTo(String letter, String[] key){
		
		boolean belong = false;
		
		for(int i = 0; i < key.length; i++){
			
			if(letter.equals(key[i])) {
				
				belong = true;
			}
		}
		
		return belong;
	}
	
	/**
	*Reads a given file, which will then be decodified using the alphabetical key, numerical key, number of lines of text and text to decodify, written in that order on the file, all on different lines
	*
	*@param inFile file given to read
	*@param outFile file given to write
	*@throws FileNotFoundException
	*@throws IOException
	*/
	public static void decodify(String inFile, String outFile)throws FileNotFoundException, IOException{
		
		Scanner s = new Scanner(new File(inFile));
		PrintWriter pw = new PrintWriter(new File(outFile));
		String alphabeticKey = s.nextLine();
		String[] alphabeticKeyBreakdown = alphabeticKey.split("");
		int numberKey = s.nextInt();
		s.nextLine();
		int lineNumber = s.nextInt();
		s.nextLine();
		int counterLine = 1;
		
		pw.println(alphabeticKey);
		pw.println(numberKey);
		pw.println(lineNumber);
		
		while(counterLine <= lineNumber){
			
			StringBuilder sb = new StringBuilder("");
			String line = s.nextLine();
			String[] lineBreakdown = line.split("");
			int m = 1;
			int pointer = -2;
			
			for(int i = 0; i < lineBreakdown.length; i++){
				
				if(lineBreakdown[i].equals(" ")){
					
					sb.append(" ");
				}
					
				else if(!belongsTo(reverseCod(lineBreakdown[i],numberKey),alphabeticKeyBreakdown)){
					
					sb.append(reverseCod(lineBreakdown[i],numberKey));
				}
				
				else if(belongsTo(reverseCod(lineBreakdown[i],numberKey),alphabeticKeyBreakdown)){
					
					if(i > 0 && i < lineBreakdown.length-1){
						
						if((alphabeticKey.charAt((m-1)% alphabeticKey.length()) == line.charAt(i-1)) && (alphabeticKey.charAt(m%alphabeticKey.length()) == line.charAt(i+1)) && (pointer < (i-1))){
							
							sb.setCharAt(sb.length()-1,(reverseCod(lineBreakdown[i],numberKey).charAt(0)));
							m++;
							i++;
							pointer = i;
						}	
						
						else{
							
							sb.delete(0,sb.length());
							sb.append("error in codification");
							i = lineBreakdown.length;
						}
					}
					
					else{
						
						sb.delete(0,sb.length());
						sb.append("error in codification");
						i = lineBreakdown.length;
					}
				}
					
			}	
			String end = sb.toString();
			pw.print(end);
			pw.println();
			counterLine++;
		}		
		
		s.close();
		pw.close();
	}
	
	/**
	*Translates the given letter into key positions be in the alphabet, considering the alphabet to be circular, key being a given integer value
	*
	*@param letter the letter to translate
	*@param key the integer value to translate the letter by
	*@return the letter given translated by key in the 26 letter English alphabet
	*/
	public static String reverseCod(String letter, int key){
		
		int index = getIndexLetter(letter);
		String finalLetter = "";
		
		if((index - key) >= 0){
			
			finalLetter = getLetterIndex(index - key);
		}
		
		else if((index - key) < 0){
			
			int remaining = 25 + (index - key);
			finalLetter = getLetterIndex(remaining + 1);
		}
		
		return finalLetter;
	}
}