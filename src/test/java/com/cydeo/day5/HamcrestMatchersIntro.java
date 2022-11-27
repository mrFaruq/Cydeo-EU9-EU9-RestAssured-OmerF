package com.cydeo.day5;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.shadow.com.univocity.parsers.common.input.LineSeparatorDetector;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class HamcrestMatchersIntro {

    @Test
    public void simpleTest1(){

        assertThat(5+5, is(10));
        assertThat(5+5,equalTo(10));

        //matchers has 2 overloaded version
        //first that accept actual value
        //second that accept another matcher
        //below examples is method accepting matchers equal to make it readable

        assertThat(5+5, is(equalTo(10)));

        assertThat(5+5, not(9));
        assertThat(5+5,is(not(9)));
        assertThat(5+5,is(not(equalTo(9))));

        //number comparison
        //greaterThan()
        //greaterThanOrEqualTo()
        //lessThan()
        //lessThanOrEqualTo()
        assertThat(5+5,is(greaterThan(9)));

    }

    @DisplayName("Assertion with String")
    @Test
    public void stringHamcrest(){

        String text = "EU-9 is learning Hamcrest";

        //checking for equality is same as numbers

        assertThat(text,is("EU-9 is learning Hamcrest"));
        assertThat(text,equalTo("EU-9 is learning Hamcrest"));
        assertThat(text,is(equalTo("EU-9 is learning Hamcrest")));

        //check if this text starts with EU-9
        assertThat(text,startsWith("EU-9"));

        //now do it in case insensitive manner
        assertThat(text,startsWithIgnoringCase("eu-9"));
        //endsWith
        assertThat(text,endsWith("rest"));

        //check if text contains String learning
        assertThat(text,containsString("learning"));
        assertThat(text,containsStringIgnoringCase("LEARNING"));

        String str = " ";

        //check if above str is blank
        assertThat(str,blankString());

        //check if trimmed str is empty string
        assertThat(str.trim(),emptyString());
    }

    @DisplayName("Hamcrest for collection")
    @Test
    public void testCollection(){

        List<Integer> listOfNumbers = Arrays.asList(1,4,5,6,32,54,66,77,45,23);

        assertThat(listOfNumbers,hasSize(10));
        assertThat(listOfNumbers,hasItem(77));
        assertThat(listOfNumbers,hasItems(77,54,23));
        assertThat(listOfNumbers,everyItem((greaterThan(0))));
    }
}
