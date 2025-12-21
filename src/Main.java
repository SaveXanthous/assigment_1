void main(){
    final int countOfStudent = 5;
    final String[]  name_students = {"Nikita","Sasha","Vova","Masha","Katya"};
    final String[] major_of_students = {"CS","SE","CS","SE","SE"};

    Course course = new Course("course_1", "Anton",countOfStudent);

    for(int i = 0;i < countOfStudent;i++){
        course.addStudent(new Student(name_students[i], i, major_of_students[i]),i);
        course.students[i].updateGPA(Math.round(Math.random() * 5.0 * 10.0) / 10.0);
        course.students[i].addCredits((int)(Math.random() * 28));
    }

    System.out.print(course);

    System.out.print("Top Student:\n" + getTopStudent(course.students));
    System.out.println("Count of honors students: " + countHonors(course.students));
    System.out.println("Total credits: " + totalCredits(course.students));

}

public static Student getTopStudent(Student[] arr){
    Student student = arr[0];
    for(int i = 1; i<arr.length; i++){
        if(arr[i].getGpa() > student.getGpa()){
            student = arr[i];
        }
    }
    return student;
}

public static int countHonors(Student[] arr){
    int count = 0;
    for(Student student: arr){
        if(student.isHonors()){
            count++;
        }
    }
    return count;
}

public static int totalCredits(Student[] arr){
    int count = 0;
    for(Student student: arr){
        count += student.getCredits();
    }
    return count;
}

public class Student{
    private String name;
    private int id;
    private String major;
    private double gpa;
    private int credits;

    public Student(String name,int id,String major){
        this.name = name;
        this.id = id;
        this.major = major;
        this.gpa = 0.0;
        credits = 0;
    }

    public String getName(){
        return this.name;
    }
    public String getMajor(){
        return this.major;
    }
    public int getCredits(){
        return this.credits;
    }
    public int getId(){
        return this.id;
    }
    public double getGpa(){
        return this.gpa;
    }

    public void addCredits(int credits){
        this.credits += credits;
    }
    public void updateGPA(double newGPA){
        this.gpa = newGPA;
    }
    public boolean isHonors(){
        return this.gpa >= 3.5;
    }

    @Override
    public String toString(){
        return "name: " + name + " id: " + id + " major: " + major + " gpa: " + gpa + " credits: " + credits + "\n";
    }
}

public class Course{
    private String courseName;
    private String instructor;
    private Student[] students;

    public Course(String courseName, String instructor, int size){
        this.courseName = courseName;
        this.instructor = instructor;
        this.students = new Student[size];
    }

    public void addStudent(Student s, int index){
        this.students[index] = s;
    }

    // it is the strangest function
    // it mustn't return gpa or to save it
    public void courseAverageGPA(){
        double gpa = 0;
        for(Student student: students){
            gpa += student.getGpa();
        }
        gpa /= students.length;
    }

    public Student highestCreditStudent(){
        Student maxCredit = students[0];
        for(int i = 1; i<students.length; i++){
            if(students[i].getCredits() > maxCredit.getCredits()){
                maxCredit = students[i];
            }
        }
        return maxCredit;
    }

    @Override
    public String toString(){
        String c = "";

        c += "Course name: " +  courseName + "\n";
        c += "Instructor name: " +  instructor + "\n";
        c += "Students: \n";

        for(Student student : students){
            c += student;
        }

        return c;
    }
}