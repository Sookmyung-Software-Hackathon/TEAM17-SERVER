package noeasy.server.util;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ResponseTemplate {
    public int status;
    public String message;
    public Object data;
}