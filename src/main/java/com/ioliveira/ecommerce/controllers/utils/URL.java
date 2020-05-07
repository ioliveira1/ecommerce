package com.ioliveira.ecommerce.controllers.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class URL {

    public static String decodeCaracters(String param){
        try {
            return URLDecoder.decode(param, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return "";
        }
    }

    public static List<Integer> convertParamsToList(String param) {
        return Arrays
                .stream(param.split(","))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

}
