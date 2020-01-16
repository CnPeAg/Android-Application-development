package com.example.xmlparser;

import java.io.InputStream;
import java.util.List;

public interface XMLParser {
	/**
	 * ���������� �õ�Product���󼯺�
	 * @param is
	 * @return
	 * @throws Exception
	 */
	public List<Product> parse(InputStream is) throws Exception;
	
	/**
	 * ���л�Product���󼯺� �õ�XML��ʽ���ַ���
	 * @param products
	 * @return
	 * @throws Exception
	 */
	public String serialize(List<Product> products) throws Exception;
}