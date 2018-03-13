package com.macappstudio.atog.loadjson;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    TextView hello;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        hello=(TextView)findViewById(R.id.hello);
        hello.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                try {
                    JSONObject obj = new JSONObject(loadJSONFromAsset());
                    ArrayList<HashMap<String, String>> formList = new ArrayList<HashMap<String, String>>();
                    HashMap<String, String> m_li;
                    int i2=obj.length();

                    for (int i = 0; i < obj.length(); i++) {
                        String formula_value = obj.optString("id");
                        String url_value = obj.optString("description");

                        //Add your values in your `ArrayList` as below:
                        m_li = new HashMap<String, String>();
                        m_li.put("id", formula_value);
                        m_li.put("description", url_value);

                        formList.add(m_li);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });
    }
    public String loadJSONFromAsset()
    {
        String json = null;
        try {
            InputStream is = getApplicationContext().getAssets().open("MockJson.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }


}
