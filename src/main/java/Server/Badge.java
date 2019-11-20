package Server;

public class Badge {
    private int studentId;
    private int badge;

    public Badge(){}

    public Badge(int studentId, int badge){
        this.studentId = studentId;
        this.badge = badge;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getBadge() {
        return badge;
    }

    public void setBadge(int badge) {
        this.badge = badge;
    }
}
