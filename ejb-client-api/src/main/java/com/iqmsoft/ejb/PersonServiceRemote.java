package com.iqmsoft.ejb;

import javax.ejb.Asynchronous;
import javax.ejb.Remote;

import com.iqmsoft.repos.User;

import java.util.List;
import java.util.concurrent.Future;

@Remote
public interface PersonServiceRemote {

  @Asynchronous
  Future<List<String>> removeAny(String name);

  @Asynchronous
  Future<User> createPerson(String name);

  @Asynchronous
  Future<List<User>> getPeople();
}
