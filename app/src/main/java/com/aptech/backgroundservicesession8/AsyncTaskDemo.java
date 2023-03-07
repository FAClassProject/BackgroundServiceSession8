package com.aptech.backgroundservicesession8;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class AsyncTaskDemo extends AppCompatActivity {

    private Button button;
    private EditText time;
    private TextView finalResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async_task_demo);

        time = (EditText) findViewById(R.id.in_time);
        button = (Button) findViewById(R.id.btn_run);
        finalResult = (TextView) findViewById(R.id.tv_result);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AsyncTaskRunner runner = new AsyncTaskRunner();
                String sleepTime = time.getText().toString();
                runner.execute(sleepTime);
            }
        });
    }
    private class AsyncTaskRunner extends AsyncTask<String, String, String>{

        private String resp;
        ProgressDialog progressDialog;


        /*
        doInBackground() : This method contains the code which needs to be executed in background.
        In this method we can send results multiple times to the UI thread by publishProgress() method.
        To notify that the background processing has been completed we just need to use the return statements
         */
        @Override
        protected String doInBackground(String... params) {
            publishProgress("Sleeping..."); // Calls onProgressUpdate()
            try {
                int time = Integer.parseInt(params[0])*1000;

                Thread.sleep(time);
                resp = "Slept for " + params[0] + " seconds";
            } catch (InterruptedException e) {
                e.printStackTrace();
                resp = e.getMessage();
            } catch (Exception e) {
                e.printStackTrace();
                resp = e.getMessage();
            }
            return resp;
        }

        /* onPostExecute() : This method is called after doInBackground method completes processing.
        Result from doInBackground is passed to this method*/
        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            // execution of result of Long time consuming operation
            progressDialog.dismiss();
            finalResult.setText(result);
        }

        //onPreExecute() : This method contains the code which is executed before the background processing starts
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            String value= time.getText().toString();
            int finalValue=Integer.parseInt(value);
            progressDialog = ProgressDialog.show(AsyncTaskDemo.this,
                    "ProgressDialog",
                    "Wait for "+time.getText().toString()+ " seconds");

        }


        /* onProgressUpdate() : This method receives progress updates from doInBackground method,
        which is published via publishProgress method, and this method can use this progress update to
        update the UI thread */
        @Override
        protected void onProgressUpdate(String... text) {
            finalResult.setText(text[0]);
        }
    }
}
