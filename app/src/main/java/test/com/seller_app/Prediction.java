package test.com.seller_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

public class Prediction extends AppCompatActivity {

    private TextView mTextView;
//    private Button result_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prediction);

        mTextView = findViewById(R.id.searchTxt);
//        result_btn = findViewById(R.id.result_btn);

        createDropdown();

//        result_btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String text = mTextView.getText().toString();
//                sendRequestToAPI(text);
//            }
//        });

    }

    private void sendRequestToAPI(String text) {
//        String url = "http://192.168.1.14:5000/predict?town="+text;
        String url ="http://ee69-112-134-179-181.ngrok.io/predict?town="+text;
        System.out.println("URL: "+ url);
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest objectRequest = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("Response is : ", response.toString());

                        try {
                            mTextView.setText(Double.toString(Math.round(response.getDouble("prediction")*100.0)/100.0));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Error is : ",error.getMessage());
                        System.out.println(error.getStackTrace());

                    }
                });

        requestQueue.add(objectRequest);

    }
    private void createDropdown() {
        Spinner s = (Spinner) findViewById(R.id.spinner);
        //Prepar adapter
        //HERE YOU CAN ADD ITEMS WHICH COMES FROM SERVER.
        final MyData items[] = new MyData[10];
        items[0] = new MyData("Akurana", "Akurana");
        items[1] = new MyData("Alawathugoda", "Alawathugoda");
        items[2] = new MyData("Arambepola", "Arambepola");
        items[3] = new MyData("Balakaduwa", "Balakaduwa");
        items[4] = new MyData("Bulugohothenna", "Bulugohothenna");
        items[5] = new MyData("Deegala", "Deegala");
        items[6] = new MyData("Delgasgoda", "Delgasgoda");
        items[7] = new MyData("Delgasthenna", "Delgasthenna");
        items[8] = new MyData("Dippitiya", "Dippitiya");
        items[9] = new MyData("Dodangolla", "Dodangolla");
        ArrayAdapter<MyData> adapter = new ArrayAdapter<MyData>(this,
                android.R.layout.simple_spinner_item, items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s.setAdapter(adapter);
        s.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                MyData d = items[position];

                //Get selected value of key
                String value = d.getValue();
                String key = d.getSpinnerText();
                System.out.println("value: " + value);
                System.out.println("key: " + key);

                sendRequestToAPI(value);
            }

            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    class MyData {
        public MyData(String spinnerText, String value) {
            this.spinnerText = spinnerText;
            this.value = value;
        }

        public String getSpinnerText() {
            return spinnerText;
        }

        public String getValue() {
            return value;
        }

        public String toString() {
            return spinnerText;
        }

        String spinnerText;
        String value;
    }
}