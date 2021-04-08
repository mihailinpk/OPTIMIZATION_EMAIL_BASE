package main.tools;

import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class EmailBaseTools {

    public Map<String, HashSet<String>> getStructuredUsersEmailMap(Map<String, String> usersEmailMap)   {
        Map<String, HashSet<String>> resultMap = new HashMap<>();
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