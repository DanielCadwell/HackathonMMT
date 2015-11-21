/*
package com.mmt.rockower.montereymandarintutor;

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

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View;
public class SigninActivity extends AsyncTask<String,Void,String>{

    final HttpClient httpClient = new DefaultHttpClient();
    String content;
    String error;
    ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);
    String data = "";
    final EditText userinput = (EditText) findViewById(R.id.username);



    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        progressDialog.setTitle("Please wait ...");
        progressDialog.show();

        try {
            data += "&" + URLEncoder.encode("data", "UTF-8") + "=" + userinput.getText();
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    @Override
    protected Void doInBackground(String... params) {
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
    protected void onPostExecute(Void result) {
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

                Intent myIntent = new Intent(MainActivity.this, CalcActivity.class);
                myIntent.putExtra("Username", user);
                myIntent.putExtra("First Name", fName);
                myIntent.putExtra("Last Name", lName);

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
*/

