package com.littleturn.todo;

public class RowItem {
	private int imageId;
    private String title;
    private String time;
     
    public RowItem(int imageId, String title, String time) {
        this.imageId = imageId;
        this.title = title;
        this.time = time;
    }
    public int getImageId() {
        return imageId;
    }
    public void setImageId(int imageId) {
        this.imageId = imageId;
    }
    public String getDesc() {
        return time;
    }
    public void setTime(String time) {
        this.time = time;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    @Override
    public String toString() {
        return title + "\n" + time;
    }   
}
