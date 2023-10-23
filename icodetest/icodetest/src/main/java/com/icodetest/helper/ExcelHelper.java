package com.icodetest.helper;

import com.icodetest.entity.User;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class ExcelHelper {
    public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    static String[] HEADERs = { "Id", "Title", "Description", "Published" };
    static String SHEET = "Tutorials";

    public static boolean hasExcelFormat(MultipartFile file) {

        if (!TYPE.equals(file.getContentType())) {
            return false;
        }

        return true;
    }

    //  generate excel file
    public static ByteArrayInputStream generateExcelTemplate(List<User> users) {
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("UserFile");


            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("Id");
            headerRow.createCell(1).setCellValue("Firstname");
            headerRow.createCell(2).setCellValue("Lastname");
            headerRow.createCell(3).setCellValue("DOB");
            headerRow.createCell(4).setCellValue("City");
            headerRow.createCell(5).setCellValue("Email");

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            workbook.write(outputStream);
            return new ByteArrayInputStream(outputStream.toByteArray());
        } catch (IOException e) {

            throw new RuntimeException("Error generating Excel template", e);
        }
    }


    public static List<User> convertExcelToListOfUsers(InputStream is) {
        List<User> list = new ArrayList<>();
        try {
            XSSFWorkbook workbook = new XSSFWorkbook(is);
            XSSFSheet sheet = workbook.getSheet("icodetest");
            int rowNumber = 0;
            for (Row row : sheet) {
                if (rowNumber == 0) {
                    rowNumber++;
                    continue;

                }
                Iterator<Cell> cells = row.iterator();
                int cid = 0;
                User user = new User();
                while (cells.hasNext()) {
                    Cell cell = cells.next();
                    switch (cid) {
                        case 0 -> user.setId((long) cell.getNumericCellValue());
                        case 1 -> user.setFirstName(cell.getStringCellValue());
                        case 2 -> user.setLastName(cell.getStringCellValue());
                        case 3 -> user.setCity(cell.getStringCellValue());
                        case 4 -> user.setDateOfBirth(cell.getStringCellValue());
                        case 5 -> user.setEmail(cell.getStringCellValue());
                        default -> {
                        }
                    }
                    cid++;

                }
                list.add(user);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    }

