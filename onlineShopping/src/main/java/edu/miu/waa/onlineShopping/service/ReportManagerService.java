package edu.miu.waa.onlineShopping.service;

import java.io.FileNotFoundException;

import net.sf.jasperreports.engine.JRException;

public interface ReportManagerService {

	String generatePdfInvoice(Long orderId) throws FileNotFoundException, JRException;

}
