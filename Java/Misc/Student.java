import edu.princeton.cs.algs4.*;
import java.lang.Object;
import java.util.*;

public class Student implements Comparable<Student>{
  private String name;
  private String lastName;
  private final int id;
  
  public Student(String lastName, String name, int id){
    this.name = name;
    this.lastName = lastName;
    this.id = id;
  }
  public String getName(){ return name; }
  public String getLastName(){ return lastName; }
  public int getID() { return id; }
  public String toString(){ return name + " " + lastName + " " + id; }
  public int compareTo(Student st){ return name.compareTo(st.name); }
  
  public static class lastNameOrder implements Comparator<Student>{
    public int compare(Student thisStudent, Student otherStudent){
      return thisStudent.getID() - otherStudent.getID();
    }
  }
  public static void main(String args[]){
    Student []student = new Student[6];
    student[0] = new Student("Tolaymat", "Samy", 2);
    student[1] = new Student("Liu", "Linxin", 6);
    student[2] = new Student("Kim", "Jung S.", 9);
    student[3] = new Student("Le", "Kimberly N.", 1);
    student[4] = new Student("Gonzalez", "Robert A.", 8);
    student[5] = new Student("Roscoe", "Sarah R.", 4);
    
    StdOut.println("\n");
    //System.out.println("\n");
    //for(int i = 0; i < student.length; i++)
    //  System.out.println("Unsorted: " + student[i]);
    for(Student person : student){
      System.out.println("Unsorted: " + person);
    }
    
    StdOut.println("\n");
    //System.out.println("\n");
    Arrays.sort(student);
    //for(int i = 0; i < student.length; i++)
    //  System.out.println("Sorted by first name: " + student[i]);
    for(Student person: student){
      System.out.println("Sorted by first name: " + person);
    }
    
    StdOut.println("\n");
    //System.out.println("\n");
    Arrays.sort(student, new Student.lastNameOrder());
    //for(int i = 0; i < student.length; i++)
    //  System.out.println("Sorted by id: " + student[i]);
    for(Student person: student){
      System.out.println("Sorted by id: " + person);
    }
  }
}