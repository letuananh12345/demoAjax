package codegym.model;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.sql.Date;

@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Size(min = 6,max = 20,message = "(min 6, max 20 characters)")
    private String name;

    @Pattern(regexp = "^[\\w]{5,}$",message = "(5 characters, no special characters)")
    private String address;

    private Date birthday;

    private String avatar;

    private String email;

    @Pattern(regexp = "^0[0-9]{9}$",message = "(vd:0123456789)")
    private String phone;

    @ManyToOne
    @JoinColumn(name = "class_Id")

    private ClassRoom classRoom;

    public Student() {
    }

    public Student(long id, String name, String address, Date birthday, String avatar, String email, String phone, ClassRoom classRoom) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.birthday = birthday;
        this.avatar = avatar;
        this.email = email;
        this.phone = phone;
        this.classRoom = classRoom;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public ClassRoom getClassRoom() {
        return classRoom;
    }

    public void setClassRoom(ClassRoom classRoom) {
        this.classRoom = classRoom;
    }
}
