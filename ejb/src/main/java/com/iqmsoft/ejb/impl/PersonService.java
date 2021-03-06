package com.iqmsoft.ejb.impl;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import lombok.val;

import javax.ejb.*;

import com.iqmsoft.ejb.PersonServiceRemote;
import com.iqmsoft.repos.User;
import com.iqmsoft.repos.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

@Slf4j
@Stateless
//@LocalBean
@Remote(PersonServiceRemote.class)
public class PersonService implements PersonServiceRemote {

  @EJB UserRepository userRepository;

  @Asynchronous
  @SneakyThrows
  public Future<List<String>> removeAny(final String name) {
    Thread.sleep(4321);

    val users = userRepository.findByName(name);

    log.info("will be removed: {}", users);
    userRepository.deleteByName(name);
    log.info("removeAny done.");

    val result = new ArrayList<String>();

    if (null != users)
      for (final User user : users)
        result.add(user.getName());

    return new AsyncResult<List<String>>(result);
  }

  @Asynchronous
  @SneakyThrows
  public Future<User> createPerson(final String name) {
    Thread.sleep(3456);
    val person = new User(name);
    return new AsyncResult<User>(userRepository.save(person));
  }

  @Asynchronous
  @SneakyThrows
  public Future<List<User>> getPeople() {
    Thread.sleep(2345);
    return new AsyncResult<List<User>>(
        new ArrayList<User>(userRepository.findAll())
    );
  }
}
