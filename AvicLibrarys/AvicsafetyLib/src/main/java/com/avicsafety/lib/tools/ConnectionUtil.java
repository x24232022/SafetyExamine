package com.avicsafety.lib.tools;

import android.database.Cursor;

import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;

public class ConnectionUtil {

   /**
   * ?��????class�????并�??????et??et?��???bject
   * @param clazz
   * @param beanProperty
   * @return
   */
   @SuppressWarnings("rawtypes")
private Object[] beanMatch(Class clazz, String beanProperty) {
       Object[] result = new Object[2];
       char beanPropertyChars[] = beanProperty.toCharArray();
       beanPropertyChars[0] = Character.toUpperCase(beanPropertyChars[0]);
       String s = new String(beanPropertyChars);
       String names[] = { ("set" + s).intern(), ("get" + s).intern()};
       Method getter = null;
       Method setter = null;
       Method methods[] = clazz.getMethods();
       for (int i = 0; i < methods.length; i++) {
           Method method = methods[i];
           if(names[0].equalsIgnoreCase(method.getName())){
        	   setter = method;
           }else if(names[1].equalsIgnoreCase(method.getName())){
        	   getter = method;
           }
       }
       result[0] = getter;
       result[1] = setter;
       return result;
   }

private void beanRegister(Object object, String beanProperty, String value) {
       Object[] beanObject = beanMatch(object.getClass(), beanProperty);
       System.out.println("Bean properties is "+beanProperty);
       if(beanObject[0]==null){
    	   System.err.println("null is =============="+beanProperty);
    	   return;
       }
       Object[] cache = new Object[1];
       Method getter = (Method) beanObject[0];
       Method setter = (Method) beanObject[1];
       try {
           String methodType = getter.getReturnType().getName();// ???get?��??��?类�?
           if(value != null){
        	   if (methodType.equalsIgnoreCase("long") || methodType.equalsIgnoreCase("java.lang.Long")) {
                   cache[0] = new Long(value);
                   setter.invoke(object, cache);
               } else if (methodType.equalsIgnoreCase("int")|| methodType.equalsIgnoreCase("java.lang.Integer")) {
                   try{cache[0] = new Integer(value);
                   setter.invoke(object, cache);}
                   catch(Exception e){
                	   Long time=Timestamp.valueOf(value).getTime()/1000;
                	   Integer val=Integer.valueOf(time.toString());
                	   cache[0] = val;
                       setter.invoke(object, cache);
                   }
               } else if (methodType.equalsIgnoreCase("short")) {
                   cache[0] = new Short(value);
                   setter.invoke(object, cache);
               } else if (methodType.equalsIgnoreCase("float")) {
                   cache[0] = new Float(value);
                   setter.invoke(object, cache);
               } else if (methodType.equalsIgnoreCase("double")) {
                   cache[0] = new Double(value);
                   setter.invoke(object, cache);
               } else if (methodType.equalsIgnoreCase("boolean")) {
                   cache[0] = new Boolean(value);
                   setter.invoke(object, cache);
               } else if (methodType.equalsIgnoreCase("java.lang.String")) {
                   cache[0] = value;
                   setter.invoke(object, cache);
               } else if (methodType.equalsIgnoreCase("char")) {
                   cache[0] = (Character.valueOf(value.charAt(0)));
                   setter.invoke(object, cache);
               } else if (methodType.equalsIgnoreCase("java.io.InputStream")) {
            	   
               } 
           }
       } catch (Exception e) {
           e.printStackTrace();
       }
   }

   
   @SuppressWarnings({ "rawtypes", "unchecked" })
public Collection get(final Cursor result, final Class clazz) {
       Collection collection = null; // ??��collection
       try {
           int cols = result.getColumnCount();// ?��??��????
           collection = new ArrayList(cols);// ??���???��??????rraylist类�?collection�??
           while (result.moveToNext()) { // ???�????
               Object object = null;// ??��对象
               try {
                   object = clazz.newInstance();   // �?lass?��?对象�??
               } catch (Exception e) {
            	   e.printStackTrace();
               }
               for (int i = 0; i < cols; i++) { // �??�??记�?
                   beanRegister(object, result.getColumnName(i), result.getString(i));
               }
               collection.add(object); // �???????ollection
           }
       } catch (Exception e) {
    	   e.printStackTrace();
           System.err.println(e.getMessage());
       } finally {

       }
       return collection;
   }

   /*public static void main(String[] args) {
       try {
           Class.forName("org.gjt.mm.mysql.Driver");
       } catch (ClassNotFoundException e) {
           e.printStackTrace();
       }
       String url = "jdbc:mysql://localhost:3306/test?useUnicode=true";
       Connection connection = null;
       PreparedStatement ps = null;
       ResultSet rs = null;
       try {
           connection = DriverManager.getConnection(url, "root", "xxxx");
           ConnectionUtil test = new ConnectionUtil();
           // Ltest???�???�类�?????�??注�??��?对象�????et�?et�?s�?ead�?riter为�?�?????�??�??继续添�???
           Collection collection = test.get(rs, FMessage.class);
           for (Iterator it = collection.iterator(); it.hasNext();) {
        	   FMessage ltest = (FMessage) it.next();
               System.out.println(ltest.getId() + ":" + ltest.getSendername());
           }
       }
       // SQL�?���??�????QL�??�??�??�?????�??
       catch (SQLException e) {
           System.err.println(e.getMessage());
       }
       // finally�??????�以???�?��访�????容�?
       finally {
           try {
               // ?��?rs并�??��?�?
               if (rs != null) {
                   rs.close();
                   rs = null;
               }
               // ?��?ps并�??��?�?
               if (ps != null) {
                   ps.close();
                   ps = null;
               }
               // ?��?connection并�??��?�?
               if (connection != null) {
                   connection.close();
                   connection = null;
               }
               // �???��??�产???常�??��????
           } catch (SQLException e) {
               System.err.println(e.getMessage());
           }

       }
   }*/
}
