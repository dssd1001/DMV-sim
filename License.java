import java.text.SimpleDateFormat;
import java.util.Date;

public class License {

    public Person Person;
    public String DLNumber;
    public Character LicenseType;
    public Date ExpirationDate, DateWhen21;
    public int TViolationPoints;
    public boolean LicenseSuspended, LicenseRevoked;
    public String Notes;

    SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");

    public License(Person person, String DLNum, char Type, Date Expiration, Date When21, int SuspensionPoints, boolean Suspended, boolean Revoked, String note) {
        Person = person;
        DLNumber = DLNum;
        LicenseType = Type;
        ExpirationDate = Expiration;
        DateWhen21 = When21;
        TViolationPoints = SuspensionPoints;
        LicenseSuspended = Suspended;
        LicenseRevoked = Revoked;
        Notes = note;
    }

    public String AlltoString() {
        return Person + "|" + DLNumber + "|" + LicenseType + "|" + format.format(ExpirationDate) + "|" + format.format(DateWhen21) + "|" + TViolationPoints + "|" + LicenseSuspended + "|" + LicenseRevoked;
    }

    public String toString2() {
        return DLNumber + "|" + LicenseType + "|" + format.format(ExpirationDate) + "|" + format.format(DateWhen21) + "|" + TViolationPoints + "|" + LicenseSuspended + "|" + LicenseRevoked + "|" + Notes;
    }

}
