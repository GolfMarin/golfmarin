package com.golfmarin.golfmarinapp;

import android.content.Context;
import java.util.ArrayList;
import java.io.InputStream;
import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;

//import android.util.Log;


public class JsonParser {
	
	public JSONArray getJSONFromFile(Context ctx, String filename, String objects, String object)
	{
		InputStream input;
		String jsonString = null;
		JSONObject object1 = null;
		JSONObject object2 = null;
		JSONArray array = null;
		
		// Read the file to a string
		try {
			input = ctx.getAssets().open(filename);
			int size = input.available();
			byte[] buffer = new byte[size];
			input.read(buffer);
			input.close();
			jsonString = new String(buffer);
		}
		catch (Exception e) {
    		//Log.i("mytag","Couldn't read json file " + e.toString());
		}
		
		// Extract JSONArray from string
		try {
			object1 = new JSONObject(jsonString);
			object2 = object1.getJSONObject(objects);
			array = object2.getJSONArray(object);
		}
		catch (JSONException e) {
    		//Log.i("mytag","Couldn't parse JSON string." + e);
		}
		return array; 
	}
	
	public ArrayList<County> getCountiesFromJSON(JSONArray counties) {
		
		ArrayList<County> countiesList = new ArrayList<County>();
		
		try{
			for(int i=0; i<counties.length(); i++) {
				JSONObject currentObject = counties.getJSONObject(i);
				County county = new County(currentObject.getString("name"));
				county.countyInfo = currentObject.getString("countyInfo");
				county.latitude = currentObject.getDouble("latitude");
				county.longitude = currentObject.getDouble("longitude");
				county.id = currentObject.getString("id");
				county.woeid = currentObject.getString("woeid");
				county.thumbnailURL = currentObject.getString("thumbnailURL");
				
				countiesList.add(county);
				// Log.i("mytag","Added county " + county);
			}
		}
		catch (JSONException e) {
    		//Log.i("mytag","Couldn't parse JSON county object." + e);
		}
		return countiesList;
	}
	
	// Fetch golf courses
	
	public ArrayList<Course> getCoursesFromJSON(JSONArray courses, String county) {
		
		ArrayList<Course> coursesList = new ArrayList<Course>();
		
		try{
			for(int i=0; i<courses.length(); i++) {
				JSONObject currentObject = courses.getJSONObject(i);
				Course course = new Course(currentObject.getString("name"));
				course.address = currentObject.getString("address");
				course.city = currentObject.getString("city");
				course.phone = currentObject.getString("phone");
				course.holes = currentObject.getInt("holes");
				course.slope = currentObject.getString("slope");
				course.isPublic = currentObject.getBoolean("isPublic");
				course.courseInfo = currentObject.getString("info");
				course.directions = currentObject.getString("directions");
				course.latitude = currentObject.getDouble("latitude");
				course.longitude = currentObject.getDouble("longitude");
				course.id = currentObject.getString("id");
				course.woeid = currentObject.getString("woeid");
				course.imageURL = currentObject.getString("imageURL");
				course.thumbnailURL = currentObject.getString("thumbnailURL");
				course.county = county;
				
				coursesList.add(course);
				// Log.i("mytag","Added course " + course);
			}
		}
		catch (JSONException e) {
    		//Log.i("mytag","Couldn't parse JSON course object." + e);
		}
		return coursesList;
	}
}
