package com.example;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.PictureData;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcelFileToList {

	public static List<Country> readExcelData(String fileName) {
		List<Country> countriesList = new ArrayList<Country>();
		
		try {
			//Create the input stream from the xlsx/xls file
			FileInputStream fis = new FileInputStream(fileName);
			
			//Create Workbook instance for xlsx/xls file input stream
			Workbook workbook = null;
			if(fileName.toLowerCase().endsWith("xlsx")){
				workbook = new XSSFWorkbook(fis);
			}else if(fileName.toLowerCase().endsWith("xls")){
				workbook = new HSSFWorkbook(fis);
			}
			
			//Get the number of sheets in the xlsx file
			int numberOfSheets = workbook.getNumberOfSheets();
			
			//loop through each of the sheets
			for(int i=0; i < numberOfSheets; i++){
				
				//Get the nth sheet from the workbook
				Sheet sheet = workbook.getSheetAt(i);
				
				//every sheet has rows, iterate over them
				Iterator<Row> rowIterator = sheet.iterator();
				while (rowIterator.hasNext()) 
		        {
					String name = "";
					String shortCode = "";
					
					//Get the row object
					Row row = rowIterator.next();
					
					//Every row has columns, get the column iterator and iterate over them
					Iterator<Cell> cellIterator = row.cellIterator();
		             
		            while (cellIterator.hasNext()) 
		            {
		            	//Get the Cell object
		            	Cell cell = cellIterator.next();
		            	
		            	//check the cell type and process accordingly
		            	switch(cell.getCellType()){
		            	case Cell.CELL_TYPE_STRING:
		            		if(shortCode.equalsIgnoreCase("")){
		            			shortCode = cell.getStringCellValue().trim();
		            		}else if(name.equalsIgnoreCase("")){
		            			//2nd column
		            			name = cell.getStringCellValue().trim();
		            		}else{
		            			//random data, leave it
		            			System.out.println("Random data::"+cell.getStringCellValue());
		            		}
		            		break;
		            	case Cell.CELL_TYPE_NUMERIC:
		            		System.out.println("Random data::"+cell.getNumericCellValue());
		            	}
		            } //end of cell iterator
		            Country c = new Country(name, shortCode);
		            countriesList.add(c);
		        } //end of rows iterator
				
				
			} //end of sheets for loop
			
			//close file input stream
			fis.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return countriesList;
	}

	public static void main(String args[]) throws IOException, InvalidFormatException{
		List<Country> list = readExcelData("c://USERS//ARPAN//BOOK1.xlsx");
		System.out.println("Country List\n"+list);
		
		// READING IMAGES 
		
		InputStream inp = new FileInputStream("c://USERS//ARPAN//BOOK1.xlsx");
		
		Workbook wb = WorkbookFactory.create(inp);
		
		
		  List lst = wb.getAllPictures();
		    for (Iterator it = lst.iterator(); it.hasNext(); ) {
		        PictureData pict = (PictureData)it.next();
		        String ext = pict.suggestFileExtension();
		        byte[] data = pict.getData();
		        if (ext.equals("JPG")){
		          FileOutputStream out = new FileOutputStream("c://users//arpan//pict.jpg");
		          out.write(data);
		          out.close();
		        }
		    }
		
		
		
		
		
		
		/*List lst = workbook.getAllPictures();
		for (Iterator it = lst.iterator(); it.hasNext(); ) {
		    PictureData pict = (PictureData)it.next();
		    String ext = pict.suggestFileExtension();
		    byte[] data = pict.getData();
		    if (ext.equals("jpeg")){
		      FileOutputStream out = new FileOutputStream("pict.jpg");
		      out.write(data);
		      out.close();
		    }*/
	//	}
		
		
	}

}