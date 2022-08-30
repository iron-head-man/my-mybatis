package com.xiaoxing.mybatis04.builder.xml;

import com.xiaoxing.mybatis04.builder.BaseBuilder;
import com.xiaoxing.mybatis04.io.Resources;
import com.xiaoxing.mybatis04.mapping.MappedStatement;
import com.xiaoxing.mybatis04.mapping.SqlCommandType;
import com.xiaoxing.mybatis04.session.Configuration;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.xml.sax.InputSource;

import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>
 *
 * </p>
 *
 * @author xxingheng@hand-china.com 2022/08/22 23:15
 */
public class XMLConfigBuilder extends BaseBuilder {

    private Element root;

    public XMLConfigBuilder(Reader reader) {
        // 1.调用父类方法初始话configuraiton
        super(new Configuration());
        SAXReader saxReader = new SAXReader();
        // 2.调用dom4j处理xml信息
        try {
            Document document = saxReader.read(new InputSource(reader));
            root = document.getRootElement();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    /**
     * 解析配置；类型别名、插件、对象工厂、对象包装工厂、设置、环境、类型转换、映射器
     *
     * @return Configuration
     */
    public Configuration parse() {
        try {
            // 解析映射器
            mapperElement(root.element("mappers"));
        } catch (Exception e) {
            throw new RuntimeException("Error parsing SQL Mapper Configuration. Cause: " + e, e);
        }
        return configuration;
    }

    private void mapperElement(Element mappers) throws IOException, DocumentException, ClassNotFoundException {
        List<Element> mapperList = mappers.elements("mapper");
        for (Element e : mapperList) {
            String resource = e.attributeValue("resource");
            Reader reader = Resources.getResourceAsReader(resource);
            SAXReader saxReader = new SAXReader();
            Document document = saxReader.read(new InputSource(reader));
            Element root = document.getRootElement();
            //命名空间
            String namespace = root.attributeValue("namespace");

            // SELECT
            List<Element> selectNodes = root.elements("select");
            for (Element node : selectNodes) {
                String id = node.attributeValue("id");
                String parameterType = node.attributeValue("parameterType");
                String resultType = node.attributeValue("resultType");
                String sql = node.getText();

                // ? 匹配
                Map<Integer, String> parameter = new HashMap<>();
                Pattern pattern = Pattern.compile("(#\\{(.*?)})");
                Matcher matcher = pattern.matcher(sql);
                for (int i = 1; matcher.find(); i++) {
                    String g1 = matcher.group(1);
                    String g2 = matcher.group(2);
                    parameter.put(i, g2);
                    sql = sql.replace(g1, "?");
                }

                String msId = namespace + "." + id;
                String nodeName = node.getName();
                SqlCommandType sqlCommandType = SqlCommandType.valueOf(nodeName.toUpperCase(Locale.ENGLISH));
                MappedStatement mappedStatement = new MappedStatement.Builder(configuration, msId, sqlCommandType, parameterType, resultType, sql, parameter).build();
                // 添加解析 SQL
                configuration.addMappedStatement(mappedStatement);
            }

            // 注册Mapper映射器
            configuration.addMapper(Resources.classForName(namespace));
        }
    }
}
