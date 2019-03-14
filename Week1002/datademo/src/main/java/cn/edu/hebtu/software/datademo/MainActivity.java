package cn.edu.hebtu.software.datademo;

import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Xml;
import android.view.View;
import android.widget.Button;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class MainActivity extends AppCompatActivity {
    private  AssetManager assetManager;
    private  Student stu;
    private List<Student> students;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        assetManager=getAssets();
        Button dom=findViewById(R.id.btn_dom);
        dom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parseByDOM();

            }
        });
        Button sas=findViewById(R.id.btn_sas);
        sas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parseBySAS();
            }
        });

        Button pull=findViewById(R.id.btn_pull);
        pull.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parseBYPULL();
            }
        });
    }
    private void parseBYPULL(){
        try {
            InputStream inputStream=assetManager.open("students.xml");
            XmlPullParser parser= Xml.newPullParser();

            parser.setInput(inputStream,"utf-8");
            //
            int type=parser.getEventType();
            while(type!=XmlPullParser.END_DOCUMENT){
                switch (type){
                    case XmlPullParser.START_TAG:
                        if("students".equals(parser.getName())){
                            students=new ArrayList<>();

                        }
                        if("student".equals(parser.getName())){
                            stu=new Student();

                        }
                        if("name".equals(parser.getName())){
                           stu.setSex(parser.getAttributeValue(null,"sex"));
                           //获取name标签内容
                            stu.setNickname(parser.nextText());
                        }
                        if("nickname".equals(parser.getName())){
                            stu.setNickname(parser.nextText());
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        if("students".equals(parser.getName())){
                            students.add(stu);

                        }
                        break;
                }

                Log.e("pull",students.toString());
                //获取下一个事件类型
                type=parser.next();

            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }

    }





    private void parseByDOM(){

        students=new ArrayList<Student>();
        try {
            InputStream is=assetManager.open("students.xml");
            DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();//静态方法
            DocumentBuilder builder=factory.newDocumentBuilder();
            Document document=builder.parse(is);
            //获根
            Element element=document.getDocumentElement();
            //获子列表
            NodeList stuNodeList=element.getElementsByTagName("student");
            //遍历子列表
            for(int i=0;i<stuNodeList.getLength();i++){
                //创建学生对象
                stu=new Student();
                //获取每一个student节点
                Node studentNode =stuNodeList.item(i);
                //获取Studnet的子节点
                NodeList childNodeList=studentNode.getChildNodes();
                //遍历Studnet的子节点
                for(int j=0;j<childNodeList.getLength();j++){
                    //获得每一个Studnet的子节点
                    Node childNode=childNodeList.item(j);
                    if(childNode.getNodeName().equals("name")){
                        stu.setName(childNode.getTextContent());
                        //获取name节点属性
                        NamedNodeMap map=childNode.getAttributes();
                        //获sex
                        Node sexNode=map.item(0);
                        stu.setSex(sexNode.getTextContent());

                    }else if(childNode.getNodeName().equals("nickname")){
                        stu.setNickname(childNode.getTextContent());
                    }

                }

                //把学生对象加入list
                students.add(stu);

                Log.e("msg",students.toString());

            }


        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }


    }




    private void parseBySAS(){
        //创建SASParseFactory


        try {
            InputStream is=assetManager.open("students.xml");
            SAXParserFactory factory=SAXParserFactory.newInstance();
            SAXParser parser=factory.newSAXParser();
            XMLReader reader=parser.getXMLReader();
            MyHandle myHandle=new MyHandle();
            reader.setContentHandler(myHandle);
            reader.parse(new InputSource(is));
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
