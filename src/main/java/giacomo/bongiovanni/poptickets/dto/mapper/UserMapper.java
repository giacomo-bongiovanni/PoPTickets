package giacomo.bongiovanni.poptickets.dto.mapper;

import giacomo.bongiovanni.poptickets.dto.UserDTO;
import giacomo.bongiovanni.poptickets.model.User;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;
@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
    UserDTO userToUserDTO(User user);

    List<UserDTO> usersToUserDTOs(List<User> users);

    @InheritInverseConfiguration
    User userDTOToUser(UserDTO userDTO);
}
