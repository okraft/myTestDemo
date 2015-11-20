package mytest;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.flows.entity.User;
import com.flows.service.IUserService;

public class Test1 extends BaseJunit4Test{

	@Autowired
	private IUserService userService;
	
	@Test
	public void testFindByUserName(){
		User user = userService.findByUserName("wanggg");
		if(user != null){
			System.out.println("user test ----->" + user.getCode());
		}
	}
	
}
