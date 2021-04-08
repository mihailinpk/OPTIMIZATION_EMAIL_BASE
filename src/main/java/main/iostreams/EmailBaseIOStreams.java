package main.iostreams;

import org.springframework.stereotype.Component;

import java.io.*;
import java.util.*;

@Component
public class EmailBaseIOStreams {

    public Map<String, String> getMapParsingDataFromInputStream(InputStream inputStream) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        Map<String, String> resultMap = new HashMap<>();
        while (reader.ready())  {
            String line = reader.readLine();
            String[] parsedCurrentLineArray = line.split(" -> |, ");
            String currentUserName = parsedCurrentLineArray[0];
            List<String> currentUserEmailsList = Arrays.asList(parsedCurrentLineArray)
                    .subList(1, parsedCurrentLineArray.length);
            String matchingEmailLine = getMatchingEmailLine(resultMap, currentUserEmailsList);
            if (matchingEmailLine == null)  {
                currentUserEmailsList.stream().forEach(o -> {
                    resultMap.put(o, currentUserName);
                });
            }   else    {
                String matchingUserName = resultMap.get(matchingEmailLine);
                currentUserEmailsList.stream().forEach(o -> {
                    resultMap.put(o, matchingUserName);
                });
            }
        }
        return resultMap;
    }

    public void writeMapToOutputStream(OutputStream outputStream, Map<String, HashSet<String>> map) throws IOException {
        for(String key : map.keySet())    {
            StringBuilder lineToStream = new StringBuilder(key);
            lineToStream.append(" -> ");
            for(String email : map.get(key)) {
                lineToStream.append(" " + email + ",");
            }
            lineToStream.setCharAt(lineToStream.length()-1, '\n');
            outputStream.write(lineToStream.toString().getBytes());
        }
    }

    private String getMatchingEmailLine(Map<String, String> usersEmailMap, List<String> usersNameList)   {
        for(String line : usersNameList)    {
            if (usersEmailMap.containsKey(line))    {
                return line;
            }
        }
        return null;
    }

}