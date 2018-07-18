package org.vincent.multthread;

import java.io.IOException;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @Package: PACKAGE_NAME <br/>
 * @Description： TODO <br/>
 * @author: PengRong <br/>
 * @Date: Created in 2018/7/14 17:03 <br/>
 * @Company: PLCC <br/>
 * @Copyright: Copyright (c) 2018 <br/>
 * @Version: 1.0 <br/>
 * @Modified By: <br/>
 * @Created by PengRong on 2018/7/14. <br/>
 */

public class Main {
		public static void main(String[] args) throws IOException {
				System.out.println("hello , welcome to multthread project. SDF ");
				OkHttpClient client = new OkHttpClient();
				Request request=new  Request.Builder().url("https://www.sogou.com/").build();
				Response response=client.newCall(request).execute();
				System.out.println(response.body().toString());
		    System.out.println("hello ,  bye .");
		}
}
