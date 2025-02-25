package com.example.simplerestapi.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

@Service
public class DataStoreService {

    private final HashMap<String, Integer> cacheMap;
    private final List<String> cacheList;
    private int currentMaxIndex;
    private final Random random;

    public DataStoreService() {
        cacheMap = new HashMap<>();
        cacheList = new ArrayList<>();
        currentMaxIndex = -1;
        random = new Random();
    }

    public boolean contains(String name) {
        return cacheMap.containsKey(name);
    }

    public String saveItem(String item) {
        Integer currIndex = cacheMap.get(item);
        if (currIndex == null) {
            cacheList.add(item);
            ++currentMaxIndex;
            cacheMap.put(item, currentMaxIndex);
            return "New Item saved: " + item;
        }else{
            return "Item was already present: " + item;
        }
    }

    public String deleteItem(String item) {
        Integer currIndex = cacheMap.get(item);
        if (currIndex == null) {
           return "Item was not found: " + item;
        }else{
            String lastItem = cacheList.get(currentMaxIndex);
            if (lastItem.equals(item)) {
                cacheMap.remove(item);
                cacheList.add(currentMaxIndex, null);
                --currentMaxIndex;
            }else{
                cacheList.add(currIndex, lastItem);
                cacheList.add(currentMaxIndex, null);
                cacheMap.remove(item);
                cacheMap.put(lastItem, currIndex);
                --currentMaxIndex;
            }
            System.out.println(cacheMap);
            System.out.println(cacheList);
            return "Item has been deleted: " + item;
        }
    }

    public String randomItem(){
        if ( currentMaxIndex < 0 ) return null;
        int randomIndex = random.nextInt(currentMaxIndex+1);
        return cacheList.get(randomIndex);
    }
}

