package com.itstar.util.words;

import java.util.HashMap;

/**
* maxLength-��Ҫ������ַ����ĳ���
* filterStrs<string,string>-��Ҫ�����ַ����ļ��ϣ�keyΪ��Ҫ�����ַ�����valueΪ���˳ɵ��ַ�����"*"
*
*/
public class WordsFilter{

private int maxLength;

private HashMap<String,String> filterStrs=new HashMap<String,String>();
/**
* ��ʼ����Ҫ���˵�*������
*/
private String initStr(int n){
   StringBuffer sb=new StringBuffer();
   for(int i=0;i<n;i++){
    sb.append('*');
   }
   return sb.toString();
}
/**
* str-�����˵��ַ���s
* s-��Ҫ���˵��ַ���
* ���ʣ��δ���˵��ַ���
*/
private String getNextStr(String str,int start,int slength){
   if(start==0){
    str=str.substring(slength);
   }else if(start+slength<str.length()){
    str=str.substring(start+slength);
   }
   return str;
}
/**
* str-�����˵��ַ���
* s-��Ҫ���˵��ַ���
* ��ù��˺���ַ���
*/
private StringBuffer getFilterStr(StringBuffer sb,String str,int start,String s){
   if(start!=0){
    sb.append(str.substring(0,start));
   }
   sb.append(filterStrs.get(s));
   return sb;
}
/**
* str-�����˵��ַ���
* ���ˣ�����Ϲ��˺���ַ���
*/
private String filter(String str) {
  // str=str.replaceAll(" ","");
   str=str+" ";
   StringBuffer resultStr=new StringBuffer();
   for(int start=0;start<str.length();start++){
    for(int end=start+1;end<=str.length()&&end<=start+maxLength;end++){
     String s=str.substring(start, end);
     int slength=s.length();
     if(filterStrs.containsKey(s)){
      resultStr=getFilterStr(resultStr,str,start,s);
      str=getNextStr(str,start,slength);
      start=0;
      end=start;
     }
    }
   }
   resultStr.append(str);
   return resultStr.toString();
}

private void put(String key) {
   int keyLength=key.length();
   filterStrs.put(key, initStr(keyLength));
   if(keyLength>this.maxLength)
    maxLength=keyLength;
}
/**
 * �õ����˺������
 * @param str
 * @return
 */
 public String filterWords(String str){
	 String filePath=Configuration.class.getResource("").toString();
	 filePath = filePath.substring(6, filePath.length());
	 filePath = filePath.replaceAll("%20", " ");
     Configuration cf = new Configuration(filePath+"/config.properties");
     int i=1;
     while(cf.getValue(Integer.toString(i))!=null&&!cf.getValue(Integer.toString(i)).equals("")){       	
     	put(cf.getValue(Integer.toString(i)));//���¶�ȡproperties�ļ���ֵ
        i++;
     }
     str = filter(str);
	 return str;
 }
//public static void main(String[] agrs){
//   Test t=new Test();
//   t.put("TMD");
//   t.put("TNND");
//   t.put("��");
//   t.put("NND");
//   System.out.println(t.filter("tTeMD,��nu��    ?!@#$%^*TNN D.��..TMDTMDTMDTMD.tTNND.��TMDTNNDTNNDTNND��"));
//}
/*public static void main(String[] agrs){
	WordsFilter t=new WordsFilter();
	   System.out.println(t.filterWords("�����);"+"����ҳ����ת����ָ��jspҳ�棬�����Ұ�F5������ˢ��ҳ�棬���ظ������ݿ����ͬ���ļ�¼�����ʸ�������ô���ѽ����Ұ�æѽno3 "));
	} */
}
