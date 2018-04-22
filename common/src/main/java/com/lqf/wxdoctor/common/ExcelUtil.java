package com.lqf.wxdoctor.common;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExcelUtil {


    public static List<?> readXlsx(InputStream in) throws IOException {
        List list = new ArrayList();
        XSSFWorkbook workbook = new XSSFWorkbook(in);
        Sheet sheet = workbook.getSheetAt(0);
        for (int i = 0; i < sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            List<String> cellList = new ArrayList<String>();
            for(int j=0; j < row.getLastCellNum(); j++) {
                Cell cell = row.getCell(j);
                if (cell == null) {
                    cellList.add("");
                    continue;
                }
                String val = getCellVal(cell);
                cellList.add(val);
            }
            list.add(cellList);
        }
        return list;
    }

    public static String getCellVal(Cell cell) {
        String result = null;
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        switch (cell.getCellTypeEnum()) {
            case FORMULA:
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    result = fmt.format(cell.getDateCellValue());
                } else {
                    result = String.format("%.0f", cell.getNumericCellValue());
                }
                break;
            case STRING:
                result = cell.getStringCellValue();
                break;
            case BOOLEAN:
                result = String.valueOf(cell.getBooleanCellValue());
                break;
            case _NONE:
            case BLANK:
                result = "";
                break;
            case ERROR:
                result = "错误";
            default:
                break;
        }
        return result;
    }

    public static void main(String[] args) throws IOException {
        File excelFile = new File("/Users/genius/Desktop/治疗过的GIST患者名单180208.xlsx");
        FileInputStream in = new FileInputStream(excelFile); //文件流
        List<List> list = (List<List>) readXlsx(in);
        Pattern pattern = Pattern.compile("[\\u4E00-\\u9FA5\\w]*");
        List header = Arrays.asList(list.remove(0).stream().map(x -> {
            Matcher matcher = pattern.matcher(x.toString());
            matcher.find();
            return "\"" + matcher.group() + "\"";}).toArray());
        StringBuilder sb = new StringBuilder("CREATE TABLE gist_patient( id serial primary key,");
        sb.append(header.stream().map(x -> x + " varchar(500)").reduce((x, y) -> x + "," + y).get());
        sb.append(")");
        String createSql = sb.toString();
        StringBuilder sb2 = new StringBuilder("insert into gist_patient(");
        sb2.append(header.stream().reduce((x, y) -> x + "," + y).get());
        sb2.append(") values (");
        sb2.append(list.stream().map(x -> {
            int size = header.size() - x.size();
            if (size > 0) {
                while (size-- != 0) {
                    x.add("");
                }
                size = 0;
            }
            if (size < 0) {
                while (size++ != 0) {
                    x.remove(x.size() - 1);
                }
            }
            return x.stream().map(a -> "'" + a.toString().replaceAll("\"", "\\\"") + "'").reduce((b, c) -> b + "," + c).get();
        }).reduce((x, y) -> x + "),(" + y).get());
        sb2.append(")");
        String c = sb2.toString();
        c = "1";
    }
}
