package com.light.usercenter;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SpringBootTest(classes={UserCenterApplication.class})
@RunWith(SpringRunner.class)
public class UserCenterApplicationTests {


	@Test
	public void testSelect() {
		String validPattern ="[\\\\u00A0\\\\s\\\"`~!@#$%^&* ()+=| {}':;',\\\\ [\\\\].<>/?~！ @#￥%……&* ()——+| {}【】‘；：”“'。 ，、？ ]  ";
		Matcher matcher= Pattern.compile(validPattern).matcher("light");
		if(matcher.find()){
			System.out.println("没有问题");
		}
	}
}
