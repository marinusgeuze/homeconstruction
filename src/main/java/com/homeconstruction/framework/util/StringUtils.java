package com.homeconstruction.framework.util;

import java.util.Optional;

public class StringUtils {

    public static Optional<String> getStringValueIfSet(String value) {

        boolean isSet = value != null && !value.trim().isEmpty();
        return isSet ? Optional.of(value) : Optional.empty();
    }
}
