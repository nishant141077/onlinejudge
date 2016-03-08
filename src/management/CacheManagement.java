/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package management;

import entities.Problem;
import java.util.ArrayList;
import java.util.List;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

/**
 *
 * @author nishant
 */
public class CacheManagement {
    static CacheManager cacheManager;
    static Cache cache;
    static Element element;
    
    public static String getHandle() {
        cacheManager = CacheManager.getInstance();
        cache = cacheManager.getCache("loginCache");
        if(cache == null) {
            return "";
        }
        element = cache.get("handle");
        if(element == null) {
            return "";
        }
        else return element.getObjectValue().toString();
    }

    public static String getPassword() {
        cacheManager = CacheManager.getInstance();
        cache = cacheManager.getCache("loginCache");
        if(cache == null) {
            return "";
        }
        element = cache.get("password");
        if(element == null) {
            return "";
        }
        else return element.getObjectValue().toString();
    }

    public static void addCache(String handle, String password) {
        cacheManager = CacheManager.getInstance();
        cache = cacheManager.getCache("loginCache");
        if(cache == null) {
            cacheManager.addCache("loginCache");
        }
        cache = cacheManager.getCache("loginCache");
        cache.put(new Element("handle", handle));
        cache.put(new Element("password", password));
    }

    public static void addCache(List<Problem> problemsList) {
        cacheManager = CacheManager.getInstance();
        cache = cacheManager.getCache("problemListCache");
        
        if(cache == null) {
            cacheManager.addCache("problemListCache");
        }
        cache = cacheManager.getCache("problemListCache");
        cache.put(new Element("problems", problemsList));
    }
    
    public static List<Problem> getProblemList() {
        cacheManager = CacheManager.getInstance();
        cache = cacheManager.getCache("problemListCache");
        if(cache == null) {
            return new ArrayList<Problem>();
        }
        element = cache.get("problems");
        if(element == null) {
            return new ArrayList<Problem>();
        }
        return (List<Problem>)element.getObjectValue();
    }
}
