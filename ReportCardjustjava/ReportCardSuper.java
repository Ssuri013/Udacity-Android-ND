
public class ReportCardSuper{  // this is the class that utilize the ReportCard class
  //for testing purpose
  public static void main(String[] args) {
       //object created and parameterized constructor used to initialize values
        ReportCard rc = new ReportCard(90, 20, 45, "Shubham", 579);
        //to make any corrections in marks of a student
        //use object name .set{name of subject}
        rc.setmChemMarks(67);
        rc.displayResult();
        //OR
        system.out.println(rc.toString());
    }
}

 class ReportCard {

    final String schoolName = "Mount Carmel School";
    final double TOTAL = 300;

    private String mStudentName;
    private int mRNo;

    private intmMathMarks;
    private int mPhyMarks;
    private int mChemMarks;

    public ReportCard(int mChemMarks,int mPhyMarks,
                      intmMathMarks,String mStudentName,
                      int mRNo) {

        this.mChemMarks = mChemMarks;
        this.mPhyMarks = mPhyMarks;
        this.mathMarks =mMathMarks;
        this.mStudentName = mStudentName;
        this.mRNo = mRNo;
    }

    public String getStudentName() {
        return mStudentName;
    }

    public void setStudentName(String mStudentName) {
        this.mStudentName = mStudentName;
    }

    public int getmRNo() {
        return mRNo;
    }

    public void setmRNo(int mRNo) {
        this.mRNo = mRNo;
    }

    public int getMathMarks() {
        returnmMathMarks;
    }

    public void setMathMarks(intmMathMarks) {
        this.mathMarks =mMathMarks;
    }

    public int getmPhyMarks() {
        return mPhyMarks;
    }

    public void setmPhyMarks(int mPhyMarks) {
        this.mPhyMarks = mPhyMarks;
    }

    public int getmChemMarks() {
        return mChemMarks;
    }

    public void setmChemMarks(int mChemMarks) {
        this.mChemMarks = mChemMarks;
    }

    public  String getSchoolName() {
        return schoolName;
    }

//private as not used externally
    private String getGrade(int phy,
                            int math,
                            int chem) {
        String grade;
        int sum =  math + phy + chem ;
        double percentage = sum / TOTAL;

        if (percentage >= 90.0) {//90.0 used as using 90 compares it as int and cause error
            grade = "A";
        } else if (percentage < 90.0 && percentage >= 80.0) {
            grade = "B";
        } else if (percentage < 80.0 && percentage >= 70.0) {
            grade = "C";
        } else if (percentage < 70.0 && percentage >= 60.0) {
            grade = "D";
        } else if (percentage < 60.0) {
            grade = "Failed";
        } else {
            grade = "error";
        }
        return grade;
    }

    public String toString(){
      return "University: " + getSchoolName() + '  ' +
              "Student Name: " + mStudentName + '  ' +
              "Roll Number: " + mRNo + '  ' +
              "Chemistry Marks: " + mChemMarks + '  ' +
              "Physics Marks: " + mPhyMarks + '  ' +
              "Math Marks: " +mMathMarks + '  ' +
              "Grade: " + getGrade( mPhyMarks,mMathMarks, mChemMarks);
     }
   }
