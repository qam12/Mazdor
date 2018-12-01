package com.example.maju.mazdor.Model;

import java.util.Date;

/**
 * Created by qamber.haider on 11/22/2018.
 */

public class ChatMessage {
    private String message;
    private String senderId;
    private String receiverId;
    private String  messageTime;
    private String messageDate;
    private String messageSenderName;
    private String messageReceiverName;

    public ChatMessage() {
    }

    public ChatMessage(String message, String senderId, String receiverId, String messageTime, String messageDate, String messageSenderName, String messageReceiverName) {
        this.message = message;
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.messageTime = messageTime;
        this.messageDate = messageDate;
        this.messageSenderName = messageSenderName;
        this.messageReceiverName = messageReceiverName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(String receiverId) {
        this.receiverId = receiverId;
    }

    public String getMessageTime() {
        return messageTime;
    }

    public void setMessageTime(String messageTime) {
        this.messageTime = messageTime;
    }

    public String getMessageDate() {
        return messageDate;
    }

    public void setMessageDate(String messageDate) {
        this.messageDate = messageDate;
    }

    public String getMessageSenderName() {
        return messageSenderName;
    }

    public void setMessageSenderName(String messageSenderName) {
        this.messageSenderName = messageSenderName;
    }

    public String getMessageReceiverName() {
        return messageReceiverName;
    }

    public void setMessageReceiverName(String messageReceiverName) {
        this.messageReceiverName = messageReceiverName;
    }
}
