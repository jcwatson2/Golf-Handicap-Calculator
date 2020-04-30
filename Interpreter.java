package application;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Interpreter {
	private File user;
	private List<String> dateList;
	private List<Integer> scoreList;
	private List<Double> crList;
	private List<Integer> slopeList;
	
	public Interpreter(File user) {
		this.user = user;
		dateList = new ArrayList<>();
		scoreList = new ArrayList<>();
		crList = new ArrayList<>();
		slopeList = new ArrayList<>();
	}
	
	/**
	 * Parser method that seperarates the provided csv file into its 
	 * components: Date, Scorer, Course Rating and Slope
	 */
	public void parse() {
		try {
			Scanner scnr = new Scanner(user);
			scnr.nextLine();
			while(scnr.hasNext()) {
				String[] line = scnr.nextLine().split(",");
				line[0] = line[0].replaceAll("\"", "");
				dateList.add(line[0]);
				scoreList.add(Integer.parseInt(line[1]));
				crList.add(Double.parseDouble(line[2]));
				slopeList.add(Integer.parseInt(line[3]));
			}
			scnr.close();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public double calculateIndex() {
		// calculate differentials for each score
		List<Double> diffList = new ArrayList<Double>();
		for(int x = 0; x < scoreList.size(); x++) {
			diffList.add(calcDiff(scoreList.get(x), crList.get(x), slopeList.get(x)));
		}
		
		// select the 10 best differentials out of the last 
		// 20 scores. If there are fewer than 10 scores than
		// calculate using all the scores
		if(scoreList.size() <= 10) {
			Collections.sort(diffList); 
		}
		else {
			Collections.sort(diffList); 
			diffList.subList(10, diffList.size()).clear();
			
		}
		// calculate the average of the lowest handicap diff.
		double average = 0; 
		for(int x = 0; x < diffList.size(); x++) {
			average += diffList.get(x);
			average /= diffList.size();
		}
		// multiply average by .96 and delete numbers right of the 
		// tenths place
		average *= 0.96;
		double index = Math.round(average * 10.0) / 10.0;
		
		
		
		
		return index;
	}
	
	private double calcDiff(int score, double cr, int slope) {
		double diff =  ((score - cr) * 113) / slope;
		return diff;
	}
	
	public List<Integer> getScoreHistory() {
		return this.scoreList;
	}
	
	public void comparePlayers() {
		
	}

	public List<String> getDateList() {
		return dateList;
	}


	
	public static void main(String[] args) {
		Interpreter x = new Interpreter(new File("/Users/jameswatson/desktop/handicap-database/jwat.csv"));
		x.parse();
		System.out.println(x.calculateIndex());
	}

}
