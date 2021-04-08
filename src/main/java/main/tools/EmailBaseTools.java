package main.tools;

import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

@Component
public class EmailBaseTools {

    public Map<String, HashSet<String>> getStructuredUsersEmailMap(Map<String, String> usersEmailMap)   {
        Map<String, HashSet<String>> resultMap = new TreeMap<>();
        for(String email : usersEmailMap.keySet())    {
            String user = usersEmailMap.get(email);
            if (resultMap.containsKey(user))    {
                resultMap.get(user).add(email);
            }   else    {
                resultMap.put(user, new HashSet<>(Set.of(email)));
            }
        }
        return resultMap;
    }

}