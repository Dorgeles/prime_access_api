package com.wdyapplications.prime_access.utils.redis;

import com.wdyapplications.prime_access.utils.Utilities;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Component
public class CacheUtils {

    private final static String IDENTIFIER  = "token";
    private final static String NODE_PREFIX = "node";
    private static Logger log;
    

    @Autowired
    private RedisTemplate<String, String>     redisTemplateForCaching;
    @Autowired
    private RedisTemplate<String, Object>     redisTemplateForObjectCaching;

    public void cacheString(String key, String value) {
        try {
            redisTemplateForCaching.opsForValue().set(key, value, 12, TimeUnit.HOURS);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getCachedString(String key) {
        String value = null;
        try {
            value = redisTemplateForCaching.opsForValue().get(key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return value;
    }

    public void cacheData(String key, Object value) {
        try {
            redisTemplateForObjectCaching.opsForValue().set(key, value, 5, TimeUnit.MINUTES);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void cacheData(String key, Object value, long timeInMinut) {
        try {
            redisTemplateForObjectCaching.opsForValue().set(key, value, timeInMinut, TimeUnit.MINUTES);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void cacheData(String key, Object value, long time, TimeUnit timeUnit) {
        try {
            redisTemplateForObjectCaching.opsForValue().set(key, value, time, timeUnit);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Object getCachedData(String key) {
        Object value = null;
        try {
            value = redisTemplateForObjectCaching.opsForValue().get(key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return value;
    }

    

    public Map<String, Object> getExistingUser(String key) {
        Object value = null;
        try {
            value = redisTemplateForObjectCaching.opsForValue().get(key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (Map<String, Object>)value;
    }
    
    
    public List<Object> getAllCachedData(String pattern) {
    	List<Object> value = null;
        try {
            List<Object> lstToReturn = new ArrayList<Object>();
            try {
            	System.out.println(" pattern ===> "+pattern);
                Set<String> keys = scanObject(pattern);
                lstToReturn = redisTemplateForObjectCaching.opsForValue().multiGet(keys);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return lstToReturn;
        	
        	
        } catch (Exception e) {
            e.printStackTrace();
        }
        return value;
    }

//    public  List<Object> findPositionByCriteria(TechnicienDto dto) {
//    	List<Object> listToReturn = new ArrayList();
//    	try {
//    		//Build the Pattern
//    		String pattern = Utilities.notBlank(dto.getTag()) ? dto.getTag() + "_" : "";
//    		if (Utilities.notBlank(dto.getLogin())) {
//    			pattern += dto.getLogin();
//    		}
//    		Set<String> keys = scanObject(pattern);
//    		//Get datas
//    		listToReturn = redisTemplateForObjectCaching.opsForValue().multiGet(keys);
//
//    	} catch(Exception e) {
//    		e.printStackTrace();
//    	}
//    	return listToReturn;
//    }


    public Set<String> scanObject(String matchKey) {
        return redisTemplateForObjectCaching.execute((RedisCallback<Set<String>>) connection -> {
            Set<String> keysTmp = new HashSet<>();
            ScanOptions options = ScanOptions.scanOptions()
                    .match("*" + matchKey + "*")
                    .count(1000)
                    .build();

            try (Cursor<byte[]> cursor = connection.keyCommands().scan(options)) {
                while (cursor.hasNext()) {
                    byte[] key = cursor.next();
                    keysTmp.add(new String(key, StandardCharsets.UTF_8));  // Spécification explicite de l'encodage
                }
            } catch (Exception e) {
                // Gestion d'erreur si nécessaire
                throw new RuntimeException("Erreur lors du scan Redis", e);
            }

            return keysTmp;
        });
    }
    
    public List<Object> getDataByListKey(List<String> keys) {
    	List<String> listKey = addPrefix(keys);
        return redisTemplateForObjectCaching.opsForValue().multiGet(listKey);
    }
    
    public List<String> addPrefix(List<String> listKey) {    	
    	List<String> _listKey = listKey.stream().map(key-> key).collect(Collectors.toList());
    	return _listKey;
    }
    




     public Set<String> scan(String matchKey) {
         return redisTemplateForCaching.execute((RedisCallback<Set<String>>) connection -> {
             Set<String> keysTmp = new HashSet<>();
             ScanOptions options = ScanOptions.scanOptions()
                     .match("*" + matchKey + "*")
                     .count(1000)
                     .build();

             try (Cursor<byte[]> cursor = connection.keyCommands().scan(options)) {
                 while (cursor.hasNext()) {
                     byte[] key = cursor.next();
                     keysTmp.add(new String(key, StandardCharsets.UTF_8));  // Spécification explicite de l'encodage
                 }
             } catch (Exception e) {
                 // Gestion d'erreur si nécessaire
                 throw new RuntimeException("Erreur lors du scan Redis", e);
             }

             return keysTmp;
         });
    }

    private String getNodeKey(String key) {
        return NODE_PREFIX + "_" + key;
    }
   

//    public RedisOperations<String, Object> getAllCachedData(List<String> key) {
//    	RedisOperations<String, Object> value = null;
//        try {
//            value = redisTemplateForObjectCaching.opsForList().getOperations();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return value;
//    }
//    
    
/*
    public Map<String, Object> getCachedDatas(String matchKey, String keySeparator) {
        Map<String, Object> map = new HashMap<>();
        try {
            Set<String>      keys     = scan(matchKey + keySeparator);
            for (String key : keys) {
                final Object data = getCachedData(key);
                if (data == null) {
                    continue;
                }
                key = key.contains(keySeparator) ? key.split(keySeparator)[1] : key;
                map.put(key, data);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }
*/
    public void deleteCache(String key) {
        try {
            if (Boolean.TRUE.equals(redisTemplateForCaching.hasKey(key))) {
                //jedis.del(key);
                redisTemplateForCaching.delete(key);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
    // use for cron leader
    public void saveNode(String key, SystemNode value) {
        try {
            redisNodeTemplate.opsForValue().set(getNodeKey(key), value, 12, TimeUnit.HOURS);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveNodes(Map<String, SystemNode> nodes) {
        if (nodes == null)
            return;
        for (Entry<String, SystemNode> entry : nodes.entrySet()) {
            saveNode(entry.getKey(), entry.getValue());
        }
    }

    public SystemNode findNode(String key) {
        SystemNode value = null;
        try {
            value = redisNodeTemplate.opsForValue().get(getNodeKey(key));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return value;
    }

    public List<SystemNode> findNodes() {
        List<SystemNode> lstToReturn = new ArrayList<SystemNode>();
        try {
            //Set<String> keys = redisNodeTemplate.keys(NODE_PREFIX + "_*");
            Set<String> keys = scan(NODE_PREFIX + "_");
            lstToReturn = redisNodeTemplate.opsForValue().multiGet(keys);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lstToReturn;
    }

    public Set<String> scan(String matchKey) {
        Set<String> keys = redisNodeTemplate.execute((RedisCallback<Set<String>>) connection -> {
            Set<String>    keysTmp = new HashSet<>();
            Cursor<byte[]> cursor  = connection.scan(new ScanOptions.ScanOptionsBuilder().match("*" + matchKey + "*").count(1000).build());
            while (cursor.hasNext()) {
                keysTmp.add(new String(cursor.next()));
            }
            return keysTmp;
        });
        return keys;
    }

    private String getNodeKey(String key) {
        return NODE_PREFIX + "_" + key;
    }
    */



    public boolean save(String key, String value, boolean isDelay) {
        return false;
    }


    public void saveValueWithExpiration(String key, Object cmdsTosave, int expiration) {
        try {
            // set to 6Hours / minutes before
            redisTemplateForObjectCaching.opsForValue().set(key, cmdsTosave, expiration, TimeUnit.MINUTES);
        } catch (Exception e) {
            e.printStackTrace();
            log.warn("saveValueWithExpiration : " + e.getCause(), e.getMessage());
        }
    }

    public void saveValueWithExpirationInSecond(String key, Object cmdsTosave, int expiration) {
        try {
            // set to 6Hours / minutes before
            redisTemplateForObjectCaching.opsForValue().set(key, cmdsTosave, expiration, TimeUnit.SECONDS);
        } catch (Exception e) {
            e.printStackTrace();
            log.warn("saveValueWithExpiration : " + e.getCause(), e.getMessage());
        }
    }

    public void saveValueWithExpirationImmediatly(String key, Object cmdsTosave) {
        try {
            redisTemplateForObjectCaching.opsForValue().set(key, cmdsTosave, 1, TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            e.printStackTrace();
            log.warn("saveValueWithExpiration : " + e.getCause(), e.getMessage());
        }
    }

    public void saveValueWithExpirationImmediatly(String key) {
        try {
            redisTemplateForObjectCaching.opsForValue().set(key, "", 1, TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            e.printStackTrace();
            log.warn("saveValueWithExpiration : " + e.getCause(), e.getMessage());
        }
    }

    public void saveValueWithoutExpiration(String key, Object cmdsTosave) {
        try {

            redisTemplateForObjectCaching.opsForValue().set(key, cmdsTosave);
        } catch (Exception e) {
            e.printStackTrace();
            log.warn("saveValueWithExpiration : " + e.getCause(), e.getMessage());
        }
    }

    public Boolean delete(String pattern) {
    	Boolean isDelete = false;
        try {
       //     if (Boolean.TRUE.equals(redisTemplateForObjectCaching.hasKey(key))) {        	
        	 Set<String> keys = scanObject(pattern);
        	 for (String key : keys) {
        		  	isDelete = redisTemplateForObjectCaching.delete(key);
			 }
            System.out.println(" isDelete ==> "+isDelete);
        //    }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isDelete;
    }

    public void saveValueWithoutExpirationGetway(String key, List<Object> cmdsTosave) {
        try {

            redisTemplateForObjectCaching.opsForList().rightPushAll(key, cmdsTosave);
        } catch (Exception e) {
            e.printStackTrace();
            log.warn("saveValueWithExpiration : " + e.getCause(), e.getMessage());
        }
    }

    public Object getValue(String key) {
        Object value = null;
        try {
            value = redisTemplateForObjectCaching.opsForValue().get(key);
        } catch (Exception e) {
            log.warn("getValue : " + e.getCause(), e.getMessage());
        }
        return value;
    }

    public List<Object> getValues(String key) {
        List<Object> value = null;
        try {
            Long size = redisTemplateForObjectCaching.opsForList().size(key);
            value = redisTemplateForObjectCaching.opsForList().range(key, 0, size);
        } catch (Exception e) {
            log.warn("getValue : " + e.getCause(), e.getMessage());
        }
        return value;
    }

    public String getVarSession() {
        String key = null;
        try {
            boolean continuer = true;
            while (continuer) {
                key = Utilities.combinaisonString();
                Object dto = null;
                dto = getValue(key);
                if (dto == null) {
                    continuer = false;
                }
            }
        } catch (Exception e) {
            log.warn("getVarSession : " + e.getCause(), e.getMessage());
        }
        return key;
    }
}
