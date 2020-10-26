package ex04;

public interface UsersList {

    void userAdd(User user);

    User getUserId(Integer id) throws UserNotFoundException;

    User getUserInd(Integer i);

    Integer countOfUser();
}
