import java.util.*;
public class Main {
    static class School {
        //region stuff
        public void populateSchool() {
            student NebulaDreamer = new student(
                    "Nebula Dreamer",
                    Arrays.asList("CSC101", "BIO404"),
                    new HashMap<>() {{
                        put("CSC101", 87.5);
                        put("BIO404", 93.8);
                    }}
            );

            student QuantumHarmony = new student(
                    "Quantum Harmony",
                    Arrays.asList("MAT202", "PHY505", "CHE606"),
                    new HashMap<>() {{
                        put("MAT202", 94.3);
                        put("PHY505", 90.1);
                        put("CHE606", 92.7);
                    }}
            );


            student StellarVoyager = new student(
                    "Stellar Voyager",
                    Arrays.asList("HIS707", "ENG303"),
                    new HashMap<>() {{
                        put("HIS707", 08.5);
                        put("ENG303", 91.2);
                    }}
            );

            student TechnoInnovator = new student(
                    "Techno Innovator",
                    Arrays.asList("CSC101", "ENG303"),
                    new HashMap<>() {{
                        put("CSC101", 10.0);
                        put("ENG303", 86.8);
                    }}
            );
            student QuantumSeeker = new student(
                    "Quantum Seeker",
                    Arrays.asList("CSC101", "PHY505", "CHE606"),
                    new HashMap<>() {{
                        put("CSC101", 92.5);
                        put("PHY505", 88.2);
                        put("CHE606", 95.0);
                    }}
            );

            student LunaStarlight = new student(
                    "Luna Starlight",
                    Arrays.asList("MAT202", "ART808"),
                    new HashMap<>() {{
                        put("MAT202", 89.7);
                        put("ART808", 91.8);
                    }}
            );

            student TechInnovator = new student(
                    "Tech Innovator",
                    Arrays.asList("CSC101", "ENG303", "SOC101"),
                    new HashMap<>() {{
                        put("CSC101", 87.0);
                        put("ENG303", 94.5);
                        put("SOC101", 90.3);
                    }});


            student StarryDreamer = new student(
                    "Starry Dreamer",
                    Arrays.asList("ART808", "PSY909"),
                    new HashMap<>() {{
                        put("ART808", 68.8);
                        put("PSY909", 92.1);
                    }}
            );
            teacher ThompsonFitzgerald = new teacher("Thompson Fitzgerald", Arrays.asList("CSC101", "MAT202"));
            teacher MrsJohnson = new teacher("Mrs. Johnson", Arrays.asList("PHY505", "ENG303"));
            teacher DrDavis = new teacher("Dr. Davis", Arrays.asList("BIO404", "ART808"));

        }

// im sorry I ran out the will to make 14 more also chatgpt made the random names beacause i couldn't come up with anything creative at the time. and each student is just a copy and paste of the first one.



        //endregion


        private List<String> changeLog = new ArrayList<>();
        public Map<String, String> TeacherClassCatalog;
        String[] courseCodes = {"CSC101", "MAT202", "ENG303", "BIO404", "PHY505", "CHE606", "HIS707", "ART808", "PSY909", "SOC101"};
        public student getStudent(){
            student User = new student(
                    "User user",
                    Arrays.asList("PHY505", "CHE606", "HIS707"),
                    new HashMap<>() {{
                        put("PHY505", 71.5);
                        put("CHE606", 97.9);
                        put("HIS707", 93.2);
                    }}
            );
            return User;
        }


