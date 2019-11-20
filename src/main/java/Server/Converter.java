package Server;

import java.util.ArrayList;

public class Converter {
   // private int[] listArray = new int[50];

    public ArrayList<Integer> convertRequestToArray(String data) {
        ArrayList<Integer> idArray = new ArrayList<Integer>();

        for (String str: data.split(",")) {
            idArray.add(Integer.parseInt(str));
        }
        return idArray;
    }

    public ArrayList<Badge> convertRequestToBadgeList(String request){
        ArrayList<Badge> badgesList = new ArrayList<Badge>();
        request = request.replace(" ", "");
        request = request.replace("{","");
        request = request.replace("}","");

        for (String object: request.split(",")) {
            String[] para= object.split("=");
            badgesList.add(new Badge(Integer.parseInt(para[0]), Integer.parseInt(para[1])));
        }
        return badgesList;
    }
}
