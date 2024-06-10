/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package helpers;

import org.json.JSONArray;

/**
 *
 * @author dudam
 */
public class Formater {
    public static String convertJSONArrayToString(JSONArray jsonArray) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < jsonArray.length(); i++) {
            sb.append(jsonArray.getString(i));
            if (i < jsonArray.length() - 1) {
                sb.append(", ");
            }
        }

        return sb.toString();
    }

    public static JSONArray convertStringToJSONArray(String input) {
        JSONArray jsonArray = new JSONArray();

        String[] items = input.split(", ");

        for (String item : items) {
            jsonArray.put(item);
        }

        return jsonArray;
    }
}
