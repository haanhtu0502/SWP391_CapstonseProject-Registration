/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

/**
 *
 * @author SE161740 Pham Nguyen Hung Anh
 */
public class FeedBack {
    private int  feedBackId;
    private String name;
    private String email;
    private String subject;
    private String feedBackMsg;
    private int userId;

    

    public FeedBack() {
    }

    public FeedBack(int feedBackId, String name, String email, String subject, String feedBackMsg, int userId) {
        this.feedBackId = feedBackId;
        this.name = name;
        this.email = email;
        this.subject = subject;
        this.feedBackMsg = feedBackMsg;
        this.userId = userId;
    }

    public int getFeedBackId() {
        return feedBackId;
    }

    public void setFeedBackId(int feedBackId) {
        this.feedBackId = feedBackId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getFeedBackMsg() {
        return feedBackMsg;
    }

    public void setFeedBackMsg(String feedBackMsg) {
        this.feedBackMsg = feedBackMsg;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    
    

    
    
    
    
}
