package com.yanghao.parse;

import org.dom4j.*;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class ParseXml {

    /**
     * 使用dom4j生成xml文件
     */

    @Test
    public void createXML() throws IOException {

        //创建一个空白的文档对象
        Document document = DocumentHelper.createDocument();

        //给文档对象创建一个根元素
        Element root = document.addElement("root");

        //给root添加两个个子元素
        Element childOne = root.addElement("childOne");
        Element childTwo = root.addElement("childTwo");


        //给这两个子元素添加属性
        childOne.addAttribute("id","childOneID");
        childOne.addAttribute("name","childOneName");
        childOne.addAttribute("class","childOneQualifiedName");

        childTwo.addAttribute("id","childTwoID");
        childTwo.addAttribute("name","childTwoName");
        childTwo.addAttribute("class","childTwoQualifiedName");

        //使用XMLWriter生成XML文件(OutputFormat.createPrettyPrint()格式化输出)
        XMLWriter xmlWriter = new XMLWriter(OutputFormat.createPrettyPrint());
        xmlWriter.setOutputStream(new FileOutputStream(new File("C:\\Users\\YanoHao\\Desktop\\aaa\\test.xml")));
        xmlWriter.write(document);

    }


    /**
     * 使用SAXReader解析器解析xml文件(Document----Element---Attribute)
     */
    @Test
    public void parseXmlBySAXReader() throws DocumentException {

        //创建SAXReader解析器
        SAXReader saxReader = new SAXReader();

        //使用SAXReader解析器获取文档对象
        Document document = saxReader.read("C:\\Users\\YanoHao\\Desktop\\aaa\\test.xml");

        //获取dom对象的数据
        Element rootElement = document.getRootElement();
        System.out.println("根元素名称"+rootElement.getName()+"\n");
        List elements = rootElement.elements();

        for (Object e: elements
             ) {
            Element element = (Element) e;
            System.out.println("子元素名称"+element.getName()+" ");
            List<Attribute> attributes = element.attributes();
            for (Attribute attribute :attributes
                 ) {
                System.out.println("子元素属性名"+attribute.getName()+" 值："+attribute.getValue());
            }
            System.out.println();
        }
    }

    /**
     * 使用XPath查找xml中特定的节点
     */
    @Test
    public void parseXmlByXPath() throws DocumentException {

        /* 创建SAXReader解析器 */
        SAXReader saxReader = new SAXReader();

        //使用SAXReader解析器获取文档对象
        Document document = saxReader.read("C:\\Users\\YanoHao\\Desktop\\aaa\\test.xml");

        //使用XPath解析路径获取元素节点
        //1、选择AAA所有的BBB元素(xpath格式:/AAA/CCC)
        List<Element> list = document.selectNodes("/root/childTwo");

        for (Element e: list
             ) {
            System.out.println(e.getName());
        }

    }



}
