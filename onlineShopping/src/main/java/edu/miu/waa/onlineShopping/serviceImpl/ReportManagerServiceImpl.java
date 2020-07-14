package edu.miu.waa.onlineShopping.serviceImpl;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import edu.miu.waa.onlineShopping.domain.PlaceOrder;
import edu.miu.waa.onlineShopping.repository.OrderRepository;
import edu.miu.waa.onlineShopping.service.ReportManagerService;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
public class ReportManagerServiceImpl implements ReportManagerService {

	@Autowired
	private OrderRepository orderRepo;
	
	@Autowired
	private ServletContext servletContext;

	@Override
	public String generatePdfInvoice(Long orderId) throws FileNotFoundException, JRException {

		String path = servletContext.getRealPath("invoices/");

		PlaceOrder order = orderRepo.findById(orderId).get();
		// load file and compile it
		File file = ResourceUtils.getFile("classpath:invoice.jrxml");
		JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(order.getCartItems());
		Map<String, Object> parameters = new HashMap<>();

		parameters.put("billAddress", order.getBillingAddress().getStreet() + "\n"
				+ order.getBillingAddress().getCity() + "\n" + order.getBillingAddress().getState());

		parameters.put("shipAddress", order.getShippingAddress().getStreet() + "\n"
				+ order.getShippingAddress().getCity() + "\n" + order.getShippingAddress().getState());

		parameters.put("totalPrice", order.getTotalPrice());

		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

		JasperExportManager.exportReportToPdfFile(jasperPrint, path + "/invoice.pdf");
		
		return path;

	}

}
