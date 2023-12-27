package org.example.atbconfigure.tcpService;

import org.example.atbconfigure.domain.enums.ResponseState;

public interface ResponseStateCallback {
     void sendState(ResponseState state);
}
