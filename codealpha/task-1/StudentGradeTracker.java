package com.CodeAlpha.task1;
import java.util.Scanner;
public class StudentGradeTracker {
	public static void main(String[] args) {

//		Loading scanner
		Scanner sc = new Scanner(System.in);

//		Taking number of students
		System.out.print("Enter the number of students: ");
		int numberOfStudents = sc.nextInt();

//		Creating Array of students grades (I am using Array because i already know the exact number of students)
		double[] studentsGrades = new double[numberOfStudents];

		for (int i = 0; i < numberOfStudents; i++) 
		{
//			Taking grades of students
			System.out.print("Enter the grade of student" + (i + 1) + ":");
			double grade = sc.nextDouble();
			studentsGrades[i] = grade;
		}

//		Calculating average 
		double average = findAverage(studentsGrades);

//		Calculating lowest & highest
		double highest = findhighest(studentsGrades);
		double lowest = findlowest(studentsGrades);

//		Printing the results
		System.out.println("The Results are: ");
		System.out.println("The average: " + average);
		System.out.println("The highest: " + highest);
		System.out.println("The lowest: " + lowest);
		
//		Closing scanner
		sc.close();
	}

//	Calculating average method
	private static double findAverage(double[] studentsGrades) {
		double sum = 0;
		for(double grade : studentsGrades) {
			sum = sum + grade;
		}
		double avg = sum/studentsGrades.length;
		return avg;
	}

//	Calculating lowest & highest methods
	private static double findhighest(double[] studentsGrades) {
		double highest = studentsGrades[0];
		for(double grade : studentsGrades) {
			if(grade > highest) {
				highest = grade;
			}
		}
		return highest;
	}
	
	private static double findlowest(double[] studentsGrades) {
		double lowest = studentsGrades[0];
		for(double grade : studentsGrades) {
			if(grade < lowest) {
				lowest = grade;
			}
		}
		return lowest;
	}


}
