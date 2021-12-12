package com.example.myapplication;


public class userСomments {
    private String nameUser;
    private String textCommentUser;
    private String timeComment;

    public userСomments(String nameUser, String textCommentUser, String timeComment){

        this.nameUser = nameUser;
        this.textCommentUser = textCommentUser;
        this.timeComment = timeComment;
    }

    public String getNameUser() {
        return this.nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    public String getTextCommentUser() {
        return this.textCommentUser;
    }

    public void setTextCommentUser(String textCommentUser) {
        this.textCommentUser = textCommentUser;
    }

    public String getTimeComment() {
        return this.timeComment;
    }

    public void setTimeComment(String timeComment) {
        this.timeComment = timeComment;
    }
}