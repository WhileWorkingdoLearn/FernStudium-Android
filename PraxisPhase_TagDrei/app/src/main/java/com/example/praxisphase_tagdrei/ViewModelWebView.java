package com.example.praxisphase_tagdrei;

import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

public class ViewModelWebView extends ViewModel {
    private DownloadWebsiteTask taskHanderl;
    private LiveData<String> website;

    public ViewModelWebView() {
        taskHanderl = new DownloadWebsiteTask();
    }

    public void loadData(String html, String webView) {

       taskHanderl.execute(new TaskResult(html,webView));
    }
    public LiveData<String> getData(){
        return website;
    }


    private class DownloadWebsiteTask extends AsyncTask<TaskResult, Integer, Long> {
        private List<TaskResult> list = new ArrayList<>();
        protected Long doInBackground(TaskResult... urls) {
            long totalSize = 0;
            for (TaskResult task: urls) {
                HttpsURLConnection httpsURLConnection = null;
                InputStream inputStream = null;
                int responseCode = 0;
                StringBuffer result = new StringBuffer();
                try {
                    String urlText = task.getUrl();
                    if(!urlText.startsWith("https://")){
                        urlText = "https://" + urlText;
                    }

                    URL url = new URL(urlText);
                    httpsURLConnection = (HttpsURLConnection) url.openConnection();
                    responseCode = httpsURLConnection.getResponseCode();
                    if(responseCode == HttpsURLConnection.HTTP_OK){
                        inputStream = httpsURLConnection.getInputStream();
                        byte[] paket = new byte[4096];
                        int noBytes;
                        while ((noBytes= inputStream.read(paket)) >0){
                            result.append(new String(paket,0,noBytes));
                        }
                        Log.d("HTMLString",result.toString());
                    }
                } catch (Exception e){

                }finally{
                    try {
                        if(inputStream!= null){
                            inputStream.close();
                        }
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
                if(httpsURLConnection != null){
                    httpsURLConnection.disconnect();
                }
                task.setData(result.toString());
            }
            return totalSize;
        }

        protected void onProgressUpdate(Integer... progress) {

        }


        protected void onPostExecute(Long result) {

        }
    }

}