        public void userInterface(Scanner input) {
            System.out.println("Are you a teacher or a student? T/S");
            String userType = input.nextLine();
            if (userType.equalsIgnoreCase("T")) {
                teacherInitialInterface(input);

            } else if (userType.equalsIgnoreCase("S")) {
               studentUserInterface(input,getStudent());

            } else {
                System.out.println("Please try again");
                userInterface(input);

            }
        }
        public  void teacherInitialInterface(Scanner input) {
//            input.nextLine();
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
        public void studentUserInterface(Scanner input, student user){
            System.out.println("Welcome to the student portal. please select the action you wish by entering its corresponding number.\n1: View current grades\n2: view course teacher pairings\n3:Exit");
            String chosenActionStr = input.nextLine();
            int chosenAction = Integer.parseInt(chosenActionStr);
            switch (chosenAction){
                case 1:
                    System.out.println("Your current grades are "+ user.getGrades().toString());
                    studentUserInterface(input, user);
                    break;
                case 2:
                    user.ViewTeachers();
                    studentUserInterface(input,user);
                    break;
                case 3:
                    System.exit(147);

                    break;
                default:
                    System.out.println("Something went wrong please try again");
                    studentUserInterface(input, user);
                    break;
            }
        }

        public void teacherUserInterface(Scanner input, teacher CurrentUser) {
            System.out.println("Welcome to the School management system please choose what you would like to do by picking the corresponding number note the available course codes are\n"+ Arrays.toString(courseCodes));
            System.out.println("1:add a class that you teach\n2: see class roster for a given class code\n3: see a list of students and there grades for a given class code\n4: Filter a class given the class code by current grade\n5: change the grade in a certain class given the class code for a certain student\n6: Get student information based on student name\n7: Switch to student view\n8: Exit program.");
            String choice = input.nextLine();
            int chosenAction = Integer.parseInt(choice);
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
                    input.nextLine();
                    System.out.println("Please input the upper bound of grades that you wish to include in a decimal form");
                    double maximum = input.nextDouble();
                    input.nextLine();
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
                    input.nextLine();
                    CurrentUser.changeGrade(changeGradeCode, newGrade, studentName);
                    changeLog.add(CurrentUser.getName() + " changed the grade of " + studentName + " for the class with class code " + changeGradeCode + "to a" + newGrade + "%");
                    teacherUserInterface(input, CurrentUser);
                    break;

                case 6:
                    System.out.println("Please input the name of the student that you wish to view information for.");
                    String studentNameInfo = input.nextLine();
                    CurrentUser.getStudentInfo(studentNameInfo);
                    changeLog.add(CurrentUser.getName() + " viewed information for " + studentNameInfo);
                    teacherUserInterface(input, CurrentUser);

                    break;

                case 7:
                    int i = 1;

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

            public void ViewTeachers(){
               List<teacher> teacherList = teacher.teacherCourses;
               Map<String,List<String>> teacherClassPairing = new HashMap<>();
               for (teacher teacher : teacherList){
                   teacherClassPairing.put(teacher.name,teacher.classesTaught);
               }
               System.out.println(teacherClassPairing.toString());
            }
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
            static List<teacher> teacherCourses = new ArrayList<>();

            public teacher(String initialName, List<String> initialClassesTaught) {
                this.name = initialName;
                this.classesTaught = initialClassesTaught;
                teacherCourses.add(this);

            }
            //region get and set funcs
            // get funcs
            public String getName() {
                return name;
            }
            public List<teacher> getAllTeachers() {
                return teacherCourses;
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
            //endregion

            //other func
            public void addClass(String classCode) {
                this.classesTaught.add(classCode);
            }


            public List<String> seeClassRoster(String classCode) {
                List<student> studentCatalog = student.getAllStudents();
                List<String> studentsInSelectedClass = new ArrayList<>();
                for (student student : studentCatalog) {
                    if (student.classes.contains(classCode)) {
                        studentsInSelectedClass.add(student.name);
                    }


                }
                return studentsInSelectedClass;
            }

            public Map<String, Double> seeClassRosterWithGrades(String classCode) {
                List<student> studentCatalog = student.getAllStudents();
                Map<String, Double> studentGrades = new HashMap<>();
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
                String studentName = name;
                int i = 0;
                for (student student : student.allStudents) {
                    i++;
                    if (student.getName().equals(name)) {
                        studentClasses = student.getClasses();
                        studentGrades = student.getGrades();
                        studentName = student.getName();

                        break;
                    } }
                if (i>student.allStudents.size()){
                    System.out.println("It appears that the student that you requested is not in the system please try again");
                }
                else {
                    System.out.println(studentName + " is in " + studentClasses + " and their grades are " + studentGrades + ".");
            }
        }
        }
        }
public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
            School school = new School();
            school.populateSchool();
            school.userInterface(input);

        }


}


