public class Person {

    public String fname, lname, address, DOB, Restrictions, Gender, HairColor, EyesColor, Height, Weight;

    public Person() {
        fname = null;
        lname = null;
        address = null;
        DOB = null;
        Restrictions = null;
        Gender = null;
        HairColor = null;
        EyesColor = null;
        Height = null;
        Weight = null;
    }

    public Person(String fname, String lname, String address, String DOB, String Restrictions, String Gender, String HairColor, String EyesColor, String Height, String Weight) {
        this.fname = fname;
        this.lname = lname;
        this.address = address;
        this.DOB = DOB;
        this.Restrictions = Restrictions;
        this.Gender = Gender;
        this.HairColor = HairColor;
        this.EyesColor = EyesColor;
        this.Height = Height;
        this.Weight = Weight;
    }

    public String toString() {
        return lname + ", " + fname;
    }

    public String toString2() {
        return fname + "|" + lname + "|" + address + "|" + DOB + "|" + Restrictions + "|" + Gender + "|" + HairColor + "|" + EyesColor + "|" + Height + "|" + Weight;
    }

    public String toString3() {
        return fname.charAt(0) + fname.substring(1).toLowerCase() + lname.charAt(0);
    }
}
