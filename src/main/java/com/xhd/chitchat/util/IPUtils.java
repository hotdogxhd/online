package com.xhd.chitchat.util;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class IPUtils {
    /**
     * 根据ip获取归属地
     * @param ip
     * @return
     */
    public static String getAddress(String ip) {
        String url = "http://ip.ws.126.net/ipquery?ip=" + ip;
        String str = HttpUtil.get(url);
        if(!StrUtil.hasBlank(str)){
            String substring = str.substring(str.indexOf("{"), str.indexOf("}")+1);
            JSONObject jsonObject = JSONUtil.parseObj(substring);
            String province = jsonObject.getStr("province");
            String city = jsonObject.getStr("city");
            return province + city;
        }
        return null;
    }
    public static final String getIpAddr(final HttpServletRequest request)
            throws Exception {
        String ip = "http://pv.sohu.com/cityjson?ie=utf-8";

        String inputLine = "";
        String read = "";
        String toIp="";
        try {
            URL url = new URL(ip);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            while ((read = in.readLine()) != null) {
                inputLine += read;
            }
            String ObjJson=inputLine.substring(inputLine.indexOf("=")+1,inputLine.length()-1);
//            System.out.println(ObjJson);
            com.alibaba.fastjson.JSONObject jsonObj= JSON.parseObject(ObjJson);
            toIp=jsonObj.getString("cip");
//            throw new Exception();
        } catch (Exception e) {
            toIp="";
        }
        return toIp;

    }

}
