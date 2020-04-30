package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.json.simple.JSONArray;

public class Records {
	
	HashMap<String, File> scoreRecords;
	
	public Records() {
		scoreRecords = new HashMap<String, File>();
	}
	
	private void createRecord(String username) {
		try {
			File file = new File("/Users/jameswatson/desktop/handicap-database/"+username+".csv");
			FileWriter writer = new FileWriter(file, true);
			writer.append("Date,Score,Course Rating,Slope Rating");
			writer.write(System.getProperty( "line.separator" ));
			writer.flush();
			writer.close();
			scoreRecords.put(username, file);
		} 
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	private File getRecord(String username) {
		return scoreRecords.get(username);
	}
	
	private void addScore(String username, HandicapEntry entry) {
		try {
			File file = getRecord(username);
			FileWriter writer = new FileWriter(file, true);
			writer.append("\"" + entry.getDate() + "\"" + ",");
			writer.append(entry.getScore() + ",");
			writer.append(entry.getCourseRating() + ",");
			writer.append(entry.getSlopeRating() + "");
			writer.write(System.getProperty( "line.separator" ));			
			writer.flush();
			writer.close();
		}
		catch(IOException e) {
			
		}
		
	}

	
	public static void main(String[] args) throws FileNotFoundException, IOException, ParseException {
		Records rec = new Records();
		rec.createRecord("jwat");
		rec.addScore("jwat", new HandicapEntry(10, 10, 10, "10/10/10"));
		rec.addScore("jwat", new HandicapEntry(10, 10, 1, "10/2/10"));

		//rec.addScore("jwat", new HandicapEntry(10, 11, 10, "10/10/10"));

	}

}
