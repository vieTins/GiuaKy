package Midterm;

import java.util.List;
import java.util.concurrent.BlockingQueue;

class PrimeChecker implements Runnable {
    private BlockingQueue<Student> inputQueue;
    private List<Student> resultStudents;

    public PrimeChecker(BlockingQueue<Student> inputQueue, List<Student> resultStudents) {
        this.inputQueue = inputQueue;
        this.resultStudents = resultStudents;
    }

    @Override
    public void run() {
        try {
            while (true) {
            	if(inputQueue.isEmpty()) {
	                break;
	            }
                Student student = inputQueue.take();
                if (student.isPrime()) { // Kiểm tra nếu tuổi của sinh viên là số nguyên tố
                    resultStudents.add(student); // Thêm sinh viên vào danh sách kết quả
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}


