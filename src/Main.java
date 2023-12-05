import com.sun.source.doctree.SystemPropertyTree;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.HashMap;

public class Main {
    static class School {

        private List<String> changeLog = new ArrayList<>();
        public Map<String, String> TeacherClassCatalog;
        String[] courseCodes = {"CSC101", "MAT202", "ENG303", "BIO404", "PHY505", "CHE606", "HIS707", "ART808", "PSY909", "SOC101"};


        public  void teacherInitialInterface(Scanner input) {
            input.nextLine();
            System.out.println("Please input your name");
            String teacherName = input.nextLine();
            List<String> taughtClasses = new ArrayList<>();
            String classTaught = "default";

            while (!classTaught.isEmpty()) {
                System.out.println("Welcome " + teacherName + " please input the next class code for the class you teach leave blank when done");
                classTaught = input.nextLine();
                taughtClasses.add(classTaught);
            }
            teacher CurrentUser = new teacher(teacherName, taughtClasses);
            changeLog.add("New teacher " + teacherName + " added.");
            teacherUserInterface(input, CurrentUser);

        }

        public void userInterface(Scanner input) {
            System.out.println("Are you a teacher or a student? T/S");
            String userType = input.nextLine();
            if (userType.toUpperCase().equals("T")) {
                teacherInitialInterface(input);

            } else if (userType.toUpperCase().equals("S")) {
                //call student interface

            } else {
                System.out.println("Please try again");
                userInterface(input);

            }
        }

        public void teacherUserInterface(Scanner input, teacher CurrentUser) {
            System.out.println("Welcome to the School management system please choose what you would like to do by picking the corresponding number note the available course codes are "+ courseCodes);
            System.out.println("1:add a class that you teach\n2: see class roster for a given class code\n3: see a list of students and there grades for a given class code\n4: Filter a class given the class code by current grade\n5: change the grade in a certain class given the class code for a certain student\n6:Get student information based on student name\n7: Switch to student view\n8: Exit program.");
            int chosenAction = input.nextInt();
            switch (chosenAction) {
                case 1:
                    System.out.println("Please enter the class code that corresponds to the class you would like to add");
                    String classCode = input.nextLine();
                    CurrentUser.addClass(classCode);
                    changeLog.add(CurrentUser.getName() + " added a class with the code " + classCode);
                    teacherUserInterface(input, CurrentUser);
                    break;

                case 2:
                    System.out.println("Please enter the class code that you would like to view the roster of.");
                    String viewRosterCode = input.nextLine();
                    System.out.println(CurrentUser.seeClassRoster(viewRosterCode));
                    changeLog.add(CurrentUser.getName() + " viewed the roster of a class with the code " + viewRosterCode);
                    teacherUserInterface(input, CurrentUser);
                    break;

                case 3:
                    System.out.println("Please enter the class code of the class that you would like to view the grades of.");
                    String gradeCode = input.nextLine();
                    changeLog.add(CurrentUser.getName() + " viewed the graded roster of a class with the code " + gradeCode);
                    System.out.println(CurrentUser.seeClassRosterWithGrades(gradeCode));
                    teacherUserInterface(input, CurrentUser);

                    break;

                case 4:
                    System.out.println("Please input the class code of the class that you would like to view filtered grades off");
                    String filterCode = input.nextLine();
                    System.out.println("Please input the lower bound of grades that you wish to include in a decimal form. e.g 96.4");
                    double minimum = input.nextDouble();
                    System.out.println("Please input the upper bound of grades that you wish to include in a decimal form");
                    double maximum = input.nextDouble();
                    System.out.println(CurrentUser.filterClassByGrade(minimum, maximum, filterCode));
                    teacherUserInterface(input, CurrentUser);


                    break;

                case 5:
                    System.out.println("Please enter the name of the student who's grades you wish to modify.");
                    String studentName = input.nextLine();
                    System.out.println("Please enter the class code of the class that you wish to modify the students grade in ");
                    String changeGradeCode = input.nextLine();
                    System.out.println("Please input the new grade to add");
                    double newGrade = input.nextDouble();
                    CurrentUser.changeGrade(changeGradeCode, newGrade, studentName);
                    changeLog.add(CurrentUser.getName() + " changed the grade of " + studentName + " for the class with class code " + changeGradeCode + "to a" + newGrade + "%");
                    teacherUserInterface(input, CurrentUser);
                    break;

                case 6:
                    System.out.println("Please input the name of the student that you wish to view information for.");
                    String studentNameInfo = input.nextLine();
                    CurrentUser.getStudentInfo(studentNameInfo);
                    teacherUserInterface(input, CurrentUser);
                    changeLog.add(CurrentUser.getName() + " viewed information for " + studentNameInfo);

                    break;

                case 7:

                    break;

                case 8:
                    System.out.println("Thank you for using our system have a wonderful day!");
                    System.exit(147);
                    break;

                default:
                    System.out.println("Please try again");
                    teacherUserInterface(input, CurrentUser);
            }

        }

