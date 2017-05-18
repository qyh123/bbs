package com.itstar.util.words;

import java.util.HashMap;

/**
* maxLength-需要过滤最长字符串的长度
* filterStrs<string,string>-需要过滤字符串的集合，key为需要过滤字符串，value为过滤成的字符串如"*"
*
*/
public class WordsFilter{

private int maxLength;

private HashMap<String,String> filterStrs=new HashMap<String,String>();
/**
* 初始化需要过滤掉*的数量
*/
private String initStr(int n){
   StringBuffer sb=new StringBuffer();
   for(int i=0;i<n;i++){
    sb.append('*');
   }
   return sb.toString();
}
/**
* str-被过滤得字符串s
* s-需要过滤得字符串
* 获得剩下未过滤的字符串
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
* str-被过滤得字符串
* s-需要过滤得字符串
* 获得过滤后的字符串
*/
private StringBuffer getFilterStr(StringBuffer sb,String str,int start,String s){
   if(start!=0){
    sb.append(str.substring(0,start));
   }
   sb.append(filterStrs.get(s));
   return sb;
}
/**
* str-被过滤的字符串
* 过滤，并组合过滤后的字符串
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
 * 得到过滤后的文字
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
     	put(cf.getValue(Integer.toString(i)));//以下读取properties文件的值
        i++;
     }
     str = filter(str);
	 return str;
 }
//public static void main(String[] agrs){
//   Test t=new Test();
//   t.put("TMD");
//   t.put("TNND");
//   t.put("操");
//   t.put("NND");
//   System.out.println(t.filter("tTeMD,操nu，    ?!@#$%^*TNN D.操..TMDTMDTMDTMD.tTNND.操TMDTNNDTNNDTNND操"));
//}
/*public static void main(String[] agrs){
	WordsFilter t=new WordsFilter();
	   System.out.println(t.filterWords("江泽慧);"+"现在页面跳转到了指定jsp页面，但是我按F5键或则刷新页面，会重复向数据库插入同样的记录，请问该问题怎么解决呀？大家帮忙呀no3 "));
	} */
}
