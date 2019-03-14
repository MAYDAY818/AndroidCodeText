package cn.edu.hebtu.software.datademo;

import android.util.Log;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

public class MyHandle extends DefaultHandler {
    private List<Student> students;
    private Student student;
    private String charStr;


    @Override
    public void startDocument() throws SAXException {
        students=new ArrayList<Student>();
        super.startDocument();
    }

    @Override
    //uri->app: adroid:
    //layout_width
    //--------
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

        super.startElement(uri, localName, qName, attributes);
        if(qName.equals("students")){
            student=new Student();
        }else if(qName.equals("name")){
            student.setSex(attributes.getValue("sex"));
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {

        super.characters(ch, start, length);
        //将解析到的放入字符串
        charStr=new String(ch,start,length);
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        super.endElement(uri, localName, qName);
        if(qName.equals("name")){
            student.setName(charStr);
        }else if(qName.equals("nickname")){
            student.setNickname(charStr);
        }else if(qName.equals("student")){
            students.add(student);
        }
    }






    @Override
    public void endDocument() throws SAXException {
        super.endDocument();
        Log.e("SAX",students.toString());
    }
}
