package org.example.atbconfigure.domain.enums;

import lombok.Getter;

@Getter
public enum ResponseState {
    WRONG_CONNECT("Wrong"),
    SUCCESS_CONNECT("Successful"),
    WRONG_SEND("Error sending message"),
    WRONG_READ("Error reading message"),
    CONNECTING_START("Connecting...");

    private final String state;
    ResponseState(String state){
        this.state = state;
    }

}
