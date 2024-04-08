package com.mysite.account.service;

import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.mysite.account.domain.Account;
import com.mysite.account.domain.Account.CashType;
import com.mysite.account.domain.Account.Type;
import com.mysite.account.repository.AccountRepository;

@Service
public class FileService {
	
	@Autowired
	private AccountRepository accountRepository;
	
	
	public void createCsv(MultipartFile file, String id) throws Exception {
				
		OPCPackage opcPackage = OPCPackage.open(file.getInputStream());
		XSSFWorkbook workbook = new XSSFWorkbook(opcPackage);
		
		// 첫번째 시트 불러오기
		XSSFSheet sheet = workbook.getSheetAt(0);
		
		Account account = new Account();
		account.setId(id);

		// i는 몇번째 행부터 확인할건지 정함
		for(int i=1; i<sheet.getLastRowNum() + 1; i++) {
			XSSFRow row = sheet.getRow(i);
			
			if(row == null) continue;
			
			XSSFCell cell = row.getCell(1);
			
			
			cell = row.getCell(2);
			if(cell != null) {
				if(cell.getStringCellValue() == "수입") {
					account.setType(Type.income);					
				} else if (cell.getStringCellValue() == "지출") {
					account.setType(Type.expense);
				} else {
					continue;
				}
			} else {
				continue;
			}
			
			cell = row.getCell(3);
			if(cell != null) {
				if(cell.getStringCellValue() == "통장") {
					account.setType2(CashType.account);					
				} else if (cell.getStringCellValue() == "카드") {
					account.setType2(CashType.card);
				} else if (cell.getStringCellValue() == "현금") {
					account.setType2(CashType.cash);
				}
			}
			
			cell = row.getCell(4);
			if(cell != null) {
				account.setAccount(cell.getStringCellValue());
			}
			
			cell = row.getCell(5);
			if(cell != null) {
				account.setCategory(cell.getStringCellValue());
			} else {
				continue;
			}
			
			cell = row.getCell(6);
			if(cell != null) {
				account.setAmount(Integer.parseInt(cell.getStringCellValue()));
			} else {
				continue;
			}
			
			cell = row.getCell(8);
			if(cell != null) {
				account.setMemo(cell.getStringCellValue());
			}
			
			accountRepository.save(account);

		}
		
		
		
	}
	
}
