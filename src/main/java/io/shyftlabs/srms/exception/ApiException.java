package io.shyftlabs.srms.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Map;

/**
 * ApiException class to handle the api exception
 */
@Getter
@RequiredArgsConstructor
public class ApiException extends RuntimeException {

    private final String key;

    private final Map<String, String> params;

}
