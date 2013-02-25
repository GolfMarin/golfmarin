package com.golfmarin.golfmarinapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
//import android.util.Log;
import android.view.MenuItem;

public class CourseDetailActivity extends FragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	    
		setContentView(R.layout.activity_course_detail);

		// Show the Up button in the action bar.
		getActionBar().setDisplayHomeAsUpEnabled(true);


		//
		if (savedInstanceState == null) {
			// Create the detail fragment and add it to the activity
			// using a fragment transaction.

			Bundle arguments = new Bundle();
            arguments.putParcelable("county", getIntent().getParcelableExtra("county"));
            arguments.putParcelable("course", getIntent().getParcelableExtra("course"));
            arguments.putParcelableArrayList("courses",getIntent().getParcelableArrayListExtra("courses"));
			CourseDetailFragment fragment = new CourseDetailFragment();
			fragment.setArguments(arguments);
			getSupportFragmentManager().beginTransaction()
					.add(R.id.detail_container, fragment).commit();
		}
	  }
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:

			Intent intent = new Intent(this, CountyListActivity.class);
			intent.addFlags(intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intent);
			return(true);
		}
		return super.onOptionsItemSelected(item);
	}
	
	}