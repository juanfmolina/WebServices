package co.edu.udea.compumovil.gr10.ws;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import co.edu.udea.compumovil.gr10.ws.entities.Clima;
import co.edu.udea.compumovil.gr10.ws.weatherservice.WeatherHttpClient;

import com.squareup.picasso.Picasso;

public class WeatherActivity extends Activity {
	private TextView city, temp, description;
	private EditText inputCity;
	private ImageView weatherImage;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_weather);
		city = (TextView) findViewById(R.id.city);
		temp = (TextView) findViewById(R.id.temp);
		inputCity = (EditText) findViewById(R.id.editCiudad);
		description = (TextView) findViewById(R.id.description);
		weatherImage = (ImageView) findViewById(R.id.weather_image);

	}

	public void buscarCiudad(View view) {
		new WeatherTask().execute(inputCity.getText().toString());
	}

	private class WeatherTask extends AsyncTask<String, Void, Void> {
		private static final String TAG = "WeatherTask";
		private String Error = null;
		private ProgressDialog Dialog = new ProgressDialog(WeatherActivity.this);
		private Clima clima;

		@Override
		protected void onPreExecute() {
			// super.onPreExecute();
			// Start Progress Dialog (Message)
			Dialog.setMessage("Please wait..");
			Dialog.show();
		}

		@Override
		// Call after onPreExecute method
		protected Void doInBackground(String... params) {
			/************ Make Post Call To Web Server ***********/
			// BufferedReader reader=null;
			// Send data
			try {
				clima = new WeatherHttpClient().getWeatherDataObject(params[0]);
				Log.v(TAG, clima.getName());
			} catch (Exception ex) {
				Error = ex.getMessage();
			}
			/*****************************************************/
			return null;
		}

		@Override
		protected void onPostExecute(Void aVoid) {
			// NOTE: You can call UI Element here.
			// Close progress dialog
			Dialog.dismiss();
			if (Error != null) {
			} else {
				// Show Response Json On Screen (activity)
				/******************
				 * Start Parse Response JSON Data
				 *************/
				
				String nameImage = "";
			//	JSONObject jsonResponse;
				try {
					/******
					 * Creates a new JSONObject with name/value mappings from
					 * the JSON string.
					 ********/
					//jsonResponse = new JSONObject(data);
					/*****
					 * Returns the value mapped by name if it exists and is a
					 * JSONArray.
					 ***/
					/******* Returns null otherwise. *******/
					// JSONArray jsonMainNode =
					
					city.setText(clima.getName());
					temp.setText(clima.getMain().getTemp() + "°C");
					nameImage= clima.getWeather().get(0).getIcon();
					Picasso.with(getApplicationContext())
							.load(WeatherHttpClient.getURLImage(nameImage))
							.into(weatherImage);
					description.setText(clima.getWeather().get(0).getDescription());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
}
