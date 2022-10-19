package ku.cs.services;

import java.util.HashMap;

public class Parse {
    private final HashMap<String, Object> objectHashMap;

    public Parse() {
        objectHashMap = new HashMap<>();
    }

    public void add(String key, Object object){
        objectHashMap.put(key, object);
    }

    public Object getObject(String key){
        if (!objectHashMap.containsKey(key)){
            System.out.println("Can't find " + key);
        }
        return objectHashMap.get(key);
    }
    public void showAllObject(){
        for(String key : objectHashMap.keySet()){
            System.out.println(key+" "+ objectHashMap.get(key));
        }
    }
}
