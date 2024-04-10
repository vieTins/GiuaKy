package Midterm;
	
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;


public class Main {
public static void main(String[] args) {
   BlockingQueue<Student> studentQueue = new LinkedBlockingDeque<>()	;
   BlockingQueue<Student> ageQueue = new LinkedBlockingDeque<>() ;
   List<Student> resultStudents = new ArrayList<>() ;
   try {
	   ReadStudentFile readStudentFile = new ReadStudentFile("D:/JavaOPP/Mid-Term/students.xml", studentQueue);
       Thread docFileStudent = new Thread(readStudentFile);
       Thread tinhToanTuoi = new Thread(new MaHoaNgaySinh(readStudentFile.getQueue(), ageQueue));
       Thread primeCheckerThread = new Thread(new PrimeChecker(ageQueue, resultStudents));
	   docFileStudent.start(); 
	   tinhToanTuoi.start(); 
	   primeCheckerThread.start(); 
	    try {
	    	docFileStudent.join();
	    	tinhToanTuoi.join();
            primeCheckerThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
		WriteResultFile.writeResultFile(resultStudents, "D:/JavaOPP/Mid-Term/kq.xml") ;
} catch (Exception e) {
}

} 
}
