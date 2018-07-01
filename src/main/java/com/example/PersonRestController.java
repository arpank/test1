package com.example;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;

/*{
    "color":"Blue",
    "miles":200,
    "vin":"1234", 
      "name": [{"lastName":"arpan", 
               "name":[{"lastName":"test"}]
               }, {"lastName":"darpan"}]
}*/

import java.util.List;

import org.apache.poi.ss.usermodel.PictureData;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.PictureData;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

@RestController
@RequestMapping("/persons")
public class PersonRestController {

	@Autowired
	PersonRepository repo;

	@RequestMapping("")
	public List<Person> listPerson() {
		System.out.println("Inside the class arpan shivji");
		return repo.findAll();
	}

	@RequestMapping("/{id}")
	public Person getById(@PathVariable Integer id) {
		return repo.findOne(id);
	}

	@RequestMapping(value = "/getMiles", method = RequestMethod.POST)
	public ResponseEntity<Car> update(@RequestBody Car car) {

		if (car != null) {
			car.setMiles(car.getMiles() + 100);
		}

		// TODO: call persistence layer to update
		return new ResponseEntity<Car>(car, HttpStatus.OK);
	}

}

@RestController
@RequestMapping("/arpan")
class PersonRestController1 {

	@Autowired
	PersonRepository repo;

	@RequestMapping("")
	public List<Person> listPerson() {
		return repo.findAll();
	}

	@RequestMapping("/{id}")
	public Person getById(@PathVariable Integer id) {
		return repo.findOne(id);
	}

}

@RestController
@RequestMapping("/arpan2")
class PersonRestController2 {

	@Autowired
	PersonRepository repo;

	@RequestMapping("")
	public   List<Person> listPerson() throws InvalidFormatException, IOException {

		List<Country> list = readExcelData("c://USERS//ARPAN//BOOK1.xlsx");
		System.out.println("Country List\n" + list);

		// READING IMAGES

		InputStream inp = new FileInputStream("c://USERS//ARPAN//BOOK1.xlsx");

		Workbook wb = WorkbookFactory.create(inp);

		List lst = wb.getAllPictures();
		for (Iterator it = lst.iterator(); it.hasNext();) {
			PictureData pict = (PictureData) it.next();
			String ext = pict.suggestFileExtension();
			byte[] data = pict.getData();
			if (ext.equals("JPG")) {
				FileOutputStream out = new FileOutputStream("c://users//arpan//pict.jpg");
				out.write(data);
				out.close();
			}
		}

		return repo.findAll();
	}

	@RequestMapping( value="/{id}" , method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody Person  getById(@PathVariable Integer id) {
		return repo.findOne(id);
	}

	public static List<Country> readExcelData(String fileName) {
		List<Country> countriesList = new ArrayList<Country>();

		try {
			// Create the input stream from the xlsx/xls file
			FileInputStream fis = new FileInputStream(fileName);

			// Create Workbook instance for xlsx/xls file input stream
			Workbook workbook = null;
			if (fileName.toLowerCase().endsWith("xlsx")) {
				workbook = new XSSFWorkbook(fis);
			} else if (fileName.toLowerCase().endsWith("xls")) {
				workbook = new HSSFWorkbook(fis);
			}

			// Get the number of sheets in the xlsx file
			int numberOfSheets = workbook.getNumberOfSheets();

			// loop through each of the sheets
			for (int i = 0; i < numberOfSheets; i++) {

				// Get the nth sheet from the workbook
				Sheet sheet = workbook.getSheetAt(i);

				// every sheet has rows, iterate over them
				Iterator<Row> rowIterator = sheet.iterator();
				while (rowIterator.hasNext()) {
					String name = "";
					String shortCode = "";

					// Get the row object
					Row row = rowIterator.next();

					// Every row has columns, get the column iterator and
					// iterate over them
					Iterator<Cell> cellIterator = row.cellIterator();

					while (cellIterator.hasNext()) {
						// Get the Cell object
						Cell cell = cellIterator.next();

						// check the cell type and process accordingly
						switch (cell.getCellType()) {
						case Cell.CELL_TYPE_STRING:
							if (shortCode.equalsIgnoreCase("")) {
								shortCode = cell.getStringCellValue().trim();
							} else if (name.equalsIgnoreCase("")) {
								// 2nd column
								name = cell.getStringCellValue().trim();
							} else {
								// random data, leave it
								System.out.println("Random data::" + cell.getStringCellValue());
							}
							break;
						case Cell.CELL_TYPE_NUMERIC:
							System.out.println("Random data::" + cell.getNumericCellValue());
						}
					} // end of cell iterator
					Country c = new Country(name, shortCode);
					countriesList.add(c);
				} // end of rows iterator

			} // end of sheets for loop

			// close file input stream
			fis.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

		return countriesList;
	}

}