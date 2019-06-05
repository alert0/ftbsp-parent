/*    */ package com.dstz.bpm.core.util;
/*    */ 
/*    */ import java.io.ByteArrayInputStream;
/*    */ import java.io.InputStream;
/*    */ import java.io.StringWriter;
/*    */ import java.io.UnsupportedEncodingException;
/*    */ import javax.xml.bind.JAXBContext;
/*    */ import javax.xml.bind.JAXBException;
/*    */ import javax.xml.bind.Marshaller;
/*    */ import javax.xml.bind.Unmarshaller;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class XmlCovertUtil
/*    */ {
/*    */   @SafeVarargs
/*    */   public static <T> T a(String xml, Class... classes) throws JAXBException, UnsupportedEncodingException {
/* 23 */     JAXBContext jAXBContext = JAXBContext.newInstance(classes);
/*    */     
/* 25 */     Unmarshaller unmarshaller = jAXBContext.createUnmarshaller();
/* 26 */     InputStream is = new ByteArrayInputStream(xml.getBytes("utf-8"));
/* 27 */     return (T)unmarshaller.unmarshal(is);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static String a(Object serObj) throws JAXBException {
/* 34 */     JAXBContext jc = JAXBContext.newInstance(new Class[] { serObj.getClass() });
/*    */     
/* 36 */     StringWriter out = new StringWriter();
/* 37 */     Marshaller m = jc.createMarshaller();
/* 38 */     m.setProperty("jaxb.formatted.output", Boolean.valueOf(true));
/* 39 */     m.setProperty("jaxb.encoding", "utf-8");
/* 40 */     m.marshal(serObj, out);
/* 41 */     return out.toString();
/*    */   }
/*    */ }


/* Location:              D:\Repository\com\dstz\agilebpm\wf-core\1.5.1\wf-core-1.5.1-pg.jar!\com\dstz\bpm\cor\\util\XmlCovertUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.0
 */