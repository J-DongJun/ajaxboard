import org.apache.ibatis.annotations.Select;


import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("userMapper")
public interface UserMapper {
	
	public UserVO userInsertVO(UserVO userVO);
}
