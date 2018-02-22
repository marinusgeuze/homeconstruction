package com.homeconstruction.framework.util;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StringUtilsTest {

    @Test
    public void getStringValueIfSet_nullValue() throws Exception {

        assertEquals(false, StringUtils.getStringValueIfSet(null).isPresent());
    }

    @Test
    public void getStringValueIfSet_emptyValue() throws Exception {

        assertEquals(false, StringUtils.getStringValueIfSet("").isPresent());
    }

    @Test
    public void getStringValueIfSet_blankValue() throws Exception {

        assertEquals(false, StringUtils.getStringValueIfSet("  ").isPresent());
    }

    @Test
    public void getStringValueIfSet_value() throws Exception {

        assertEquals(true, StringUtils.getStringValueIfSet("test").isPresent());
    }
}