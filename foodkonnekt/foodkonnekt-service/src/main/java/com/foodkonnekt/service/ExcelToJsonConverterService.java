package com.foodkonnekt.service;

import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import com.foodkonnekt.model.ExcelWorkbook;
import com.foodkonnekt.model.Merchant;
import com.foodkonnekt.serviceImpl.ExcelToJsonConverterConfig;

public interface ExcelToJsonConverterService {
	
	public ExcelWorkbook saveInventoryByExcel(ExcelToJsonConverterConfig config,Merchant merchant)throws InvalidFormatException, IOException;

}
