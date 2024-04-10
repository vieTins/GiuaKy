	package Midterm;
	
	import java.util.Calendar;
	import java.util.concurrent.BlockingQueue;
	
	public class MaHoaNgaySinh implements Runnable {
	    private BlockingQueue<Student> inputQueue;
	    private BlockingQueue<Student> outputQueue;
	
	    public MaHoaNgaySinh(BlockingQueue<Student> inputQueue, BlockingQueue<Student> outputQueue) {
	        this.inputQueue = inputQueue;
	        this.outputQueue = outputQueue;
	    }
	
	    @Override
	    public void run() {
	        try {
	            while (true) {
	                if(inputQueue.isEmpty()) {
	                    System.out.println("Input queue is empty");
	                    break;
	                }
	                Student student = inputQueue.take();
	                int age = calculateAge(student.getDateOfBirth());
	                boolean isPrime = isPrime(sumDigits(student.getDateOfBirth()));
	                student.setAge(age);
	                student.setIPrime(isPrime);
	                System.out.println(student.toString());
	                outputQueue.put(student);
	            }
	        } catch (InterruptedException e) {
	            Thread.currentThread().interrupt();
	        }
	    }
	
	
	    private int calculateAge(String dateOfBirth) {	
	        int age = 0;
	        try {
	            Calendar now = Calendar.getInstance();
	            Calendar dob = Calendar.getInstance();
	            dob.setTime(Student.dateFormat.parse(dateOfBirth));
	
	            int yearDiff = now.get(Calendar.YEAR) - dob.get(Calendar.YEAR);
	            int monthDiff = now.get(Calendar.MONTH) - dob.get(Calendar.MONTH);
	            int dayDiff = now.get(Calendar.DAY_OF_MONTH) - dob.get(Calendar.DAY_OF_MONTH);
	
	            if (monthDiff < 0 || (monthDiff == 0 && dayDiff < 0)) {
	                age = yearDiff - 1;
	            } else {
	                age = yearDiff;
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return age;
	    }
	
	    private int sumDigits(String input) {
	        int sum = 0;
	        for (int i = 0; i < input.length(); i++) {
	            if (Character.isDigit(input.charAt(i))) {
	                sum += Character.getNumericValue(input.charAt(i));
	            }
	        }
	        return sum;
	    }
	
	    private boolean isPrime(int number) {
	        if (number <= 1) {
	            return false;
	        }
	        if (number <= 3) {
	            return true;
	        }
	        if (number % 2 == 0 || number % 3 == 0) {
	            return false;
	        }
	        for (int i = 5; i * i <= number; i += 6) {
	            if (number % i == 0 || number % (i + 2) == 0) {
	                return false;
	            }
	        }
	        return true;
	    }
	}
