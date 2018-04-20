package com.lqf.wxdoctor.common;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ExcelUtil {


    public static List<?> readXlsx(FileInputStream fin) throws IOException {
        List list = new ArrayList();
        XSSFWorkbook workbook = new XSSFWorkbook(fin);
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
        File excelFile = new File("C:\\Users\\viruser.v-desktop\\Documents\\WeChat Files\\talent_captain\\Files\\治疗过的GIST患者名单180208.xlsx");
        FileInputStream in = new FileInputStream(excelFile); //文件流
        List<List> list = (List<List>) readXlsx(in);
        List header = list.remove(0);
        StringBuilder sb = new StringBuilder("CREATE TABLE gist_patient( id serial primary key,");
        sb.append(header.stream().map(x -> "\"" + x + "\" varchar(500)").reduce((x, y) -> x + "," + y).get());
        sb.append(")");
        String createSql = sb.toString();
        StringBuilder sb2 = new StringBuilder("insert into gist_patient(");
        sb2.append(header.stream().map(x -> "\"" + x + "\"").reduce((x, y) -> x + "," + y).get());
        sb2.append(") values (");
        sb2.append(list.stream().map(x -> x.stream().map(a -> "\"" + a + "\"").reduce((b, c) -> b + "," + c).get()).reduce((x, y) -> x + ")(" + y).get());
        String c = sb2.toString();
        c = "1";
    }
}
