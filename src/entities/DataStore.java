/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 *
 * @author nishant
 */
public class DataStore {
    public static HashMap tagsHashMap = new HashMap();

    public static List<String> getTagsList() {
        List<String> tagsList = new ArrayList<String>();
        Iterator it = tagsHashMap.entrySet().iterator();
        while(it.hasNext()) {
            tagsList.add(((Map.Entry)it.next()).getKey().toString());
        }
        Collections.sort(tagsList);
        return tagsList;
    }

    public static void addTagsList(List<String> tagsList) {
        int i = 1;
        for(String tag : tagsList) {
            tagsHashMap.put(tag, i);
        }
    }
}
