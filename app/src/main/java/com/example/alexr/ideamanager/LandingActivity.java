package com.example.alexr.ideamanager;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.alexr.ideamanager.services.MessageService;
import com.example.alexr.ideamanager.services.ServiceBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LandingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);
        //Retrofit method
        /*MessageService taskService = ServiceBuilder.buildService(MessageService.class);
        //Uses a different base url basically uses two API sources
        //Call<String> call = taskService.getMessagesFromDiffUrl("http://different.api.url.com/");
        Call<String> call = taskService.getMessages();
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                ((TextView)findViewById(R.id.message)).setText(response.body());
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                ((TextView)findViewById(R.id.message)).setText("Request Failed");
            }
        });
        */
        new GetMessageTask().execute();
    }

    public class GetMessageTask extends AsyncTask<Void, Void, Boolean>{

        private String message = "";
        @Override
        protected Boolean doInBackground(Void... voids) {
            MessageService taskService = ServiceBuilder.buildService(MessageService.class);
            Call<String> call = taskService.getMessages();
            try {
                message = call.execute().body();
            }catch (Exception e){
                // Todo
            }
            return true;
        }

        @Override
        protected void onPostExecute(final Boolean success){
            ((TextView) findViewById(R.id.message)).setText(message);
        }

        @Override
        protected void onCancelled(final Boolean success){
            // Todo
        }
    }


    public void GetStarted(View view){
        Intent intent = new Intent(this, IdeaListActivity.class);
        startActivity(intent);
    }
}
