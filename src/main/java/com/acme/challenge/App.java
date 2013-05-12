package com.acme.challenge;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import jxl.Cell;
import jxl.NumberFormulaCell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.read.biff.BiffException;
import jxl.write.Formula;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

/**
 * Hello world!
 * 
 */
public class App {

	private String inputFile;
	private List<Item> items = new ArrayList<Item>();

	public static void main(String[] args) throws IOException,
			RowsExceededException, WriteException {
		App app = new App();
		app.setInputFile("c:/result2.xls");
		app.read();
	}

	public void setInputFile(String inputFile) {
		this.inputFile = inputFile;
	}

	public void read() throws IOException, RowsExceededException,
			WriteException {
		File inputWorkbook = new File(inputFile);
		Workbook w;
		try {
			w = Workbook.getWorkbook(inputWorkbook);

			for (int sheetindex = 0; sheetindex < w.getSheets().length; sheetindex++) {
				String actual = "";
				int begin = 0;
				int end = 0;
				List<Integer> machines = new ArrayList<Integer>();
				Sheet sheet = w.getSheet(sheetindex);
				for (int i = 0; i < sheet.getRows(); i++) {
					Cell cell = sheet.getCell(0, i);
					Cell valueCell = sheet.getCell(1, i);
					machines.add(Integer.valueOf(valueCell.getContents()));
					if (actual == "") {
						actual = cell.getContents().split(":")[0];
						begin = i;
					}
					if (cell.getContents().startsWith(actual)) {
						end = i;

					} else {
						addnew(actual, begin + 1, end + 1, sheet.getName(),
								machines);
						machines = new ArrayList<Integer>();
						machines.add(Integer.valueOf(valueCell.getContents()));
						actual = cell.getContents().split(":")[0];
						begin = i;

					}
					if (cell.getContents().trim().equals("")) {
						addnew(actual, begin, end, sheet.getName(), machines);
						machines = new ArrayList<Integer>();
						machines.add(Integer.valueOf(valueCell.getContents()));
						break;
					}
				}
			}

			writeToFile();

		} catch (BiffException e) {
			e.printStackTrace();
		}
	}

	private void writeToFile() throws RowsExceededException, WriteException,
			IOException {
		File file = new File("c:/resultList2.xls");
		WorkbookSettings wbSettings = new WorkbookSettings();

		wbSettings.setLocale(new Locale("en", "EN"));

		WritableWorkbook workbook = Workbook.createWorkbook(file, wbSettings);
		workbook.createSheet("Report", 0);
		WritableSheet excelSheet = workbook.getSheet(0);

		createContent(excelSheet);

		workbook.write();
		workbook.close();

	}

	private void createContent(WritableSheet sheet) throws WriteException,
			RowsExceededException {
		int i = 0;
		addLabel(sheet, 0, i, "sheet");
		addLabel(sheet, 1, i, "date");
		addLabel(sheet, 2, i, "begin");
		addLabel(sheet, 3, i, "end");
		addLabel(sheet, 4, i, "max1");
		addLabel(sheet, 5, i, "max2");
		addLabel(sheet, 6, i, "max3");
		addLabel(sheet, 7, i, "min");
		addLabel(sheet, 8, i, "average");
		for (Item item : items) {
			System.out.println(item.toString());
			addLabel(sheet, 0, i, item.getSheet());
			addLabel(sheet, 1, i, item.getDate());
			addLabel(sheet, 2, i, item.getBegin());
			addLabel(sheet, 3, i, item.getEnd());
			addLabel(sheet, 4, i, item.getMax1());
			addLabel(sheet, 5, i, item.getMax2());
			addLabel(sheet, 6, i, item.getMax3());
			addLabel(sheet, 7, i, item.getMin());
			addLabel(sheet, 8, i, item.getAverage());

			i++;
		}
		System.out.println("end...");

	}

	private void addLabel(WritableSheet sheet, int column, int row, String s)
			throws WriteException, RowsExceededException {
		Label label;
		label = new Label(column, row, s);
		sheet.addCell(label);
	}

	private void addnew(String actual, int begin, int end, String sheetName,
			List<Integer> machines) {
		Collections.sort(machines);
		double average = 0;
		int sum = 0;
		for (Integer integer : machines) {
			sum += integer;
		}
		average = sum / machines.size();
		Item item = new Item(begin, end, actual, sheetName,
				(int) machines.get(0), (int) machines.get(1),
				(int) machines.get(2), (int) machines.get(machines.size() - 1),
				average);
		System.out.println(item.toString());
		items.add(item);
	}

}
