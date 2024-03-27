// Lab07vst100.java
// This lab creates a "school" which has a list of students.
// Within this school you can selection sort and search linearly and binarily
// this is the 100-point version. The 80-point version is completely different.
// Evin Lodder 3/27/24
import java.util.ArrayList;
public class Main
{
    public static void main (String[] args)
    {
        System.out.println("Lab07v100 by Evin Lodder");
        System.out.println();
        int size = 10;
        School bhs = new School(size);
        System.out.println(bhs);
        System.out.println(bhs.linearSearch("Meg"));
        System.out.println(bhs.linearSearch("Sid"));
        System.out.println();
        bhs.selectionSort();
        System.out.println(bhs);
        System.out.println(bhs.binarySearch("Meg"));
        System.out.println(bhs.binarySearch("Sid"));
        System.out.println();
    }
}
class School
{
    private ArrayList<Student> students;
    private int size;
    public School (int s) {
        students = new ArrayList<Student>(s);
        size = s;
        addData();
    }

    //adds example data
    public void addData() {
        //create lists of names ages and gpas
        String[] names = new String[]{"Tom", "Ann", "Bob", "Jan", "Joe", "Sue", "Jay", "Meg", "Art", "Deb"};
        int[] ages = new int[]{21, 34, 18, 45, 27, 19, 30, 38, 40, 35};
        double[] gpas = new double[]{1.685, 3.875, 2.5, 4.0, 2.975, 3.225, 3.65, 2.0, 3.999, 2.125};
        //add new students to the array
        for(int i = 0; i < size; i++) {
            students.add(new Student(names[i], ages[i], gpas[i]));
        }
    }
    //selection sorts the values by name
    public void selectionSort() {
        for(int i = 0; i < size; i++) {
            //start with current index, then if anything beyond this index in the list is smaller,
            //make that the new smallest index
            int smallestIndex = i;
            for(int j = i + 1; j < size; j++) {
                if(students.get(j).getName().compareTo(students.get(smallestIndex).getName()) < 0) {
                    smallestIndex = j;
                }
            }
            //swap the current value with the new smallest value
            Student temp = students.get(i);
            students.set(i, students.get(smallestIndex));
            students.set(smallestIndex, temp);
        }
    }
    public String toString() {
        //creates a stringbuilder and appends each student with a newline
        StringBuilder builder = new StringBuilder();
        for(Student stu: students) {
            builder.append(stu);
        }
        return builder.toString();
    }
    public int linearSearch (String str) {
        //search linearly through the students until you find a matching name.
        //returns the index of the match if found, otherwise -1
        for(int i = 0; i < size; i++) {
            if(students.get(i).getName().equals(str)) {
                return i;
            }
        }
        return -1;
    }
    public int binarySearch (String str) {
        //binary searches the students for name str.
        //returns index of match if found, otherwise -1
        //note: students must be sorted for this binary search to work
        int low = 0;
        int high = size - 1;
        while(low <= high) {
            int middle = (low + high) / 2;
            if(students.get(middle).getName().equals(str))
                return middle;
            else if(students.get(middle).getName().compareTo(str) < 0)
                low = middle + 1;
            else
                high = middle - 1;
        }
        return -1;
    }
}


class Student
{
    private String name;
    private int age;
    private double gpa;
    public Student (String n, int a, double g)
    {
        name = n;
        age = a;
        gpa = g;
    }
    public String getName() { return name; }
    public int getAge() { return age; }
    public double getGPA() { return gpa; }
    public String toString()
    {
        return name + " " + age + " " + gpa + "\n";
    }
}
