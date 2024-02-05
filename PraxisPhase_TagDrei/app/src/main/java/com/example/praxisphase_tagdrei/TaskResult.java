package com.example.praxisphase_tagdrei;

public class TaskResult {
    private String id;
    private String url;
    private String data;
   public TaskResult(String id, String url){
        this.id = id;
        this.url = url;
   }

    public String getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }
    public String getData(){
       return data;
    }
    public void setData(String data){
       this.data = data;
    }
}
