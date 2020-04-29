package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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
	
	public void createRecord(String username) {
		File file = new File("/Users/jameswatson/desktop/handicap-database/"+username+".json");
		scoreRecords.put(username, file);
	}
	
	public File getRecord(String username) {
		return scoreRecords.get(username);
	}
	
	@SuppressWarnings("unchecked")
	public void addScore(String username, HandicapEntry entry) throws FileNotFoundException, IOException, ParseException {
		JSONObject mainObj;
		try {
			if(getRecord(username).length() == 0) {
				mainObj = new JSONObject();
				JSONObject score = new JSONObject();
				score.put("Score", entry.getScore());
				JSONObject cr = new JSONObject();
				cr.put("Course Rating", entry.getCourseRating());
				JSONObject slope = new JSONObject();
				slope.put("Slope", entry.getSlopeRating());
				
				JSONArray newEntry = new JSONArray();
				newEntry.add(score);
				newEntry.add(cr);
				newEntry.add(slope);
				mainObj.put("Entry " + entry.getDate(), newEntry);
			}
			else if(getRecord(username).exists() && getRecord(username).length() != 0) {
				Object obj = new JSONParser().parse(new FileReader("/User/jameswatson/desktop/handicap-database/"+username+".json"));
			    mainObj = (JSONObject) obj;
			    JSONObject score = new JSONObject();
				score.put("Score", entry.getScore());
				JSONObject cr = new JSONObject();
				cr.put("Course Rating", entry.getCourseRating());
				JSONObject slope = new JSONObject();
				slope.put("Slope", entry.getSlopeRating());
			    
				JSONArray newEntry = new JSONArray();
				newEntry.add(score);
				newEntry.add(cr);
				newEntry.add(slope);
				mainObj.put("Entry " + entry.getDate(), newEntry);
	
				
				
			}
			else {
				mainObj = null;
			}
				
			FileWriter writer;
			
			try{
				writer = new FileWriter(getRecord(username), true);
				writer.append(mainObj.toJSONString());
				writer.write(System.getProperty("line.separator"));
				writer.flush();
				writer.close();
			}
			catch(IOException e) {
				e.printStackTrace();
			}
			catch(NullPointerException e) {
				createRecord(username);
				addScore(username, entry);
			}
		}
		catch(NullPointerException e) {
			createRecord(username);
			addScore(username, entry);
		}
	}
	
	public static void main(String[] args) throws FileNotFoundException, IOException, ParseException {
		Records rec = new Records();
		rec.addScore("jwat", new HandicapEntry(10, 10, 10, "10/10/10"));
		//rec.addScore("jwat", new HandicapEntry(10, 11, 10, "10/10/10"));

	}

}
