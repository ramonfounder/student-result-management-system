package io.shyftlabs.srms.shared;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;


/**
 * Response class to handle the response
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Response<T> {

    // The type of the response
    private Type type;

    // The message of the response
    private Message message;

    // The data of the response
    private T data;

    /**
     * The type of the response
     */
    public enum Type {FAILURE}

    /**
     * The message of the response
     */
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Message {

        private String key;

        private Map<String, String> params;

    }

}
