package com.salimatech.zazie.controller;



import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
public class WordCountController {

    @PostMapping(value = "/countWords" , consumes = MediaType.TEXT_PLAIN_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String,Long> countWords(@RequestBody String text) {
        return process(text);

    }

    /**
     *
     * @param para SON object containing a paragraph of text
     * @return  unique words from the supplied paragraph along with a count
     *          of the number of occurrences.
     *          Note: returned list is soted alphabetically
     */
    private Map<String, Long> process(String para){

        Map<String, Long> wordCounts =Arrays.stream(para.trim().split("[ ,.!?\r\n]"))
                .map(word -> word.replaceAll("[^a-zA-Z]", "").toLowerCase().trim())
                .filter(word -> !word.isEmpty())
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));


        Map<String, Long> sortedMap = new LinkedHashMap<>();

        Stream<Map.Entry<String, Long>> stream = wordCounts.entrySet().stream();

        stream.sorted(Map.Entry.comparingByKey())
                .forEachOrdered(c -> sortedMap.put(c.getKey(), c.getValue()));

        return sortedMap;

    }
}