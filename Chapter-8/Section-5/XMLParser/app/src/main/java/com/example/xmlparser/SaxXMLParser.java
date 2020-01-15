package com.example.xmlparser;

import java.io.InputStream;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXTransformerFactory;
import javax.xml.transform.sax.TransformerHandler;
import javax.xml.transform.stream.StreamResult;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;
import org.xml.sax.helpers.DefaultHandler;

import android.util.Log;

public class SaxXMLParser implements XMLParser {
	private static String TAG = "XML";
	@Override
	public List<Product> parse(InputStream is) throws Exception {
		SAXParserFactory factory = SAXParserFactory.newInstance();	//ȡ��SAXParserFactoryʵ��
		SAXParser parser = factory.newSAXParser();					//��factory��ȡSAXParserʵ��
		MyHandler handler = new MyHandler();						//ʵ�����Զ���Handler
		parser.parse(is, handler);									//�����Զ���Handler�������������
		return handler.getProducts();
	}

	@Override
	public String serialize(List<Product> products) throws Exception {
		SAXTransformerFactory factory = (SAXTransformerFactory) TransformerFactory.newInstance();//ȡ��SAXTransformerFactoryʵ��
		TransformerHandler handler = factory.newTransformerHandler();			//��factory��ȡTransformerHandlerʵ��
		Transformer transformer = handler.getTransformer();						//��handler��ȡTransformerʵ��
		transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");			// ����������õı��뷽ʽ
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");				// �Ƿ��Զ���Ӷ���Ŀհ�
		transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");	// �Ƿ����XML����
		
		StringWriter writer = new StringWriter();
		StreamResult result = new StreamResult(writer);
	    handler.setResult(result);
	    
	    String uri = "";	//���������ռ��URI ��URI��ֵʱ ����Ϊ���ַ���
	    String localName = "";	//�����ռ�ı�������(������ǰ׺) ��û�н��������ռ䴦��ʱ ����Ϊ���ַ���
	    
	    handler.startDocument();
	    handler.startElement(uri, localName, "products", null);
	    
	    AttributesImpl attrs = new AttributesImpl();	//������Ԫ�ص�������Ϣ
	    char[] ch = null;
	    for (Product product : products) {
	    	attrs.clear();	//��������б�
		    attrs.addAttribute(uri, localName, "id", "string", String.valueOf(product.getId()));//���һ����Ϊid������(typeӰ�첻��,������Ϊstring)
	    	handler.startElement(uri, localName, "product", attrs);	//��ʼһ��bookԪ�� ���������趨��id����
	    	
	    	handler.startElement(uri, localName, "name", null);	//��ʼһ��nameԪ�� û������
	    	ch = String.valueOf(product.getName()).toCharArray();
	    	handler.characters(ch, 0, ch.length);	//����nameԪ�ص��ı��ڵ�
	    	handler.endElement(uri, localName, "name");
	    	
	    	handler.startElement(uri, localName, "price", null);//��ʼһ��priceԪ�� û������
	    	ch = String.valueOf(product.getPrice()).toCharArray();
	    	handler.characters(ch, 0, ch.length);	//����priceԪ�ص��ı��ڵ�
	    	handler.endElement(uri, localName, "price");
	    	
	    	handler.endElement(uri, localName, "product");
	    }
	    handler.endElement(uri, localName, "products");
	    handler.endDocument();
	    
		return writer.toString();
	}
	
	//SAX�ࣺDefaultHandler����ʵ����ContentHandler�ӿڡ���ʵ�ֵ�ʱ��ֻ��Ҫ�̳и��࣬������Ӧ�ķ������ɡ�
	private class MyHandler extends DefaultHandler {

		private List<Product> products;
		private Product product;
		private StringBuilder builder;
		
		//���ؽ�����õ���Product���󼯺�
		public List<Product> getProducts() {
			return products;
		}	
		@Override
		public void startDocument() throws SAXException {
			super.startDocument();
			products = new ArrayList<Product>();
			builder = new StringBuilder();
		}
		public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
			super.startElement(uri, localName, qName, (org.xml.sax.Attributes) attributes);  
			if (localName.equals("product")) {
				product = new Product();
			}
		}
		@Override
		public void characters(char[] ch, int start, int length) throws SAXException {
			super.characters(ch, start, length);
			builder.append(ch, start, length);	//����ȡ���ַ�����׷�ӵ�builder��
		}
		@Override
		public void endElement(String uri, String localName, String qName) throws SAXException {
			super.endElement(uri, localName, qName);
			if (localName.equals("id")) {
				product.setId(Integer.parseInt(builder.toString().trim()));	
			} else if (localName.equals("name")) {
				product.setName(builder.toString().trim());
			} else if (localName.equals("price")) {
				product.setPrice(Float.parseFloat(builder.toString().trim()));
			} else if (localName.equals("product")) {
				products.add(product);
			}
		}
	}
}
