package com.mmt.rockower.montereymandarintutor;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;
import android.widget.Button;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View;

public class MainActivity extends Activity{

    private EditText usernameField,passwordField;
    private TextView status,role,method;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usernameField = (EditText)findViewById(R.id.username);
        passwordField = (EditText)findViewById(R.id.password);
        Button loginB = (Button) findViewById(R.id.loginB);

        loginB.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                String phpPage = "http://hosting.otterlabs.org/shawmarkusr/CST336/hackathon/login.php";


                    String loginURL = phpPage + "?username=" + usernameField.getText() + "&password=" + passwordField.getText();
                    new SigninActivity().execute(loginURL);


            }
        });
        //status = (TextView)findViewById(R.id.textView6);
        //role = (TextView)findViewById(R.id.textView7);
        //method = (TextView)findViewById(R.id.textView9);
    }

    public void onClick(View v){
        String phpPage = "http://hosting.otterlabs.org/shawmarkusr/CST336/hackathon/login.php";

        try {
            URL loginURL = new URL(phpPage + "?username=" + usernameField.getText() + "&password=" + passwordField.getText());

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

    }
/*
    public void login(View view){
        String username = usernameField.getText().toString();
        String password = passwordField.getText().toString();
        method.setText("Get Method");
        new SigninActivity(this,0).execute(username, password);

    }

    public void loginPost(View view){
        String username = usernameField.getText().toString();
        String password = passwordField.getText().toString();
        method.setText("Post Method");
        new SigninActivity(this,1).execute(username,password);
    }
    */

    public class SigninActivity extends AsyncTask<String,Void,String>{

        final HttpClient httpClient = new DefaultHttpClient();
        String content;
        String error;
        ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);
        String data = "";
        final EditText username = (EditText) findViewById(R.id.username);



        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog.setTitle("Please wait ...");
            progressDialog.show();

            try {
                data += "&" + URLEncoder.encode("data", "UTF-8") + "=" + username.getText();
            } catch (UnsupportedEncodingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }


        @Override
        protected String doInBackground(String... params) {
            BufferedReader br = null;

            URL url;
            try {
                url = new URL(params[0]);

                URLConnection connection = url.openConnection();
                connection.setDoOutput(true);


                OutputStreamWriter outputStreamWr = new OutputStreamWriter(connection.getOutputStream());
                outputStreamWr.write(data);
                outputStreamWr.flush();

                br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder sb = new StringBuilder();
                String line = null;

                while((line = br.readLine())!=null) {
                    sb.append(line);
                    sb.append(System.getProperty("line.separator"));
                }

                content = sb.toString();



            } catch (MalformedURLException e) {
                error = e.getMessage();
                e.printStackTrace();
            } catch (IOException e) {
                error = e.getMessage();
                e.printStackTrace();
            } finally {
                try {
                    br.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            progressDialog.dismiss();

            if(error!=null) {
                // Set error if something happens!?
            } else {
                try {
                    JSONObject o = new JSONObject(content);
                    String user = o.getString("username");
                    String fName = o.getString("firstName");
                    String lName = o.getString("lastName");

                    Intent myIntent = new Intent(MainActivity.this, verify.class);
                    myIntent.putExtra("Username", user);
                    //myIntent.putExtra("First Name", fName);
                    //myIntent.putExtra("Last Name", lName);

                    progressDialog.setTitle("Sucessful Login ...");
                    progressDialog.show();
                    startActivity(myIntent);
                    finish();
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
}



}