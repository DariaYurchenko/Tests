package model.service.impl;

import model.dao.UserDao;
import model.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.*;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {

    @Mock
    UserDao userDao;
    @InjectMocks
    UserServiceImpl userService;

    @Test
    public void shouldFindAllUsers() {
        when(userDao.findAll()).thenReturn(Arrays.asList(new User.Builder().build(), new User.Builder().build()));
        List<User> users = userService.findAllUsers();
        assertNotNull(users);
    }

    @Test
    public void shouldAddUser() {
        Mockito.doNothing().when(userDao).add(new User.Builder().build());
        userService.registerUser(new User.Builder().build());
        verify(userDao).add(new User.Builder().build());
    }

    @Test
    public void shouldDeleteById() {
        Long userId = 1L;
        Mockito.doNothing().when(userDao).deleteById(userId);
        userService.deleteUserById(userId);
        verify(userDao).deleteById(userId);
    }

    @Test
    public void shouldDeleteAll() {
        Mockito.doNothing().when(userDao).deleteAll();
        userService.deleteAllUsers();
        verify(userDao).deleteAll();
    }

    @Test
    public void shouldFindUsersByParameter() {
        String column = "name";
        String name = "Alex";
        when(userDao.findByParameter(column, name)).thenReturn(Arrays.asList(new User.Builder().build(), new User.Builder().build()));
        List<User> users = userService.findByParameter(column, name);
        assertNotNull(users);
    }

    @Test
    public void shouldUpdateUserByLogin() {
        String column = "name";
        String name = "Alex";
        String userLogin = "alex@gmail.com";
        Mockito.doNothing().when(userDao).updateUserByLogin(column, name, userLogin);
        userService.updateUserByLogin(column, name, userLogin);
        verify(userDao).updateUserByLogin(column, name, userLogin);
    }

    @Test
    public void shouldFindUserById() {
        Long userId = 1L;
        when(userDao.findById(userId)).thenReturn(Optional.ofNullable(new User.Builder().build()));
        User actual = userService.findById(userId);
        assertNotNull(actual);
    }

    @Test
    public void shouldFindUserByLogin() {
        String userLogin = "alex@gmail.com";
        when(userDao.findUserByLogin(userLogin)).thenReturn(Optional.ofNullable(new User.Builder().build()));
        Optional<User> actual = userService.findUserByLogin(userLogin);
        assertNotNull(actual.get());
    }

    @Test
    public void shouldChangeUserPassword() {
        String hash = "qqqqqqq";
        byte[] salt = new byte[]{'a', 'f'};
        String userLogin = "alex@gmail.com";

        Mockito.doNothing().when(userDao).changePassword(hash, salt, userLogin);
        userService.changeUsersPassword(hash, salt, userLogin);
        verify(userDao).changePassword(hash, salt, userLogin);
    }

    @Test
    public void shouldSetRank() {
        Map<String, Integer> startUserPoints = new HashMap<>();
        startUserPoints.put("currentPoints", 1);
        startUserPoints.put("maxPossiblePoints", 2);
        String userLogin = "alex@gmail.com";
        int plusUserPoints = 1;
        int plusMaxPoints = 2;
        Integer newUserPoints = 2;
        Integer newMaxPoints = 4;

        when(userDao.getUserPointsFromDb(userLogin)).thenReturn(startUserPoints);
        Mockito.doNothing().when(userDao).changeUserRankInDb(userLogin, newUserPoints, newMaxPoints);

        userService.setRank(userLogin, plusUserPoints, plusMaxPoints);
        verify(userDao).getUserPointsFromDb(userLogin);
        verify(userDao).changeUserRankInDb(userLogin, newUserPoints, newMaxPoints);
    }

    @Test
    public void shouldFindUsersForPagination() {
        int recordsPerPage = 5;
        int startRecords = 1;

        when(userDao.findForPagination(startRecords, recordsPerPage)).thenReturn(Arrays.asList(new User.Builder().build(), new User.Builder().build()));
        List<User> users = userService.findUsersForPagination(startRecords, recordsPerPage);
        assertNotNull(users);
    }

    @Test
    public void shouldAddMagicKey() {
        String magicKey = "qqqqqqq";
        String userLogin = "alex@gmail.com";

        Mockito.doNothing().when(userDao).addMagicKey(magicKey, userLogin);

        userService.addMagicKey(magicKey, userLogin);
        verify(userDao).addMagicKey(magicKey, userLogin);
    }

    @Test
    public void shouldSubmitKeyStatus() {
        String userLogin = "alex@gmail.com";

        Mockito.doNothing().when(userDao).changeSubmitKeyStatus(userLogin);

        userService.changeSubmitKeyStatus(userLogin);
        verify(userDao).changeSubmitKeyStatus(userLogin);
    }

    /*//TODO: exception?
    @Test
    public void shouldFindMagicKey() {
        String userLogin = "alex@gmail.com";

        when(userDao.findMagicKey(userLogin)).thenReturn(Optional.ofNullable("qqqqqq"));

        String expected = "qqqqqq";
        String actual = userService.findMagicKey(userLogin);
        assertEquals(expected, actual);
    }*/

    //TODO: get()
    @Test
    public void shouldFindIfSubmit() {
        String userLogin = "alex@gmail.com";

        when(userDao.findIfSubmit(userLogin)).thenReturn(Optional.ofNullable(1));

        int expected = 1;
        int actual = userService.findIfSubmit(userLogin).get();
        assertEquals(expected, actual);
    }

}

