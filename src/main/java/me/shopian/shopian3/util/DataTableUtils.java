package me.shopian.shopian3.util;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * parse request of javascript dataTable and return List<ColumnDirection>

 columns[0][data]:id
 columns[0][name]:
 columns[0][searchable]:true
 columns[0][orderable]:true
 columns[0][search][value]:
 columns[0][search][regex]:false
 columns[1][data]:uuid
 columns[1][name]:
 columns[1][searchable]:true
 columns[1][orderable]:true
 columns[1][search][value]:
 columns[1][search][regex]:false
 columns[2][data]:major
 columns[2][name]:
 columns[2][searchable]:true
 columns[2][orderable]:true
 columns[2][search][value]:
 columns[2][search][regex]:false
 columns[3][data]:minor
 columns[3][name]:
 columns[3][searchable]:true
 columns[3][orderable]:true
 columns[3][search][value]:
 columns[3][search][regex]:false
 columns[4][data]:title
 columns[4][name]:
 columns[4][searchable]:true
 columns[4][orderable]:true
 columns[4][search][value]:
 columns[4][search][regex]:false

 order[0][column] = 0
 order[0][dir] = asc
 order[1][column] = 1
 order[1][dir] = asc
 order[2][column] = 2
 order[2][dir] = asc

 */
public class DataTableUtils {
    private static final Pattern patternColumn =  Pattern.compile("columns\\[(\\d+)\\]\\[data\\]");
    private static final Pattern patternOrderColumn =  Pattern.compile("order\\[(\\d+)\\]\\[column\\]");

    public static  Map<Integer, String> getColumnMap(HttpServletRequest request){
        Map<String, Object> params = request.getParameterMap();
        Map<Integer, String> columns = new HashMap();
        for(Map.Entry<String, Object> entry: params.entrySet()){
            Matcher matcher =  patternColumn.matcher(entry.getKey());
            while (matcher.find()) {
                columns.put( Integer.parseInt(matcher.group(1)),request.getParameter(entry.getKey()));
            }
        }
        return columns;
    }

    public static  List<String> getColumnList(HttpServletRequest request){
        Map<String, Object> params = request.getParameterMap();
        List<String> columns = new ArrayList<>();
        for(Map.Entry<String, Object> entry: params.entrySet()){
            Matcher matcher =  patternColumn.matcher(entry.getKey());
            while (matcher.find()) {
                columns.add(request.getParameter(entry.getKey()));
            }
        }
        return columns;
    }

    public static  List<ColumnDirection> getColumnDirectionList(HttpServletRequest request){
        Map<String, Object> params = request.getParameterMap();
        List<ColumnDirection> orderList = new ArrayList<>();

        Map<Integer, String> columns = getColumnMap(request);

        for(Map.Entry<String, Object> entry: params.entrySet()){
            String key=entry.getKey();
            Matcher matcher =  patternOrderColumn.matcher(key);
            while (matcher.find()) {
                String val=request.getParameter(entry.getKey());
                int column= Integer.parseInt(val);
                if (columns.containsKey(column)){
                    String dir=request.getParameter("order[" + matcher.group(1) + "][dir]");
                    orderList.add(new ColumnDirection(columns.get(column),dir));
                }
            }
        }
        return orderList;
    }
}
