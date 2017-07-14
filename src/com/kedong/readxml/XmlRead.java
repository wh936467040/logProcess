package com.kedong.readxml;

import java.io.File;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.kedong.keyword.KeywordParse;

public class XmlRead {
	private String xmlFileName;
	
	public XmlRead(String file) {
		this.xmlFileName = file;
	}
	public List<TableStruct> read() {
		Document doc = readFile(xmlFileName);
		Element root = getRootElement(doc);
		List<TableStruct> tableList = new LinkedList<TableStruct>();
		for (Iterator<?> i = root.elementIterator(); i.hasNext();) {
			Element element = (Element) i.next();
			TableStruct table = new TableStruct();
			for (Iterator<?> j = element.attributeIterator(); j.hasNext();) {
				Attribute attribute = (Attribute) j.next();
				if ("tablename" == attribute.getName().toString()) {
					table.setTableName(attribute.getValue());
				}else if ("id" == attribute.getName().toString()) {
					table.setId(Integer.parseInt(attribute.getValue()));
				}
			}
			for (Iterator<?> m = element.elementIterator(); m.hasNext();) {
				Element prop = (Element) m.next();
				if("keywords" != prop.getName()) {
					table.names.add(prop.getName());
					table.rules.add(prop.getStringValue());
				} else {
					table.keywords = KeywordParse.parse(prop.getStringValue());
				}
			}
			tableList.add(table);
		}
		return  tableList;
	}

	
	
	private Document readFile(String fileName) {
		SAXReader reader = new SAXReader();
		Document document = null;
		try {
			document = (Document) reader.read(new File(fileName));
		} catch (DocumentException e) {
			System.out.println("read xml failed");
			e.printStackTrace();
		}
		return document;
	}

	private Element getRootElement(Document doc) {
		return doc.getRootElement();
	}
}
