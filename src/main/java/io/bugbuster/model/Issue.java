package io.bugbuster.model;

import java.util.List;

public class Issue {
    private String title;
    private String body;
    private String tags;
    private String appname;
    private long created_at;
    private long modified_at;
    private boolean open;
    private String id;
    private List<Comment> commentList;


    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public String getTags() {
        return tags;
    }

    public String getAppname() {
        return appname;
    }

    public long getCreated_at() {
        return created_at;
    }

    public long getModified_at() {
        return modified_at;
    }

    public boolean isOpen() {
        return open;
    }

    public String getId() {
        return id;
    }

    public List<Comment> getCommentList() {
        return commentList;
    }
}
