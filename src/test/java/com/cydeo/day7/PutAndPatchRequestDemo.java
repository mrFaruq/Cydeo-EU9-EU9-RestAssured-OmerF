package com.cydeo.day7;

import com.cydeo.utilities.SpartanTestBase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class PutAndPatchRequestDemo extends SpartanTestBase {


    @DisplayName("PUT Request to one spartan for update with Map")
    @Test
    public void PUTRequest (){

        Map<String, Object> putRequestMap = new LinkedHashMap<>();
        putRequestMap.put("name","BruceWayne");
        putRequestMap.put("gender","Male");
        putRequestMap.put("phone","8811111111L");


    }

}
