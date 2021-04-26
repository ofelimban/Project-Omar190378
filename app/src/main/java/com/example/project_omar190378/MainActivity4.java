package com.example.project_omar190378;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity4 extends AppCompatActivity {


    private String city;
    public  ImageView img;

    public static int imgResource;


    TextView temperature, h;
    EditText inputCity;
    Button submit;
    JSONObject jsonObj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        temperature = (TextView) findViewById(R.id.temp);
        h = (TextView) findViewById(R.id.hum);
        inputCity=(EditText)findViewById(R.id.weather);

        submit=(Button)findViewById(R.id.sbmit);
        Button backto1 = (Button) findViewById(R.id.backtoact);



        h.setVisibility(View.INVISIBLE);
        temperature.setVisibility(View.INVISIBLE);


        img = (ImageView) findViewById(R.id.imageView);



        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                h.setVisibility(View.VISIBLE);
                temperature.setVisibility(View.VISIBLE);

                String city=inputCity.getText().toString();
                String par="http://api.openweathermap.org/data/2.5/weather?q="+city+"&appid=64d30c10c2c7f7bfa647f48ecca7dee8&units=metric";

                weather(par);


            }
        });
        backto1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity4.this, MainActivity.class));
            }
        });
    }

    public void weather(String url) {


        JsonObjectRequest jsonObj =
                new JsonObjectRequest(Request.Method.GET,
                        url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        // result of the request


                        try {

                            JSONObject jsonMain = response.getJSONObject("main");

                            double temp=jsonMain.getDouble("temp");
                            temperature.setText("Temprature: " +String.valueOf(temp));

                            double humidity=jsonMain.getDouble("humidity");
                            h.setText("Humidity: "+String.valueOf(humidity));

                            whetherPic(response.getJSONArray("weather"));
                        }
                        catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //log errors here

                    }

                });
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(jsonObj);
    }


    public void whetherPic(JSONArray jArray) {

        for (int i = 0; i < jArray.length(); i++) {
            try {
                JSONObject oneObject = jArray.getJSONObject(i);
                String weatherCondition=oneObject.getString("main");
                if(weatherCondition.equals("Clouds")){

                    img.setImageResource(R.drawable.cloo);
                    img.setTag(R.drawable.cloo);
                    imgResource=(Integer)img.getTag();
                }

                else if(weatherCondition.equals("Clear")){
                    img.setImageResource(R.drawable.sun);
                    img.setTag(R.drawable.sun);
                    imgResource=(Integer)img.getTag();
                }
                else if(weatherCondition.equals("Rain")){
                    img.setImageResource(R.drawable.raaaain);
                    img.setTag(R.drawable.raaaain);
                    imgResource=(Integer)img.getTag();
                }
                else if(weatherCondition.equals("Snow")){
                    img.setImageResource(R.drawable.snoow);
                    img.setTag(R.drawable.snoow);
                    imgResource=(Integer)img.getTag();
                }



            } catch (Exception e) {
            }
        }
    }

    public int getImgResource(){
        return imgResource;
    }
}
