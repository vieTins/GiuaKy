package Midterm;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Student {
private String id ;
private String name ;
private String address ;
private String dateOfBirth ;
private int age ;
private String giaTriMaHoa ;
private boolean isPrime ;
public static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

public Student(String id, String name, String address, String dateOfBirth) {
	super();
	this.id = id;
	this.name = name;
	this.address = address;
	  if (isValidDate(dateOfBirth)) {
          this.dateOfBirth = dateOfBirth;
      } else {
          this.dateOfBirth = ""; 
      }	
}
public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getAddress() {
	return address;
}
public void setAddress(String address) {
	this.address = address;
}
public String getDateOfBirth() {
	return dateOfBirth;
}
public void setDateOfBirth(String dateOfBirth) {
	this.dateOfBirth = dateOfBirth;
}
public int getAge() {
	return age;
}
public void setAge(int age) {
	this.age = age;
}
public String getGiaTriMaHoa() {
	return giaTriMaHoa;
}
public void setGiaTriMaHoa(String giaTriMaHoa) {
	this.giaTriMaHoa = giaTriMaHoa;
}
public boolean isPrime() {
	return isPrime;
}
public void setIPrime(boolean isPrime) {
	this.isPrime = isPrime;
}
private boolean isValidDate(String date) {
    try {
        dateFormat.parse(date);
        return true;
    } catch (Exception e) {
        return false;
    }
}
@Override
public String toString() {
	return "Student [id=" + id + ", name=" + name + ", address=" + address + ", dateOfBirth=" + dateOfBirth + ", age="
			+ age + ", giaTriMaHoa=" + giaTriMaHoa + ", isPrime=" + isPrime + "]";
}

}
