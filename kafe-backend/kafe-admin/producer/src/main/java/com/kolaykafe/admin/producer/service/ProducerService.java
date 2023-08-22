package com.kolaykafe.admin.producer.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kolaykafe.admin.producer.dto.ItemDTO;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class ProducerService {
    @Autowired
    private KafkaTemplate<String, ItemDTO> _kafkaTemplate;

    @Autowired
    private ObjectMapper _mapper;
    public boolean readMenuFile(MultipartFile file) {
        try {
            DataFormatter dataFormatter = new DataFormatter();
            XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream());
            XSSFSheet sheet = workbook.getSheetAt(0);

            Iterator<Row> rowIterator = sheet.rowIterator();
            while (rowIterator.hasNext()) {
                Row currentRow = rowIterator.next();

                ItemDTO item = new ItemDTO();
                item.setCafeId((long) currentRow.getCell(0).getNumericCellValue());
                item.setItemName(currentRow.getCell(1).getStringCellValue());
                item.setPrice(currentRow.getCell(2).getNumericCellValue());
                item.setMainCategory(currentRow.getCell(3).getStringCellValue());
                item.setSubCategory(currentRow.getCell(4).getStringCellValue());

                ProducerRecord<String,ItemDTO> event = new ProducerRecord<>("ADD_MENU", item);
                _kafkaTemplate.send(event);
            }

            return true;
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
}
