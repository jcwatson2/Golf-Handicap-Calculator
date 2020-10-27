
package application;
import java.util.Scanner;
import java.io.*;
import java.util.ArrayList;


public class IndexRecords {
	private Scanner scnr;
	private ArrayList<String> entries = new ArrayList<String>();
	
	/**
	 * Creates a new file 
	 * @param name - name of file that will be created
	 */
	public void createIndex(String name){
		File newIndex = new File(name + ".txt");
		if(!newIndex.exists()) {
			try {
				newIndex.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * Adds contents of the handicap entry to specified file
	 * @param entry - data to be added
	 * @param userName - name of file to be added to
	 * @throws IOException - if file does not exist
	 */
	public void addToIndex(HandicapEntry entry, String userName) throws IOException{
		FileWriter writer = new FileWriter(userName + ".txt", true);
		writer.append(entry.getScore() + " " + entry.getCourseRating() + " " + entry.getSlopeRating() + "\n");
		writer.close();
	}	
	
	/**
	 * Retrieves data from file and stores each line in an 
	 * arraylist
	 * @param userName - name of the file
	 * @throws IOException - if file does not exist
	 */
	private void retrieveFromFile(String userName) throws IOException {
		File file = new File(userName + ".txt");
		scnr = new Scanner(file);
		// reads each line and adds to an arraylist
		while(scnr.hasNext()) {
			String a = scnr.nextLine();
			
			entries.add(a);

		}
		
	}
	
	/**
	 * Calculates the handicap index from a certain file
	 * @param userName
	 */
	public void calculateIndex(String userName) {
		// retrieves data from file
		try {
			retrieveFromFile(userName);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int score = 0;
		double courseRating = 0;
		int slope = 0;
		double differential;
		double differentialTotal = 0;
		double handicap;
		int i;
		// goes through entries and separates each string into 
		// integer and double variables
		for(i = 0; i < entries.size(); i++) {
			// if the score is a double digit number
			if(entries.get(i).length() == 11) {
				// Separates string into score, courseRating and slope
				score = Integer.parseInt(entries.get(i).substring(0, 2));
				courseRating = Double.parseDouble(entries.get(i).substring(3, 5));
				slope = Integer.parseInt(entries.get(i).substring(8, 11));
			}
			// if the score is a triple digit number
			else if(entries.get(i).length() == 12) {
				// Separates string into score, courseRating and slope
				score = Integer.parseInt(entries.get(i).substring(0, 3));
				courseRating = Double.parseDouble(entries.get(i).substring(4, 6));
				slope = Integer.parseInt(entries.get(i).substring(9, 12));
			}
			
			// calculation or the handicap index
			differential = ((score - courseRating) * 113)/slope;
			differentialTotal += differential;
		}
		handicap = differentialTotal/i;
		System.out.println("Your Handicap Index: " + handicap);
		// clears the entries array list after calculation is finished
		entries.clear();
	}

}
