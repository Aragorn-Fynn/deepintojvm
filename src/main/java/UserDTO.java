import com.feng.deepintojvm.annotation.src.annotation.AllArgsConstructor;
import com.feng.deepintojvm.annotation.src.annotation.Builder;
import com.feng.deepintojvm.annotation.src.annotation.Data;
import com.feng.deepintojvm.annotation.src.annotation.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserDTO {
    private String firstName;

    private String lastName;

    private Integer age;

    private String address;

    public static void main(String[] args) {
        UserDTO userDTO = UserDTO.builder()
                .firstName("明")
                .lastName("小")
                .age(25)
                .address("火星")
                .build();

        System.out.println(userDTO);
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", address='" + address + '\'' +
                '}';
    }
}