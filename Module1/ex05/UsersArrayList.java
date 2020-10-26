package ex05;

public class UsersArrayList implements UsersList {

    private static User[] listOfUser = new User[10];

    private static Integer count = 0;

    @Override
    public void userAdd(User user) {
        listOfUser[count] = user;

        count++;

        if (count == listOfUser.length) {
            listOfUser = listOfUserResize(listOfUser);
        }
    }

    @Override
    public User getUserId(Integer id) {
        for (Integer i = 0; i < count; i++) {
            if (listOfUser[i].getId() == id) {
                return listOfUser[i];
            }
        }

        throw new UserNotFoundException("id does not exist");
    }

    @Override
    public User getUserInd(Integer i) {
        return listOfUser[i];
    }

    @Override
    public Integer countOfUser() {
        return count;
    }

    private User[] listOfUserResize(User[] listOld) {
        int newSize = (int)(listOld.length * 1.5);

        User[] newList = new User[newSize];

        for (Integer i = 0; i < listOld.length; i++) {
            newList[i] = listOld[i];
        }

        return newList;
    }
}
