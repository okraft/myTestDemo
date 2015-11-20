package com.flows.utils;

public class UserTest implements Cloneable {
	
	private String name;
	private int age;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	@Override
    protected UserTest clone() { 
		UserTest clone = null; 
        try{ 
            clone = (UserTest) super.clone(); 
        }catch(CloneNotSupportedException e){ 
            throw new RuntimeException(e); // won't happen 
        }
        return clone; 
    }

}
