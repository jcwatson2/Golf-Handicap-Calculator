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
		JSONObject records = new JSONObject();
		try {
			FileWriter writer = new FileWriter("/Users/jameswatson/desktop/handicap-database/"+username+".json");
			writer.write(records.toJSONString());
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public File getRecord(String username) {
		return scoreRecords.get(username);
	}
	
	@SuppressWarnings("unchecked")
	public void addScore(String username, HandicapEntry entry) {
		  try {
			Object obj = new JSONParser().parse(new FileReader("/Users/jameswatson/desktop/handicap-database/"+username+".json"));
			JSONObject mainObj = (JSONObject) obj;
			
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
		
			
			FileWriter writer;
			
			try{
				writer = new FileWriter("/Users/jameswatson/desktop/handicap-database/"+username+".json", true);
				writer.append(mainObj.toJSONString());
				writer.flush();
				writer.close();
			
			}
			catch(IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			createRecord(username);
			addScore(username, entry);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
	}
	
	public static void main(String[] args) {
		Records rec = new Records();
		rec.addScore("jcwatson", new HandicapEntry(100, 10.5, 76, "4/27/20"));
		rec.addScore("jcwatson", new HandicapEntry(100, 10.6, 77, "4/28/20"));
	}

}