        public class student {
            private String name;
            private List<String> classes;

            private Map<String, Double> grades;
            static List<student> allStudents = new ArrayList<>();

            //constr
            student(String initialName, List<String> initialClasses, Map<String, Double> initialGrades) {
                this.name = initialName;
                this.classes = initialClasses;
                this.grades = initialGrades;
                allStudents.add(this);

            }

            // g methods
            public String getName() {
                return name;
            }

            public List<String> getClasses() {
                return classes;
            }

            public Map<String, Double> getGrades() {
                return grades;
            }

            // s methods
            public void setName(String newName) {
                this.name = newName;
            }

            public void setClasses(List<String> newClasses) {
                this.classes = newClasses;
            }

            public void setGrades(Map<String, Double> newGrades) {
                this.grades = newGrades;
            }

            //            }
            public static List<student> getAllStudents() {
                return allStudents;
            }

            public boolean changeGrade(Double grade, String classCode) {
                if (this.grades.containsKey(classCode)) {
                    this.grades.put(classCode, grade);

                    return true;
                } else {
                    return false;
                }

            }


        }

        public class teacher {
            public String name;
            public List<String> classesTaught;

            public teacher(String initialName, List<String> initialClassesTaught) {
                this.name = initialName;
                this.classesTaught = initialClassesTaught;
            }

            // get funcs
            public String getName() {
                return name;
            }

            public List<String> getClassesTaught() {
                return classesTaught;
            }

            // set funcs

            public void setName(String newName) {
                this.name = newName;
            }

            public void setClassesTaught(List<String> newClasslist) {
                this.classesTaught = newClasslist;
            }

            //other func
            public void addClass(String classCode) {
                this.classesTaught.add(classCode);
            }


            public List<String> seeClassRoster(String classCode) {
                List<student> studentCatalog = student.getAllStudents();
                List<String> studentsInSelectedClass = null;
                for (student student : studentCatalog) {
                    if (student.classes.contains(classCode)) {
                        studentsInSelectedClass.add(student.name);
                    }


                }
                return studentsInSelectedClass;
            }

            public Map<String, Double> seeClassRosterWithGrades(String classCode) {
                List<student> studentCatalog = student.getAllStudents();
                Map<String, Double> studentGrades = null;
                for (student student : studentCatalog) {
                    if (student.grades.containsKey(classCode)) {
                        studentGrades.put(student.name, student.grades.get(classCode));
                    }

                }
                return studentGrades;
            }

            public Map<String, Double> filterClassByGrade(Double min, Double max, String classCode) {
                Map<String, Double> studentGrades = seeClassRosterWithGrades(classCode);
                Map<String, Double> filteredResults = new HashMap<>();
                for (Map.Entry<String, Double> student : studentGrades.entrySet()) {
                    if (student.getValue() > min && student.getValue() < max) {
                        filteredResults.put(student.getKey(), student.getValue());

                    }
                }
                return filteredResults;
            }

            public void changeGrade(String classCode, Double grade, String studentName) {
                for (student student : student.allStudents) {
                    if (student.getName().equals(studentName)) {
                        if (student.changeGrade(grade, classCode)) {
                            System.out.println("The grade has been updated");
                        } else {
                            System.out.println("The grade failed to update please try again");
                        }
                    }
                }
            }

            public void getStudentInfo(String name) {
                List<String> studentClasses = new ArrayList<>();
                Map<String, Double> studentGrades = new HashMap<>();
                String studentName = "";
                for (student student : student.allStudents) {
                    if (student.getName().equals(name)) {
                        studentClasses = student.getClasses();
                        studentGrades = student.getGrades();
                        studentName = student.getName();
                        break;
                    } else {
                        System.out.println("It appears that the student that you requested is not in the system please try again");
                    }


                }
                System.out.println(studentName + " is in " + studentClasses + " and their grades are " + studentGrades + ".");
            }
        }

        public static void main(String[] args) {
            Scanner input = new Scanner(System.in);
            School school = new School();
            school.userInterface(input);
        }

    }
}


